
package com.chenjh.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.chenjh.common.type.ProcStatusType;
import com.chenjh.domain.nvd.NdDownloadProcBean;
import com.chenjh.domain.nvd.NvdVulInfoBean;
import com.chenjh.exception.CheckedException;
import com.chenjh.service.NvdVulInfoService;
import com.chenjh.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月29日
 * @since
 */
@Component
@Scope("prototype")
public class ParseXmlThread extends Thread
{
    
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(ParseXmlThread.class);
    
    /**
     * nvd 漏洞服务
     */
    @Autowired
    private NvdVulInfoService nvdVulInfoService;
    
    /**
     * 待处理列表
     */
    private List<NdDownloadProcBean> cveList = new ArrayList<NdDownloadProcBean>();
    
    /**
     * 更新记录数
     */
    private AtomicInteger updateNum;
    
    /**
     * 插入记录数
     */
    private AtomicInteger insertNum;
    
    private int updateInt;
    
    private int insertInt;
    
    public List<NdDownloadProcBean> getCveList()
    {
        return cveList;
    }
    
    public void setCveList(List<NdDownloadProcBean> cveList)
    {
        this.cveList = cveList;
    }
    
    public AtomicInteger getUpdateNum()
    {
        return updateNum;
    }
    
    public void setUpdateNum(AtomicInteger updateNum)
    {
        this.updateNum = updateNum;
    }
    
    public AtomicInteger getInsertNum()
    {
        return insertNum;
    }
    
    public void setInsertNum(AtomicInteger insertNum)
    {
        this.insertNum = insertNum;
    }
    
    /**
     * 处理过程
     */
    @Override
    public void run()
    {
        LOG.info("process cvelist begin ");
        try
        {
            for (NdDownloadProcBean procBean : cveList)
            {
                if (procBean != null)
                {
                    try
                    {
                        processCveInfo(procBean);
                        
                        procBean.setStatus(ProcStatusType.SUCCESS.getValue());
                        procBean.setModifiedTime(DateUtil.getCurrentUtilDate());
                        nvdVulInfoService.updateProcBeanByPrimaryKey(procBean);
                        
                        LOG.info("process cve info success , cve id is " + procBean.getCveId());
                    }
                    catch (CheckedException e)
                    {
                        LOG.error("process cve info error , cve id is " + procBean.getCveId(), e);
                        
                        procBean.setStatus(ProcStatusType.FAIL.getValue());
                        procBean.setModifiedTime(DateUtil.getCurrentUtilDate());
                        nvdVulInfoService.updateProcBeanByPrimaryKey(procBean);
                    }
                }
            }
            
            updateNum.addAndGet(updateInt);
            insertNum.addAndGet(insertInt);
        }
        catch (RuntimeException e)
        {
            LOG.error("an error occured during process xml ", e);
        }
    }
    
    /**
     * 处理cve信息
     * @param procBean procBean
     * @throws CheckedException CheckedException
     
     * @date 2016年9月26日
     */
    private void processCveInfo(NdDownloadProcBean procBean) throws CheckedException
    {
        try
        {
            String cveId = procBean.getCveId();
            Date modDate = procBean.getVulModDate();
            int flag = procBean.getFlag();
            
            //查询cve是否存在
            NvdVulInfoBean vulInfoBean = nvdVulInfoService.queryNvdVulInfoByCveId(cveId);
            //新增
            if (vulInfoBean == null)
            {
                LOG.info("add cve info ,cve id is " + cveId + "=========thread name : " + this.getName());
                nvdVulInfoService.addNewNvdInfo(procBean);
                this.insertInt = this.insertInt + 1;
            }
            else if (modDate.compareTo(vulInfoBean.getVulModDate()) == 1 || flag != vulInfoBean.getFlag())
            {
                LOG.info("update cve info ,cve id is " + cveId + "=========thread name : " + this.getName());
                nvdVulInfoService.updateNvdInfo(procBean, vulInfoBean);
                
                this.updateInt = this.updateInt + 1;
            }
        }
        catch (CheckedException e)
        {
            LOG.error("CheckedException occured during processCveInfo. ", e);
            throw new CheckedException("CheckedException occured during processCveInfo.", e);
        }
        catch (Exception e)
        {
            LOG.error("Exception occured during processCveInfo. ", e);
            throw new CheckedException("Exception occured during processCveInfo.", e);
        }
    }
    
}
