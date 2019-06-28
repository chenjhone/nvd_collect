
package com.chenjh;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chenjh.controller.CrawlTaskRun;


public class Application
{

    private static final Logger LOG = Logger.getLogger(Application.class);

    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring.xml");

        LOG.info("-------------SPRING STARTED ------------" + ctx);

        CrawlTaskRun taskRun = (CrawlTaskRun)ctx.getBean("taskRun");
        taskRun.run();
    }

}
