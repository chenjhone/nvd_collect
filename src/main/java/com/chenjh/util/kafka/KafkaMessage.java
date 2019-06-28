//
//package com.chenjh.util.kafka;
//
//import java.util.Date;
//
//import com.google.gson.Gson;
//
//public class KafkaMessage
//{
//    // 采集源
//    private String collectSource;
//    // 消息创建日期
//    private Date createDate;
//    // json的消息数据
//    private String data;
//
//    // 消息主题
//    private String topic;
//
//    private String status;// 1-待重发，2-重发中
//    // 重发次数
//    private int resendCount;
//
//    // 接收日期(消费者拿出来的时间)
//    private Date receiveDate;
//
//    private String batchNo; //流水号a
//
//    public void kafkaMessage(Object obj)
//    {
//        Gson gson = new Gson();
//        setData(gson.toJson(obj));
//    }
//
//    public String getCollectSource()
//    {
//        return collectSource;
//    }
//
//    public void setCollectSource(String collectSource)
//    {
//        this.collectSource = collectSource;
//    }
//
//    public Date getCreateDate()
//    {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate)
//    {
//        this.createDate = createDate;
//    }
//
//    public String getData()
//    {
//        return data;
//    }
//
//    public void setData(String data)
//    {
//        this.data = data;
//    }
//
//    public String getTopic()
//    {
//        return topic;
//    }
//
//    public void setTopic(String topic)
//    {
//        this.topic = topic;
//    }
//
//    public String getStatus()
//    {
//        return status;
//    }
//
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
//
//    public int getResendCount()
//    {
//        return resendCount;
//    }
//
//    public void setResendCount(int resendCount)
//    {
//        this.resendCount = resendCount;
//    }
//
//    public Date getReceiveDate()
//    {
//        return receiveDate;
//    }
//
//    public void setReceiveDate(Date receiveDate)
//    {
//        this.receiveDate = receiveDate;
//    }
//
//    public String getBatchNo()
//    {
//        return batchNo;
//    }
//
//    public void setBatchNo(String batchNo)
//    {
//        this.batchNo = batchNo;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "KafkaMessage [collectSource=" + collectSource + ", createDate=" + createDate + ", data=" + data
//            + ", topic=" + topic + ", status=" + status + ", resendCount=" + resendCount + ", receiveDate="
//            + receiveDate + ", batchNo=" + batchNo + "]";
//    }
//
//}
