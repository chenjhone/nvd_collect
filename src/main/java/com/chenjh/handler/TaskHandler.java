package com.chenjh.handler;

import com.chenjh.exception.CheckedException;


/**
 * 处理器
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月20日
 * @since
 */
public interface TaskHandler
{
    
    /**
     * 初始化
     * 
     
     * @date 2016年9月20日
     */
    public void init();
    
    /**
     * 处理
     * @param context context
     * @return result
     * @throws CheckedException CheckedException
     
     * @date 2016年9月20日
     */
    public TaskResult handleTask(TaskContext context) throws CheckedException;
    
    /**
     * 销毁
     * 
     
     * @date 2016年9月20日
     */
    public void destory();
    
}
