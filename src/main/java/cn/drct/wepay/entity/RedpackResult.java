package cn.drct.wepay.entity;

public class RedpackResult {
	private String mchBillno;
	private String reOpenid;
	private Integer totalAmount;
	private String sendListid;
	/**
	 * 商户订单号<br>
	 * 每个订单号必须唯一<br>
	 * @return
	 */
	public String getMchBillno() {
		return mchBillno;
	}
	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}
	/**
	 * 接受收红包的用户
	 * @return
	 */
	public String getReOpenid() {
		return reOpenid;
	}
	public void setReOpenid(String reOpenid) {
		this.reOpenid = reOpenid;
	}
	/**
	 * 付款总金额，单位分
	 * @return
	 */
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 微信红包订单号
	 * @return
	 */
	public String getSendListid() {
		return sendListid;
	}
	public void setSendListid(String sendListid) {
		this.sendListid = sendListid;
	}
}
