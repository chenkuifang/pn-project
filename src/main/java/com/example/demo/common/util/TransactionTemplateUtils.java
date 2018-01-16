package com.example.demo.common.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * spring 事务模板工具类
 *
 * @author QuiFar
 * @version V1.0
 **/
public class TransactionTemplateUtils {
    private TransactionTemplateUtils() {
    }

    private static Logger logger = LoggerFactory.getLogger(TransactionTemplateUtils.class);
    // 数据源
    private static String oracleDS = "java:OracleDS";
    private static DataSource dataSource = null;

    static {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup(oracleDS);
        } catch (NamingException e) {
            logger.info("查找数据源失败···", e);
        }
    }

    public static TransactionTemplate getTransactionTemplate() {
        PlatformTransactionManager txManager = new DataSourceTransactionManager(
                dataSource);
        return new TransactionTemplate(txManager);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

//    public static SimpleJdbcTemplate getSimpleJdbcTemplate() {
//        return new SimpleJdbcTemplate(dataSource);
//    }
}
