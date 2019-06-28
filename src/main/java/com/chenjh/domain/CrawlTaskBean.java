package com.chenjh.domain;

import java.util.Date;

/**
 * 
 * <p>Title:  漏洞爬取任务表实体类 </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author hWX392885
 * @version V1.0 2016年9月21日
 * @since
 */
public class CrawlTaskBean
{
    
    private Integer crawlId;
    
    private Integer vulSource;
    
    private Date startTime;
    
    private Date endTime;
    
    private String currentTask;
    
    private Integer vulDownloadNum;
    
    private Integer vulInsertNum;
    
    private Integer vulUpdateNum;
    
    private Integer taskStatus;
    
    private String workIp;
    
    private Date createdTime;
    
    private Date modifiedTime;
    
    private String note;
    
    private Integer taskIdx;
    
    public Integer getCrawlId()
    {
        return crawlId;
    }
    
    public void setCrawlId(Integer crawlId)
    {
        this.crawlId = crawlId;
    }
    
    public Integer getVulSource()
    {
        return vulSource;
    }
    
    public void setVulSource(Integer vulSource)
    {
        this.vulSource = vulSource;
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
    
    public String getCurrentTask()
    {
        return currentTask;
    }
    
    public void setCurrentTask(String currentTask)
    {
        this.currentTask = currentTask;
    }
    
    public Integer getVulDownloadNum()
    {
        return vulDownloadNum;
    }
    
    public void setVulDownloadNum(Integer vulDownloadNum)
    {
        this.vulDownloadNum = vulDownloadNum;
    }
    
    public Integer getVulInsertNum()
    {
        return vulInsertNum;
    }
    
    public void setVulInsertNum(Integer vulInsertNum)
    {
        this.vulInsertNum = vulInsertNum;
    }
    
    public Integer getVulUpdateNum()
    {
        return vulUpdateNum;
    }
    
    public void setVulUpdateNum(Integer vulUpdateNum)
    {
        this.vulUpdateNum = vulUpdateNum;
    }
    
    public Integer getTaskStatus()
    {
        return taskStatus;
    }
    
    public void setTaskStatus(Integer taskStatus)
    {
        this.taskStatus = taskStatus;
    }
    
    public String getWorkIp()
    {
        return workIp;
    }
    
    public void setWorkIp(String workIp)
    {
        this.workIp = workIp;
    }
    
    public Date getCreatedTime()
    {
        return createdTime;
    }
    
    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }
    
    public Date getModifiedTime()
    {
        return modifiedTime;
    }
    
    public void setModifiedTime(Date modifiedTime)
    {
        this.modifiedTime = modifiedTime;
    }
    
    public String getNote()
    {
        return note;
    }
    
    public void setNote(String note)
    {
        this.note = note;
    }
    
    public Integer getTaskIdx()
    {
        return taskIdx;
    }
    
    public void setTaskIdx(Integer taskIdx)
    {
        this.taskIdx = taskIdx;
    }
    
    /**
     * 重写toString
     * @return string
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CrawlTaskBean [crawlId=");
        builder.append(crawlId);
        builder.append(", vulSource=");
        builder.append(vulSource);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", currentTask=");
        builder.append(currentTask);
        builder.append(", vulDownloadNum=");
        builder.append(vulDownloadNum);
        builder.append(", vulInsertNum=");
        builder.append(vulInsertNum);
        builder.append(", vulUpdateNum=");
        builder.append(vulUpdateNum);
        builder.append(", taskStatus=");
        builder.append(taskStatus);
        builder.append(", workIp=");
        builder.append(workIp);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", modifiedTime=");
        builder.append(modifiedTime);
        builder.append(", note=");
        builder.append(note);
        builder.append(", taskIdx=");
        builder.append(taskIdx);
        builder.append(']');
        return builder.toString();
    }
    
}
