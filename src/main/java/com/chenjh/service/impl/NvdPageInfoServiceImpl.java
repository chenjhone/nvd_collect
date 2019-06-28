package com.chenjh.service.impl;

import com.chenjh.domain.nvd.NvdPageInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chenjh.mapper.NvdPageInfoBeanMapper;
import com.chenjh.service.NvdPageInfoService;


@Component
public class NvdPageInfoServiceImpl implements NvdPageInfoService
{
    
    @Autowired
    private NvdPageInfoBeanMapper nvdPageInfoBeanMapper;

    /**
     * 按主键删除
     * @param cveId cveId
     * @return 删除记录数
     
     * @date 2017年2月15日
     */
    @Override
    public int deleteByPrimaryKey(String cveId)
    {
        
        return nvdPageInfoBeanMapper.deleteByPrimaryKey(cveId);
    }

    /**
     * insert
     * @param  record
     * @return 插入记录数
     
     * @date 2017年2月15日
     */
    @Override
    public int insert(NvdPageInfoBean record)
    {
        
        return nvdPageInfoBeanMapper.insert(record);
    }

    /**
     * 通过主键查询
     * @param cveId cveid
     * @return nvdPageInfoBean
     
     * @date 2017年2月15日
     */
    @Override
    public NvdPageInfoBean selectByPrimaryKey(String cveId)
    {
        
        return nvdPageInfoBeanMapper.selectByPrimaryKey(cveId);
    }

    /**
     * 更新
     * @param record record
     * @return 更新记录数
     
     * @date 2017年2月15日
     */
    @Override
    public int updateByPrimaryKeySelective(NvdPageInfoBean record)
    {
        
        return nvdPageInfoBeanMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 更新
     * @param record record
     * @return 更新记录数
     
     * @date 2017年2月15日
     */
    @Override
    public int updateByPrimaryKey(NvdPageInfoBean record)
    {
        
        return nvdPageInfoBeanMapper.updateByPrimaryKey(record);
    }
    
}
