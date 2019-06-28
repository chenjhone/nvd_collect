package com.chenjh.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.chenjh.common.VulnConstant;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author chenjh
 * @version V1.0
 * @since 2018年12月27日
 */
public abstract class FileUtil {
    private static final Logger LOG = Logger.getLogger(FileUtil.class);

    /**
     * DEFAULT_SIZE
     */
    private static final int DEFAULT_SIZE = 1024;

    /**
     * 生成文件名全路径
     *
     * @param rootPath       根路径
     * @param relativelyPath 相对路径
     * @param fileName       文件名
     * @return 返回文件名全路径
     * @author chenjh
     */
    public static String getFileFullPath(final String rootPath, final String relativelyPath, final String fileName) {
        StringBuilder path = new StringBuilder(rootPath);
        path.append(relativelyPath);
        path.append(fileName);
        return path.toString();
    }

    /**
     * 生成文件名全路径
     *
     * @param rootPath       根路径
     * @param relativelyPath 相对路径
     * @return 返回文件名全路径
     * @author chenjh
     */
    public static String getFullPath(final String rootPath, final String relativelyPath) {
        StringBuilder path = new StringBuilder(rootPath);
        path.append(relativelyPath);
        return path.toString();
    }

    /**
     * 生成相对路径  例如：2015/3/2015-03-26/
     *
     * @param timeMillis timeMillis
     * @return 返回相对路径
     * @author chenjh
     */
    public static String getRelativelyPath(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return getRelativelyPath(calendar);
    }

    /**
     * 生成相对路径  例如：2015/3/2015-03-26/
     *
     * @param date date
     * @return 返回相对路径
     * @author chenjh
     */
    public static String getRelativelyPath(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getRelativelyPath(calendar);
    }

    /**
     * 生成相对路径  例如：2015/3/2015-03-26/
     *
     * @param calendar calendar
     * @return 返回相对路径
     * @author chenjh
     */
    public static String getRelativelyPath(Calendar calendar) {
        StringBuilder path = new StringBuilder();
        path.append(calendar.get(Calendar.YEAR)).append(File.separator);
        path.append(calendar.get(Calendar.MONTH) + 1).append(File.separator);
        path.append(DateUtil.format(calendar.getTime(), null, DateUtil.PATTERN_YYYY_MM_DD)).append(File.separator);
        return path.toString();
    }

    /**
     * 截取文件的扩展名
     *
     * @param fileName 文件名
     * @return 返回扩展名
     * @author chenjh
     */
    public static String generExtensionName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        }
        String extensionName = "";
        int position = fileName.lastIndexOf(".");
        if (position > 0) {
            extensionName = fileName.substring(position + 1);
        }
        return extensionName;
    }

    /**
     * 删除物理文件
     *
     * @param fileFullPath fileFullPath
     * @return 返回删除结果
     * @author chenjh
     */
    public static boolean deletePhysicalFile(String fileFullPath) {
        if (null == fileFullPath) {
            return false;
        }

        File targetFile = new File(fileFullPath);
        if (targetFile.isFile()) {
            boolean isDeleteFile = targetFile.delete();
            if (isDeleteFile) {
                LOG.info("Delete file success:" + targetFile.getAbsolutePath());
                return true;
            }
        } else {
            LOG.info("File is not exist :" + targetFile.getAbsolutePath());
            return true;
        }
        return false;
    }

    /**
     * 拷贝文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @return 拷贝文件结果
     * @author chenjh
     */
    public static boolean nioTransferCopy(String source, String target) {
        return nioTransferCopy(new File(source), new File(target));
    }

    /**
     * nio拷贝文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @return 拷贝文件结果
     * @author chenjh
     */
    public static boolean nioTransferCopy(File source, File target) {
        FileChannel fcIn = null;
        FileChannel fcOut = null;
        try {
            fcIn = new FileInputStream(source).getChannel();
            fcOut = new FileOutputStream(target).getChannel();
            fcIn.transferTo(0, fcIn.size(), fcOut);

            return true;
        } catch (IOException e) {
            LOG.error("copy file exception", e);
            return false;
        } finally {
            try {
                if (fcIn != null) {

                    fcIn.close();
                }
            } catch (IOException e) {
                LOG.error("copy file exception", e);
            }

            try {
                if (fcOut != null) {
                    fcOut.close();
                }
            } catch (IOException e) {
                LOG.error("copy file exception", e);
            }
        }
    }


    /**
     * 解压文件
     * @param filePath 文件路径
     * @param prefix 前缀
     * @author chenjh
     */
    public static void unzip(String filePath, String prefix)
    {
        File source = new File(filePath);
        if (source.exists())
        {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try
            {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory())
                {
                    String targetName = entry.getName();
                    if (StringUtils.isNotEmpty(prefix))
                    {
                        targetName = prefix + targetName;
                    }
                    File target = new File(source.getParent(), targetName);
                    if (!target.getParentFile().exists())
                    {
                        // 创建文件父目录
                        target.getParentFile().mkdirs();
                    }
                    // 写入文件
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[DEFAULT_SIZE];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1)
                    {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            }
            catch (IOException e)
            {
                //throw new RuntimeException(e);
                LOG.error("FileUtil.unzip :" + e);
            }
            finally
            {
                if (zis != null)
                {
                    try
                    {
                        zis.close();
                    }
                    catch (IOException e)
                    {
                        LOG.error("IOException occured during close zis .");
                    }
                }
                if (bos != null)
                {
                    try
                    {
                        bos.close();
                    }
                    catch (IOException e)
                    {
                        LOG.error("IOException occured during close bos .");
                    }
                }
            }
        }
    }

    /**
     * xml to bean
     *
     * @param xmlFile xmlFile
     * @param c       c
     * @param <T>     T
     * @return Class
     * @throws JAXBException JAXBException
     * @author chenjh
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(File xmlFile, Class<T> c) throws JAXBException {
        T result = null;
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        result = (T) jaxbUnmarshaller.unmarshal(xmlFile);

        return result;
    }

    /**
     * xml to bean
     *
     * @param xmlStr xmlStr
     * @param c      c
     * @param <T>    T
     * @return Class
     * @throws JAXBException JAXBException
     * @author chenjh
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xmlStr, Class<T> c) throws JAXBException {
        T result = null;
        StringReader reader = new StringReader(xmlStr);
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        result = (T) jaxbUnmarshaller.unmarshal(reader);

        return result;
    }

    /**
     * bean to xml
     *
     * @param entryType entry
     * @return xmlStr
     * @throws JAXBException JAXBException
     * @author chenjh
     */
    public static String converyToXml(Object entryType) throws JAXBException {
        StringWriter sw = null;
        String result = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(entryType.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            sw = new StringWriter();
            jaxbMarshaller.marshal(entryType, sw);
            result = sw.toString();

            return result;
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e) {
                    LOG.error("IO Exception occured during sw close. ");
                }
            }

        }
    }

    /**
     * ungz
     *
     * @param filePath 文件路径
     * @return String
     */
    public static String ungz(String filePath) {
        String ouputfile = "";
        FileInputStream fin = null;
        GZIPInputStream gzin = null;
        FileOutputStream fout = null;
        try {
            //建立gzip压缩文件输入流
            fin = new FileInputStream(filePath);
            //建立gzip解压工作流
            gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            ouputfile = filePath.substring(0, filePath.lastIndexOf('.'));
            ouputfile = ouputfile.substring(0, ouputfile.lastIndexOf('.')) + ".txt";
            fout = new FileOutputStream(ouputfile);

            int num;
            byte[] buf = new byte[VulnConstant.INTEGER_1024];

            while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                fout.write(buf, 0, num);
            }

            gzin.close();
            fout.close();
            fin.close();
        } catch (Exception ex) {
            LOG.error("FileUtil.ungz :" + ex);
        } finally {
            try {
                if (gzin != null) {
                    gzin.close();
                }
                if (fout != null) {
                    fout.close();
                }
                if (fin != null) {
                    fin.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ouputfile;
    }

    /**
     * 解压gz
     *
     * @param filePath filePath
     * @param prefix   前缀
     * @throws IOException IOException
     */
    public static void unCompressArchiveGz(String filePath, String prefix) {

        File file = new File(filePath);
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        GzipCompressorInputStream gcis = null;
        try {

            bis = new BufferedInputStream(new FileInputStream(file));

            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));

            String finalName = file.getParent() + File.separator + prefix + fileName;
            bos = new BufferedOutputStream(new FileOutputStream(finalName));

            if (file.getName().endsWith(".gz")) {
                gcis = new GzipCompressorInputStream(bis);

                byte[] buffer = new byte[VulnConstant.INTEGER_1024];
                int read = -1;
                while ((read = gcis.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                }
            } else {
                byte[] buffer = new byte[VulnConstant.INTEGER_1024];
                int read = -1;
                while ((read = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (gcis != null) {
                    gcis.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //unCompressTar(finalName);
    }

    /**
     * 解压tar
     *
     * @param finalName 文件最终名字
     * @throws IOException IOException
     */
    public static void unCompressTar(String finalName) {

        File file = new File(finalName);
        String parentPath = file.getParent();
        TarArchiveInputStream tais = null;

        try {
            tais = new TarArchiveInputStream(new FileInputStream(file));
            TarArchiveEntry tarArchiveEntry = null;
            while ((tarArchiveEntry = tais.getNextTarEntry()) != null) {
                String name = tarArchiveEntry.getName();
                File tarFile = new File(parentPath, name);
                if (!tarFile.getParentFile().exists()) {
                    tarFile.getParentFile().mkdirs();
                }
                BufferedOutputStream bos = null;
                try {
                    bos = new BufferedOutputStream(new FileOutputStream(tarFile));
                }catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bos != null) {
                            bos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                int read = -1;
                byte[] buffer = new byte[VulnConstant.INTEGER_1024];
                while ((read = tais.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (tais != null) {
                    tais.close();
                }
                file.delete();//删除tar文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取txt文档
     *
     * @param filePath 文件路径
     * @param pattern  pattern
     * @return String列表
     * @author chenjh
     */
    public static List<String> readCentTxtFile(String filePath, String pattern) {
        List<String> fileContent = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                if (pattern != null) {
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        if (RegexMatch.isMatchRegex(lineTxt, pattern)) {
                            fileContent.add(sb.toString());
                            sb.delete(0, sb.length());
                        }
                        sb.append(lineTxt + "\r\n");
                    }
                    fileContent.add(sb.toString());
                }
                read.close();
            } else {
                LOG.info("Can not find the file!");
            }
        } catch (Exception e) {
            LOG.error("Read file content error!");
        } finally {
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
     * 读取网页文档并获取json信息
     *
     * @param filePath filePath
     * @return String
     * @author chenjh
     */
    public static String readEulerJsonFile(String filePath) {

        StringBuffer sb = new StringBuffer();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                bufferedReader = new BufferedReader(read);
                String lineTxt = "";

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt + "\r\n");
                }

            } else {
                LOG.info("Can not find the file!");
            }
        } catch (Exception e) {
            LOG.error("Read file content error!");
        } finally {
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
        return sb.toString();
    }
}
