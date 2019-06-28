package com.chenjh.domain.nvd;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月26日
 * @since
 */
public class NvdExtRefBean
{
    private Long ndExtRefId;
    
    private String cveId;
    
    private String refName;
    
    private String refType;
    
    private String refSource;
    
    private String refUrl;
    
    private String note;

    public Long getNdExtRefId()
    {
        return ndExtRefId;
    }

    public void setNdExtRefId(Long ndExtRefId)
    {
        this.ndExtRefId = ndExtRefId;
    }

    public String getCveId()
    {
        return cveId;
    }

    public void setCveId(String cveId)
    {
        this.cveId = cveId;
    }

    public String getRefName()
    {
        return refName;
    }

    public void setRefName(String refName)
    {
        this.refName = refName;
    }

    public String getRefType()
    {
        return refType;
    }

    public void setRefType(String refType)
    {
        this.refType = refType;
    }

    public String getRefSource()
    {
        return refSource;
    }

    public void setRefSource(String refSource)
    {
        this.refSource = refSource;
    }

    public String getRefUrl()
    {
        return refUrl;
    }

    public void setRefUrl(String refUrl)
    {
        this.refUrl = refUrl;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    /**
     * toString
     * @return string
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("NvdExtRefBean [ndExtRefId=");
        builder.append(ndExtRefId);
        builder.append(", cveId=");
        builder.append(cveId);
        builder.append(", refName=");
        builder.append(refName);
        builder.append(", refType=");
        builder.append(refType);
        builder.append(", refSource=");
        builder.append(refSource);
        builder.append(", refUrl=");
        builder.append(refUrl);
        builder.append(", note=");
        builder.append(note);
        builder.append(']');
        return builder.toString();
    }
    
}
