package org.ibase4j.model;


import org.ibase4j.core.base.BaseModel;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;


/**
 * <p>
 * 
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-16
 */
@TableName("sys_msg_config")
public class SysMsgConfig extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 短信平台地址
     */
	@TableField("sms_plat_url")
	private String smsPlatUrl;
    /**
     * 短信平台帐号
     */
	@TableField("sms_plat_account")
	private String smsPlatAccount;
    /**
     * 短信平台密码
     */
	@TableField("sms_plat_password")
	private String smsPlatPassword;
    /**
     * 发送短信签名
     */
	@TableField("sender_name")
	private String senderName;
    /**
     * 客户下订单时是否给商家发短信
     */
	@TableField("order_is_send")
	private Integer orderIsSend;
    /**
     * 客户付款时是否给商家发短信
     */
	@TableField("pay_is_send")
	private Integer payIsSend;
    /**
     * 商家发货时是否给客户发短信
     */
	@TableField("send_goods_is_send")
	private Integer sendGoodsIsSend;
    /**
     * 用户注册时是否给客户发短信
     */
	@TableField("regist_is_send")
	private Integer registIsSend;
    /**
     * 用户付款后是否给客户发收货验证码
     */
	@TableField("advice_goods_is_send")
	private Integer adviceGoodsIsSend;


	public String getSmsPlatUrl() {
		return smsPlatUrl;
	}

	public void setSmsPlatUrl(String smsPlatUrl) {
		this.smsPlatUrl = smsPlatUrl;
	}

	public String getSmsPlatAccount() {
		return smsPlatAccount;
	}

	public void setSmsPlatAccount(String smsPlatAccount) {
		this.smsPlatAccount = smsPlatAccount;
	}

	public String getSmsPlatPassword() {
		return smsPlatPassword;
	}

	public void setSmsPlatPassword(String smsPlatPassword) {
		this.smsPlatPassword = smsPlatPassword;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String sendName) {
		this.senderName = sendName;
	}

	public Integer getOrderIsSend() {
		return orderIsSend;
	}

	public void setOrderIsSend(Integer orderIsSend) {
		this.orderIsSend = orderIsSend;
	}

	public Integer getPayIsSend() {
		return payIsSend;
	}

	public void setPayIsSend(Integer payIsSend) {
		this.payIsSend = payIsSend;
	}

	public Integer getSendGoodsIsSend() {
		return sendGoodsIsSend;
	}

	public void setSendGoodsIsSend(Integer sendGoodsIsSend) {
		this.sendGoodsIsSend = sendGoodsIsSend;
	}

	public Integer getRegistIsSend() {
		return registIsSend;
	}

	public void setRegistIsSend(Integer registIsSend) {
		this.registIsSend = registIsSend;
	}

	public Integer getAdviceGoodsIsSend() {
		return adviceGoodsIsSend;
	}

	public void setAdviceGoodsIsSend(Integer adviceGoodsIsSend) {
		this.adviceGoodsIsSend = adviceGoodsIsSend;
	}

}
