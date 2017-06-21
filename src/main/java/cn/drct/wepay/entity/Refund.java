package cn.drct.wepay.entity;

/**
 * outTradeNo,totalFee,refundFee,opUserId非空
 * 
 * @author sunzy
 *
 */
public class Refund {
	private String deviceInfo;
	private String outTradeNo;
	private String outRefundNo;
	private int totalFee;
	private int refundFee;
	private String refundFeeType;
	private String opUserId;
	private String refundAccount;
	public String getDeviceInfo() {
		return deviceInfo;
	}
	/**
	 * 设备号,最长32
	 * @param deviceInfo
	 */
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * 商户订单号,最长32
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getOutRefundNo() {
		return outRefundNo;
	}
	/**
	 * 商户退款单号，最长32
	 * @param outRefundNo
	 */
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	public int getTotalFee() {
		return totalFee;
	}
	/**
	 * 订单金额，单位分
	 * @param totalFee
	 */
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public int getRefundFee() {
		return refundFee;
	}
	/**
	 * 退款金额，单位分
	 * @param refundFee
	 */
	public void setRefundFee(int refundFee) {
		this.refundFee = refundFee;
	}
	public String getRefundFeeType() {
		return refundFeeType;
	}
	/**
	 * 货币种类
	 * @param refundFeeType
	 */
	public void setRefundFeeType(String refundFeeType) {
		this.refundFeeType = refundFeeType;
	}
	public String getOpUserId() {
		return opUserId;
	}
	/**
	 * 操作员, 默认为商户号
	 * @param opUserId
	 */
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	public String getRefundAccount() {
		return refundAccount;
	}
	/**
	 * 退款资金来源
	 *  REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
	 *  REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	 * @param refundAccount
	 */
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
}
