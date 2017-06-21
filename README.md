微信支付
------

对[微信支付开发者文档](https://pay.weixin.qq.com/wiki/doc/api/index.html)中给出的API进行了封装。

对wxpay-sdk进行进行的封装，使其更易用

cn.drct.WxPay类下提供了对应的方法：

|方法名 | 说明 |
|--------|--------|
|microPay| 刷卡支付 |
|unifiedOrder | 统一下单|
|orderQuery | 查询订单 |
|reverse | 撤销订单 |
|closeOrder|关闭订单|
|refund|申请退款|
|refundQuery|查询退款|
|downloadBill|下载对账单|
|report|交易保障|
|shortUrl|转换短链接|
|authCodeToOpenid|授权码查询openid|


## License
BSD
