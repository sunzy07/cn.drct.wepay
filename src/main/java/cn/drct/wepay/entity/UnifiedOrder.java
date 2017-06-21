package cn.drct.wepay.entity;


/**
 * body,outTradeNo,totalFee,spbillCreateIp,notifyUrl,tradeType,openid必填项
 * 
 * @author sunzy
 *
 */
public class UnifiedOrder {
	private String body;
	private String outTradeNo;
	private Integer totalFee;
	private String spbillCreateIp;
	private String notifyUrl;
	private String tradeType="JSAPI";
	private String openid;
	
	private String deviceInfo;
	private String detail;
	private String attach;
	private String feeType;
	private String timeStart;
	private String timeExpire;
	private String goodsTag;
	
	public String getDeviceInfo() {
		return deviceInfo;
	}
	
	/**
	 * 设备号,最长32字节
	 * @param deviceInfo
	 */
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getDetail() {
		return detail;
	}
	
	/**
	 * 商品详情，最长6000
	 * @param detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	
	/**
	 * 附加数据，最长127
	 * @param attach
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getFeeType() {
		return feeType;
	}
	/**
	 * 货币类型，最长16，默认为CNY
	 * @param feeType
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getTimeStart() {
		return timeStart;
	}
	/**
	 * 交易发起时间，格式为yyyyMMddHHmmss
	 * @param timeStart
	 */
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeExpire() {
		return timeExpire;
	}
	/**
	 * 交易截止数据
	 * @param timeExpire
	 */
	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}
	public String getGoodsTag() {
		return goodsTag;
	}
	/**
	 * 商品标记，最长32
	 * @param goodsTag
	 */
	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}
	public String getLimitPay() {
		return limitPay;
	}
	/**
	 * 指定支付方式，no_credit指定不能使用信用卡支付
	 * @param limitPay
	 */
	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}
	private String limitPay;
	
	public String getBody() {
		return body;
	}
	/**
	 * 商品描述，最长128
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * 商户订单号，最长32
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public int getTotalFee() {
		return totalFee;
	}
	/**
	 * 单位为分，>0
	 * @param totalFee
	 */
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	/**
	 * 交易发现终端Ip，最长16
	 * @param spbillCreateIp
	 */
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * 交易状态通知地址，最长256
	 * @param notifyUrl
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getTradeType() {
		return tradeType;
	}
	/**
	 * 交易类型，默认为小程序：JSAPI,JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
	 * @param tradeType
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getOpenid() {
		return openid;
	}
	/**
	 * 用户标识,最长128，从小程序获取的code换取
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
