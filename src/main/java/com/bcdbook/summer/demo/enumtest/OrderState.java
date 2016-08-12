package com.bcdbook.summer.demo.enumtest;

/**
 * 订单状态
 * 
 * 实现带有抽象方法的枚举
 * 
 * @author jiqinlin
 *
 */
 public enum OrderState {
     /** 已取消 */
     CANCEL {@Override
	public String getName(){return "已取消";}},
     /** 待审核 */
     WAITCONFIRM {@Override
	public String getName(){return "待审核";}},
     /** 等待付款 */
     WAITPAYMENT {@Override
	public String getName(){return "等待付款";}},
     /** 正在配货 */
     ADMEASUREPRODUCT {@Override
	public String getName(){return "正在配货";}},
     /** 等待发货 */
     WAITDELIVER {@Override
	public String getName(){return "等待发货";}},
     /** 已发货 */
     DELIVERED {@Override
	public String getName(){return "已发货";}},
     /** 已收货 */
     RECEIVED {@Override
	public String getName(){return "已收货";}};
     
     public abstract String getName();
 }