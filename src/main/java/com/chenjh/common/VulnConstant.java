/*
 * 
 *  @(#)VulnConstant.java Created on 2016年9月22日
 *
 * Copyright 2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 
 * Description 
 * 
 * CopyrightVersion 
 *
 */

package com.chenjh.common;

import java.math.BigDecimal;

/**
 * 常量类
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月22日
 * @since
 */
public class VulnConstant
{

    public static final Integer INTEGER_1024 = 1024;

    /**
     * HTTP OK
     */
    public static final int HTTP_OK = 200;

    /**
     * 404
     */
    public static final int HTTP_NOT_FOUND = 404;

    /**
     * URL SPLIT
     */
    public static final String URL_SPLIT = "/";

    /**
     * FILE_NAME_SPLIT
     */
    public static final String FILE_NAME_SPLIT = "_";

    /**
     * XML 后缀
     */
    public static final String XML_SUFFIX = "xml";

    /**
     * PRODUCT_NAME_SPLIT
     */
    public static final String PRODUCT_NAME_SPLIT = "_";

    /**
     * ONE_SPACE
     */
    public static final String ONE_SPACE = " ";

    /**
     * colon
     */
    public static final String COLON = ":";

    /**
     * CVSS为空
     */
    public static final String CVSS_VER_NULL = "0";

    /**
     * CVSS2.0
     */
    public static final String CVSS_VER2 = "2.0";

    /**
     * CVSS3.0
     */
    public static final String CVSS_VER3 = "3.0";

    /**
     * CVSS为空
     */
    public static final BigDecimal CVSS_SCORE_NULL = BigDecimal.ZERO;

    /**
     * CVSS为空
     */
    public static final String CVSS_VECTOR_NULL = "0";

    /**
     * signature
     */
    public static final String REF_SIG = "Signature";

    /**
     * Advisory
     */
    public static final String REF_ADV = "Advisory";

    /**
     * patch
     */
    public static final String REF_PATCH = "Patch";

    /**
     * comma
     */
    public static final String COMMA = ",";

    /**
     * 日期类型为漏洞发布日期
     */
    public static final String DATE_TYPE_PUBLISHED = "vulndb_published_date";

    /**
     * 日期类型为漏洞更新日期
     */
    public static final String DATE_TYPE_LAST_MODIFIED = "vulndb_last_modified";

    /**
     * vulnDB表的产品状态：初始
     */
    public static final Integer VD_VUL_INFO_PROC_STATUS_ZERO = 0;

    /**
     * vulnDB表的产品状态：已处理成功
     */
    public static final Short VD_VUL_INFO_PROC_STATUS_FIRST = 1;

    /**
     *  vulnDB表的产品状态：处理失败
     */
    public static final Short VD_VUL_INFO_PROC_STATUS_SECOND = 2;

    /**
     * 全量一页里的条数
     */
    public static final int SIZE_IN_ONE_PAGE = 100;

    /**
     * DEBIAN
     */
    public static final int DEBIAN = 6;

    /**
     * UBUNTU
     */
    public static final int UBUNTU = 3;

    /**
     * 空数据
     */
    public static final String NULL_DATA = "N/A";

    /**
     * t_sa_info表的SA状态：有效
     */
    public static final Integer SA_INFO_VALID = 1;

    /**
     * t_sa_info表的SA状态：无效
     */
    public static final Integer SA_INFO_INVALID = 1;
}
