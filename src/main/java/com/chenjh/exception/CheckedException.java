/*
 * 
 *  @(#)CheckedException.java Created on 2016年9月20日
 *
 * Copyright 2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 
 * Description 
 * 
 * CopyrightVersion 
 *
 */
package com.chenjh.exception;

/**
 * CheckedException
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月20日
 * @since
 */
public class CheckedException extends Exception
{
    private static final long serialVersionUID = 5834490959201613783L;
    
    /**
     * 构造函数 初始化code 消息及捕获的异常信息
     * @param message 详细信息
     * @param cause 异常信息
     */
    public CheckedException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     * 构造函数 初始化code 消息及捕获的异常信息
     * @param message message
     */
    public CheckedException(String message)
    {
        super(message);
    }
    
    /**
     * 实现 {@link #toString()} 方法打印错误编码code信息
     * @return 获取异常消息
     */
    @Override
    public String getMessage()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(super.getMessage());
        return sb.toString();
    }
    
}
