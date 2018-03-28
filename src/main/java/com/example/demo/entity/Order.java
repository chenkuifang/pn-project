
package com.example.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单信息实体类
 *
 * @author ：QuiFar
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Order implements Serializable {

	private static final long serialVersionUID = -1920269264219788549L;
	private Integer orderId; /* 订单编码 */
	private String orderSid; /* 订单号 */
	private String goodsNum; /* 商品编码 */
	private String goodsName; /* 商品名称 */
	private BigDecimal price; /* 拍下价格 */
	private BigDecimal payPrice; /* 支付价格 */
	private Integer amount; /* 商品数量 */
	private String buyerName; /* 收货人 */
	private String buyerAddress; /* 收货地址 */
	private String buyerPhone; /* 手机 */
	private Integer createId; /* 订单创建账号ID */
	private java.util.Date createTime; /* 订单创建时间 */
	private java.util.Date updateTime; /* 订单最后修改时间 */
	private Integer status; /* 订单状态 0:未付款，1：已付款，2：已发货，3：已完成，4：取消订单 */
}