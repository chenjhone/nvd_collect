package com.chenjh.mapper;

import com.chenjh.domain.nvd.NvdExtRefBean;
import org.apache.ibatis.annotations.Param;


public interface NvdExtRefBeanMapper
{
    /**
     * 插入extREF
     * @param record record
     * @return 插入数据数
     
     * @date 2016年9月26日
     */
    int insert(NvdExtRefBean record);
    
    /**
     * selectByPrimaryKey
     * @param ndExtRefId ndExtRefId
     * @return NvdExtRefBean
     
     * @date 2016年9月26日
     */
    NvdExtRefBean selectByPrimaryKey(Long ndExtRefId);
    
    /**
     * deleteByPrimaryKey
     * @param ndExtRefId ndExtRefId
     * @return int
     
     * @date 2016年9月26日
     */
    int deleteByPrimaryKey(Long ndExtRefId);
    
    /**
     * 通过cveId删除软件
     * @param cveId cveId
     * @return 删除记录数
     
     * @date 2016年9月27日
     */
    int deleteByCveId(@Param("cveId")String cveId);
}
