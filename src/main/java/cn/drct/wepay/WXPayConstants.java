package cn.drct.wepay;

/**
 * 常量
 */
public class WXPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";
    public static final String BASE_URL="https://api.mch.weixin.qq.com/";
    public static final String SANDBOX_BASE_URL=BASE_URL+"sandboxnew/";

    public static final String UNIFIEDORDER_URL = BASE_URL+"pay/unifiedorder";
    public static final String ORDERQUERY_URL   = BASE_URL+"pay/orderquery";
    public static final String CLOSEORDER_URL   = BASE_URL+"pay/closeorder";
    public static final String REFUND_URL       = BASE_URL+"secapi/pay/refund";
    public static final String REFUNDQUERY_URL  = BASE_URL+"pay/refundquery";
    public static final String DOWNLOADBILL_URL = BASE_URL+"pay/downloadbill";
    public static final String REPORT_URL       = BASE_URL+"payitil/report";
    public static final String TRANSFERS_URL = BASE_URL+"mmpaymkttransfers/promotion/transfers";
    public static final String TRANSFERSQUERY_URL = BASE_URL+"mmpaymkttransfers/promotion/gettransferinfo";
    public static final String REDPACK_URL = BASE_URL+"mmpaymkttransfers/sendredpack";
    public static final String GROUPREDPACK_URL =BASE_URL+"mmpaymkttransfers/sendgroupredpack";
    public static final String REDPACKQUERY_URL = BASE_URL+"mmpaymkttransfers/gethbinfo";
    
    // sandbox
    public static final String SANDBOX_UNIFIEDORDER_URL = SANDBOX_BASE_URL+"pay/unifiedorder";
    public static final String SANDBOX_ORDERQUERY_URL   = SANDBOX_BASE_URL+"pay/orderquery";
    public static final String SANDBOX_CLOSEORDER_URL   = SANDBOX_BASE_URL+"pay/closeorder";
    public static final String SANDBOX_REFUND_URL       = SANDBOX_BASE_URL+"secapi/pay/refund";
    public static final String SANDBOX_REFUNDQUERY_URL  = SANDBOX_BASE_URL+"pay/refundquery";
    public static final String SANDBOX_DOWNLOADBILL_URL = SANDBOX_BASE_URL+"pay/downloadbill";
    public static final String SANDBOX_REPORT_URL       = SANDBOX_BASE_URL+"payitil/report";
    public static final String SANDBOX_TRANSFERS_URL = SANDBOX_BASE_URL+"mmpaymkttransfers/promotion/transfers";
    public static final String SANDBOX_TRANSFERSQUERY_URL = SANDBOX_BASE_URL+"mmpaymkttransfers/promotion/gettransferinfo";
    public static final String SANDBOX_REDPACK_URL = SANDBOX_BASE_URL+"mmpaymkttransfers/sendredpack";
    public static final String SANDBOX_GROUPREDPACK_URL = SANDBOX_BASE_URL+"mmpaymkttransfers/sendgroupredpack";
    public static final String SANDBOX_REDPACKQUERY_URL = SANDBOX_BASE_URL+"mmpaymkttransfers/gethbinfo";
    
    public static final String SANDBOX_SignKey_URL = SANDBOX_BASE_URL+"pay/getsignkey";
    


}