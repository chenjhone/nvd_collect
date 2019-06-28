package com.chenjh.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.chenjh.common.type.TaskStatusType;
import com.chenjh.domain.CrawlTaskBean;
import com.chenjh.exception.CheckedException;
import com.chenjh.handler.TaskContext;
import com.chenjh.handler.TaskResult;
import com.chenjh.handler.nvd.NVDTaskHandler;
import com.chenjh.service.CrawlTaskService;
import com.chenjh.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;


/**
 * 爬虫控制器
 * @version V1.0
 */
public class CrawlController
{

    /**
     * LOG
     */
    private static final Logger LOG = Logger.getLogger(CrawlController.class);


    /**
     * 来源 默认vulnDB
     */
    private int vulSource = 1;

    /**
     * 全量任务
     */
    private boolean allDataTask = false;

    /**
     * 任务id
     */
    private Long crawlTaskId;

    /**
     * CrawlTaskService.java
     */
    @Autowired
    private CrawlTaskService crawlTaskService;

    /**
     * 处理器集合
     */
    private List<NVDTaskHandler> taskHandlerList = new ArrayList<NVDTaskHandler>();



    public Long getCrawlTaskId()
    {
        return crawlTaskId;
    }

    public void setCrawlTaskId(Long crawlTaskId)
    {
        this.crawlTaskId = crawlTaskId;
    }

    public int getVulSource()
    {
        return vulSource;
    }

    public void setVulSource(int vulSource)
    {
        this.vulSource = vulSource;
    }

    public List<NVDTaskHandler> getTaskHandlerList()
    {
        return taskHandlerList;
    }

    public void setTaskHandlerList(List<NVDTaskHandler> taskHandlerList)
    {
        this.taskHandlerList = taskHandlerList;
    }

    public boolean isAllDataTask()
    {
        return allDataTask;
    }

    public void setAllDataTask(boolean allDataTask)
    {
        this.allDataTask = allDataTask;
    }

    /**
     * 开启任务
     */
    public void start() throws CheckedException
    {
        LOG.info("nvd crawl task start ...");
        
        long newTaskId;
        CrawlTaskBean record;
        if (this.crawlTaskId == null)
        {
            //新任务    
            record = new CrawlTaskBean();
            String ip;
            InetAddress addr;
            try
            {
                addr = InetAddress.getLocalHost();
                ip = addr.getHostAddress();
            }
            catch (UnknownHostException e)
            {
                ip = "127.0.0.1";
            }
            
            record.setVulSource(vulSource);
            record.setWorkIp(ip);
            record.setCurrentTask(TaskStatusType.INIT.getCode());
            record.setStartTime(DateUtil.getCurrentUtilDate());
            record.setEndTime(DateUtil.getCurrentUtilDate());
            record.setTaskStatus(TaskStatusType.SUCCESS.getValue());
            record.setTaskIdx(0);
            record.setVulDownloadNum(0);
            record.setVulInsertNum(0);
            record.setVulUpdateNum(0);
            
            crawlTaskService.addNew(record);
            newTaskId = record.getCrawlId();
        }
        else
        {
            record = crawlTaskService.queryBeanByKey(this.crawlTaskId);
            if (record == null)
            {
                LOG.info("can not find the task by this crawlTaskId . " + this.crawlTaskId);
                throw new CheckedException("can not find the task by this crawlTaskId . ");
            }
            newTaskId = record.getCrawlId();
        }
        
        try
        {
            //初始化任务context
            TaskContext taskContext = new TaskContext();
            taskContext.setCrawlTaskId(newTaskId);
            taskContext.setAllDataTask(this.allDataTask);
            taskContext.setCurrentTask(record.getCurrentTask());
            taskContext.setHandleIndex(record.getTaskIdx());
            taskContext.setTaskStatus(record.getTaskStatus());
            
            if (CollectionUtils.isEmpty(taskHandlerList))
            {
                LOG.error("the taskHandlerList can not be empty . ");
                throw new CheckedException("taskHandlerList empty .");
            }
            
            //执行任务
            for (NVDTaskHandler taskHandler : taskHandlerList)
            {
                if ((taskHandler.getTaskIdx() < taskContext.getHandleIndex())
                        || (taskHandler.getTaskIdx() == taskContext.getHandleIndex() 
                        && TaskStatusType.SUCCESS.getValue() == taskContext.getTaskStatus()))
                {
                    continue;
                }
                
                taskContext.setHandleIndex(taskHandler.getTaskIdx());
                taskContext.setCurrentTask(taskHandler.getTaskName());
                
                // 更新任务阶段
                updateTaskStatus(record, taskContext, null);
                
                TaskResult taskResult = taskHandler.handleTask(taskContext);
                if (taskResult == null)
                {
                    LOG.info("exception during handle task...");
                    break;
                }
                
                if (taskResult.getTaskStatus() != null)
                {
                    updateTaskStatus(record, null, taskResult);
                }
                //任务执行失败，跳出循环结束任务
                if (taskResult.getResult() != null && !taskResult.getResult())
                {
                    LOG.info("task fail , over ! ");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            TaskResult taskResult = new TaskResult();
            taskResult.setTaskStatus(TaskStatusType.FAIL.getValue());
            updateTaskStatus(record, null, taskResult);
            throw new CheckedException("crawlController exception", e);
        }
        
        LOG.info("nvd crawl task end. ");
    }
    
    /**
     * 更新任务状态
     * @param record record
     * @param taskContext 目前任务
     * @param result result
     */
    private void updateTaskStatus(CrawlTaskBean record, TaskContext taskContext, TaskResult result)
    {
        record.setEndTime(DateUtil.getCurrentUtilDate());
        record.setModifiedTime(DateUtil.getCurrentUtilDate());
        if (taskContext != null)
        {
            record.setCurrentTask(taskContext.getCurrentTask());
            record.setTaskStatus(TaskStatusType.INIT.getValue());
            record.setTaskIdx(taskContext.getHandleIndex());
        }
        
        if (result != null)
        {
            if (result.getTaskStatus() != null)
            {
                record.setTaskStatus(result.getTaskStatus());
            }
            if (result.getInsertNum() != null)
            {
                record.setVulInsertNum(record.getVulInsertNum() + result.getInsertNum());
            }
            if (result.getUpdateNum() != null)
            {
                record.setVulUpdateNum(record.getVulUpdateNum() + result.getUpdateNum());
            }
            if (result.getDownloadNum() != null)
            {
                record.setVulDownloadNum(record.getVulDownloadNum() + result.getDownloadNum());
            }
        }
        
        crawlTaskService.updateByPrimaryKeySelective(record);
    }
    
}