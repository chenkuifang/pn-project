

package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品信息实体类
 *
 * @author ：QuiFar
 */
@Getter
@Setter
@ToString
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
}