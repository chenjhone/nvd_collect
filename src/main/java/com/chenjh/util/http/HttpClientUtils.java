package com.chenjh.util.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

/**
 * @description http客户端工具类
 * @author chenjh
 * @date 2018-12-22
 * @version 1.0
 */
public class HttpClientUtils {

     /**
     * logger
     */
    private static final Logger LOG = Logger.getLogger(HttpClientUtils.class);
    
    /**
     * Get
     * @param httpClient httpClient
     * @param urlStr urlStr
     * @return response
     * @author chenjh
     * @throws IOException  IOException
     * @date 2018年12月27日
     */
    public static CloseableHttpResponse sendRequestGet(CloseableHttpClient httpClient, String urlStr) throws IOException
    {
        if (urlStr == null || httpClient == null)
        {
            LOG.error("httpclient or url can not be null ...");
            return null;
        }
        HttpGet httpGet = new HttpGet(urlStr);
        return httpClient.execute(httpGet);
    }
    
    /**
     * redhat接口需要特别设置header(Accept:application/vnd.redhat.solr+json)
     * @param httpClient httpClient
     * @param urlStr urlStr
     * @return response
     * @author chenjh
     * @throws IOException  IOException
     * @date 2018年12月27日
     */
    public static CloseableHttpResponse sendRequestGetForRH(CloseableHttpClient httpClient, String urlStr)
        throws IOException
    {
        if (urlStr == null || httpClient == null)
        {
            LOG.error("httpclient or url can not be null ...");
            return null;
        }

        HttpGet httpGet = new HttpGet(urlStr);
        httpGet.setHeader("Accept", "application/vnd.redhat.solr+json");
        return httpClient.execute(httpGet);
    }
    
        /**
     * POST请求
     * @param httpClient httpClient
     * @param urlStr urlStr
     * @param param param
     * @return response
     * @throws IOException IOException
     * @author chenjh
     * @date 2018年12月27日
     */
    public static CloseableHttpResponse sendRequestPost(CloseableHttpClient httpClient, String urlStr,
        Map<String, String> param) throws IOException
    {
        if (httpClient == null || urlStr == null)
        {
            LOG.error("httpclient or url can not be null ...");
            return null;
        }

        HttpPost httpPost = new HttpPost(urlStr);

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entity : param.entrySet())
        {
            formparams.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpPost.setEntity(entity);
        return httpClient.execute(httpPost);
    }

}
