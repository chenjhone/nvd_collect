/*
 * 
 *  @(#)TaskHandlerChainList.java Created on 2016年9月30日
 *
 * 
 * Description 
 * 
 * CopyrightVersion 
 *
 */
package com.chenjh.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月30日
 * @since
 */
public class TaskHandlerChainList
{
    /**
     * chainList
     */
    private List<TaskHandlerChain> chainList = new ArrayList<TaskHandlerChain>();

    /** Get the first processor chain.
     *
     * @return the first processor chain.
     */
    public TaskHandlerChain getFirstChain()
    {
        return chainList.get(0);
    }

    /** Get the last processor chain.
     *
     * @return the last processor chain.
     */
    public TaskHandlerChain getLastChain()
    {
        if (size() == 0)
        {
            return null;
        }
        else
        {
            return chainList.get(size() - 1);
        }
    }

    /** Get the number of processor chains.
     *
     * @return the number of processor chains.
     */
    public int size()
    {
        return chainList.size();
    }

    public List<TaskHandlerChain> getChainList()
    {
        return chainList;
    }

    public void setChainList(List<TaskHandlerChain> chainList)
    {
        this.chainList = chainList;
    }

}
