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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 解析html线程V2, 2017-03-28 NVD 页面改版
 * <p>Title:  </p>
 * <p>Description:  解析html线程V2, 2017-03-28 NVD 页面改版</p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2017年2月16日
 * @since
 */
@Component
@Scope("prototype")
public class ParseHtmlInfoThread2 extends Thread
{
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(ParseHtmlInfoThread2.class);

    /**
     * BASE ID
     */
    private static final String NVD_BASE_ID =
        "p_lt_WebPartZone1_zoneCenter_pageplaceholder_p_lt_WebPartZone1_zoneCenter";

    /**
     * nvd内容DIV
     */
    private static final String MAIN_DIV_ID = NVD_BASE_ID + "_VulnerabilityDetail_VulnDetailFormPanel";

    /**
     * CVSS3内容DIV
     */
    private static final String CVSS3_DIV_ID = NVD_BASE_ID + "_VulnerabilityDetail_VulnFormView_Vuln3CvssPanel";

    /**
     * CVSS3 HREF ATTR
     */
    private static final String CVSS3_HREF_ATTR = "a[data-testid=vuln-cvssv3-base-score-link]";

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

    public List<String> getCveList()
    {
        return cveList;
    }

    public void setCveList(List<String> cveList)
    {
        this.cveList = cveList;
    }

    public String getDetailInfoPrefix()
    {
        return detailInfoPrefix;
    }

    public void setDetailInfoPrefix(String detailInfoPrefix)
    {
        this.detailInfoPrefix = detailInfoPrefix;
    }

    /**
     * 处理过程
     */
    @Override
    public void run()
    {
        LOG.info("process cveList start ! ");
        try
        {
            processCvssInfo(cveList);
        }
        catch (RuntimeException e)
        {
            LOG.error("an error occured during process xml ", e);
        }
    }

    /**
     * 遍历处理列表，处理cvss信息
     * @param cveIdList cveIdList
     
     * @date 2017年2月16日
     */
    private void processCvssInfo(List<String> cveIdList)
    {
        if (CollectionUtils.isEmpty(cveIdList))
        {
            return;
        }
        CloseableHttpClient httpClient = null;

        try
        {
            httpClient = httpClientFactory.buildHttpClient();
            for (String cveId : cveIdList)
            {
                String pageUrl = this.detailInfoPrefix + cveId;
                String htmlResult = queryNvdHtmlStr(httpClient, pageUrl);
                processHtmlStr(cveId, htmlResult);
            }
        } finally
        {
            if (httpClient != null)
            {
                try
                {
                    httpClient.close();
                }
                catch (IOException e)
                {
                    LOG.error("httpclient close exception . ");
                }
            }
        }
    }

    /**
     * 处理NVD 页面
     * @param cveId cveId
     * @param htmlResult htmlResult
     * @return 处理状态
     
     * @date 2017年2月15日
     */
    private void processHtmlStr(String cveId, String htmlResult)
    {
        NvdPageInfoBean pageInfoBean = null;
        NvdVulInfoBean vulInfoBean = new NvdVulInfoBean();
        vulInfoBean.setCveId(cveId);
        vulInfoBean.setModifiedTime(DateUtil.getCurrentUtilDate());
        vulInfoBean.setCvssStatus(ProcStatusType.FAIL.getValue());

        if (!StringUtils.isEmpty(htmlResult))
        {
            try
            {
                Document doc = Jsoup.parseBodyFragment(htmlResult);
                Element contentDiv = doc.select("#" + MAIN_DIV_ID).first();

                //保存网页信息
                pageInfoBean = new NvdPageInfoBean();
                pageInfoBean.setCveId(cveId);
                pageInfoBean.setVulInfos(contentDiv.toString());
                pageInfoBean.setLastUpdateTime(DateUtil.getCurrentUtilDate());

                Element cv3Div = contentDiv.select("#" + CVSS3_DIV_ID).first();
                Element cv3Href = cv3Div.select(CVSS3_HREF_ATTR).first();

                String cvssScoreStr =
                    org.apache.commons.lang.StringUtils.substringBeforeLast(cv3Href.text().trim(), " ");
                BigDecimal cvssScore = new BigDecimal(cvssScoreStr);
                String hrefStr = cv3Href.attr("href");
                String cvssVector = hrefStr.substring(hrefStr.indexOf("&vector=")).replaceAll("&vector=", "").trim();
                //更新cvss
                if (cvssScore != null && !StringUtils.isEmpty(cvssVector))
                {
                    LOG.info("CVSS3.0 info (score : " + cvssScore + "; vecotr : " + cvssVector + "), cveId = " + cveId);

                    vulInfoBean.setCvssVer("3.0");
                    vulInfoBean.setCvssScore(cvssScore);
                    vulInfoBean.setCvssVector(cvssVector);
                    vulInfoBean.setProcStatus(ProcStatusType.INIT.getValue());
                    vulInfoBean.setCvssStatus(ProcStatusType.SUCCESS.getValue());
                }
                else
                {
                    LOG.info("CVSS3.0 info empty, cveId = " + cveId);
                    vulInfoBean.setCvssStatus(ProcStatusType.FAIL.getValue());
                    vulInfoBean.setNote("cvss empty");
                }

            }
            catch (Exception e)
            {
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
     * @param httpClient httpClient
     * @param url url
     * @return html 字符串
     
     * @date 2017年2月15日
     */
    private String queryNvdHtmlStr(CloseableHttpClient httpClient, String url)
    {
        String result = null;
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(url);

        try
        {
            response = httpClient.execute(httpGet);
            if (response != null) {
                if ( response.getStatusLine().getStatusCode() == VulnConstant.HTTP_OK) {
                    result = EntityUtils.toString(response.getEntity());
                }
            }
            return result;
        }
        catch (ClientProtocolException e)
        {
            LOG.error("ClientProtocolException occured during httpget : url = " + url);
            return null;
        }
        catch (IOException e)
        {
            LOG.error("IOException occured during httpget : url = " + url);
            return null;
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    response.close();
                }
                catch (IOException e)
                {
                    LOG.error("IOException occured during response closing . ");
                }
            }
        }
    }
}
