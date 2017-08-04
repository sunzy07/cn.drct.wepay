package cn.drct.wepay.entity;

public class RedpackOrder {
	private String mchBillno;
	private String detailId;
	private String status;
	private String sendType;
	private String hbType;
	private Integer totalNum;
	private Integer totalAmount;
	private String reason;
	private String sendTime;
	private String refundTime;
	private Integer refundAmount;
	private String wishing;
	private String remark;
	private String actName;
	private String hblist;
	/**
	 * 商户订单号
	 * @return
	 */
	public String getMchBillno() {
		return mchBillno;
	}
	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}
	/**
	 * 红包单号
	 * @return
	 */
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	/**
	 * 红包状态<br>
	 * SENDING:发放中<br> 
	 * SENT:已发放待领取<br> 
	 * FAILED：发放失败<br> 
	 * RECEIVED:已领取<br> 
	 * RFUND_ING:退款中<br> 
	 * REFUND:已退款<br>
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 发放类型<br>
	 * API:通过API接口发放<br> 
	 * UPLOAD:通过上传文件方式发放<br> 
	 * ACTIVITY:通过活动方式发放<br>
	 * @return
	 */
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	/**
	 * 红包类型<br>
	 * GROUP:裂变红包<br>
	 * NORMAL:普通红包<br>
	 * @return
	 */
	public String getHbType() {
		return hbType;
	}
	public void setHbType(String hbType) {
		this.hbType = hbType;
	}
	/**
	 * 红包个数
	 * @return
	 */
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 红包总金额（单位分）
	 * @return
	 */
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 发送失败原因
	 * @return
	 */
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 红包发送时间 2015-04-21 20:00:00
	 * @return
	 */
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * 红包退款时间 2015-04-21 20:00:00
	 * @return
	 */
	public String getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 红包退款金额 2015-04-21 20:00:00
	 * @return
	 */
	public Integer getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}
	/**
	 * 祝福语
	 * @return
	 */
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	/**
	 * 活动描述
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 活动名称
	 * @return
	 */
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	/**
	 * 裂变红包领取列表<br>
	 * openid:领取红包的openid<br>
	 * amount:领取金额<br>
	 * rcv_time:领取红包的时间<br>
	 * @return
	 */
	public String getHblist() {
		return hblist;
	}
	public void setHblist(String hblist) {
		this.hblist = hblist;
	}
}

 