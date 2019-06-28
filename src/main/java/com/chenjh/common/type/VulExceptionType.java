package com.chenjh.common.type;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年12月27日
 * @since
 */
public enum VulExceptionType
{
    /**
     * 多个供应商
     */
    VENDORS(0),
    
    /**
     * 软件信息错误
     */
    SOFT_ERROE(1),
    
    /**
     * 软件为空
     */
    SOFT_EMPTY(2),
    
    /**
     * 漏洞信息错误
     */
    INFO_ERROR(3),
    
    /**
     * 正常
     */
    NORMAL(9);
    
    private int value;
    
    private VulExceptionType(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return this.value;
    }
}
