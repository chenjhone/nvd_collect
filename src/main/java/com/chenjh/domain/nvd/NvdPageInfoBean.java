package com.chenjh.domain.nvd;

import java.util.Date;

/**
 * NVD 网页信息
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2017年2月15日
 * @since
 */
public class NvdPageInfoBean
{
    private String cveId;
    
    private Integer procStatus;
    
    private Date createTime;
    
    private Date lastUpdateTime;
    
    private String note;
    
    private String vulInfos;
    
    public String getCveId()
    {
        return cveId;
    }
    
    public void setCveId(String cveId)
    {
        this.cveId = cveId == null ? null : cveId.trim();
    }
    
    public Integer getProcStatus()
    {
        return procStatus;
    }
    
    public void setProcStatus(Integer procStatus)
    {
        this.procStatus = procStatus;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }
    
    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
    
    public String getNote()
    {
        return note;
    }
    
    public void setNote(String note)
    {
        this.note = note == null ? null : note.trim();
    }
    
    public String getVulInfos()
    {
        return vulInfos;
    }
    
    public void setVulInfos(String vulInfos)
    {
        this.vulInfos = vulInfos == null ? null : vulInfos.trim();
    }
    
    /**
     * 重写toString
     * @return string
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("NvdPageInfoBean [cveId=");
        builder.append(cveId);
        builder.append(", procStatus=");
        builder.append(procStatus);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", lastUpdateTime=");
        builder.append(lastUpdateTime);
        builder.append(", note=");
        builder.append(note);
        builder.append(", vulInfos=");
        builder.append(vulInfos);
        builder.append(']');
        return builder.toString();
    }
    
}
