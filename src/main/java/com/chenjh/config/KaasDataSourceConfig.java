//package com.chenjh.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//@Configuration
//@MapperScan(basePackages="com.chenjh.dao.oracle.kaas",sqlSessionTemplateRef  = "kaasSqlSessionTemplate")
//public class KaasDataSourceConfig {
//
//    @Bean(name="kaasDataSource")     //声明其为Bean实例
//    @ConfigurationProperties(prefix = "spring.datasource.kaas")
//    public DataSource dataSource(){
//        DruidDataSource datasource = new DruidDataSource();
//        return datasource;
//    }
//
//    @Bean(name = "kaasSessionFactory")
//    public SqlSessionFactory kaasSqlSessionFactory(@Qualifier("kaasDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        Resource configLocation=new ClassPathResource("mybatis-config.xml");
//        bean.setConfigLocation(configLocation);
//        return bean.getObject();
//    }
//
//    @Bean(name = "kaasTransactionManager")
//    public DataSourceTransactionManager kaasTransactionManager(@Qualifier("kaasDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "kaasSqlSessionTemplate")
//    public SqlSessionTemplate kaasSqlSessionTemplate(@Qualifier("kaasSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//
//}
