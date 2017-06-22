package cn.drct.wepay.entity;

/**
 * 红包：除了sceneId，riskInfo，consumeMchId属性，都是必填项
 * @author Administrator
 *
 */
public class Redpack {
	private String mchBillno;
	private String sendName;
	private String reOpenid;
	private Integer totalAmount;
	private Integer totalNum=1;
	private String wishing;
	private String clientIp;
	private String actName;
	private String remark;
	private String sceneId;
	private String riskInfo;
	private String consumeMchId;
	public String getMchBillno() {
		return mchBillno;
	}
	/**商户订单号，最长28
	 * @param mchBillno
	 */
	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}
	public String getSendName() {
		return sendName;
	}
	/**
	 * 商户名字，最长32
	 * @param sendName
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getReOpenid() {
		return reOpenid;
	}
	/**
	 * 接受红包用户
	 * @param reOpenid
	 */
	public void setReOpenid(String reOpenid) {
		this.reOpenid = reOpenid;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 付款金额，单位为分
	 * @param totalAmount
	 */
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	/**
	 * 红包发送总人数
	 * @param totalNum
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public String getWishing() {
		return wishing;
	}
	/**
	 * 祝福语，最长128
	 * @param wishing
	 */
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getClientIp() {
		return clientIp;
	}
	/**
	 * 调用接口的机器Ip地址
	 * @param clientIp
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getActName() {
		return actName;
	}
	/**
	 * 活动名字，最长32
	 * @param actName
	 */
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getRemark() {
		return remark;
	}
	/**
	 * 备注信息，最长256
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSceneId() {
		return sceneId;
	}
	/**
	 * 场景，红包金额大于200时必传<br>
	 * PRODUCT_1:商品促销<br>
	 * PRODUCT_2:抽奖<br>
	 * PRODUCT_3:虚拟物品兑奖<br> 
	 * PRODUCT_4:企业内部福利<br>
	 * PRODUCT_5:渠道分润<br>
	 * PRODUCT_6:保险回馈<br>
	 * PRODUCT_7:彩票派奖<br>
	 * PRODUCT_8:税务刮奖<br>
	 * 
	 * @param sceneId
	 */
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getRiskInfo() {
		return riskInfo;
	}
	/**
	 * 活动信息，最长128<br>
	 * posttime:用户操作的时间戳<br>
	 * mobile:业务系统账号的手机号，国家代码-手机号。不需要+号<br>
	 * deviceid :mac 地址或者设备唯一标识<br> 
	 * clientversion :用户操作的客户端版本<br>
	 * 把值为非空的信息用key=value进行拼接，再进行urlencode<br>
	 * urlencode(posttime=xx& mobile =xx&deviceid=xx)<br>
	 * @param riskInfo
	 */
	public void setRiskInfo(String riskInfo) {
		this.riskInfo = riskInfo;
	}
	public String getConsumeMchId() {
		return consumeMchId;
	}
	/**
	 * 资金授权商户号，最长32
	 * 服务商替特约商户发放时使用
	 * @param consumeMchId
	 */
	public void setConsumeMchId(String consumeMchId) {
		this.consumeMchId = consumeMchId;
	}
}
