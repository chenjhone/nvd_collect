package com.chenjh.handler.nvd;

import com.chenjh.handler.TaskHandlerAdapter;

/**
 * nvd 任务
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年12月30日
 * @since
 */
public class NVDTaskHandler extends TaskHandlerAdapter
{
    /**
     * taskIdx
     */
    private int taskIdx;
    
    /**
     * taskName
     */
    private String taskName;
    
    public int getTaskIdx()
    {
        return taskIdx;
    }
    
    public void setTaskIdx(int taskIdx)
    {
        this.taskIdx = taskIdx;
    }
    
    public String getTaskName()
    {
        return taskName;
    }
    
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }
    
}
