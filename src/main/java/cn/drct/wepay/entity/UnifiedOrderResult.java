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
	private String tradeType;
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
	/**
	 * 交易类型<br>
	 * 默认为小程序：JSAPI,JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
	 * @return
	 */
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
}
