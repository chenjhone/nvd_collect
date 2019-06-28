/*
 *
 *  @(#)ParseHtmlInfoThread.java Created on 2017年2月16日
 *
 * Copyright 2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 *
 * Description
 *
 * CopyrightVersion
 *
 */
package com.chenjh.handler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.chenjh.common.VulnConstant;
import com.chenjh.common.type.ProcStatusType;
import com.chenjh.domain.nvd.NvdPageInfoBean;
import com.chenjh.domain.nvd.NvdVulInfoBean;
import com.chenjh.service.NvdVulInfoService;
import com.chenjh.util.DateUtil;
import com.chenjh.util.http.HttpClientFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;



/**
 * 解析html线程
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 *
 
 * @version V1.0 2017年2月16日
 * @since
 */
@Component
@Scope("prototype")
public class ParseHtmlInfoThread extends Thread {
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(ParseHtmlInfoThread.class);

    /**
     * httpclientFactory
     */
    @Autowired
    private HttpClientFactory httpClientFactory;

    /**
     * nvd 漏洞服务
     */
    @Autowired
    private NvdVulInfoService nvdVulInfoService;

    /**
     * 待处理列表
     */
    private List<String> cveList = new ArrayList<String>();

    /**
     * nvd详情页地址
     */
    private String detailInfoPrefix;

    public List<String> getCveList() {
        return cveList;
    }

    public void setCveList(List<String> cveList) {
        this.cveList = cveList;
    }

    public String getDetailInfoPrefix() {
        return detailInfoPrefix;
    }

    public void setDetailInfoPrefix(String detailInfoPrefix) {
        this.detailInfoPrefix = detailInfoPrefix;
    }

    /**
     * 处理过程
     */
    @Override
    public void run() {
        LOG.info("process cveList start ! ");
        try {
            processCvssInfo(cveList);
        } catch (RuntimeException e) {
            LOG.error("an error occured during process xml ", e);
        }
    }

    /**
     * 遍历处理列表，处理cvss信息
     *
     * @param cveIdList cveIdList
     
     * @date 2017年2月16日
     */
    private void processCvssInfo(List<String> cveIdList) {
        if (CollectionUtils.isEmpty(cveIdList)) {
            return;
        }
        CloseableHttpClient httpClient = null;

        try {
            httpClient = httpClientFactory.buildHttpClient();
            for (String cveId : cveIdList) {
                String pageUrl = this.detailInfoPrefix + cveId;
                String htmlResult = queryNvdHtmlStr(httpClient, pageUrl);
                processHtmlStr(cveId, htmlResult);
            }
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LOG.error("httpclient close exception . ");
                }
            }
        }
    }

    /**
     * 处理NVD 页面
     *
     * @param cveId      cveId
     * @param htmlResult htmlResult
     * @return 处理状态
     
     * @date 2017年2月15日
     */
    private void processHtmlStr(String cveId, String htmlResult) {
        NvdPageInfoBean pageInfoBean = null;
        NvdVulInfoBean vulInfoBean = new NvdVulInfoBean();
        vulInfoBean.setCveId(cveId);
        vulInfoBean.setModifiedTime(DateUtil.getCurrentUtilDate());
        vulInfoBean.setCvssStatus(ProcStatusType.FAIL.getValue());

        if (!StringUtils.isEmpty(htmlResult)) {
            try {
                Document doc = Jsoup.parseBodyFragment(htmlResult);
                Element contentDiv = doc.select(".vuln-detail").first();

                //保存网页信息
                pageInfoBean = new NvdPageInfoBean();
                pageInfoBean.setCveId(cveId);
                pageInfoBean.setVulInfos(contentDiv.toString());
                pageInfoBean.setLastUpdateTime(DateUtil.getCurrentUtilDate());

                Element cv3Div = contentDiv.select(
                        "#BodyPlaceHolder_cplPageContent_"
                                + "plcZones_lt_zoneCenter_VulnerabilityDetail_VulnFormView_Vuln3CvssPanel")
                        .first();
                Elements hrefs = cv3Div.select("a");
                Element scoreElement = hrefs.get(0);
                Element vectorElement = hrefs.get(1);

                String cvssScoreStr = scoreElement.text().trim();
                BigDecimal cvssScore = new BigDecimal(cvssScoreStr);
                String regexCvss="CVSS:3.0/";
                String cvssVector = vectorElement.text().trim().replaceAll(regexCvss, "");
                //更新cvss
                if (cvssScore != null && !StringUtils.isEmpty(cvssVector)) {
                    LOG.info("CVSS3.0 info (score : " + cvssScore + "; vecotr : " + cvssVector + "), cveId = " + cveId);

                    vulInfoBean.setCvssVer("3.0");
                    vulInfoBean.setCvssScore(cvssScore);
                    vulInfoBean.setCvssVector(cvssVector);
                    vulInfoBean.setProcStatus(ProcStatusType.INIT.getValue());
                    vulInfoBean.setCvssStatus(ProcStatusType.SUCCESS.getValue());
                } else {
                    LOG.info("CVSS3.0 info empty, cveId = " + cveId);
                    vulInfoBean.setCvssStatus(ProcStatusType.FAIL.getValue());
                    vulInfoBean.setNote("cvss empty");
                }

            } catch (Exception e) {
                LOG.error("get cvss3.0 info fail . cveId = " + cveId);
                vulInfoBean.setCvssStatus(ProcStatusType.FAIL.getValue());
                vulInfoBean.setNote(e.getMessage());
                pageInfoBean = null;
            }
        }
        nvdVulInfoService.processVulCvssInfo(vulInfoBean, pageInfoBean);
    }

    /**
     * 查询NVD html
     *
     * @param httpClient httpClient
     * @param url        url
     * @return html 字符串
     
     * @date 2017年2月15日
     */
    private String queryNvdHtmlStr(CloseableHttpClient httpClient, String url) {
        String result = null;
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(url);

        try {
            response = httpClient.execute(httpGet);
            if (response != null) {
                if (response.getStatusLine().getStatusCode() == VulnConstant.HTTP_OK) {
                    result = EntityUtils.toString(response.getEntity());
                }
            }
            return result;
        } catch (ClientProtocolException e) {
            LOG.error("ClientProtocolException occured during httpget : url = " + url);
            return null;
        } catch (IOException e) {
            LOG.error("IOException occured during httpget : url = " + url);
            return null;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOG.error("IOException occured during response closing . ");
                }
            }
        }
    }

}
