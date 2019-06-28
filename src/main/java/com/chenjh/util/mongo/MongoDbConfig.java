//
//package com.chenjh.util.mongo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//
//import com.chenjh.util.crypto.DesCryptoUtil;
//
//@Configuration
//public class MongoDbConfig
//{
//
//    private static final Logger logger = LoggerFactory.getLogger(MongoDbConfig.class);
//
//    @Value("${spring.data.mongodb.host}")
//    private String host;
//
//    @Value("${spring.data.mongodb.port}")
//    private int port;
//
//    @Value("${spring.data.mongodb.database}")
//    private String database;
//
//    @Value("${spring.data.mongodb.username}")
//    private String username;
//
//    @Value("${spring.data.mongodb.password}")
//    private String password;
//
//    private MongoCredential mongoCredential()
//    {
//        try
//        {
//            return MongoCredential.createCredential(username, database,
//                    DesCryptoUtil.getInstance().decode(password).toCharArray());
//        }
//        catch (Exception e)
//        {
//            logger.error("create  mongodb credential error.", e);
//        }
//
//        return null;
//    }
//
//    @Bean
//    @Primary
//    public MongoDbFactory mongoDbFactory()
//    {
//        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
//        credentialsList.add(mongoCredential());
//        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port), credentialsList), database);
//    }
//}
