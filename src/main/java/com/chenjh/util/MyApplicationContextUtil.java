package com.chenjh.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月29日
 * @since
 */
public class MyApplicationContextUtil implements ApplicationContextAware
{
    //声明一个静态变量保存
    private static ApplicationContext context;



    /**
     * setApplicationUtil
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        if (MyApplicationContextUtil.context==null){
            MyApplicationContextUtil.context = applicationContext;
        }
    }
    
    public static ApplicationContext getContext()
    {
        return context;
    }

    
    /**
     * getBean
     * @param beanName beanName
     * @return object
     
     * @date 2016年9月29日
     */
    public static final Object getBean(String beanName)
    {
        return context.getBean(beanName);
    }
    
    /**
     * getBean
     * @param beanName beanName
     * @param requiredType requiredType
     * @return bean
     
     * @date 2016年9月29日
     */
    public static final Object getBean(String beanName, Class<?> requiredType)
    {
        return context.getBean(beanName, requiredType);
    }
    
}
