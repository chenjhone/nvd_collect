package com.chenjh.handler.nvd;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.chenjh.common.type.DownloadStatusType;
import com.chenjh.domain.nvd.NdFilterMetaBean;
import com.chenjh.handler.TaskContext;
import com.chenjh.handler.TaskResult;
import com.chenjh.mapper.NdFilterMetaBeanMapper;
import com.chenjh.util.DateUtil;
import com.chenjh.util.FileUtil;
import com.chenjh.util.http.HttpClientFactory;
import com.chenjh.util.http.HttpClientUtils;
import com.chenjh.exception.CheckedException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.chenjh.domain.nvd.NdDownloadFileBean;
import com.chenjh.common.VulnConstant;
import com.chenjh.common.type.TaskStatusType;
import com.chenjh.mapper.NdDownloadFileBeanMapper;
import org.springframework.util.StringUtils;


public class DownloadFileHandler extends NVDTaskHandler
{
    /**
     * logger
     */
    private static final Logger LOG = Logger.getLogger(DownloadFileHandler.class);

    /**
     * httpclientFactory
     */
    private HttpClientFactory httpClientFactory;

    /**
     * 判断地址
     */
    private String metaUrl;

    /**
     * 目标地址
     */
    private String destUrl;

    /**
     * Modifyed
     */
    private String modifyUrl;

    /**
     * 全量开始时间
     */
    private String beginYear;

    /**
     * 目标url集合
     */
    private List<String> feedList;

    /**
     * 文件保存地址
     */
    private String rootPath;

    @Autowired
    private NdDownloadFileBeanMapper ndDownloadFileBeanMapper;

    @Autowired
    private NdFilterMetaBeanMapper ndFilterMetaBeanMapper;

    /**
     * 下载文件
     * @param context context
     * @return result
     * @throws CheckedException CheckedException
     */
    @Override
    public TaskResult handleTask(TaskContext context) throws CheckedException
    {
        TaskResult taskResult = new TaskResult();
        LOG.info("download file handler start ..");

        if (context.isAllDataTask())
        {
            this.feedList = DateUtil.getYearListAfter(this.beginYear);
        }
        else
        {
            this.feedList = new ArrayList<String>();
            this.feedList.add(this.modifyUrl);
        }

        if (CollectionUtils.isEmpty(this.feedList))
        {
            LOG.error("destUrlList is empty ... ");

            taskResult.setResult(false);
            taskResult.setTaskStatus(TaskStatusType.FAIL.getValue());
            return taskResult;
        }

        //获取httpClient
        CloseableHttpClient httpClient = null;
        try
        {
            httpClient = httpClientFactory.buildHttpClient();
            //下载文件
            for (String feed : feedList)
            {

                LOG.info("download zip file begin , the feed is " + feed);
                //判断是否已经下载成功
                NdDownloadFileBean queryBean = new NdDownloadFileBean();
                queryBean.setCrawlId(context.getCrawlTaskId());
                queryBean.setFeed(feed);
                List<NdDownloadFileBean> zipFileList = ndDownloadFileBeanMapper.queryZipFileList(queryBean);
                if (!CollectionUtils.isEmpty(zipFileList))
                {
                    NdDownloadFileBean downloadFileBean = zipFileList.get(0);
                    int downStatus = downloadFileBean.getStatus();
                    if (downStatus == DownloadStatusType.DOWNLOAD_SUCCESS.getValue()
                            || downStatus == DownloadStatusType.PROC_SUCCESS.getValue())
                    {
                        continue;
                    }
                    else if (downStatus == DownloadStatusType.PROC_FAIL.getValue())
                    {
                        //更新处理状态
                        downloadFileBean.setStatus(DownloadStatusType.DOWNLOAD_SUCCESS.getValue());
                        ndDownloadFileBeanMapper.updateByPrimaryKeySelective(downloadFileBean);

                        continue;
                    }
                    else
                    {
                        doHandleResponse(httpClient, feed, context, downloadFileBean);
                    }
                }
                else
                {
                    doHandleResponse(httpClient, feed, context, null);
                }

                LOG.info("download zip file end , the feed is " + feed);
            }

            taskResult.setResult(true);
            taskResult.setTaskStatus(TaskStatusType.SUCCESS.getValue());
            return taskResult;
        }
        catch (CheckedException e)
        {
            LOG.error("an exception occured during build httpclient ", e);

            taskResult.setResult(false);
            taskResult.setTaskStatus(TaskStatusType.FAIL.getValue());
            return taskResult;
        }
        finally
        {
            if (httpClient != null)
            {
                try
                {
                    httpClient.close();
                }
                catch (IOException e)
                {
                    LOG.error("IOException occured during closing httpclient", e);
                }
            }
        }
    }

    /**
     * doHandleResponse
     * @param client client
     * @param context context
     * @param downBeanDB downBeanDB
     * @throws CheckedException
     
     * @date 2016年9月22日
     */
    private void doHandleResponse(CloseableHttpClient client, String feed, TaskContext context,
            NdDownloadFileBean downBeanDB) throws CheckedException
    {
        CloseableHttpResponse response = null;
        OutputStream os = null;
        String fileName = null;
        String fileOrgName = null;
        String fullPath = null;
        try
        {
            //获取元数据信息
            String urlStrMeta = this.metaUrl.replace("{0}", feed);
            response = HttpClientUtils.sendRequestGet(client, urlStrMeta);
            //GET请求得到信息报文，以换行符合分割转换成数组格式
            String textMeta = EntityUtils.toString(response.getEntity());
            String [] metaArr= textMeta.split("\n");
            NdFilterMetaBean metaBean = new NdFilterMetaBean();
            Map<String,String> map = new HashMap<String,String>();
            //把数组格式转换为map格式
            if (!StringUtils.isEmpty(metaArr)) {
                for (String strMeta : metaArr) {
                    int indexOf=strMeta.indexOf(":");
                    String metaName=strMeta.substring(0, indexOf).trim();
                    String metaVaule=strMeta.substring(indexOf+1, strMeta.length()).trim();
                    map.put(metaName,metaVaule);
                }
            }
            //把map格式转换为json格式
            String metaJson = JSONObject.toJSONString(map);
            //把json格式转换换bean实体类
            metaBean= JSONObject.parseObject(metaJson,NdFilterMetaBean.class);
            metaBean.setFeed(feed);
            //根据feed和sha值判断是否存在过，存在则直接返回，不存在做新增处理
            NdFilterMetaBean ndFilterMeta = ndFilterMetaBeanMapper.findNdFilterMeta(metaBean.getFeed(), metaBean.getShaDense());
            if (StringUtils.isEmpty(ndFilterMeta)){
//                int filterMeta = ndFilterMetaBeanMapper.updateNdFilterMeta(metaBean);
//                if (filterMeta==0){
                ndFilterMetaBeanMapper.saveNdFilterMeta(metaBean);
//                }
            }else {
                return;
            }
            String urlStr = this.destUrl.replace("{0}", feed);
            LOG.info("the url is -------" + urlStr);
            response = HttpClientUtils.sendRequestGet(client, urlStr);
            if(response != null) {
                if (response.getStatusLine().getStatusCode() == VulnConstant.HTTP_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    if (httpEntity != null) {
                        fileOrgName = urlStr.substring(urlStr.lastIndexOf(VulnConstant.URL_SPLIT) + 1);
                        fileName = context.getCrawlTaskId() + VulnConstant.FILE_NAME_SPLIT + fileOrgName;
                        String relativelyPath = FileUtil.getRelativelyPath(System.currentTimeMillis());
                        fullPath = FileUtil.getFullPath(this.rootPath, relativelyPath);
                        File pathFile = new File(fullPath);
                        //创建目录
                        if (!pathFile.exists()) {
                            pathFile.mkdirs();
                        }
                        String fullFile = FileUtil.getFileFullPath(this.rootPath, relativelyPath, fileName);
                        File targetFile = new File(fullFile);
                        os = new BufferedOutputStream(new FileOutputStream(targetFile));
                        httpEntity.writeTo(os);
                        os.flush();

                        LOG.info("download file success . ");
                        //记录刷新数据库
                        NdDownloadFileBean downBean = new NdDownloadFileBean();
                        downBean.setCrawlId(context.getCrawlTaskId());
                        downBean.setFileName(fileName);
                        downBean.setFileOrgName(fileOrgName);
                        downBean.setFeed(feed);
                        downBean.setFilePath(fullPath);
                        downBean.setFileSize(targetFile.length());
                        downBean.setStatus(DownloadStatusType.DOWNLOAD_SUCCESS.getValue());
                        if (downBeanDB == null) {
                            ndDownloadFileBeanMapper.insert(downBean);
                        } else {
                            downBean.setFileId(downBeanDB.getFileId());
                            downBean.setModifiedTime(DateUtil.getCurrentUtilDate());
                            ndDownloadFileBeanMapper.updateByPrimaryKeySelective(downBean);
                        }
                    }
                }
            }
        }
        catch (ClientProtocolException e)
        {
            LOG.error("ClientProtocolException occured during sending request get ", e);
            throw new CheckedException("client protocol exception occured during sending request get", e);
        }
        catch (FileNotFoundException e)
        {
            LOG.error("FileNotFoundException occured during downloading", e);
            throw new CheckedException("FileNotFoundException occured during downloading", e);
        }
        catch (IOException e)
        {
            LOG.error("IOException occured during downloading ", e);
            throw new CheckedException("IOException occured during downloading", e);
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e)
                {
                    LOG.error("outputStream close error.");
                }
            }

            if (response != null)
            {
                try
                {
                    response.close();
                }
                catch (IOException e)
                {
                    LOG.error("response close error.");
                }
            }

        }
    }



    public HttpClientFactory getHttpClientFactory()
    {
        return httpClientFactory;
    }

    public void setHttpClientFactory(HttpClientFactory httpClientFactory)
    {
        this.httpClientFactory = httpClientFactory;
    }

    public List<String> getFeedList()
    {
        return feedList;
    }

    public void setFeedList(List<String> feedList)
    {
        this.feedList = feedList;
    }

    public String getRootPath()
    {
        return rootPath;
    }

    public void setRootPath(String rootPath)
    {
        this.rootPath = rootPath;
    }

    public String getMetaUrl() {
        return metaUrl;
    }

    public void setMetaUrl(String metaUrl) {
        this.metaUrl = metaUrl;
    }

    public String getDestUrl()
    {
        return destUrl;
    }

    public void setDestUrl(String destUrl)
    {
        this.destUrl = destUrl;
    }

    public String getModifyUrl()
    {
        return modifyUrl;
    }

    public void setModifyUrl(String modifyUrl)
    {
        this.modifyUrl = modifyUrl;
    }

    public String getBeginYear()
    {
        return beginYear;
    }

    public void setBeginYear(String beginYear)
    {
        this.beginYear = beginYear;
    }

}
