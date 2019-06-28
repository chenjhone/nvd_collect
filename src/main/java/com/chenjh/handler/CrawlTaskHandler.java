package com.chenjh.handler;

public class CrawlTaskHandler  extends TaskHandlerAdapter {
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
