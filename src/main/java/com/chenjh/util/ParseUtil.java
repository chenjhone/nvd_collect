package com.chenjh.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenjh
 * @version V1.0
 * @since 2018年12月27日
 */
public abstract class ParseUtil
{
    /**
     * logger
     */
    private static final Logger LOG = Logger.getLogger(ParseUtil.class);

    /**
     * 读取txt，提取所需字段存入数组
     *@param filePath 文件路径
     *@param pattern pattern
     * @return Strings
     * @author chenjh
     */
    public static List<String> readTxtFile(String filePath, String pattern)
    {
        List<String> fileContent = new ArrayList<String>();
        InputStreamReader read=null;
        BufferedReader bufferedReader=null;
        Pattern p;
        Matcher m;
        try
        {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                if (pattern != null)
                {
                    String txt = "";
                    while ((lineTxt = bufferedReader.readLine()) != null)
                    {
                        // System.out.println(lineTxt);

                        p = Pattern.compile(pattern);
                        m = p.matcher(lineTxt);
                        if (m.find())
                        {
                            fileContent.add(txt + "\r\n");
                            txt = "";
                        }
                        txt = txt + "\r\n" + lineTxt;
                    }
                    fileContent.add(txt + "\r\n");
                }
                read.close();
            }
            else
            {
                LOG.info("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            LOG.error("读取文件内容出错");
//            e.printStackTrace();
        }finally {
            try {
                if (read != null) {
                    read.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContent;
    }

    /**
     * 读取字符串，提取所需字段
     * @param context context
     * @param pattern pattern
     * @return String
     * @author chenjh
     */
    public static String readString(String context, String pattern)
    {
        StringBuffer str = new StringBuffer();
        Pattern p;
        Matcher m;
        if (pattern != null && context != null)
        {
            p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            m = p.matcher(context);
            while (m.find())
            {
                str.append(m.group(1));
            }
        }
        else
        {
            LOG.info("内容空或匹配规则有误");
        }
        return str.toString();
    }

    /**
     * 读取字符串，提取所需字段
     * @param context context
     * @param pattern pattern
     * @return String列表
     * @author chenjh   
     */
    public static List<String> readStringList(String context, String pattern)
    {
        List<String> fileContent = new ArrayList<String>();
        Pattern p;
        Matcher m;
        if (pattern != null && context != null)
        {
            p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            m = p.matcher(context);
            while (m.find())
            {
                fileContent.add(m.group(0));
            }
        }
        else
        {
            LOG.info("内容空或匹配规则有误");
        }
        return fileContent;
    }

}
