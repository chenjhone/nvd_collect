//
//package com.chenjh.util.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//@Component
//public class KafkaService
//{
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//    private Gson gson = new GsonBuilder().create();
//
//
//
//    /**
//     * Description: 发送成功是返回true，发送失败时返回false
//     * @param msg
//     *  发送的消息内容
//     * @param batchNo
//     *  流水号(无-的UUID)
//     * @return
//     * @author tWX566557
//     * Date 2018年8月18日
//     */
//    public boolean pushSync(KafkaMessage msg, String batchNo)
//    {
//        try
//        {
//            kafkaTemplate.send(msg.getTopic(), batchNo, gson.toJson(msg)).get();
//        }
//        catch (Exception e)
//        {
//            log.warn("{},kafka消息同步发送失败!", e.getMessage());
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//}
