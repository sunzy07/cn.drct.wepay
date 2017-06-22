package cn.drct.wepay.entity;

public class GroupRedpack extends Redpack {
	private String amtType = "ALL_RAND";

	public String getAmtType() {
		return amtType;
	}

	/**
	 * 红包金额设置方式，最长32<br>
	 * ALL_RAND—全部随机,商户指定总金额和红包发放总人数，由微信支付随机计算出各红包金额
	 * @param amtType
	 */
	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}
}
