
package com.chenjh.common.type;

/**
 *
 * <p>Title: DownloadSourceType </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月23日
 * @since
 */
public enum DownloadSourceType
{

    /**
     * 来源ubuntu
     */
    SOURCE_UBUNTU(3),

    /**
     * 来源suse
     */
    SOURCE_SUSE(1),

    /**
     * 来源cent
     */
    SOURCE_CENT(4),

    /**
     * 来源euler
     */
    SOURCE_EULER(5),

    /**
     * 来源debian
     */
    SOURCE_DEBIAN(6),

    /**
     * 来源RH
     */
    SOURCE_REDHAT(2);

    private Integer value;

    private DownloadSourceType(Integer value)
    {
        this.value = value;
    }

    public Integer getValue()
    {
        return this.value;
    }
}
