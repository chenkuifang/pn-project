
package com.example.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单信息实体类
 *
 * @author ：QuiFar
 */
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderSid() {
		return orderSid;
	}

	public void setOrderSid(String orderSid) {
		this.orderSid = orderSid;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	/**
	 * 返回 商品编码
	 */
	public String getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * 返回 商品名称
	 */
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 返回 拍下价格
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	/**
	 * 返回 支付价格
	 */
	public BigDecimal getPayPrice() {
		return this.payPrice;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * 返回 商品数量
	 */
	public Integer getAmount() {
		return this.amount;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/**
	 * 返回 收货人
	 */
	public String getBuyerName() {
		return this.buyerName;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	/**
	 * 返回 收货地址
	 */
	public String getBuyerAddress() {
		return this.buyerAddress;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	/**
	 * 返回 手机
	 */
	public String getBuyerPhone() {
		return this.buyerPhone;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	/**
	 * 返回 订单创建账号ID
	 */
	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回 订单创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 返回 订单最后修改时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 返回 订单状态 0:未付款，1：已付款，2：已发货，3：已完成，4：取消订单
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("orderId", this.orderId).append("orderSid", this.orderSid)
				.append("goodsNum", this.goodsNum).append("goodsName", this.goodsName).append("price", this.price)
				.append("payPrice", this.payPrice).append("amount", this.amount).append("buyerName", this.buyerName)
				.append("buyerAddress", this.buyerAddress).append("buyerPhone", this.buyerPhone)
				.append("createId", this.createId).append("createTime", this.createTime)
				.append("updateTime", this.updateTime).append("status", this.status).toString();
	}
}