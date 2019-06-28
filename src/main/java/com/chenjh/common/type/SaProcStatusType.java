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
public enum SaProcStatusType
{
    /**
     * 初始化
     */
    INIT(0),
    /**
     * 解析中
     */
    PARSING(1),
    /**
     * 解析成功
     */
    SUCCESS(3),

    /**
     * 解析失败
     */
    FAIL(2),

    /**
     * 解析异常
     */
    EXCEPTION(4);

    private int value;

    private SaProcStatusType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
