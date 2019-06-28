package com.chenjh.handler.nvd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.chenjh.domain.nvd.NdDownloadProcBean;
import com.chenjh.handler.ParseXmlThread;
import com.chenjh.handler.TaskContext;
import com.chenjh.handler.TaskResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import com.chenjh.common.type.ProcStatusType;
import com.chenjh.common.type.TaskStatusType;
import com.chenjh.exception.CheckedException;
import com.chenjh.mapper.NdDownloadProcBeanMapper;
import com.chenjh.service.NvdVulInfoService;
import com.chenjh.util.MyApplicationContextUtil;


public class NvdVulnInfoHandler extends NVDTaskHandler
{
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(NvdVulnInfoHandler.class);
    
    /**
     * 睡眠时间
     */
    private static final long SLEEP_MILLIS = 30000;
    
    /**
     * nvd 漏洞信息服务
     */
    @Autowired
    private NvdVulInfoService nvdVulInfoService;
    
    /**
     * ndDownloadProcBeanMapper
     */
    @Autowired
    private NdDownloadProcBeanMapper ndDownloadProcBeanMapper;
    
    /**
     * taskExecutor
     */
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    
    /**
     * 处理DOWNLOAD_PROC表数据到 NVD_VUL_INFO
     * @param context context
     * @return result
     * @throws CheckedException CheckedException
     */
    @Override
    public TaskResult handleTask(TaskContext context) throws CheckedException
    {
        LOG.info("nvd vuln info handle begin ...");
        
        AtomicInteger insertNum = new AtomicInteger();
        AtomicInteger updateNum = new AtomicInteger();
        TaskResult taskResult = new TaskResult();
        
        //查询需要处理的集合
        NdDownloadProcBean queryBean = new NdDownloadProcBean();
        queryBean.setStatus(ProcStatusType.INIT.getValue());
        
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
            
            List<NdDownloadProcBean> cveList = ndDownloadProcBeanMapper.queryCveList(queryBean);
            
            if (!CollectionUtils.isEmpty(cveList))
            {
                //lock
                List<Long> procIdList = new ArrayList<Long>();
                for (NdDownloadProcBean procBean : cveList)
                {
                    procIdList.add(procBean.getProcId());
                }
                // ndDownloadProcBeanMapper.updateProcBeanForLock(procIdList);
                nvdVulInfoService.lockSelectedProcList(procIdList);
                
                ParseXmlThread parseThread = (ParseXmlThread)MyApplicationContextUtil.getBean("parseXmlThread");
                parseThread.setCveList(cveList);
                parseThread.setUpdateNum(updateNum);
                parseThread.setInsertNum(insertNum);
                
                taskExecutor.execute(parseThread);
            }
            else
            {
                if (taskExecutor.getActiveCount() == 0)
                {
                    LOG.info("nvd vuln info handle end ! ");
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
        taskResult.setInsertNum(insertNum.get());
        taskResult.setUpdateNum(updateNum.get());
        return taskResult;
    }
    
}
