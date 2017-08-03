package cn.drct.wepay;

import javax.net.ssl.*;

import cn.drct.wepay.WXPayConstants.SignType;
import cn.drct.wepay.entity.GroupRedpack;
import cn.drct.wepay.entity.Order;
import cn.drct.wepay.entity.Redpack;
import cn.drct.wepay.entity.RedpackOrder;
import cn.drct.wepay.entity.RedpackResult;
import cn.drct.wepay.entity.Refund;
import cn.drct.wepay.entity.RefundResult;
import cn.drct.wepay.entity.UnifiedOrder;
import cn.drct.wepay.entity.UnifiedOrderResult;
import cn.drct.wepay.exception.MsgException;
import cn.drct.wepay.exception.SignatureValidException;
import cn.drct.wepay.exception.TradeException;
import cn.drct.wepay.util.ReflectUtil;
import cn.drct.wepay.util.WXPayUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class WXPay {
	private String UTF8 = "UTF-8";
	private String RETURN_CODE = "return_code";
	private String RESULT_CODE = "result_code";
	private WXPayConfig config;

	public WXPay(final WXPayConfig config) {
		this.config = config;
		try {
			char[] password = config.getMchID().toCharArray();
			KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(new ByteArrayInputStream(config.getCertBytes()), password);
			KeyManagerFactory kmf = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, password);
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());
			if(config.isUseSandbox()){
				config.setKey(getSandBoxSignKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向 Map 中添加 appid、mch_id、nonce_str、sign_type、sign <br>
	 * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
	 *
	 * @param reqData
	 * @return
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	private Map<String, String> fillRequestData(Map<String, String> reqData)
			throws MsgException, TradeException,MsgException,Exception {
		reqData.put("appid", config.getAppID());
		reqData.put("mch_id", config.getMchID());
		reqData.put("nonce_str", WXPayUtil.generateNonceStr());
		if (SignType.MD5.equals(config.getSignType())) {
			reqData.put("sign_type", WXPayConstants.MD5);
		} else if (SignType.HMACSHA256.equals(config.getSignType())) {
			reqData.put("sign_type", WXPayConstants.HMACSHA256);
		}
		reqData.put("sign", WXPayUtil.generateSignature(reqData,
				config.getKey(), config.getSignType()));
		return reqData;
	}
	
	
	/**
	 * 向 Map 中添加 wxappid、mch_id、nonce_str、sign_type、sign <br>
	 * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
	 *
	 * @param reqData
	 * @return
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	private Map<String, String> fillRedpackRequestData(Map<String, String> reqData)
			throws MsgException, TradeException,MsgException,Exception {
		reqData.put("wxappid", config.getAppID());
		reqData.put("mch_id", config.getMchID());
		reqData.put("nonce_str", WXPayUtil.generateNonceStr());
		reqData.put("sign", WXPayUtil.generateSignature(reqData,
				config.getKey(), config.getSignType()));
		return reqData;
	}
	

	/**
	 * 判断xml数据的sign是否有效，必须包含sign字段，否则返回false。
	 *
	 * @param reqData
	 *            向wxpay post的请求数据
	 * @return 签名是否有效
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	private boolean isResponseSignatureValid(Map<String, String> reqData)
			throws MsgException, TradeException,MsgException,Exception {
		// 返回数据的签名方式和请求中给定的签名方式是一致的
		return WXPayUtil.isSignatureValid(reqData, this.config.getKey(),
				config.getSignType());
	}

	/**
	 * 判断支付结果通知中的sign是否有效
	 *
	 * @param reqData
	 *            向wxpay post的请求数据
	 * @return 签名是否有效
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public boolean isPayResultNotifySignatureValid(Map<String, String> reqData)
			throws MsgException, TradeException,MsgException,Exception {
		String signTypeInData = reqData.get(WXPayConstants.FIELD_SIGN_TYPE);
		SignType signType;
		if (signTypeInData == null) {
			signType = SignType.MD5;
		} else {
			signTypeInData = signTypeInData.trim();
			if (signTypeInData.length() == 0) {
				signType = SignType.MD5;
			} else if (WXPayConstants.MD5.equals(signTypeInData)) {
				signType = SignType.MD5;
			} else if (WXPayConstants.HMACSHA256.equals(signTypeInData)) {
				signType = SignType.HMACSHA256;
			} else {
				throw new Exception(String.format("Unsupported sign_type: %s",
						signTypeInData));
			}
		}
		return WXPayUtil.isSignatureValid(reqData, this.config.getKey(),
				signType);
	}

	/**
	 * 不需要证书的请求
	 * 
	 * @param strUrl
	 *            String
	 * @param reqData
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	protected String requestWithoutCert(String strUrl,
			Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs)
			throws IOException,Exception {
		String reqBody = WXPayUtil.mapToXml(reqData);
		URL httpUrl = new URL(strUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl
				.openConnection();
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setConnectTimeout(connectTimeoutMs);
		httpURLConnection.setReadTimeout(readTimeoutMs);
		httpURLConnection.connect();
		OutputStream outputStream = httpURLConnection.getOutputStream();
		outputStream.write(reqBody.getBytes(UTF8));

		// 获取内容
		InputStream inputStream = httpURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, UTF8));
		final StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
		}
		String resp = stringBuffer.toString();
		if (stringBuffer != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resp;
	}

	/**
	 * 需要证书的请求
	 * 
	 * @param strUrl
	 *            String
	 * @param reqData
	 *            向wxpay post的请求数据 Map
	 * @param connectTimeoutMs
	 *            超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	protected String requestWithCert(String strUrl, Map<String, String> reqData,
			int connectTimeoutMs, int readTimeoutMs) throws IOException,Exception {
		String reqBody = WXPayUtil.mapToXml(reqData);
		URL httpUrl = new URL(strUrl);

		HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl
				.openConnection();

		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setConnectTimeout(connectTimeoutMs);
		httpURLConnection.setReadTimeout(readTimeoutMs);
		httpURLConnection.connect();
		OutputStream outputStream = httpURLConnection.getOutputStream();
		outputStream.write(reqBody.getBytes(UTF8));

		// 获取内容
		InputStream inputStream = httpURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, UTF8));
		final StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
		}
		String resp = stringBuffer.toString();
		if (stringBuffer != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		// if (httpURLConnection!=null) {
		// httpURLConnection.disconnect();
		// }

		return resp;
	}

	/**
	 * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
	 * 
	 * @param xmlStr
	 *            API返回的XML格式数据
	 * @return Map类型数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	protected Map<String, String> processResponseXml(String xmlStr)
			throws MsgException, TradeException,MsgException,Exception{
		String return_code;
		Map<String, String> respData = WXPayUtil.xmlToMap(xmlStr);
		if (respData.containsKey(RETURN_CODE)) {
			return_code = respData.get(RETURN_CODE);
		} else {
			throw new MsgException(String.format("No `return_code` in XML: %s",
					xmlStr));
		}
		if (return_code.equals(WXPayConstants.SUCCESS)) {
			if (this.isResponseSignatureValid(respData)) {
				String result_code;
				if (respData.containsKey(RESULT_CODE)) {
					result_code = respData.get(RESULT_CODE);
				} else {
					throw new MsgException(String.format("No `result_code` in XML: %s",
							xmlStr));
				}
				if(result_code.equals(WXPayConstants.SUCCESS)){
					return respData;
				}else{
					throw new TradeException(respData.get("err_code"),respData.get("err_code_des"));
				}
				
			} else {
				throw new SignatureValidException(String.format(
						"Invalid sign value in XML: %s", xmlStr));
			}
		} else {
			throw new MsgException(String.format(
					"return_code value %s is invalid in XML: %s", return_code,
					xmlStr));
		}
	}
	
	public <T> T processResponseXml(String xmlStr,Class<T>clazz)
			throws MsgException, TradeException,MsgException,Exception {
		return ReflectUtil.toObject(this.processResponseXml(xmlStr),clazz);
	}


	/**
	 * 作用：统一下单<br>
	 * 场景：公共号支付、扫码支付、APP支付
	 * 
	 * @param order
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public UnifiedOrderResult unifiedOrder(UnifiedOrder order)
			throws MsgException, TradeException,MsgException,Exception {
		return this.unifiedOrder(order, config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：统一下单<br>
	 * 场景：公共号支付、扫码支付、APP支付
	 * 
	 * @param order
	 *           商户订单号
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public UnifiedOrderResult unifiedOrder(UnifiedOrder order,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_UNIFIEDORDER_URL;
		} else {
			url = WXPayConstants.UNIFIEDORDER_URL;
		}
		String respXml = this.requestWithoutCert(url,
				this.fillRequestData(ReflectUtil.toMap(order)), connectTimeoutMs, readTimeoutMs);
		return processResponseXml(respXml,UnifiedOrderResult.class);
	}

	/**
	 * 作用：查询订单<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param outTradeNo
	 *           商户订单号
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Order orderQuery(String outTradeNo)
			throws MsgException, TradeException,MsgException,Exception {
		return this.orderQuery(outTradeNo, config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：查询订单<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param outTradeNo
	 *            商户订单号
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Order orderQuery(String outTradeNo,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("out_trade_no", outTradeNo);
		String url;
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_ORDERQUERY_URL;
		} else {
			url = WXPayConstants.ORDERQUERY_URL;
		}
		String respXml = this.requestWithoutCert(url,
				this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
		return processResponseXml(respXml,Order.class);
	}

	
	/**
	 * 作用：关闭订单<br>
	 * 场景：公共号支付、扫码支付、APP支付
	 * 
	 * @param outTradeNo
	 *            商户订单号
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public boolean closeOrder(String outTradeNo)
			throws MsgException, TradeException,MsgException,Exception {
		return this.closeOrder(outTradeNo, config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：关闭订单<br>
	 * 场景：公共号支付、扫码支付、APP支付
	 * 
	 * @param outTradeNo
	 *            商户订单号
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public boolean closeOrder(String outTradeNo,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("out_trade_no", outTradeNo);
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_CLOSEORDER_URL;
		} else {
			url = WXPayConstants.CLOSEORDER_URL;
		}
		String respXml = this.requestWithoutCert(url,
				this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
		Map<String, String> result = this.processResponseXml(respXml);
		String resultCode = result.get("result_code");
		if(resultCode!=null){
			if(resultCode.equals("SUCCESS")){
				return true;
			}
		}
		return false;
	}

	/**
	 * 作用：申请退款<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param refund
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RefundResult refund(Refund refund)
			throws MsgException, TradeException,MsgException,Exception {
		return this.refund(refund, this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：申请退款<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
	 * 其他：需要证书
	 * 
	 * @param refund
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RefundResult refund(Refund refund,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_REFUND_URL;
		} else {
			url = WXPayConstants.REFUND_URL;
		}
		String respXml = this.requestWithCert(url,
				this.fillRequestData(ReflectUtil.toMap(refund)), connectTimeoutMs, readTimeoutMs);
		return processResponseXml(respXml,RefundResult.class);
	}
	
	/**
	 * 作用：退款查询<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param outRefundNo
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Map<String, String> refundQuery(String outRefundNo)
			throws MsgException, TradeException,MsgException,Exception {
		return this.refundQuery(outRefundNo, this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：退款查询<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param outRefundNo
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Map<String, String> refundQuery(String outRefundNo,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("out_refund_no", outRefundNo);
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_REFUNDQUERY_URL;
		} else {
			url = WXPayConstants.REFUNDQUERY_URL;
		}
		String respXml = this.requestWithoutCert(url,
				this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
		return this.processResponseXml(respXml);
	}

	
	/**
	 * 作用：微信红包<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param redpack
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RedpackResult redpack(Redpack redpack)
			throws MsgException, TradeException,MsgException,Exception {
		return this.redpack(redpack, this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：微信红包<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
	 * 其他：需要证书
	 * 
	 * @param redpack
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RedpackResult redpack(Redpack redpack,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_REDPACK_URL;
		} else {
			url = WXPayConstants.REDPACK_URL;
		}
		String respXml = this.requestWithCert(url,
				this.fillRedpackRequestData(ReflectUtil.toMap(redpack)), connectTimeoutMs, readTimeoutMs);
		return processResponseXml(respXml,RedpackResult.class);
	}
	
	/**
	 * 作用：发送微信分裂红包<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param groupRedpack
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RedpackResult groupRedpack(GroupRedpack groupRedpack)
			throws MsgException, TradeException,MsgException,Exception {
		return this.groupRedpack(groupRedpack, this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：发送微信分裂红包<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
	 * 其他：需要证书
	 * 
	 * @param groupRedpack
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RedpackResult groupRedpack(GroupRedpack groupRedpack,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_GROUPREDPACK_URL;
		} else {
			url = WXPayConstants.GROUPREDPACK_URL;
		}
		String respXml = this.requestWithCert(url,
				this.fillRedpackRequestData(ReflectUtil.toMap(groupRedpack)), connectTimeoutMs, readTimeoutMs);
		return processResponseXml(respXml,RedpackResult.class);
	}
	
	/**
	 * 作用：查询微信红包<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param mchBillno
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RedpackOrder redpackQuery(String mchBillno)
			throws MsgException, TradeException,MsgException,Exception {
		return this.redpackQuery(mchBillno, this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：微信红包查询<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
	 * 其他：需要证书
	 * 
	 * @param mchBillno
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public RedpackOrder redpackQuery(String mchBillno,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("mch_billno", mchBillno);
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_REDPACKQUERY_URL;
		} else {
			url = WXPayConstants.REDPACKQUERY_URL;
		}
		String respXml = this.requestWithCert(url,
				this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
		return processResponseXml(respXml,RedpackOrder.class);
	}

	
	/**
	 * 作用：对账单下载（成功时返回对账单数据，失败时返回XML格式数据）<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param reqData
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Map<String, String> downloadBill(Map<String, String> reqData)
			throws MsgException, TradeException,MsgException,Exception {
		return this.downloadBill(reqData,
				this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：对账单下载<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
	 * 其他：无论是否成功都返回Map。若成功，返回的Map中含有return_code、return_msg、data，
	 * 其中return_code为`SUCCESS`，data为对账单数据。
	 * 
	 * @param reqData
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return 经过封装的API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Map<String, String> downloadBill(Map<String, String> reqData,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_DOWNLOADBILL_URL;
		} else {
			url = WXPayConstants.DOWNLOADBILL_URL;
		}
		String respStr = this.requestWithoutCert(url,
				this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs)
				.trim();
		Map<String, String> ret;
		// 出现错误，返回XML数据
		if (respStr.indexOf("<") == 0) {
			ret = WXPayUtil.xmlToMap(respStr);
		} else {
			// 正常返回csv数据
			ret = new HashMap<String, String>();
			ret.put("return_code", WXPayConstants.SUCCESS);
			ret.put("return_msg", "ok");
			ret.put("data", respStr);
		}
		return ret;
	}

	/**
	 * 作用：交易保障<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param reqData
	 *            向wxpay post的请求数据
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Map<String, String> report(Map<String, String> reqData)
			throws MsgException, TradeException,MsgException,Exception {
		return this.report(reqData, this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
	}

	/**
	 * 作用：交易保障<br>
	 * 场景：刷卡支付、公共号支付、扫码支付、APP支付
	 * 
	 * @param reqData
	 *            向wxpay post的请求数据
	 * @param connectTimeoutMs
	 *            连接超时时间，单位是毫秒
	 * @param readTimeoutMs
	 *            读超时时间，单位是毫秒
	 * @return API返回数据
	 * @throws MsgException, TradeException,MsgException,Exception
	 */
	public Map<String, String> report(Map<String, String> reqData,
			int connectTimeoutMs, int readTimeoutMs) throws MsgException, TradeException,MsgException,Exception {
		String url;
		if (config.isUseSandbox()) {
			url = WXPayConstants.SANDBOX_REPORT_URL;
		} else {
			url = WXPayConstants.REPORT_URL;
		}
		String respXml = this.requestWithoutCert(url,
				this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
		return WXPayUtil.xmlToMap(respXml);
	}
	
	public String getSandBoxSignKey() throws MsgException, TradeException,MsgException,Exception{
		Map<String, String> reqData = new HashMap<String, String>();
		reqData.put("mch_id", config.getMchID());
		reqData.put("nonce_str", WXPayUtil.generateNonceStr());
		reqData.put("sign", WXPayUtil.generateSignature(reqData,config.getKey(), WXPayConstants.SignType.MD5));
		String respXml = this.requestWithoutCert(WXPayConstants.SANDBOX_SignKey_URL,
				this.fillRequestData(reqData), this.config.getHttpConnectTimeoutMs(),
				this.config.getHttpReadTimeoutMs());
		Map<String, String> respData = WXPayUtil.xmlToMap(respXml);
		return respData.get("sandbox_signkey");
	}
	
	
	public String generateWeappPaySign(String prepay_id,String nonceStr,long timestamp) throws Exception{
		Map<String, String> reqData =new  HashMap<String, String>();
		reqData.put("appId", config.getAppID());
		reqData.put("package", "prepay_id="+prepay_id);
		reqData.put("nonceStr", nonceStr);
		reqData.put("timeStamp",timestamp+"");
		reqData.put("signType","MD5");
		return WXPayUtil.generateSignature(reqData,config.getKey(), WXPayConstants.SignType.MD5);
	}

} // end class
