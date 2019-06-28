package com.chenjh.mapper;

import java.util.List;

import com.chenjh.domain.nvd.NdDownloadFileBean;


public interface NdDownloadFileBeanMapper
{
    /**
     * deleteByPrimaryKey
     * @param fileId fileId
     * @return num
     
     * @date 2016年9月24日
     */
    int deleteByPrimaryKey(Long fileId);
    
    /**
     * insert 
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int insert(NdDownloadFileBean record);
    
    /**
     * insertSelective
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int insertSelective(NdDownloadFileBean record);
    
    /**
     * selectByPrimaryKey
     * @param fileId fileId
     * @return NdDownloadFileBean
     
     * @date 2016年9月24日
     */
    NdDownloadFileBean selectByPrimaryKey(Long fileId);
    
    /**
     * updateByPrimaryKeySelective
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int updateByPrimaryKeySelective(NdDownloadFileBean record);
    
    /**
     * updateByPrimaryKey
     * @param record record
     * @return num
     
     * @date 2016年9月24日
     */
    int updateByPrimaryKey(NdDownloadFileBean record);
    
    /**
     * queryZipFileList
     * @param record record
     * @return list
     
     * @date 2016年9月24日
     */
    List<NdDownloadFileBean> queryZipFileList(NdDownloadFileBean record);
    
}
