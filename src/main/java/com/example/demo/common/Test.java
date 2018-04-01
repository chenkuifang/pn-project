package com.example.demo.common;

import java.util.Map;
import java.util.Properties;

/**
 * 测试类
 *
 * @author QuiFar
 * @version V1.0
 **/
public class Test {


    public static void main(String[] args) {
//        StringBuilder sql = new StringBuilder("insert into dyf_waterpail_info (pail_no,storagetype) values ");
//
//        for (int i = 0; i < 200; i++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("(");
//            sb.append(i + 1900001);
//            sb.append(",");
//            sb.append(10007);
//            sb.append("),");
//            sql.append(sb);
//        }
//        System.out.println(sql);
        // JVM系统属性值
        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.err.println(entry.getKey() + ":" + entry.getValue());
        }

        // OS系统属性
        Map<String, String> getenv = System.getenv();
        for (String key : getenv.keySet()) {
            System.err.println(key + ":" + getenv.get(key));
        }

    }
}
