package cn.drct.wepay.entity;


/**
 * 统一下单返回结果
 * 
 * @author sunzy
 *
 */
public class UnifiedOrderResult {
	private String prepayId;
	private String codeUrl;
	/**
	 * 预支付交易会话标识
	 * @return
	 */
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	/**
	 * 二维码链接
	 * @return
	 */
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
}
