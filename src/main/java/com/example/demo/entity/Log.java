package com.example.demo.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志实体类
 *
 * @author QuiFar
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer userId;
    private String userName;
    private String operation;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}

