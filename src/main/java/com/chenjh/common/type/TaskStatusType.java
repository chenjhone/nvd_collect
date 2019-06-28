package com.chenjh.common.type;

public enum TaskStatusType
{
    /**
     * 初始化
     */
    INIT(0, "init"),
    
    /**
     * 成功
     */
    SUCCESS(1, "success"),
    
    /**
     * 失败
     */
    FAIL(2, "fail");
    
    private int value;
    
    private String code;
    
    private TaskStatusType(int value, String code)
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
}
