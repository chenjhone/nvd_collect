package com.chenjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chenjh.domain.nvd.NdDownloadProcBean;

public interface NdDownloadProcBeanMapper
{
    /**
     * deleteByPrimaryKey
     * @param procId procId
     * @return num
     
     * @date 2016年9月24日
     */
    int deleteByPrimaryKey(Long procId);
    
    /**
     * insert
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int insert(NdDownloadProcBean record);
    
    /**
     * insertSelective
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int insertSelective(NdDownloadProcBean record);
    
    /**
     * selectByPrimaryKey
     * @param procId procId
     * @return NdDownloadProcBean
     
     * @date 2016年9月24日
     */
    NdDownloadProcBean selectByPrimaryKey(Long procId);
    
    /**
     * 查询列表
     * @param record record
     * @return 集合
     
     * @date 2016年9月26日
     */
    List<NdDownloadProcBean> queryCveList(NdDownloadProcBean record);
    
    /**
     * updateByPrimaryKeySelective
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int updateByPrimaryKeySelective(NdDownloadProcBean record);
    
    /**
     * lock
     * @param procIdList procIdList
     * @return 更新记录数
     
     * @date 2016年9月29日
     */
    int updateProcBeanForLock(@Param("procIdList")List<Long> procIdList);
    
    /**
     * updateByPrimaryKey
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int updateByPrimaryKey(NdDownloadProcBean record);
}
