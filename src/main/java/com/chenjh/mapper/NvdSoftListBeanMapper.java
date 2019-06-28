package com.chenjh.mapper;

import org.apache.ibatis.annotations.Param;

import com.chenjh.domain.nvd.NvdSoftListBean;

public interface NvdSoftListBeanMapper
{
    /**
     * 插入数据
     * @param record record
     * @return 插入数据数
     
     * @date 2016年9月26日
     */
    int insert(NvdSoftListBean record);
    
    /**
     * NvdSoftListBean
     * @param ndSoftId ndSoftId
     * @return NvdSoftListBean
     
     * @date 2016年9月26日
     */
    NvdSoftListBean selectByPrimaryKey(Long ndSoftId);
    
    /**
     * deleteByPrimaryKey
     * @param ndSoftId ndSoftId
     * @return int
     
     * @date 2016年9月26日
     */
    int deleteByPrimaryKey(Long ndSoftId);
    
    /**
     * 通过cveId删除软件
     * @param cveId cveId
     * @return 删除记录数
     
     * @date 2016年9月27日
     */
    int deleteByCveId(@Param("cveId")
    String cveId);
    
    /**
     * insertSelective
     * @param record record
     * @return int
     
     * @date 2016年9月26日
     */
    int insertSelective(NvdSoftListBean record);
    
    /**
     * updateByPrimaryKeySelective
     * @param record record
     * @return int
     
     * @date 2016年9月26日
     */
    int updateByPrimaryKeySelective(NvdSoftListBean record);
    
    /**
     * updateByPrimaryKey
     * @param ndSoftId ndSoftId
     * @return int
     
     * @date 2016年9月26日
     */
    int updateByPrimaryKey(Long ndSoftId);
}
