package cn.drct.wepay;


import cn.drct.wepay.WXPayConstants.SignType;

public interface WXPayConfig {


    /**
     * 获取 App ID
     *
     * @return App ID
     */
    public String getAppID();


    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    public String getMchID();


    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public String getKey();
    

    public void setKey(String key);


    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    public byte[] getCertBytes();

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpConnectTimeoutMs();

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpReadTimeoutMs();
    
    /**
     * 签名类型算法
     * @return
     */
    public SignType getSignType();
    
    /**
     * 是否使用沙盒测试huangjk
     * @return
     */
    public boolean isUseSandbox();
    

}
