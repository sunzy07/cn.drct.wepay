package cn.drct.wepay.entity;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Test;

import cn.drct.wepay.util.ReflectUtil;
import cn.drct.wepay.util.WXPayUtil;

public class XmlToObjectTest {

	private String readResourceFile(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(ClassLoader
					.getSystemResource(path).toURI())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void GroupRedpack() throws Exception {
		Map<String, String> values = WXPayUtil
				.xmlToMap(readResourceFile("GroupRedpack.xml"));
		GroupRedpack object = ReflectUtil.toObject(values, GroupRedpack.class);
		assertEquals("0010010404201411170000046545", object.getMchBillno());
		assertEquals("send_name", object.getSendName());
		assertEquals("onqOjjmM1tad-3ROpncN-yUfa6uI", object.getReOpenid());
		assertEquals(new Integer(600), object.getTotalAmount());
		assertEquals("ALL_RAND", object.getAmtType());
		assertEquals(new Integer(3), object.getTotalNum());
		assertEquals("恭喜发财", object.getWishing());
		assertEquals("新年红包", object.getActName());
		assertEquals("新年红包", object.getRemark());
		assertEquals("PRODUCT_2", object.getSceneId());
		assertEquals("posttime%3d123123412%26clientversion%3d234134%26mobile%3d122344545%26deviceid%3dIOS",object.getRiskInfo());
		assertEquals("10000097", object.getConsumeMchId());
		values = WXPayUtil.xmlToMap(WXPayUtil.mapToXml(ReflectUtil.toMap(object)));
		GroupRedpack object2 = ReflectUtil.toObject(values, GroupRedpack.class);
		same(object, object2);
		assertEquals(12, getNotNullCount(object));
	}
	
	
	@Test
	public void allTest() throws Exception {
		baseTest(Order.class, 12);
		baseTest(Redpack.class, 12);
		baseTest(RedpackResult.class, 4);
		baseTest(Refund.class, 5);
		baseTest(RefundResult.class, 6);
		baseTest(UnifiedOrder.class, 9);
		baseTest(UnifiedOrderResult.class, 2);
	}
	
	
	private <T> void baseTest(Class<T>clazz,int count) throws Exception{
		Map<String, String> values = WXPayUtil
				
				.xmlToMap(readResourceFile(clazz.getSimpleName()+".xml"));
		T object = ReflectUtil.toObject(values, clazz);
		assertEquals(count, getNotNullCount(object));
		
		values = WXPayUtil.xmlToMap(WXPayUtil.mapToXml(ReflectUtil.toMap(object)));
		T object2 = ReflectUtil.toObject(values, clazz);
		same(object, object2);
	}
	
	
	private <T> void same(T object,T object2) throws IllegalArgumentException, IllegalAccessException{
		Map<String, Field> map = ReflectUtil.getFieldMap(object.getClass());
		for (Field field : map.values()) {
			assertEquals(field.getName(),field.get(object), field.get(object2));
		}
	}
	
	
	private <T> int getNotNullCount(T object) throws IllegalArgumentException, IllegalAccessException{
		int count =0;
		Map<String, Field> map = ReflectUtil.getFieldMap(object.getClass());
		for (Field field : map.values()) {
			if(field.get(object)!=null)
				count++;
		}
		return count;
	}
	


}
