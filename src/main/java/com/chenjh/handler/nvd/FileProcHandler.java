package com.chenjh.handler.nvd;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.chenjh.domain.nvd.NdDownloadFileBean;
import com.chenjh.domain.nvd.NdDownloadProcBean;
import com.chenjh.handler.TaskContext;
import com.chenjh.handler.TaskResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.chenjh.common.VulnConstant;
import com.chenjh.common.nvd.EntryType;
import com.chenjh.common.nvd.Nvd;
import com.chenjh.common.type.DownloadStatusType;
import com.chenjh.common.type.ProcStatusType;
import com.chenjh.common.type.TaskStatusType;
import com.chenjh.exception.CheckedException;
import com.chenjh.mapper.NdDownloadFileBeanMapper;
import com.chenjh.mapper.NdDownloadProcBeanMapper;
import com.chenjh.util.DateUtil;
import com.chenjh.util.FileUtil;

/**
 *
 * <p>Title:  </p>
 * <p>Description:  </p>
 * <pre>  </pre>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: </p>
 
 * @version V1.0 2016年9月23日
 * @since
 */
public class FileProcHandler extends NVDTaskHandler
{

    /**
     * logger
     */
    private static final Logger LOG = Logger.getLogger(FileProcHandler.class);

    /**
     * ndDownloadFileBeanMapper
     */
    @Autowired
    private NdDownloadFileBeanMapper ndDownloadFileBeanMapper;

    /**
     * ndDownloadProcBeanMapper
     */
    @Autowired
    private NdDownloadProcBeanMapper ndDownloadProcBeanMapper;

    /**
     * 处理
     * @param context context
     * @return result
     * @throws CheckedException CheckedException
     */
    @Override
    public TaskResult handleTask(TaskContext context) throws CheckedException
    {
        LOG.info("file parse handler start ... ");

        boolean result = false;
        int downloadNum = 0;
        TaskResult taskResult = new TaskResult();

        NdDownloadFileBean queryBean = new NdDownloadFileBean();
        queryBean.setCrawlId(context.getCrawlTaskId());
        queryBean.setStatus(DownloadStatusType.DOWNLOAD_SUCCESS.getValue());
        List<NdDownloadFileBean> zipFileList = ndDownloadFileBeanMapper.queryZipFileList(queryBean);

        for (NdDownloadFileBean fileBean : zipFileList)
        {
            LOG.info("xml file parse begin , the file id is " + fileBean.getFileId());
            try
            {
                downloadNum = downloadNum + doHandleZipFile(fileBean);

                fileBean.setStatus(DownloadStatusType.PROC_SUCCESS.getValue());
                fileBean.setModifiedTime(DateUtil.getCurrentUtilDate());
            }
            catch (JAXBException e)
            {
                LOG.error("JAXBException occured during handle zip file .", e);
                fileBean.setStatus(DownloadStatusType.PROC_FAIL.getValue());
                fileBean.setModifiedTime(DateUtil.getCurrentUtilDate());
                fileBean.setNote("JAXBException");
            }
            catch (Exception e)
            {
                LOG.error("Exception occured during handle zip file .", e);
                fileBean.setStatus(DownloadStatusType.PROC_FAIL.getValue());
                fileBean.setModifiedTime(DateUtil.getCurrentUtilDate());
                fileBean.setNote("Exception");
            }

            if (fileBean.getStatus() == DownloadStatusType.PROC_SUCCESS.getValue())
            {
                result = true;
            }

            ndDownloadFileBeanMapper.updateByPrimaryKey(fileBean);

            LOG.info("xml file parse end , the file id is " + fileBean.getFileId());
        }

        taskResult.setResult(result);
        taskResult.setDownloadNum(downloadNum);
        if (result)
        {
            taskResult.setTaskStatus(TaskStatusType.SUCCESS.getValue());
        }
        else
        {
            taskResult.setTaskStatus(TaskStatusType.FAIL.getValue());
        }
        return taskResult;
    }

    /**
     * 处理zip文件
     * @param zipFileBean zip文件对象
     * @return 处理结果
     
     * @throws JAXBException JAXBException
     * @date 2016年9月26日
     */
    @Transactional
    private int doHandleZipFile(NdDownloadFileBean zipFileBean) throws JAXBException
    {
        int handleNum = 0;
        String sourceFileName = zipFileBean.getFileName();
        String targetPath = zipFileBean.getFilePath();
        String sourceFile = targetPath + sourceFileName;
        String prefix = sourceFileName + VulnConstant.FILE_NAME_SPLIT;

        FileUtil.unzip(sourceFile, prefix);

        File destFile = new File(targetPath);
        if (destFile!=null) {
            File[] xmlFileArr = destFile.listFiles();
            if (xmlFileArr!=null) {
                for (File xmlFile : xmlFileArr) {
                    if (xmlFile.isFile() && xmlFile.getName().startsWith(prefix)) {
                        Nvd nvdBean = FileUtil.converyToJavaBean(xmlFile, Nvd.class);
                        if (nvdBean != null) {
                            for (EntryType entry : nvdBean.getEntry()) {
                                handleNum = handleNum + saveEnryInfo(zipFileBean, entry);
                                LOG.info("save cve xml info ,cve id is " + entry.getName());
                            }
                        }
                    }
                }
            }
        }

        return handleNum;
    }

    /**
     * 插入procBean
     * @param zipFileBean zip文件对象
     * @param entry cve Entry
     * @throws JAXBException JAXBException
     
     * @date 2016年9月26日
     */
    private int saveEnryInfo(NdDownloadFileBean zipFileBean, EntryType entry) throws JAXBException
    {
        String cveEntry = FileUtil.converyToXml(entry);
        if (cveEntry == null)
        {
            cveEntry = "";
        }

        NdDownloadProcBean procBean = new NdDownloadProcBean();
        procBean.setFileId(zipFileBean.getFileId());
        procBean.setCveId(entry.getName());
        procBean.setCveEntry(cveEntry);
        procBean.setFlag(cveEntry.hashCode());
        procBean.setStatus(ProcStatusType.INIT.getValue());
        Date modifyDate = DateUtil.parse(entry.getModified(), DateUtil.PATTERN_YYYY_MM_DD);
        procBean.setVulModDate(modifyDate);

        return ndDownloadProcBeanMapper.insert(procBean);
    }
}
