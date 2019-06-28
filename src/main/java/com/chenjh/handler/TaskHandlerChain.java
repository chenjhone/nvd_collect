/*
 * 
 *  @(#)TaskHandlerChain.java Created on 2016年9月20日
 *
 * Copyright 2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 
 * Description 
 * 
 * CopyrightVersion 
 *
 */
package com.chenjh.handler;


/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月20日
 * @since
 */
public class TaskHandlerChain
{
    /**
     * 上一个处理器
     */
    private TaskHandler prevHandler;
    
    /**
     * 当前处理器
     */
    private TaskHandler processHandler;
    
    /**
     * 下一个处理器
     */
    private TaskHandler nextHandler;

    public TaskHandler getPrevHandler()
    {
        return prevHandler;
    }

    public void setPrevHandler(TaskHandler prevHandler)
    {
        this.prevHandler = prevHandler;
    }

    public TaskHandler getProcessHandler()
    {
        return processHandler;
    }

    public void setProcessHandler(TaskHandler processHandler)
    {
        this.processHandler = processHandler;
    }

    public TaskHandler getNextHandler()
    {
        return nextHandler;
    }

    public void setNextHandler(TaskHandler nextHandler)
    {
        this.nextHandler = nextHandler;
    }
    
}
