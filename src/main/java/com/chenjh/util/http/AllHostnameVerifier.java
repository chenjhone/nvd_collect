package com.chenjh.util.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * MyHostnameVerifier
 * @auth chenjh
 */
public class AllHostnameVerifier implements HostnameVerifier
{
    /**
     * 判断host
     * 
     * @param hostname hostname
     * @param session session
     * @return boolean
     */
    @Override
    public boolean verify(String hostname, SSLSession session)
    {
        return true;
    }
}
