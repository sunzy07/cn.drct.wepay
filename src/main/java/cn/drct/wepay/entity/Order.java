package cn.drct.wepay.entity;

public class Order {
	private String deviceInfo;
	private String openid;
	private String isSubscribe;
	private String tradeType;
	private String tradeState;
	private String bankType;
	private Integer totalFee;
	private Integer settlementTotalFee;
	private String feeType;
	private Integer cashFee;
	private String cashFeeType;
	private String transactionId;
	private String outTradeNo;
	private String attach;

	private String timeEnd;
	private String tradeStateDesc;
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
	 * 用户标识
	 * @return
	 */
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 用户是否关注公众账号，Y-关注，N-未关注
	 * @return
	 */
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	/**
	 * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP
	 * @return
	 */
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * 交易状态 SUCCESS—支付成功<br>
	 * REFUND—转入退款<br>
	 * NOTPAY—未支付<br>
	 * CLOSED—已关闭<br>
	 * REVOKED—已撤销（刷卡支付）<br>
	 * USERPAYING--用户支付中<br>
	 * PAYERROR--支付失败(其他原因，如银行返回失败)
	 * @return
	 */
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	/**
	 * 付款银行
	 * @return
	 */
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	/**
	 * 订单金额
	 * @return
	 */
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * 应结订单金额
	 * @return
	 */
	public Integer getSettlementTotalFee() {
		return settlementTotalFee;
	}
	public void setSettlementTotalFee(Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	/**
	 * 货币种类
	 * @return
	 */
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	/**
	 * 现金支付金额
	 * @return
	 */
	public Integer getCashFee() {
		return cashFee;
	}
	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}
	/**
	 * 现金支付货币类型
	 * @return
	 */
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	/**
	 * 微信支付订单号
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
	 * 附加数据
	 * @return
	 */
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	/**
	 * 支付完成时间 格式为yyyyMMddHHmmss
	 * @return
	 */
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	/**
	 * 交易状态描述
	 * @return
	 */
	public String getTradeStateDesc() {
		return tradeStateDesc;
	}
	public void setTradeStateDesc(String tradeStateDesc) {
		this.tradeStateDesc = tradeStateDesc;
	}
	
}
