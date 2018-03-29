package com.example.demo.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 上下文POJO,方便读取系统常用属性
 *
 * @author QuiFar
 * @version V1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WebContext implements Serializable {

    private static final long serialVersionUID = -8809214340534494050L;

    /**
     * 当前登录用户ID
     */
    private Integer userId;
    /**
     * 当前登陆用户名
     */
    private String userName;
    /**
     * 当前登陆用户昵称
     */
    private String userNike;
    /**
     * 当前用户角色id
     */
    private Integer roleId;

    /**
     * 当前用户IP
     */
    private String ip;

}
