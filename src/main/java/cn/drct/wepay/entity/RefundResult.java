package cn.drct.wepay.entity;

public class RefundResult {
	private String deviceInfo;
	private String transactionId;
	private String outTradeNo;
	private String outRefundNo;
	private String refundId;
	private String refundChannel;
	private Integer refundFee;
	private Integer settlementRefundFee;
	private Integer totalFee;
	private Integer settlementTotalFee;
	private String feeType;
	private Integer cashFee;
	private Integer cashRefundFee;
	/**
	 * 设备号
	 * @return
	 */
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	/**
	 * 微信订单号
	 * @return
	 */
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * 商户订单号
	 * @return
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * 商户退款单号
	 * @return
	 */
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	/**
	 * 微信退款单号
	 * @return
	 */
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	/**
	 * 退款渠道
	 * ORIGINAL—原路退款<br>
	 * BALANCE—退回到余额<br>
	 * @return
	 */
	public String getRefundChannel() {
		return refundChannel;
	}
	public void setRefundChannel(String refundChannel) {
		this.refundChannel = refundChannel;
	}
	/**
	 * 退款总金额,单位为分,可以做部分退款
	 * @return
	 */
	public Integer getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(Integer refundFee) {
		this.refundFee = refundFee;
	}
	/**
	 * 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
	 * @return
	 */
	public Integer getSettlementRefundFee() {
		return settlementRefundFee;
	}
	public void setSettlementRefundFee(Integer settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}
	/**
	 * 订单总金额，单位为分，只能为整数
	 * @return
	 */
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * 去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	 * @return
	 */
	public Integer getSettlementTotalFee() {
		return settlementTotalFee;
	}
	public void setSettlementTotalFee(Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	/**
	 * 订单金额货币种类
	 * @return
	 */
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	/**
	 * 现金支付金额，单位为分，只能为整数
	 * @return
	 */
	public Integer getCashFee() {
		return cashFee;
	}
	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}
	/**
	 * 现金退款金额，单位为分，只能为整数
	 * @return
	 */
	public Integer getCashRefundFee() {
		return cashRefundFee;
	}
	public void setCashRefundFee(Integer cashRefundFee) {
		this.cashRefundFee = cashRefundFee;
	}

}
