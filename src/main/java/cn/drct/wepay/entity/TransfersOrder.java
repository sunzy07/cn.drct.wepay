package cn.drct.wepay.entity;

public class TransfersOrder {
	private String partnerTradeNo;
	private String status;
	private String reason;
	private String openid;
	private String transferName;
	private Integer paymentAmount;
	private String transferTime;
	private String desc;
	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}
	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * SUCCESS:转账成功
	 * FAILED:转账失败
	 * PROCESSING:处理中
	 * @return
	 */
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 转账的openid
	 * @return
	 */
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 收款用户姓名
	 * @return
	 */
	public String getTransferName() {
		return transferName;
	}
	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}
	/**
	 * 付款金额单位分
	 * @return
	 */
	public Integer getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**
	 * 2015-04-21 20:00:00 发起转账的时间
	 * @return
	 */
	public String getTransferTime() {
		return transferTime;
	}
	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime;
	}
	/**
	 * 	付款时候的描述
	 * @return
	 */
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
