package com.chenjh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chenjh.domain.CrawlTaskBean;
import com.chenjh.mapper.CrawlTaskBeanMapper;
import com.chenjh.service.CrawlTaskService;

@Component(value = "crawlTaskService")
public class CrawlTaskServiceImpl implements CrawlTaskService
{
    
    /**
     * hwCrawlTaskBeanMapper
     */
    @Autowired
    private CrawlTaskBeanMapper crawlTaskBeanMapper;
    
    /**
     * 通过主键查询对象
     * @param crawlId 主键id
     * @return 对象
     
     * @date 2017年4月7日
     */
    @Override
    public CrawlTaskBean queryBeanByKey(Long crawlId)
    {
        
        return crawlTaskBeanMapper.selectByPrimaryKey(crawlId);
    }
    
    /**
     * 更新
     * @param record 更新对象
     * @return 更新记录数
     * @date 2017年4月7日
     */
    @Override
    public int updateByPrimaryKeySelective(CrawlTaskBean record)
    {
        
        return crawlTaskBeanMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 新增任务
     * @param record 任务对象
     * @return 新增记录数
     
     * @date 2017年4月7日
     */
    @Override
    public long addNew(CrawlTaskBean record)
    {
        
        return crawlTaskBeanMapper.insert(record);
    }

    /**
     * 更新下载完成时间
     * @return 更新记录数
     
     * @date 2017年9月15日
     */
    @Override
    public int updateCrawlEndTime()
    {
        
        return crawlTaskBeanMapper.updateCrawlEndTime();
    }    
}
