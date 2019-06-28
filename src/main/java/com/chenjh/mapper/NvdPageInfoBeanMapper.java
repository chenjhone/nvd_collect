package com.chenjh.mapper;

import com.chenjh.domain.nvd.NvdPageInfoBean;


public interface NvdPageInfoBeanMapper
{
    /**
     * 按主键删除
     * @param cveId cveId
     * @return 删除记录数
     
     * @date 2017年2月15日
     */
    int deleteByPrimaryKey(String cveId);
    
    /**
     * insert
     * @param record record
     * @return 插入记录数
     
     * @date 2017年2月15日
     */
    int insert(NvdPageInfoBean record);
    
    /**
     * 通过主键查询
     * @param cveId cveid
     * @return nvdPageInfoBean
     
     * @date 2017年2月15日
     */
    NvdPageInfoBean selectByPrimaryKey(String cveId);
    
    /**
     * 更新
     * @param record record
     * @return 更新记录数
     
     * @date 2017年2月15日
     */
    int updateByPrimaryKeySelective(NvdPageInfoBean record);
    
    /**
     * 更新
     * @param record record
     * @return 更新记录数
     
     * @date 2017年2月15日
     */
    int updateByPrimaryKey(NvdPageInfoBean record);
}
