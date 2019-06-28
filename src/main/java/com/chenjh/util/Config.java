package com.chenjh.util;

import java.io.*;
import java.util.Properties;

/**
 * com.huawei.KaaS.KaaSUtil
 * Created by d00380361 on 2018/6/21.
 */
public class Config {
    private static final String config_file = "knet.config";
    private static final Properties config;
    static {
        Properties fallback = new Properties();
//        fallback.put("mongodb_server", "mongodb://kaas:kaas@100.100.235.206:27017");
//        fallback.put("mongodb_db", "kaas");
//        fallback.put("kafka_server", "10.90.23.133:21005,10.90.23.134:21005,10.90.23.135:21005,10.90.23.172:21005");
//        fallback.put("kafka_input_topic", "kaas_input");
//        fallback.put("kafka_consumer_group", "kafka_demo");
//        fallback.put("rally_parallel", "5");
//        fallback.put("hdfs_base_path", "hdfs:///tmp/kaas/");
//        fallback.put("local_base_path", "file:///tmp/kaas/");
//        fallback.put("rally_period", "86400");
//        fallback.put("rally_lag", "60");
        config = new Properties(fallback);

        InputStream stream;
        try {
            stream = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir")+"/"+config_file));
        } catch (FileNotFoundException e1) {
            stream = Config.class.getClassLoader().getResourceAsStream(config_file);
        }
        try {
            config.load(stream);
            stream.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static String get(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }
    public static void set(String key, String defaultValue) {
        config.setProperty(key, defaultValue);
    }
    public static String get(String key) {
        return get(key, "");
    }
    public static Properties getConfig() {
        return config;
    }
}
