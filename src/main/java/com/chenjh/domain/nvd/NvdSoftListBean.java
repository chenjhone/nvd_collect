package com.chenjh.domain.nvd;

/**
 * 
 * <p>Title: NVD漏洞受影响第三方软件列表实体类 </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author hWX392885
 * @version V1.0 2016年9月21日
 * @since
 */
public class NvdSoftListBean
{
    private Long ndSoftId;
    
    private String cveId;
    
    private String vendorName;
    
    private String productName;
    
    private String productVersion;
    
    private String edition;
    
    private String cpeId;
    
    private Integer includePrevious;
    
    private String note;
    
    public Long getNdSoftId()
    {
        return ndSoftId;
    }
    
    public void setNdSoftId(Long ndSoftId)
    {
        this.ndSoftId = ndSoftId;
    }
    
    public String getCveId()
    {
        return cveId;
    }
    
    public void setCveId(String cveId)
    {
        this.cveId = cveId == null ? null : cveId.trim();
    }
    
    public String getVendorName()
    {
        return vendorName;
    }
    
    public void setVendorName(String vendorName)
    {
        this.vendorName = vendorName == null ? null : vendorName.trim();
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName == null ? null : productName.trim();
    }
    
    public String getProductVersion()
    {
        return productVersion;
    }
    
    public void setProductVersion(String productVersion)
    {
        this.productVersion = productVersion == null ? null : productVersion.trim();
    }
    
    public String getEdition()
    {
        return edition;
    }
    
    public void setEdition(String edition)
    {
        this.edition = edition == null ? null : edition.trim();
    }
    
    public String getCpeId()
    {
        return cpeId;
    }
    
    public void setCpeId(String cpeId)
    {
        this.cpeId = cpeId == null ? null : cpeId.trim();
    }
    
    public Integer getIncludePrevious()
    {
        return includePrevious;
    }
    
    public void setIncludePrevious(Integer includePrevious)
    {
        this.includePrevious = includePrevious;
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
     * toString
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("NvdSoftListBean [ndSoftId=");
        builder.append(ndSoftId);
        builder.append(", cveId=");
        builder.append(cveId);
        builder.append(", vendorName=");
        builder.append(vendorName);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", productVersion=");
        builder.append(productVersion);
        builder.append(", edition=");
        builder.append(edition);
        builder.append(", cpeId=");
        builder.append(cpeId);
        builder.append(", includePrevious=");
        builder.append(includePrevious);
        builder.append(", note=");
        builder.append(note);
        builder.append(']');
        return builder.toString();
    }
    
}
