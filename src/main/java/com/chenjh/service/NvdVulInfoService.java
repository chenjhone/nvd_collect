package com.chenjh.service;

import java.util.Date;
import java.util.List;

import com.chenjh.domain.nvd.NdDownloadProcBean;
import com.chenjh.domain.nvd.NvdPageInfoBean;
import com.chenjh.domain.nvd.NvdVulInfoBean;
import com.chenjh.exception.CheckedException;

public interface NvdVulInfoService
{
    /**
     * 锁住选中的数据
     * @param procIdList 处理列表
     * @return 锁住记录数
     
     * @date 2016年12月13日
     */
    int lockSelectedProcList(List<Long> procIdList);
    
    /**
     * 通过组件更新procBean
     * @param procBean procBean
     * @return 更新记录数
     
     * @date 2016年12月13日
     */
    int updateProcBeanByPrimaryKey(NdDownloadProcBean procBean);
    
    /**
     * 新增nvd漏洞信息
     * @param procBean procBean
     * @throws CheckedException CheckedException
     
     * @date 2016年12月13日
     */
    void addNewNvdInfo(NdDownloadProcBean procBean) throws CheckedException;
    
    /**
     * 更新nvd漏洞信息
     * @param procBean procBean
     * @param vulInfoDb 被更新的漏洞信息
     * @throws CheckedException CheckedException
     
     * @date 2016年12月13日
     */
    void updateNvdInfo(NdDownloadProcBean procBean, NvdVulInfoBean vulInfoDb) throws CheckedException;
    
    /**
     * 通过cveId查询nvd漏洞信息
     * @param cveId cveId
     * @return nvd漏洞信息
     
     * @date 2016年12月13日
     */
    NvdVulInfoBean queryNvdVulInfoByCveId(String cveId);
    
    /**
     * 查询列表
     * @param procBean procBean
     * @return 集合
     
     * @date 2016年9月26日
     */
    List<NdDownloadProcBean> queryCveList(NdDownloadProcBean procBean);
    
    /**
     * 分页获取cveId
     * @param crawlId 抓取任务Id
     * @return cve集合
     
     * @date 2017年2月15日
     */
    List<String> queryCveListByCrawlId(Long crawlId);
    
    /**
     * 获取时间段内的待处理集合
     * @param pubDateStart 开始时间
     * @param pubDateEnd 结束时间
     * @return 集合
     
     * @date 2017年2月16日
     */
    List<String> queryCveListByPublishDate(Date pubDateStart, Date pubDateEnd);
    
    /**
     * 处理cvss信息
     * @param vulInfo 漏洞信息
     * @param pageInfo 页面信息
     
     * @date 2017年2月16日
     */
    void processVulCvssInfo(NvdVulInfoBean vulInfo, NvdPageInfoBean pageInfo);
    
    /**
     * lockVulForCvssUpdate
     * @param cveList cveList
     * @return 更新记录数
     
     * @date 2017年2月16日
     */
    int lockVulForCvssUpdate(List<String> cveList);
    
    /**
     * 初始化待处理cveList
     * @param fromDate 开始时间
     * @param toDate 结束时间
     * @param crawlId crawlId
     * @param force 是否强制
     * @return 更新记录数
     
     * @date 2017年2月17日
     */
    int initCveListCvssStatus(Date fromDate, Date toDate, Long crawlId, Boolean force);

}
