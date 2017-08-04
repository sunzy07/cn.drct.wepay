package cn.drct.wepay.entity;

public class TransfersResult {
	private String partnerTradeNo;
	private String paymentNo;
	private String paymentTime;
	/**
	 * 商户订单号，需保持唯一性
	 * @return
	 */
	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}
	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	/**
	 * 企业付款成功，返回的微信订单号
	 * @return
	 */
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	/**
	 * 企业付款成功时间
	 * @return
	 */
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	
}
