
package com.chenjh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * @author chenjh
 * @version V1.0
 * @since 2018年12月27日
 */
public abstract class RegexMatch
{

    /**
     * LOG
     */
    private static final Logger LOG = Logger.getLogger(RegexMatch.class);

    /**
     * 根据正则匹配获取对应字符串
     * @param context context
     * @param pattern pattern
     * @param flag flag
     * @return 匹配的字符串
     */
    public static String readStringByRegex(String context, String pattern, int flag)
    {
        StringBuffer str = new StringBuffer();
        if (pattern != null && context != null)
        {
            Pattern p = Pattern.compile(pattern, Pattern.DOTALL);
            Matcher m = p.matcher(context);
            if (m.find())
            {
                str.append(m.group(flag));
            }
        }
        else
        {
            LOG.info("");
        }
        return str.toString();
    }

    /**
     * 判断字符串和正则是否匹配
     * @param context context
     * @param pattern pattern
     * @return 匹配结果
     */
    public static boolean isMatchRegex(String context, String pattern)
    {
        Pattern p;
        Matcher m;
        if (pattern != null && context != null)
        {
            p = Pattern.compile(pattern, Pattern.DOTALL);
            m = p.matcher(context);
            if (m.find())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据正则匹配获取对应字符串
     * @param context context
     * @param pattern pattern
     * @param flag flag
     * @return 匹配的字符串列表
     */
    /*public static List<String> readListByRegex(String context, String pattern, int flag)
    {
        List<String> list = new ArrayList<String>();

        String[] line = context.split(CentosConstant.SINGLE_ENTER_REGEX);
        for (int i = 0; i < line.length; i++)
        {

            if (pattern != null && line[i] != null)
            {
                // System.out.println(line[i]);
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(line[i]);
                if (m.find())
                {
                    //System.out.println(m.groupCount());
                    if (!list.contains(m.group(flag)))
                    {

                        list.add(line[i]);
                    }
                    m.reset();

                }
            }
            else
            {
                LOG.info("no matched string!");
            }
        }

        return list;
    }*/
}
