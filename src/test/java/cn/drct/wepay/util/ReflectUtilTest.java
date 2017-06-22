package cn.drct.wepay.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import cn.drct.wepay.entity.UnifiedOrder;

public class ReflectUtilTest {

	@Test
	public void testGetFieldMap() {
		UnifiedOrder order = new UnifiedOrder();
		order.setBody("fdsfdsfsd");
		order.setNotifyUrl("dddd");
		order.setOutTradeNo("fd");
		order.setTotalFee(3432);
		Map<String, String> values = ReflectUtil.toMap(order);
		assertEquals(5,values.size());
		assertEquals(order.getTotalFee()+"",values.get("total_fee"));
		assertEquals(order.getBody(),values.get("body"));
		assertEquals(order.getNotifyUrl(),values.get("notify_url"));
		assertEquals(order.getOutTradeNo(),values.get("out_trade_no"));
		UnifiedOrder order2 = ReflectUtil.toObject(values, UnifiedOrder.class);
		assertEquals(order.getTotalFee(),order2.getTotalFee());
		assertEquals(order.getBody(),order2.getBody());
		assertEquals(order.getNotifyUrl(),order2.getNotifyUrl());
		assertEquals(order.getOutTradeNo(),order2.getOutTradeNo());
	}

}
