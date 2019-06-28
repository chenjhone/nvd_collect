/*
 * 
 *  @(#)TaskStatusType.java Created on 2016年10月8日
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
 
 * @version V1.0 2016年10月8日
 * @since
 */
public enum SaInfoHandleStatusType
{
    /**
     * 解析成功
     */
    SUCC(0, "Succ"),

    /**
     * SA ID 无法解析
     */
    SAIDEXCEPTION(1, "Sa Id error"),

    /**
     * 描述无法解析
     */
    DESCEXCEPTION(2, "description error"),

    /**
     * 无法解析summary
     */
    SUMMARYEXCEPTION(3, "summary exception"),

    /**
     * 无法解析solution
     */
    SOLUTIONEXCEPTION(4, "solution exception"),

    /**
     * 无法解析references
     */
    REFEXCEPTION(5, "references exception"),

    /**
     * 无法解析references
     */
    NULLEXCEPTION(6, "DSA null"),

    /**
     * 无法解析CVE
     */
    CVEEXCEPTION(7, "CVE exception");

    private int value;

    private String code;

    private SaInfoHandleStatusType(int value, String code)
    {
        this.value = value;
        this.code = code;
    }

    public int getValue()
    {
        return this.value;
    }

    public String getCode()
    {
        return this.code;
    }

    /**
     * getException
     * @param value value
     * @return String
     */
    public static String getException(int value)
    {
        for (SaInfoHandleStatusType c : SaInfoHandleStatusType.values())
        {
            if (c.getValue() == value)
            {
                return c.getCode();
            }
        }
        return null;
    }
}
