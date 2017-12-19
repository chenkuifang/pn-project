package com.example.demo.common;

/**
 * 测试类
 *
 * @author QuiFar
 * @version V1.0
 **/
public class Test {


    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder("insert into dyf_adjust_pail (id,pail_no) values ");

        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(i + 1);
            sb.append(",");
            sb.append((i + 10001));
            sb.append("),");
            sql.append(sb);
        }
        System.out.println(sql);
    }
}
