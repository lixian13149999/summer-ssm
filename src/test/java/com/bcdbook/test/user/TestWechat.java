package com.bcdbook.test.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.wechat.message.pojo.Message;
import com.bcdbook.summer.wechat.service.ConnectService;
import com.bcdbook.summer.wechat.service.CoreService;
import com.bcdbook.summer.wechat.service.EncapsulationService;
import com.bcdbook.summer.wechat.service.EventService;
import com.bcdbook.summer.wechat.service.MaterialService;
import com.bcdbook.summer.wechat.service.MessageService;
import com.bcdbook.summer.wechat.service.TemplateService;

//import com.bcdbook.wechat.service.impl.ConnectService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestWechat {
	
	private static Logger logger = Logger.getLogger(TestWechat.class);
	
	@Resource
	private ConnectService connectService = null;
	@Resource
	private CoreService coreService = null;
	@Resource
	private MessageService messageService = null;
	@Resource
	private EventService eventService = null;
	@Resource
	private EncapsulationService encapsulationService = null;
	@Resource
	private MaterialService materialService = null;
	@Resource
	private TemplateService templateService = null;
	
	@Test
	public void testGetAccessToken(){
//		System.out.println(connectService.makeAccessToken());
		connectService.updateAccessToken(1);
	}
	
	@Test
	public void testCreateMenu(){
		System.out.println(connectService.createMenu());
	}
	
	@Test
	public void testEncapsulationTextMsg(){
//		
//		<ToUserName>gh_0e183f03e273<ToUserName/>
//		<FromUserName>o8yDuvoYJ-39Z1o9YRRjREfIfFL4<FromUserName/>
//		<CreateTime>1464159131<CreateTime/>
//		<MsgType>text<MsgType/>
//		<Content>1<Content/>
//		<MsgId>6288515584202222661<MsgId/>
//		
		Map<String, String> reqMsg = new HashMap<String, String>();
		reqMsg.put("ToUserName", "gh_0e183f03e273");
		reqMsg.put("FromUserName", "o8yDuvoYJ-39Z1o9YRRjREfIfFL4");
		reqMsg.put("CreateTime", "1464159131");
		reqMsg.put("MsgType", "text");
		reqMsg.put("Content", "1");
		reqMsg.put("MsgId", "6288515584202222661");
		
//		String respContent = "测试";
//		
		Message msg = messageService.getMsgByKeyword("文图");
		String respXML = encapsulationService.encapsulationMsg(reqMsg, msg);
		
		System.out.println(respXML);
		
	}
	
	@Test
	public void testMaterialService(){
//		{"voice_count":3,"video_count":0,"image_count":0,"news_count":0}
//		System.out.println(materialService.countMaterials());
		
//		{"item":[{"media_id":"mErowUdivp53pbwYC5N7cQk9MZGKfo70ZYIr3I8Ef8c","name":"测试语音3","update_time":1464576022000,1465273414234},{"media_id":"mErowUdivp53pbwYC5N7cQsgDFIqenVBc5yRFoTVarA","name":"测试语音2","update_time":1464576013},{"media_id":"mErowUdivp53pbwYC5N7cXSMwyjbxQlCSalIexy9-5E","name":"测试语音1","update_time":1464575998}],"total_count":3,"item_count":3}
//		{"item":[{"media_id":"mErowUdivp53pbwYC5N7cSLDFoji2Lq7x5K5GbE0H40","name":"测试图片4","update_time":1464700717,"url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5o1mE68gaFuYBIkvq4J21Eh3CDAYJP70uU9RRrIctTNU6JWDR8P3gRA\/0?wx_fmt=jpeg"},{"media_id":"mErowUdivp53pbwYC5N7cb5bu4n-0E6Kz9JRwDZsBuk","name":"测试图片3","update_time":1464700732,"url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5lP0VjdfganUMN80tHX0pKmzhPVMn6meORp4Dr5YicLZ1NeRamRl6JMw\/0?wx_fmt=jpeg"},{"media_id":"mErowUdivp53pbwYC5N7cdZHcaxtwbYbZLPcpnaK-UA","name":"测试图片2","update_time":1464700739,"url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5lDIaibicjMxCE9TmgwvQLGP6Fryg3pXHYazBsN6zJHLapEKla37TQYHg\/0?wx_fmt=jpeg"},{"media_id":"mErowUdivp53pbwYC5N7cf0Dq8nMnlYlh_RoRx-OBLY","name":"测试图片1","update_time":1464700696,"url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5zV8Znjm8wcUuFzDzb0vPDx1LaOFJNnzxQEs40muTicrDJtIQkoskiaLw\/0?wx_fmt=jpeg"}],"total_count":4,"item_count":4}
//		{"item":[{"media_id":"mErowUdivp53pbwYC5N7cRQ0ntcZj-1oPX09E5LrSBo","name":"papi酱003","update_time":1464702356},{"media_id":"mErowUdivp53pbwYC5N7cS6URBZzQfNGTm278exn9As","name":"papi酱001","update_time":1464702234}],"total_count":2,"item_count":2}
//		{"item":[{"media_id":"mErowUdivp53pbwYC5N7cTIGQ6rdRS35adixH6s2tss","content":{"news_item":[{"title":"文图消息标题","author":"文图消息作者","digest":"正文","content":"<p><br  \/><\/p><p>正文<\/p><p><img data-s=\"300,640\" data-type=\"jpeg\" data-src=\"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5TppkAZ1USuQ44yMO0I9S01PfeiafGcMWib6UCicaX0aPOlzCoE5nswH9Q\/0?wx_fmt=jpeg\" data-ratio=\"0.6258992805755396\" data-w=\"\"  \/><br  \/><\/p>","content_source_url":"http:\/\/www.baidu.com","thumb_media_id":"mErowUdivp53pbwYC5N7cZS1tm9BcwGvbUq-R4WbMew","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000016&idx=1&sn=f5436156e2fda081086c1d988ff0e6d4#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B58LBgjl2F5ToMSeMx6Gk1Ox8icwh4mZw3ScL9qrJSCcRibPGsbbZ16aKQ\/0?wx_fmt=jpeg"},{"title":"二级消息","author":"作者","digest":"正文","content":"<p>正文<\/p><p><img data-s=\"300,640\" data-type=\"jpeg\" data-src=\"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5YhuWy89UcYOSQXK54omXXPAWYMsFwkYJ7Ghicq3X9UfklibqIhs1ib1Vw\/0?wx_fmt=jpeg\" data-ratio=\"0.6258992805755396\" data-w=\"\"  \/><br  \/><\/p>","content_source_url":"","thumb_media_id":"mErowUdivp53pbwYC5N7cVwFSM54J4qLsTG71UdhgJI","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000016&idx=2&sn=af12257a24aba5f5dc97b9d360a04910#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5FlUicv6kCfAI1FGIGlomZUbu0glyZRGO9ibibevVyQK9HaO7h2RtCsodg\/0?wx_fmt=jpeg"}],"create_time":1464703272,"update_time":1464703272},"update_time":1464703272}],"total_count":1,"item_count":1}
//		{"item":[{"media_id":"mErowUdivp53pbwYC5N7cQonQ8MCSPtJ3gVJblKNJjc","content":{"news_item":[{"title":"标题","author":"作者","digest":"摘要","content":"<p>正文<\/p>","content_source_url":"http:\/\/strom.bcdbook.com","thumb_media_id":"mErowUdivp53pbwYC5N7cZS1tm9BcwGvbUq-R4WbMew","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000075&idx=1&sn=53b9ceb6685a6de343a7cd90415f3297#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B58LBgjl2F5ToMSeMx6Gk1Ox8icwh4mZw3ScL9qrJSCcRibPGsbbZ16aKQ\/0?wx_fmt=jpeg"}],"create_time":1464848761,"update_time":1464848761},"update_time":1464848761},{"media_id":"mErowUdivp53pbwYC5N7cWHso263srzn3ThPzOOxbPY","content":{"news_item":[{"title":"第二个文图消息第一条","author":"","digest":"摘要信息","content":"<p>第二个文图消息第一条<\/p><p>正文内容<\/p><p><img data-s=\"300,640\" data-type=\"jpeg\" data-src=\"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5FlUicv6kCfAI1FGIGlomZUbu0glyZRGO9ibibevVyQK9HaO7h2RtCsodg\/0?wx_fmt=jpeg\" data-ratio=\"0.6258992805755396\" data-w=\"\"  \/><br  \/><\/p>","content_source_url":"","thumb_media_id":"mErowUdivp53pbwYC5N7cWSuFwOFI5-_EvRpRpEeLXw","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000074&idx=1&sn=abf221b8a65950eb613a53f2734d7b06#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5TppkAZ1USuQ44yMO0I9S01PfeiafGcMWib6UCicaX0aPOlzCoE5nswH9Q\/0?wx_fmt=jpeg"},{"title":"第二条文图消息第二条","author":"","digest":"第二条文图消息第二条","content":"<p>第二条文图消息第二条<img data-s=\"300,640\" data-type=\"jpeg\" data-src=\"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B58LBgjl2F5ToMSeMx6Gk1Ox8icwh4mZw3ScL9qrJSCcRibPGsbbZ16aKQ\/0?wx_fmt=jpeg\" data-ratio=\"0.6258992805755396\" data-w=\"\"  \/><br  \/><\/p>","content_source_url":"http:\/\/strom.bcdbook.com","thumb_media_id":"mErowUdivp53pbwYC5N7cfHzTsw-5Z1a36A2jnT6SI0","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000074&idx=2&sn=ba2ba27337205aa9fc5d264423908832#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5YhuWy89UcYOSQXK54omXXPAWYMsFwkYJ7Ghicq3X9UfklibqIhs1ib1Vw\/0?wx_fmt=jpeg"},{"title":"第二条文图消息第三条内容","author":"","digest":"这是摘要信息","content":"<p><iframe class=\"video_iframe\" style=\"   z-index:1; \" height=\"375\" width=\"500\" frameborder=\"0\" data-src=\"https:\/\/v.qq.com\/iframe\/preview.html?vid=n13067sx1or&amp;width=500&amp;height=375&amp;auto=0\" allowfullscreen=\"\"><\/iframe><br  \/><\/p>","content_source_url":"http:\/\/strom.bcdbook.com","thumb_media_id":"mErowUdivp53pbwYC5N7cdZHcaxtwbYbZLPcpnaK-UA","show_cover_pic":1,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000074&idx=3&sn=8ddfbef1d230e003bdb66e510bd765e0#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5lDIaibicjMxCE9TmgwvQLGP6Fryg3pXHYazBsN6zJHLapEKla37TQYHg\/0?wx_fmt=jpeg"}],"create_time":1464844984,"update_time":1464847732},"update_time":1464847732},{"media_id":"mErowUdivp53pbwYC5N7cTIGQ6rdRS35adixH6s2tss","content":{"news_item":[{"title":"文图消息标题","author":"文图消息作者","digest":"正文","content":"<p><br  \/><\/p><p>正文<\/p><p><img data-s=\"300,640\" data-type=\"jpeg\" data-src=\"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5TppkAZ1USuQ44yMO0I9S01PfeiafGcMWib6UCicaX0aPOlzCoE5nswH9Q\/0?wx_fmt=jpeg\" data-ratio=\"0.6258992805755396\" data-w=\"\"  \/><br  \/><\/p>","content_source_url":"http:\/\/www.baidu.com","thumb_media_id":"mErowUdivp53pbwYC5N7cZS1tm9BcwGvbUq-R4WbMew","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000016&idx=1&sn=f5436156e2fda081086c1d988ff0e6d4#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B58LBgjl2F5ToMSeMx6Gk1Ox8icwh4mZw3ScL9qrJSCcRibPGsbbZ16aKQ\/0?wx_fmt=jpeg"},{"title":"二级消息","author":"作者","digest":"正文","content":"<p>正文<\/p><p><img data-s=\"300,640\" data-type=\"jpeg\" data-src=\"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5YhuWy89UcYOSQXK54omXXPAWYMsFwkYJ7Ghicq3X9UfklibqIhs1ib1Vw\/0?wx_fmt=jpeg\" data-ratio=\"0.6258992805755396\" data-w=\"\"  \/><br  \/><\/p>","content_source_url":"","thumb_media_id":"mErowUdivp53pbwYC5N7cVwFSM54J4qLsTG71UdhgJI","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIwMzI3NzA0Mw==&mid=100000016&idx=2&sn=af12257a24aba5f5dc97b9d360a04910#rd","thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B5FlUicv6kCfAI1FGIGlomZUbu0glyZRGO9ibibevVyQK9HaO7h2RtCsodg\/0?wx_fmt=jpeg"}],"create_time":1464703272,"update_time":1464703272},"update_time":1464703272}],"total_count":3,"item_count":3}
//		System.out.println(materialService.listMaterial("news",0,20));
		
//		{"title":"papi酱003","description":"papi酱视频003","down_url":"http:\/\/mp.weixin.qq.com\/mp\/mp\/video?__biz=MzIwMzI3NzA0Mw==&mid=100000010&sn=0ae1a89826b0a2a06b24da36e61d3719&vid=q1306dsrspg&idx=1&scene=1#rd"}
//		System.out.println(materialService.getMaterial("mErowUdivp53pbwYC5N7cRQ0ntcZj-1oPX09E5LrSBo"));
//		materialService.refreshMaterialToLocal();
//		materialService.refreshNewsMaterialToLocal(3);
		materialService.refreshVoiceMaterialToLocal(3);
	}
	
	@Test
	public void testAddAccessToken(){
		connectService.addAccessToken();
	}
	
	@Test
	public void testGetRecentAccessToken(){
		System.out.println(connectService.getAccessToken());
	}
	
	@Test
	public void testUpdateToken(){
		   
		connectService.updateAccessToken(1);
		System.out.println("更新成功。。。查看数据库");
		
	}
	//1.测试添加一条消息记录
	@Test
	public void testAddMsg(){
		
//		//Message msg=new Message(3, "1", "8", "3", "4","5", "2016-5-31", "2016-5-31", "记录", "24", "444", "66", "ppp");
//		Message msg=new Message(5, "名称变化了", "8", "3", "4","5", "2016-5-31", "2016-5-31", "记录", "2", "3", "4","5");
//
//		System.out.println(messageService.addMsg(msg));
//		System.out.println("测试完毕查看数据库。。。。。。。。。。。。。。。");
		Message msg = new Message();
		String keyword = null;
		String name = null;
		for (int i = 10; i < 57; i++) {
			keyword = "papi"+i;
			name = "papi_00"+i;
			msg.setKeyword(keyword);
			msg.setName(name);
			msg.setMsgType("video");
			messageService.addMsg(msg);
		}
		
		
	}
	//2.测试获取一条消息记录
	@Test
	public void testGetMsg(){
		
		Message msg=null;
		//根据keyword 获取	
		System.out.println("================ 根据keyword 获取	====================");
		msg=messageService.getMsgByKeyword("2");
		
		System.out.println(msg);
		
		//根据name获取		
		System.out.println("=============== 根据name获取=====================");
		msg=messageService.getMsgByName("1");
		
		System.out.println(msg);
		
		//根据id获取
		System.out.println("===============根据id获取====================");
		msg=messageService.getMsgById(1);
		
		System.out.println(msg);
		
	}
	
	//3.测试更新记录
	@Test
	public void testUpdateMsg(){
		
		Message msg=new Message(5, "56", "5", "6", "4","5", "2016-5-31", "2016-5-31", "记录", "2", "3", "4","5");
	
	System.out.println(messageService.updateMsg(msg));
	System.out.println("更新完毕查看数据库。。。。。。。。。。。。。。。");
	}
	//4.根据名字和type查询排除id
	@Test
	public void TestgetMsgByNameAndType(){
		
		Message msg=null;
		//根据keyword 获取	
		System.out.println("================ 根据名字和类型获取排除id 获取	====================");
		msg=messageService.getMsgByNameAndType(1, "3", "3");
		
		System.out.println(msg);
		
	}
	
	@Test
	public void processTextMsg(){
//		<ToUserName>gh_0e183f03e273<ToUserName/>
//		<FromUserName>o8yDuvoYJ-39Z1o9YRRjREfIfFL4<FromUserName/>
//		<CreateTime>1464753480<CreateTime/>
//		<MsgType>text<MsgType/>
//		<Content>测试<Content/>
//		<MsgId>6291068293719748634<MsgId/>
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("ToUserName", "gh_0e183f03e273");
		msg.put("FromUserName", "o8yDuvoYJ-39Z1o9YRRjREfIfFL4");
		msg.put("CreateTime", "1464753480");
		msg.put("MsgType", "text");
		msg.put("Content", "测试");
		msg.put("MsgId", "6291068293719748634");
		
		messageService.processTextMsg(msg);
	}
	
	//测试获取message列表的接口方法
	@Test
	public void testGetAllMsg(){
		
		for(Message m:messageService.getAllMsg()){
			
			
			System.out.println(m.getName());
			
		}
		
		
		
		
	}
	@Test
	public void testRenzheng(){
//		<ToUserName>gh_0e183f03e273<ToUserName/>
//		<FromUserName>o8yDuvoYJ-39Z1o9YRRjREfIfFL4<FromUserName/>
//		<CreateTime>1464946594<CreateTime/>
//		<MsgType>event<MsgType/>
//		<Event>CLICK<Event/>
//		<EventKey>myhome<EventKey/>
		
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("ToUserName", "gh_0e183f03e273");
		msg.put("FromUserName", "o8yDuvoYJ-39Z1o9YRRjREfIfFL4");
		msg.put("CreateTime", "1464946594");
		msg.put("MsgType", "event");
		msg.put("Event", "CLICK");
		msg.put("EventKey", "myhome");
		
//		messageService.processTextMsg(msg);
		Message s = eventService.processEvent(msg);
		System.out.println(s);
	}
	
	@Test
	public void testGetUserInfo(){
		String accessToken = "j0tEMKGP7hAf7brlLpCbLJEwjJBxr1zQt8yaymSB-tnmU7ff3yquCueSTqoDFtm3wJ2GFzRIEMxLcVED5NozQe-eq_yYaeq7XJDOlJzgfBY";
		String openId = "o8yDuvoYJ-39Z1o9YRRjREfIfFL4"; 
		String userInfo = connectService.getWechatUserInfo(accessToken, openId);
		System.out.println(userInfo);
		
		
////		"041EaVQ020c7B41D0YR02x1WQ02EaVQZ"
//		String endorData = connectService.exchangeAccessToken("041EaVQ020c7B41D0YR02x1WQ02EaVQZ");
//		System.out.println(endorData);
	}
	@Test
	public void testTemplateService(){
		String toUser = "o8yDuvoYJ-39Z1o9YRRjREfIfFL4";
		String template_id = "OUfT2itzksr8dDxutzZbEKGTT25zjo3brZBVeDj9hgo";
		String linkUrl = "strom.bcdbook.com";
		
		JSONObject data = new JSONObject();
		
		JSONObject first = new JSONObject();
		JSONObject keynote1 = new JSONObject();
		JSONObject keynote2 = new JSONObject();
		JSONObject keynote3 = new JSONObject();
		JSONObject keynote4 = new JSONObject();
		JSONObject remark = new JSONObject();
		
		first.put("value", "绿能宝新产品上线通知");
		keynote1.put("value", "美桔209号");
		keynote2.put("value", "9个月");
		keynote3.put("value", "7.8%");
		keynote4.put("value", "有实物支撑");
		remark.put("value", "敬请关注");
		
		first.put("color", "#173177");
		keynote1.put("color", "#173177");
		keynote2.put("color", "#173177");
		keynote3.put("color", "#173177");
		keynote4.put("color", "#173177");
		remark.put("color", "#666666");
		
		data.put("first", first);
		data.put("keyword1", keynote1);
		data.put("keyword2", keynote2);
		data.put("keyword3", keynote3);
		data.put("keyword4", keynote4);
		data.put("remark", remark);
		
		System.out.println(data.toJSONString());
		
		templateService.sendTemplate(toUser, template_id, linkUrl, data.toJSONString());
	}
	
	@Test
	public void testGetpage(){
		
	List<Message> messagelist=messageService.listMsgByCondition(3, 3, "video", null);
	for(Message m:messagelist)
	System.out.println(m.getName()+":"+m.getMsgType());
	}
	//获取总分页数
	@Test
	public void testCountMsgPage(){
		
		System.out.println(messageService.countPage(5, "video", "papi"));
		
	}
}
