package com.chenjh.util.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author chenjh
 * @version V1.0
 * @since 2018年12月27日
 */
public class CustomHttpClientBuilder
{
    
    /**
     * httpClientBuilder
     */
    private HttpClientBuilder builder;
    
    /**
     * 构造器
     */
    public CustomHttpClientBuilder()
    {
        this.builder = HttpClientBuilder.create();
    }
    
    /**
     * 创建customClientBuilder
     * @return customClientBuilder
     * @author chenjh
     */
    public static CustomHttpClientBuilder create()
    {
        return new CustomHttpClientBuilder();
    }
    
    /**
     * setTimeout
     * @param timeout timeout
     * @author chenjh
     */
    public void setTimeout(int timeout)
    {
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        
        this.builder.setDefaultRequestConfig(config);
    }
    
    /**
     * setProxy NTCredentials
     * @param httpProxy httpProxy
     * @author chenjh
     */
    public void setProxy(HttpProxy httpProxy)
    {
        if (null != httpProxy)
        {
            HttpHost proxyHost = new HttpHost(httpProxy.getHost(), httpProxy.getPort());
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            if (null != httpProxy.getUser() && null != httpProxy.getPassword() && httpProxy.getDomain() != null)
            {
                Credentials creds = new NTCredentials(httpProxy.getUser(), httpProxy.getPassword(), null,
                        httpProxy.getDomain());
                credentialsProvider.setCredentials(AuthScope.ANY, creds);
                
                this.builder.setDefaultCredentialsProvider(credentialsProvider);
            }
            this.builder.setProxy(proxyHost);
        }
    }
    
    /**
     * setCredentialsProvider
     * @param credsProvider credsProvider
     * @author chenjh
     */
    public void setCredentialsProvider(CredentialsProvider credsProvider)
    {
        this.builder.setDefaultCredentialsProvider(credsProvider);
    }
    
    /**
     * enableSSL
     * @author chenjh
     * @throws NoSuchAlgorithmException  NoSuchAlgorithmException
     * @throws KeyManagementException KeyManagementException
     */
    public void enableSSL() throws NoSuchAlgorithmException, KeyManagementException
    {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        X509TrustManager[] xtmArray = new X509TrustManager[] {new AllX509TrustManager()};
        sslcontext.init(null, xtmArray, new SecureRandom());
        
        // Allow TLSv1 TLSv1.1 TLSv1.2 protocol
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] {"TLSv1", "TLSv1.2",
            "TLSv1.1"}, null, new AllHostnameVerifier());
        
        this.builder.setSSLSocketFactory(sslsf);
    }
    
    /**
     * build
     * @return CloseableHttpClient
     * @author chenjh
     */
    public CloseableHttpClient build()
    {
        return this.builder.build();
    }
    
    public HttpClientBuilder getBuilder()
    {
        return builder;
    }
    
    public void setBuilder(HttpClientBuilder builder)
    {
        this.builder = builder;
    }
    
}
