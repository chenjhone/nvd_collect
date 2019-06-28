package com.chenjh.mapper;

import java.util.Date;
import java.util.List;

import com.chenjh.domain.nvd.NvdVulInfoBean;
import org.apache.ibatis.annotations.Param;


public interface NvdVulInfoBeanMapper
{
 
    /**
     * insert
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int insert(NvdVulInfoBean record);
    
    /**
     * updateByPrimaryKey
     * @param record record
     * @return num
     
     * @date 2016年9月27日
     */
    int updateByPrimaryKey(NvdVulInfoBean record);
    
    /**
     * updateByPrimaryKeySelective
     * @param record record
     * @return num
     
     * @date 2016年9月27日
     */
    int updateByPrimaryKeySelective(NvdVulInfoBean record);
    
    /**
     * selectByPrimaryKey
     * @param cveId cveId
     * @return NvdVulInfoBean
     
     * @date 2016年9月26日
     */
    NvdVulInfoBean selectByPrimaryKey(String cveId);
    
    /**
     * queryCveByCrawlId
     * @param crawlId crawlId
     * @return cve集合
     
     * @date 2017年2月15日
     */
    List<String> queryCveByCrawlId(@Param("crawlId")Long crawlId);
    
    /**
     * 通过发布时间段取cve集合
     * @param pubDateStart 开始时间
     * @param pubDateEnd 结束时间
     * @return cve集合
     
     * @date 2017年2月16日
     */
    List<String> queryCveByPubDate(@Param("pubDateStart")
            Date pubDateStart, @Param("pubDateEnd")
            Date pubDateEnd);

    /**
     * updateVulInfoForLock
     * @param cveList cveList
     * @return 更新记录数
     
     * @date 2017年2月16日
     */
    int updateVulInfoForLock(@Param("cveList")List<String> cveList);
    
    /**
     * 初始化待处理cveList
     * @param fromDate 开始时间
     * @param toDate 结束时间
     * @param crawlId crawlId
     * @param force 是否强制
     * @return 更新记录数
     
     * @date 2017年2月17日
     */
    int initCveCvssStatus(@Param("fromDate")Date fromDate, 
            @Param("toDate")Date toDate,
            @Param("crawlId")Long crawlId, 
            @Param("force")Boolean force);
}
