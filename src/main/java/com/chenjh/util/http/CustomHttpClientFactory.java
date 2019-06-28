package com.chenjh.util.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author chenjh
 * @version V1.0
 * @date 2018-12-27
 */
public class CustomHttpClientFactory implements HttpClientFactory
{
    /**
     * 超时
     */
    private int timeout;

    /**
     * enableSSL
     */
    private boolean enableSSL;

    /**
     * 代理
     */
    private HttpProxy httpProxy;


    /**
     * buildHttpClient
     * @return httpClient
     * @author chenjh
     */
    @Override
    public CloseableHttpClient buildHttpClient() 
    {
        return buildHttpClientByConf();
    }

    private CloseableHttpClient buildHttpClientByConf() 
    {
        CustomHttpClientBuilder clientBuilder = CustomHttpClientBuilder.create();
        if (this.timeout > 0)
        {
            clientBuilder.setTimeout(timeout);
        }
        if (this.httpProxy != null)
        {
            clientBuilder.setProxy(httpProxy);
        }
        if (this.enableSSL)
        {        
            try {
				      clientBuilder.enableSSL();
            } catch (KeyManagementException e) {
              e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
              e.printStackTrace();
            }         
        }

        return clientBuilder.build();
    }

    public int getTimeout()
    {
        return timeout;
    }

    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }

    public boolean isEnableSSL()
    {
        return enableSSL;
    }

    public void setEnableSSL(boolean enableSSL)
    {
        this.enableSSL = enableSSL;
    }

    public HttpProxy getHttpProxy()
    {
        return httpProxy;
    }

    public void setHttpProxy(HttpProxy httpProxy)
    {
        this.httpProxy = httpProxy;
    }
}
