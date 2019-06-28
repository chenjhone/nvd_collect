/*
 * 
 *  @(#)BooleanStatus.java Created on 2017年6月12日
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
 
 * @version V1.0
 * @since 2017年6月12日
 */
public enum BooleanStatus
{
    /**
     * 有效
     */
    TRUE(1),
    
    /**
     * 无效
     */
    FALSE(0);
    
    private int value;
    
    private BooleanStatus(int val)
    {
        this.value = val;
    }
    
    public int getValue()
    {
        return value;
    }
    
}
