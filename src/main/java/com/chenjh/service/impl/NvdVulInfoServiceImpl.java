
package com.chenjh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.chenjh.common.nvd.RefType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.chenjh.domain.nvd.NdDownloadProcBean;
import com.chenjh.domain.nvd.NvdExtRefBean;
import com.chenjh.domain.nvd.NvdPageInfoBean;
import com.chenjh.domain.nvd.NvdSoftListBean;
import com.chenjh.domain.nvd.NvdVulInfoBean;
import com.chenjh.common.VulnConstant;
import com.chenjh.common.nvd.EntryType;
import com.chenjh.common.nvd.VulnSoftType;
import com.chenjh.common.type.ProcStatusType;
import com.chenjh.exception.CheckedException;
import com.chenjh.mapper.NdDownloadProcBeanMapper;
import com.chenjh.mapper.NvdExtRefBeanMapper;
import com.chenjh.mapper.NvdPageInfoBeanMapper;
import com.chenjh.mapper.NvdSoftListBeanMapper;
import com.chenjh.mapper.NvdVulInfoBeanMapper;
import com.chenjh.service.NvdVulInfoService;
import com.chenjh.util.DateUtil;
import com.chenjh.util.FileUtil;


@Component
public class NvdVulInfoServiceImpl implements NvdVulInfoService
{
    /**
     * LOG
     */
    private static final Logger LOG = Logger.getLogger(NvdVulInfoServiceImpl.class);
    
    /**
     * ndDownloadProcBeanMapper
     */
    @Autowired
    private NdDownloadProcBeanMapper ndDownloadProcBeanMapper;
    
    /**
     * nvdVulInfoBeanMapper
     */
    @Autowired
    private NvdVulInfoBeanMapper nvdVulInfoBeanMapper;
    
    /**
     * nvdExtRefBeanMapper
     */
    @Autowired
    private NvdExtRefBeanMapper nvdExtRefBeanMapper;
    
    /**
     * nvdSoftListBeanMapper
     */
    @Autowired
    private NvdSoftListBeanMapper nvdSoftListBeanMapper;
    
    @Autowired
    private NvdPageInfoBeanMapper nvdPageInfoBeanMapper;
    
    /**
     * 锁住选中的数据
     * @param procIdList 处理列表
     * @return 锁住记录数
     
     * @date 2016年12月13日
     */
    @Override
    public int lockSelectedProcList(List<Long> procIdList)
    {
        
        return ndDownloadProcBeanMapper.updateProcBeanForLock(procIdList);
    }
    
    /**
     * 通过组件更新procBean
     * @param procBean procBean
     * @return 更新记录数
     
     * @date 2016年12月13日
     */
    @Override
    public int updateProcBeanByPrimaryKey(NdDownloadProcBean procBean)
    {
        
        return ndDownloadProcBeanMapper.updateByPrimaryKey(procBean);
    }
    
    /**
     * 新增nvd漏洞信息
     * @param procBean procBean
     
     * @throws CheckedException JAXBException
     * @date 2016年12月13日
     */
    @Override
    @Transactional
    public void addNewNvdInfo(NdDownloadProcBean procBean) throws CheckedException
    {
        int flag = procBean.getFlag();
        EntryType entryBean;
        
        try
        {
            entryBean = FileUtil.converyToJavaBean(procBean.getCveEntry(), EntryType.class);
        }
        catch (JAXBException e)
        {
            // JAXBException 
            LOG.error("JAXBException occured during parse xml to bean .");
            throw new CheckedException("JAXBException occured during parse xml to bean .", e);
        }
        //漏洞信息
        NvdVulInfoBean insertBean = createOrUpdateNvdInfoBean(entryBean, null, flag);
        //版本列表
        List<NvdSoftListBean> softBeanList = getNvdSoftList(entryBean);
        //ext-ref
        List<NvdExtRefBean> extRefBeanList = getNvdExtRefList(entryBean);
        
        //保存数据
        nvdVulInfoBeanMapper.insert(insertBean);
        for (NvdExtRefBean nvdRef : extRefBeanList)
        {
            nvdExtRefBeanMapper.insert(nvdRef);
        }
        for (NvdSoftListBean nvdSoft : softBeanList)
        {
            nvdSoftListBeanMapper.insert(nvdSoft);
        }
        
    }
    
    /**
     * 更新nvd漏洞信息
     * @param procBean procBean
     * @param vulInfoDb 被更新的漏洞信息
     * @throws CheckedException CheckedException
     
     * @date 2016年12月13日
     */
    @Override
    @Transactional
    public void updateNvdInfo(NdDownloadProcBean procBean, NvdVulInfoBean vulInfoDb) throws CheckedException
    {
        String cveId = procBean.getCveId();
        int flag = procBean.getFlag();
        EntryType entryBean;
        
        try
        {
            entryBean = FileUtil.converyToJavaBean(procBean.getCveEntry(), EntryType.class);
        }
        catch (JAXBException e)
        {
            // JAXBException 
            LOG.error("JAXBException occured during parse xml to java bean.");
            throw new CheckedException("JAXBException occured during parse xml to java bean . ", e);
        }
        
        //漏洞信息
        NvdVulInfoBean updateBean = createOrUpdateNvdInfoBean(entryBean, vulInfoDb, flag);
        //版本列表
        List<NvdSoftListBean> softBeanList = getNvdSoftList(entryBean);
        //ext-ref
        List<NvdExtRefBean> extRefBeanList = getNvdExtRefList(entryBean);
        
        //保存数据
        nvdVulInfoBeanMapper.updateByPrimaryKey(updateBean);
        //清除softList,extRefList
        nvdSoftListBeanMapper.deleteByCveId(cveId);
        nvdExtRefBeanMapper.deleteByCveId(cveId);
        //插入新的softList,extRefList
        for (NvdSoftListBean nvdSoft : softBeanList)
        {
            nvdSoftListBeanMapper.insert(nvdSoft);
        }
        for (NvdExtRefBean nvdRef : extRefBeanList)
        {
            nvdExtRefBeanMapper.insert(nvdRef);
        }
    }
    
    /**
     * 通过cveId查询nvd漏洞信息
     * @param cveId cveId
     * @return nvd漏洞信息
     
     * @date 2016年12月13日
     */
    @Override
    public NvdVulInfoBean queryNvdVulInfoByCveId(String cveId)
    {
        return nvdVulInfoBeanMapper.selectByPrimaryKey(cveId);
    }
    
    /**
     * 创建或更新vulInfo
     * @param entryBean entryBean
     * @param vulInfoDb vulInfoDb
     * @param flag flag
     * @return NvdVulInfoBean
     
     * @date 2016年9月27日
     */
    private NvdVulInfoBean createOrUpdateNvdInfoBean(EntryType entryBean, NvdVulInfoBean vulInfoDb, int flag)
    {
        //无效状态
        final Integer rejectStatus = 0;
        final Integer normalStatus = 1;
        NvdVulInfoBean nvdInfoBean = new NvdVulInfoBean();
        nvdInfoBean.setCveId(entryBean.getName());
        nvdInfoBean.setVulTitle("Vulnerability Summary for " + entryBean.getName());
        if (entryBean.getDesc().getDescript().size() != 0)
        {
            nvdInfoBean.setVulDescription(entryBean.getDesc().getDescript().get(0).getValue());
        }
        if (entryBean.getReject() != null && "1".equals(entryBean.getReject()))
        {
            nvdInfoBean.setStatus(rejectStatus);
        }
        else 
        {
            nvdInfoBean.setStatus(normalStatus);
        }
        
        nvdInfoBean.setCvssVer(entryBean.getCVSSVersion());
        nvdInfoBean.setCvssScore(entryBean.getCVSSScore());
        nvdInfoBean.setCvssVector(entryBean.getCVSSVector());
        nvdInfoBean.setPublishedDate(DateUtil.parse(entryBean.getPublished(), DateUtil.PATTERN_YYYY_MM_DD));
        nvdInfoBean.setVulModDate(DateUtil.parse(entryBean.getModified(), DateUtil.PATTERN_YYYY_MM_DD));
        nvdInfoBean.setProcStatus(ProcStatusType.INIT.getValue());
        nvdInfoBean.setCvssStatus(ProcStatusType.INIT.getValue());
        nvdInfoBean.setFlag(flag);
        nvdInfoBean.setCreatedTime(DateUtil.getCurrentUtilDate());
        nvdInfoBean.setModifiedTime(DateUtil.getCurrentUtilDate());
        
        if (vulInfoDb != null)
        {
            nvdInfoBean.setNote(vulInfoDb.getNote());
            nvdInfoBean.setCreatedTime(vulInfoDb.getCreatedTime());
        }
        
        return nvdInfoBean;
    }
    
    /**
     * getNvdSoftList
     * @param entryBean entryBean
     * @return nvd 软件列表
     
     * @date 2016年9月27日
     */
    private List<NvdSoftListBean> getNvdSoftList(EntryType entryBean)
    {
        List<NvdSoftListBean> resultList = new ArrayList<NvdSoftListBean>();
        
        String cveId = entryBean.getName();
        VulnSoftType softType = entryBean.getVulnSoft();
        
        if (softType != null)
        {
            List<VulnSoftType.Prod> prodList = softType.getProd();
            if (!CollectionUtils.isEmpty(prodList))
            {
                for (VulnSoftType.Prod prodInfo : prodList)
                {
                    String prodName = prodInfo.getName();
                    String vendorName = prodInfo.getVendor();
                    
                    for (VulnSoftType.Prod.Vers verInfo : prodInfo.getVers())
                    {
                        int prev = StringUtils.isEmpty(verInfo.getPrev()) ? 0 : 1;
                        
                        NvdSoftListBean softBean = new NvdSoftListBean();
                        softBean.setCveId(cveId);
                        softBean.setVendorName(vendorName);
                        softBean.setProductName(prodName);
                        softBean.setProductVersion(verInfo.getNum());
                        softBean.setEdition(verInfo.getEdition());
                        softBean.setIncludePrevious(prev);
                        //加入集合
                        resultList.add(softBean);
                    }
                }
            }
        }
        return resultList;
    }
    
    /**
     * getNvdExtRefList
     * @param entryBean entryBean
     * @return nvd ext ref 集合
     
     * @date 2016年9月27日
     */
    private List<NvdExtRefBean> getNvdExtRefList(EntryType entryBean)
    {
        List<NvdExtRefBean> resultList = new ArrayList<NvdExtRefBean>();
        String cveId = entryBean.getName();
        
        EntryType.Refs entryRef = entryBean.getRefs();
        if (entryRef != null)
        {
            List<RefType> refTypeList = entryRef.getRef();
            if (!CollectionUtils.isEmpty(refTypeList))
            {
                for (RefType refInfo : refTypeList)
                {
                    NvdExtRefBean refBean = new NvdExtRefBean();
                    refBean.setCveId(cveId);
                    refBean.setRefSource(refInfo.getSource());
                    refBean.setRefUrl(refInfo.getUrl());
                    refBean.setRefName(refInfo.getValue());
                    
                    //获取ref Type
                    StringBuilder typeSB = new StringBuilder();
                    if (!StringUtils.isEmpty(refInfo.getAdv()))
                    {
                        typeSB.append(VulnConstant.REF_ADV).append(VulnConstant.COMMA);
                    }
                    if (!StringUtils.isEmpty(refInfo.getPatch()))
                    {
                        typeSB.append(VulnConstant.REF_PATCH).append(VulnConstant.COMMA);
                    }
                    if (!StringUtils.isEmpty(refInfo.getSig()))
                    {
                        typeSB.append(VulnConstant.REF_SIG).append(VulnConstant.COMMA);
                    }
                    
                    String refType = typeSB.toString();
                    if (!StringUtils.isEmpty(refType))
                    {
                        refType = refType.substring(0, refType.length() - 1);
                    }
                    refBean.setRefType(refType);
                    
                    resultList.add(refBean);
                }
            }
        }
        
        return resultList;
    }
    
    /**
     * 查询列表
     * @param procBean procBean
     * @return 集合
     
     * @date 2016年9月26日
     */
    @Override
    public List<NdDownloadProcBean> queryCveList(NdDownloadProcBean procBean)
    {
        return ndDownloadProcBeanMapper.queryCveList(procBean);
    }
    
    /**
     * 分页获取cveId
     * @param crawlId 抓取任务Id
     * @return cve集合
     
     * @date 2017年2月15日
     */
    @Override
    public List<String> queryCveListByCrawlId(Long crawlId)
    {
        return nvdVulInfoBeanMapper.queryCveByCrawlId(crawlId);
    }
    
    /**
     * 处理cvss信息
     * @param vulInfo 漏洞信息
     * @param pageInfo 页面信息
     
     * @date 2017年2月16日
     */
    @Override
    @Transactional
    public void processVulCvssInfo(NvdVulInfoBean vulInfo, NvdPageInfoBean pageInfo)
    {
        //更新漏洞处理信息
        nvdVulInfoBeanMapper.updateByPrimaryKeySelective(vulInfo);
        //保存网页信息
        if (pageInfo != null)
        {
            //覆盖原有数据
            int update = nvdPageInfoBeanMapper.updateByPrimaryKeySelective(pageInfo);
            if (update == 0)
            {
                nvdPageInfoBeanMapper.insert(pageInfo);
            }
        }
    }
    
    /**
     * 获取时间段内的待处理集合
     * @param pubDateStart 开始时间
     * @param pubDateEnd 结束时间
     * @return 集合
     
     * @date 2017年2月16日
     */
    @Override
    public List<String> queryCveListByPublishDate(Date pubDateStart, Date pubDateEnd)
    {
        
        return nvdVulInfoBeanMapper.queryCveByPubDate(pubDateStart, pubDateEnd);
    }

    /**
     * lockVulForCvssUpdate
     * @param cveList cveList
     * @return 更新记录数
     
     * @date 2017年2月16日
     */
    @Override
    public int lockVulForCvssUpdate(List<String> cveList)
    {
        return nvdVulInfoBeanMapper.updateVulInfoForLock(cveList);
    }

    /**
     * 初始化待处理cveList
     * @param fromDate 开始时间
     * @param toDate 结束时间
     * @param crawlId crawlId
     * @param force 是否强制
     * @return 更新记录数
     
     * @date 2017年2月17日
     */
    @Override
    public int initCveListCvssStatus(Date fromDate, Date toDate, Long crawlId, Boolean force)
    {
        
        return nvdVulInfoBeanMapper.initCveCvssStatus(fromDate, toDate, crawlId, force);
    }        
}
