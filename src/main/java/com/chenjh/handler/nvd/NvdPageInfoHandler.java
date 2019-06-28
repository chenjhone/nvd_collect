package com.chenjh.handler.nvd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chenjh.handler.ParseHtmlInfoThread2;
import com.chenjh.handler.TaskContext;
import com.chenjh.handler.TaskResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.chenjh.common.type.TaskStatusType;
import com.chenjh.exception.CheckedException;
import com.chenjh.service.NvdVulInfoService;
import com.chenjh.util.DateUtil;
import com.chenjh.util.MyApplicationContextUtil;

/**
 * nvd 网页信息处理
 * @version V1.0
 */
public class NvdPageInfoHandler extends NVDTaskHandler
{
    /**
     * logger
     */
    private static final Logger LOG = Logger.getLogger(NvdPageInfoHandler.class);
    
    /**
     * 睡眠时间
     */
    private static final long SLEEP_MILLIS = 30000;
    
    /**
     * nvdVulInfoService
     */
    @Autowired
    private NvdVulInfoService nvdVulInfoService;
    
    /**
     * taskExecutor
     */
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    
    /**
     * nvd 详情页前缀
     */
    private String detailInfoPrefix;
    
    /**
     * 是否是重跑
     */
    private boolean fixByDate = false;
    
    /**
     * 是否全部重跑
     */
    private boolean force = false;
    
    /**
     * 开始时间 yyyy-MM-dd
     */
    private String pubDateStart;
    
    /**
     * 结束时间  yyyy-MM-dd
     */
    private String pubDateEnd;
    
    /**
     * 处理T_ND_VUL_INFO表中数据为0
     * @param context context
     * @return result
     * @throws CheckedException CheckedException
     */
    @Override
    public TaskResult handleTask(TaskContext context) throws CheckedException
    {
        LOG.info("nvd page info handle begin ...");
        TaskResult taskResult = new TaskResult();
        
        if (StringUtils.isEmpty(this.detailInfoPrefix))
        {
            taskResult.setResult(false);
            taskResult.setTaskStatus(TaskStatusType.FAIL.getValue());
            return taskResult;
        }
        
        Date pubDateFrom = DateUtil.parse(this.pubDateStart, DateUtil.PATTERN_YYYY_MM_DD);
        Date pubDateTo = null;
        
        if (this.fixByDate)
        {
            pubDateTo = DateUtil.parse(this.pubDateEnd, DateUtil.PATTERN_YYYY_MM_DD);
            
            Date today = DateUtil.getCurrentDay(DateUtil.PATTERN_YYYY_MM_DD);
            if (pubDateFrom == null)
            {
                pubDateFrom = today;
            }
            if (pubDateTo == null)
            {
                pubDateTo = DateUtil.addDay(today, 1);
            }
            
        }
        
        //初始化待处理数据
        nvdVulInfoService.initCveListCvssStatus(pubDateFrom, pubDateTo, context.getCrawlTaskId(), this.force);
        
        while (true)
        {
            if (taskExecutor.getActiveCount() >= taskExecutor.getMaxPoolSize())
            {
                try
                {
                    Thread.sleep(SLEEP_MILLIS);
                    continue;
                }
                catch (InterruptedException e)
                {
                    LOG.error("InterruptedException occured ", e);
                }
            }
            
            //查询需要处理的集合
            List<String> cveList = new ArrayList<String>();
            if (this.fixByDate)
            {
                cveList = nvdVulInfoService.queryCveListByPublishDate(pubDateFrom, pubDateTo);
            }
            else
            {
                cveList = nvdVulInfoService.queryCveListByCrawlId(context.getCrawlTaskId());
            }
            
            if (!CollectionUtils.isEmpty(cveList))
            {
                //lock
                nvdVulInfoService.lockVulForCvssUpdate(cveList);
                
                ParseHtmlInfoThread2 parseThread = (ParseHtmlInfoThread2) MyApplicationContextUtil.getBean("parseHtmlInfoThread2");
                parseThread.setCveList(cveList);
                parseThread.setDetailInfoPrefix(this.detailInfoPrefix);
                
                taskExecutor.execute(parseThread);
            }
            else
            {
                if (taskExecutor.getActiveCount() == 0)
                {
                    LOG.info("nvd page info of cvss3.0 handle end ! ");
                    break;
                }
                else
                {
                    try
                    {
                        LOG.info("waiting for thread executing 30s");
                        Thread.sleep(SLEEP_MILLIS);
                    }
                    catch (InterruptedException e)
                    {
                        LOG.error("InterruptedException occured ", e);
                    }
                }
            }
        }
        
        taskResult.setResult(true);
        taskResult.setTaskStatus(TaskStatusType.SUCCESS.getValue());
        
        return taskResult;
    }
    
    public String getDetailInfoPrefix()
    {
        return detailInfoPrefix;
    }
    
    public void setDetailInfoPrefix(String detailInfoPrefix)
    {
        this.detailInfoPrefix = detailInfoPrefix;
    }
    
    public String getPubDateStart()
    {
        return pubDateStart;
    }

    public void setPubDateStart(String pubDateStart)
    {
        this.pubDateStart = pubDateStart;
    }

    public String getPubDateEnd()
    {
        return pubDateEnd;
    }

    public void setPubDateEnd(String pubDateEnd)
    {
        this.pubDateEnd = pubDateEnd;
    }

    public boolean isFixByDate()
    {
        return fixByDate;
    }
    
    public void setFixByDate(boolean fixByDate)
    {
        this.fixByDate = fixByDate;
    }
    
    public boolean isForce()
    {
        return force;
    }
    
    public void setForce(boolean force)
    {
        this.force = force;
    }
    
}
