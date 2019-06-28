package com.chenjh.mapper;

import com.chenjh.domain.CrawlTaskBean;


public interface CrawlTaskBeanMapper
{
    /**
     * 通过主键查询
     * @param crawlId crawlId
     * @return HwCrawlTaskBean
     
     * @date 2016年9月30日
     */
    CrawlTaskBean selectByPrimaryKey(Long crawlId);
    
    /**
     * 通过主键删除
     * @param crawlId crawlId
     * @return 删除记录数
     
     * @date 2016年9月30日
     */
    int deleteByPrimaryKey(Long crawlId);
    
    /**
     * 插入数据
     * @param record record
     * @return 插入主键
     
     * @date 2016年9月30日
     */
    long insert(CrawlTaskBean record);
    
    /**
     * 插入数据
     * @param record record
     * @return 插入数据数
     
     * @date 2016年9月30日
     */
    int insertSelective(CrawlTaskBean record);
    
    /**
     * updateByPrimaryKey
     * @param record record
     * @return 更新记录数
     
     * @date 2016年9月30日
     */
    int updateByPrimaryKey(CrawlTaskBean record);
    
    /**
     * updateByPrimaryKeySelective
     * @param record record
     * @return 更新记录数
     
     * @date 2016年9月30日
     */
    int updateByPrimaryKeySelective(CrawlTaskBean record);
    
    /**
     * 更新下载完成时间
     * @return 更新记录数
     
     * @date 2017年9月15日
     */
    int updateCrawlEndTime();
}
