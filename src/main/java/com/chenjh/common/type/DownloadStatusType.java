package com.chenjh.common.type;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月23日
 * @since
 */
public enum DownloadStatusType
{
    /**
     * 初始化
     */
    INIT(0),
    
    /**
     * 下载成功
     */
    DOWNLOAD_SUCCESS(1),
    
    /**
     * 下载失败
     */
    DOWNLOAD_FAIL(2),
    
    /**
     * 处理成功
     */
    PROC_SUCCESS(3),
    
    /**
     * 处理失败
     */
    PROC_FAIL(4),
    
    /**
     * 重复SAID
     */
    PROC_REPTITION(5);
    
    private int value;
    
    private DownloadStatusType(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return this.value;
    }
}
