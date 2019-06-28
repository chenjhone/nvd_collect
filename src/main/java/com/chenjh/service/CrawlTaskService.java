package com.chenjh.service;

import com.chenjh.domain.CrawlTaskBean;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2017年4月7日
 * @since
 */
public interface CrawlTaskService
{
    /**
     * 通过主键查询对象
     * @param crawlId 主键id
     * @return 对象
     
     * @date 2017年4月7日
     */
    CrawlTaskBean queryBeanByKey(Long crawlId);
    
    /**
     * 更新
     * @param record 更新对象
     * @return 更新记录数
     
     * @date 2017年4月7日
     */
    int updateByPrimaryKeySelective(CrawlTaskBean record);
    
    /**
     * 新增任务
     * @param record 任务对象
     * @return 新增记录数
     
     * @date 2017年4月7日
     */
    long addNew(CrawlTaskBean record);
    
    /**
     * 更新下载完成时间
     * @return 更新记录数
     
     * @date 2017年9月15日
     */
    int updateCrawlEndTime();
}
