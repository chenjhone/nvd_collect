package com.chenjh.util.http;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author chenjh
 * @version V1.0
 * @date 2018-12-27
 */
public interface HttpClientFactory
{
    /**
     * buildHttpClient
     * @return httpClient
     * @author chenjh
     */
    CloseableHttpClient buildHttpClient();
}
