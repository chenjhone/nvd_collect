package com.chenjh.domain.nvd;

import java.util.Date;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * @since
 */
public class NdDownloadFileBean
{
    private Long fileId;
    
    private Long crawlId;
    
    private String feed;
    
    private String filePath;
    
    private String fileName;
    
    private String fileOrgName;
    
    private Long fileSize;
    
    private Integer status;
    
    private Date createdTime;
    
    private Date modifiedTime;
    
    private String note;
    
    public Long getFileId()
    {
        return fileId;
    }

    public void setFileId(Long fileId)
    {
        this.fileId = fileId;
    }

    public Long getCrawlId()
    {
        return crawlId;
    }

    public void setCrawlId(Long crawlId)
    {
        this.crawlId = crawlId;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getFeed()
    {
        return feed;
    }
    
    public void setFeed(String feed)
    {
        this.feed = feed == null ? null : feed.trim();
    }
    
    public String getFilePath()
    {
        return filePath;
    }
    
    public void setFilePath(String filePath)
    {
        this.filePath = filePath == null ? null : filePath.trim();
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName == null ? null : fileName.trim();
    }
    
    public String getFileOrgName()
    {
        return fileOrgName;
    }
    
    public void setFileOrgName(String fileOrgName)
    {
        this.fileOrgName = fileOrgName == null ? null : fileOrgName.trim();
    }
    
    public Long getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
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
        this.note = note == null ? null : note.trim();
    }

    /**
     * 重写toString
     * @return string
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("NdDownloadFileBean [fileId=");
        builder.append(fileId);
        builder.append(", crawlId=");
        builder.append(crawlId);
        builder.append(", feed=");
        builder.append(feed);
        builder.append(", filePath=");
        builder.append(filePath);
        builder.append(", fileName=");
        builder.append(fileName);
        builder.append(", fileOrgName=");
        builder.append(fileOrgName);
        builder.append(", fileSize=");
        builder.append(fileSize);
        builder.append(", status=");
        builder.append(status);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", modifiedTime=");
        builder.append(modifiedTime);
        builder.append(", note=");
        builder.append(note);
        builder.append(']');
        return builder.toString();
    }
    
    
}
