package com.chenjh.domain.nvd;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class NdFilterMetaBean {
    @JSONField(name = "feed")
    public String feed;
    @JSONField(name = "lastModifiedDate")
    public Date lastModifiedDate;
    @JSONField(name = "size")
    public Long fileSize;
    @JSONField(name = "zipSize")
    public Long zipSize;
    @JSONField(name = "gzSize")
    public Long gzSize;
    @JSONField(name = "sha256")
    public String shaDense;

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getZipSize() {
        return zipSize;
    }

    public void setZipSize(Long zipSize) {
        this.zipSize = zipSize;
    }

    public Long getGzSize() {
        return gzSize;
    }

    public void setGzSize(Long gzSize) {
        this.gzSize = gzSize;
    }

    public String getShaDense() {
        return shaDense;
    }

    public void setShaDense(String shaDense) {
        this.shaDense = shaDense;
    }
}
