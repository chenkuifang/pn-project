

package com.example.demo.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品信息实体类
 *
 * @author ：QuiFar
 */
public class Goods implements Serializable {
	private static final long serialVersionUID = 903598773132953830L;
	
	private Integer id; 		/*id*/
    private String goodsNum; 		/*商品编码*/
    private String goodsName; 		/*商品名称*/
    private BigDecimal salePrice; 		/*销售价格*/
    private BigDecimal discountPrice; 		/*折后价格*/
    private Integer stock; 		/*库存*/
    private Integer saleCount; 		/*销量*/
    private Integer status; 		/*状态  1:正常, 0:下架*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * 返回 销售价格
     */
    public BigDecimal getSalePrice() {
        return this.salePrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 返回 折后价格
     */
    public BigDecimal getDiscountPrice() {
        return this.discountPrice;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 返回 库存
     */
    public Integer getStock() {
        return this.stock;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 返回 销量
     */
    public Integer getSaleCount() {
        return this.saleCount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 返回 状态  1:正常, 0:下架
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("goodsNum", this.goodsNum)
                .append("goodsName", this.goodsName)
                .append("salePrice", this.salePrice)
                .append("discountPrice", this.discountPrice)
                .append("stock", this.stock)
                .append("saleCount", this.saleCount)
                .append("status", this.status)
                .toString();
    }
}