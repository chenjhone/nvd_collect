/*
 * 
 *  @(#)TaskContext.java Created on 2016年9月22日
 *
 * Copyright 2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 
 * Description 
 * 
 * CopyrightVersion 
 *
 */
package com.chenjh.handler;

import java.util.Date;

/**
 * 任务执行上下文
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月22日
 * @since
 */
public class TaskContext
{
    /**
     * 爬虫任务ID
     */
    private Long crawlTaskId;
    
    /**
     * 是否全量任务
     */
    private boolean allDataTask;
    
    /**
     * 当前任务Id
     */
    private int handleIndex;
    
    /**
     * 当前任务
     */
    private String currentTask;
    
    /**
     * 当前任务状态
     */
    private int taskStatus;
    
    /**
     * 起始vulnDB_id
     */
    private Long startId;
    
    /**
     * 结束vulnDB_id
     */
    private Long endId;
    
    /**
     * currentPage
     */
    private Integer currentPage;
    
    /**
     * 每页下载数量
     */
    private int pageSize;
    
    /**
     * 任务类型 新增0，更新1
     */
    private int taskType;
    
    /**
     * 开始时间
     */
    private String startDate;
    
    /**
     * 结束时间
     */
    private String endDate;
    
    /**
     * 任务开始时间
     */
    private Date startTime;
    
    public Long getCrawlTaskId()
    {
        return crawlTaskId;
    }
    
    public void setCrawlTaskId(Long crawlTaskId)
    {
        this.crawlTaskId = crawlTaskId;
    }
    
    public boolean isAllDataTask()
    {
        return allDataTask;
    }
    
    public void setAllDataTask(boolean allDataTask)
    {
        this.allDataTask = allDataTask;
    }
    
    public int getHandleIndex()
    {
        return handleIndex;
    }
    
    public void setHandleIndex(int handleIndex)
    {
        this.handleIndex = handleIndex;
    }
    
    public String getCurrentTask()
    {
        return currentTask;
    }
    
    public void setCurrentTask(String currentTask)
    {
        this.currentTask = currentTask;
    }
    
    public int getTaskStatus()
    {
        return taskStatus;
    }
    
    public void setTaskStatus(int taskStatus)
    {
        this.taskStatus = taskStatus;
    }
    
    public Long getStartId()
    {
        return startId;
    }
    
    public void setStartId(Long startId)
    {
        this.startId = startId;
    }
    
    public Long getEndId()
    {
        return endId;
    }
    
    public void setEndId(Long endId)
    {
        this.endId = endId;
    }
    
    public Integer getCurrentPage()
    {
        return currentPage;
    }
    
    public void setCurrentPage(Integer currentPage)
    {
        this.currentPage = currentPage;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public int getTaskType()
    {
        return taskType;
    }
    
    public void setTaskType(int taskType)
    {
        this.taskType = taskType;
    }
    
    public String getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
}
