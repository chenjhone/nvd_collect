package com.chenjh.controller;

import com.chenjh.exception.CheckedException;
import com.chenjh.service.CrawlTaskService;
import org.apache.log4j.Logger;

public class CrawlTaskRun
{
    private static final Logger LOG = Logger.getLogger(CrawlTaskRun.class);

    /**
     * nvd 抓取任务
     */
    private CrawlController nvdCrawl;

    /**
     * crawlTaskService
     */
    private CrawlTaskService crawlTaskService;

    public CrawlController getNvdCrawl()
    {
        return nvdCrawl;
    }

    public void setNvdCrawl(CrawlController nvdCrawl)
    {
        this.nvdCrawl = nvdCrawl;
    }

    public CrawlTaskService getCrawlTaskService()
    {
        return crawlTaskService;
    }

    public void setCrawlTaskService(CrawlTaskService crawlTaskService)
    {
        this.crawlTaskService = crawlTaskService;
    }

    public void run()
    {
        LOG.info("run nvd crawler start. ");

        //执行任务
        try
        {
            nvdCrawl.start();
        }
        catch (CheckedException e)
        {
            e.printStackTrace();
            LOG.info("exception during nvd info process .");
        }

        //更新下载成功时间
        //crawlTaskService.updateCrawlEndTime();
        LOG.info("run nvd crawler end. ");
    }

}
