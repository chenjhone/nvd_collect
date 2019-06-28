/*
 * 
 *  @(#)ProcStatusType.java Created on 2016年9月23日
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
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月23日
 * @since
 */
public enum ProcStatusType
{
    /**
     * 初始化
     */
    INIT(0),
    
    /**
     * 处理成功
     */
    SUCCESS(1),
    
    /**
     * 处理失败
     */
    FAIL(2);
    
    private int value;
    
    private ProcStatusType(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return this.value;
    }
}
