package cn.drct.wepay.entity;

public class Transfers {
	private String partnerTradeNo;
	private String openid;
	private String checkName;
	private String reUserName;
	private String amount;
	private String desc;
	private String spbillCreateIp;
	
	
	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}
	/**
	 * 商户订单号，需保持唯一性
	 * @param partnerTradeNo
	 */
	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCheckName() {
		return checkName;
	}
	/**
	 * NO_CHECK：不校验真实姓名 
	 * FORCE_CHECK：强校验真实姓名
	 * @param checkName
	 */
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getReUserName() {
		return reUserName;
	}
	/**
	 * 收款用户真实姓名。 
	 * 如果check_name设置为FORCE_CHECK，则必填用户真实姓名
	 * @param reUserName
	 */
	public void setReUserName(String reUserName) {
		this.reUserName = reUserName;
	}
	public String getAmount() {
		return amount;
	}
	/**
	 * 企业付款金额，单位为分
	 * @param amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDesc() {
		return desc;
	}
	/**
	 * 企业付款操作说明信息。必填。
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	/**
	 * 调用接口的机器Ip地址
	 * @param spbillCreateIp
	 */
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
}
