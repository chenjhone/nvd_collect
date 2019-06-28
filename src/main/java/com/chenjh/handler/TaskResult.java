/*
 * 
 *  @(#)TaskResult.java Created on 2016年10月9日
 *
 * Copyright 2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 
 * Description 
 * 
 * CopyrightVersion 
 *
 */
package com.chenjh.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年10月9日
 * @since
 */
public class TaskResult
{
    private Boolean result = Boolean.FALSE;
    
    private Integer taskStatus;
    
    private Integer downloadNum;
    
    private Integer insertNum;
    
    private Integer updateNum;
            
    private Map<String, Object> resultData = new HashMap<String, Object>();
    
    public Boolean getResult()
    {
        return result;
    }
    
    public void setResult(Boolean result)
    {
        this.result = result;
    }
    
    public Map<String, Object> getResultData()
    {
        return resultData;
    }
    
    public void setResultData(Map<String, Object> resultData)
    {
        this.resultData = resultData;
    }

    public Integer getTaskStatus()
    {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus)
    {
        this.taskStatus = taskStatus;
    }

    public Integer getDownloadNum()
    {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum)
    {
        this.downloadNum = downloadNum;
    }

    public Integer getInsertNum()
    {
        return insertNum;
    }

    public void setInsertNum(Integer insertNum)
    {
        this.insertNum = insertNum;
    }

    public Integer getUpdateNum()
    {
        return updateNum;
    }

    public void setUpdateNum(Integer updateNum)
    {
        this.updateNum = updateNum;
    }
    
}
