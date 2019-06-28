/*
 * 
 *  @(#)HttpMethodType.java Created on 2016年9月18日
 *
 * Copyright 2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 
 * Description 
 * 
 * CopyrightVersion 
 *
 */
package com.chenjh.common.type;

/**
 * httpclient请求方式 post get
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月18日
 * @since
 */
public enum HttpMethodType
{
    /**
     * get
     */
    GET("GET"),
    
    /**
     * post
     */
    POST("POST");
    
    private final String code;
    
    private HttpMethodType(String code)
    {
        this.code = code;
    }
    
    public String getCode()
    {
        return code;
    }
    
}
