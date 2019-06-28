package com.chenjh.util.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * AllX509TrustManager
 * @auth chenjh
 */
public class AllX509TrustManager implements X509TrustManager
{
    /**
     * checkClientTrusted
     * 
     * @param arg0 arg0
     * @param arg1 arg1
     * @throws CertificateException CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
    {
    }
    
    /**
     * checkServerTrusted
     * 
     * @param arg0 arg0
     * @param arg1 arg1
     * @throws CertificateException CertificateException
     * 
     */
    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
    {
    }
    
    /**
     * getAcceptedIssuers
     * @return Certificate
     */
    @Override
    public X509Certificate[] getAcceptedIssuers()
    {
        return new X509Certificate[0];
    }
}
