package com.chenjh.domain.nvd;

import java.util.Date;

public class NdDownloadProcBean
{
    private Long procId;
    
    private Long fileId;
    
    private String cveId;
    
    private Date vulModDate;
    
    private String cveEntry;
    
    private Integer status;
    
    private Date createdTime;
    
    private Date modifiedTime;
    
    private String note;
    
    /**
     * 保存cveEntity的hash值
     */
    private int flag;
    
    public Long getProcId()
    {
        return procId;
    }

    public void setProcId(Long procId)
    {
        this.procId = procId;
    }

    public Long getFileId()
    {
        return fileId;
    }

    public void setFileId(Long fileId)
    {
        this.fileId = fileId;
    }

    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getCveId()
    {
        return cveId;
    }
    
    public void setCveId(String cveId)
    {
        this.cveId = cveId == null ? null : cveId.trim();
    }
    
    public Date getVulModDate()
    {
        return vulModDate;
    }
    
    public void setVulModDate(Date vulModDate)
    {
        this.vulModDate = vulModDate;
    }
    
    public String getCveEntry()
    {
        return cveEntry;
    }

    public void setCveEntry(String cveEntry)
    {
        this.cveEntry = cveEntry;
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
    
    public int getFlag()
    {
        return flag;
    }

    public void setFlag(int flag)
    {
        this.flag = flag;
    }

    /**
     * toString
     * @return string
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("NdDownloadProcBean [procId=");
        builder.append(procId);
        builder.append(", fileId=");
        builder.append(fileId);
        builder.append(", cveId=");
        builder.append(cveId);
        builder.append(", vulModDate=");
        builder.append(vulModDate);
        builder.append(", cveEntry=");
        builder.append(cveEntry);
        builder.append(", status=");
        builder.append(status);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", modifiedTime=");
        builder.append(modifiedTime);
        builder.append(", note=");
        builder.append(note);
        builder.append(", flag=");
        builder.append(flag);
        builder.append(']');
        return builder.toString();
    }
    
}
