//package com.bcdbook.summer.wechatold.service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.bcdbook.summer.common.enums.WechatEnum.MsgType;
//import com.bcdbook.summer.wechatold.common.util.WechatUtil;
//import com.bcdbook.summer.wechatold.pojo.message.Message;
//import com.bcdbook.summer.wechatold.pojo.message.resp.Article;
//import com.bcdbook.summer.wechatold.pojo.message.resp.Image;
//import com.bcdbook.summer.wechatold.pojo.message.resp.ImageMessage;
//import com.bcdbook.summer.wechatold.pojo.message.resp.Music;
//import com.bcdbook.summer.wechatold.pojo.message.resp.MusicMessage;
//import com.bcdbook.summer.wechatold.pojo.message.resp.NewsMessage;
//import com.bcdbook.summer.wechatold.pojo.message.resp.TextMessage;
//import com.bcdbook.summer.wechatold.pojo.message.resp.Video;
//import com.bcdbook.summer.wechatold.pojo.message.resp.VideoMessage;
//import com.bcdbook.summer.wechatold.pojo.message.resp.Voice;
//import com.bcdbook.summer.wechatold.pojo.message.resp.VoiceMessage;
//import com.bcdbook.summer.wechatold.service.EncapsulationService;
//
///**
// * 
// * @Title: EncapsulationServiceImpl.java
// * @Description: 封装消息的方法
// * @author lason
// * @created 2016年5月27日 上午10:42:00
// */
//@Service("encapsulationService")
//public class EncapsulationService {
//	@Resource
//	private MessageService messageService;
//	/**
//	 * 
//	    * @Discription 封装消息的总控制器,用来分发封装的消息的类型
//	    * @author lason       
//	    * @created 2016年5月31日 下午7:20:45      
//	    * @param reqMsg
//	    * @param message
//	    * @return     
//	    * @see com.bcdbook.summer.wechatold.service.EncapsulationService#encapsulationMsg(java.util.Map, com.bcdbook.summer.wechatold.pojo.message.Message)
//	 */
//	public String encapsulationMsg(Map<String, String> reqMsg, Message message) {
//		String encapsulationXML = null;
//		if(message!=null){
//			//根据传入message对象,获取到要返回的消息的类型
//			String msgType = message.getMsgType();
//			
//			switch (msgType) {
//			case "text":
//				//封装文本消息
//				//根据客户端发来的消息和要返回的文字,生成要返回的xml格式的消息
//				encapsulationXML = encapsulationTextMsg(reqMsg, message.getContent());
//				break;
//				
//			case "image":
//				//封装图片消息
//				Image img = new Image();//创建image对象
//				img.setMediaId(message.getMediaId());//设置image的素材id
//				//根据客户端发来的消息和要返回img对象,生成要返回的xml格式的消息
//				encapsulationXML = encapsulationImageMsg(reqMsg, img);
//				break;
//				
//			case "voice":
//				//封装语音消息
//				//创建语音对象
//				Voice voice = new Voice();
//				voice.setMediaId(message.getMediaId());//设置语音对象的素材id
//				//根据客户端发来的消息和要返回的语音,生成要返回的xml格式的消息
//				encapsulationXML = encapsulationVoiceMsg(reqMsg, voice);
//				break;
//			case "video":
//				//封装视频消息
//				//创建视频对象
//				Video video = new Video();
//				video.setMediaId(message.getMediaId());//设置视频对象的素材id
//				video.setTitle(message.getTitle());//设置视频对象的title
//				video.setDescription(message.getDescription());//设置视频对象的备注
//				//根据客户端发来的消息和要返回的视频,生成要返回的xml格式的消息
//				encapsulationXML = encapsulationVideoMsg(reqMsg, video);
//				break;
//			case "music":
//				//封装音乐消息
//				//创建音乐对象
//				Music music = new Music();
//				music.setTitle(message.getTitle());//设置音乐对象的title
//				music.setDescription(message.getDescription());//设置音乐对象的备注
//				music.setMusicUrl(message.getMusicUrl());//设置音乐对象的素材地址
//				music.setHQMusicUrl(message.getHqMusicUrl());//设置音乐对象的高清素材地址
//				//根据客户端发来的消息和要返回的音乐,生成要返回的xml格式的消息
//				encapsulationXML = encapsulationMusicMsg(reqMsg, music);
//				break;
//			case "news":
//				//封装文图消息
//				//处理并从数据库中查询到的文图消息
//				List<Article> artList = parseNewsMsg(reqMsg,message);
//				
//				//根据客户端发来的消息和要返回的文图消息,生成要返回的xml格式的消息
//				encapsulationXML = encapsulationNewsMsg(reqMsg,artList);
//				break;
//			
//			default:
//				//如果不能识别消息要返回的消息类型,则返回默认消息
//				//如果进入到这里说明要返回的消息类型是不能识别的,为了避免死循环,直接返回一条写死的消息
//				encapsulationXML = encapsulationNoMsg(reqMsg);
//				break;
//			}
//		}
//		
//		//如果上述处理的最终结果使得返回值为空,则不返回消息
//		if(encapsulationXML==null){
//			encapsulationXML= encapsulationNoMsg(reqMsg);
//		}
//		return encapsulationXML;
//	}
//	
//
//	/**
//	 * 
//	 * @Discription 封装成文本消息
//	 * @author lason
//	 * @created 2016年5月27日 上午10:42:21
//	 * @param reqMsg
//	 * @param respContent
//	 * @return
//	 * @see com.bcdbook.summer.wechatold.service.EncapsulationService#encapsulationTextMsg(java.util.Map,
//	 *      java.lang.String)
//	 */
//	public String encapsulationTextMsg(Map<String, String> reqMsg,String respContent) {
//		if(respContent!=null){
//			
//			String encapsulationXML = null;
//			
//			TextMessage textMessage = new TextMessage();
//			
//			// 发送方帐号（open_id）
//			String fromUserName = reqMsg.get("FromUserName");
//			// 公众帐号
//			String toUserName = reqMsg.get("ToUserName");
//			// 消息类型
//			String msgType = MsgType.RESP_MESSAGE_TYPE_TEXT.getValue();
//			
//			textMessage.setToUserName(fromUserName);// 设置接收者(客户端用户)
//			textMessage.setFromUserName(toUserName);// 设置发送者(微信公众平台)
//			textMessage.setCreateTime(new Date().getTime());// long类型的时间戳
//			textMessage.setMsgType(msgType);// 数据类型
//			textMessage.setFuncFlag(0);// 标记成未读
//			textMessage.setContent(respContent);// 要回复的消息
//			
//			encapsulationXML = WechatUtil.textMessageToXml(textMessage);
//			return encapsulationXML;
//		}
//		return null;
//	}
//	
//	public String encapsulationImageMsg(Map<String, String> reqMsg, Image image) {
//		if(image!=null){
//			
//			String encapsulationXML = null;
//			
//			ImageMessage imageMessage = new ImageMessage();
//			
//			// 发送方帐号（open_id）
//			String fromUserName = reqMsg.get("FromUserName");
//			// 公众帐号
//			String toUserName = reqMsg.get("ToUserName");
//			// 消息类型
//			String msgType = MsgType.RESP_MESSAGE_TYPE_IMAGE.getValue();
//			
//			imageMessage.setToUserName(fromUserName);// 设置接收者(客户端用户)
//			imageMessage.setFromUserName(toUserName);// 设置发送者(微信公众平台)
//			imageMessage.setCreateTime(new Date().getTime());// long类型的时间戳
//			imageMessage.setMsgType(msgType);// 数据类型
//			imageMessage.setFuncFlag(0);// 标记成未读
//			imageMessage.setImage(image);
//			
//			encapsulationXML = WechatUtil.imageMessageToXml(imageMessage);
//			return encapsulationXML;
//		}
//		return null;
//	}
//
//	public String encapsulationVoiceMsg(Map<String, String> reqMsg, Voice voice) {
//		if(voice!=null){
//			String encapsulationXML = null;
//	
//			VoiceMessage voiceMessage = new VoiceMessage();
//	
//			// 发送方帐号（open_id）
//			String fromUserName = reqMsg.get("FromUserName");
//			// 公众帐号
//			String toUserName = reqMsg.get("ToUserName");
//			// 消息类型
//			String msgType = MsgType.RESP_MESSAGE_TYPE_VOICE.getValue();
//	
//			voiceMessage.setToUserName(fromUserName);// 设置接收者(客户端用户)
//			voiceMessage.setFromUserName(toUserName);// 设置发送者(微信公众平台)
//			voiceMessage.setCreateTime(new Date().getTime());// long类型的时间戳
//			voiceMessage.setMsgType(msgType);// 数据类型
//			voiceMessage.setFuncFlag(0);// 标记成未读
//			voiceMessage.setVoice(voice);
//			
//			encapsulationXML = WechatUtil.voiceMessageToXml(voiceMessage);
//			return encapsulationXML;
//		}
//		return null;
//	}
//	
//	public String encapsulationVideoMsg(Map<String, String> reqMsg, Video video) {
//		if(video!=null){
//			String encapsulationXML = null;
//	
//			VideoMessage videoMessage = new VideoMessage();
//	
//			// 发送方帐号（open_id）
//			String fromUserName = reqMsg.get("FromUserName");
//			// 公众帐号
//			String toUserName = reqMsg.get("ToUserName");
//			// 消息类型
//			String msgType = MsgType.RESP_MESSAGE_TYPE_VIDEO.getValue();
//	
//			videoMessage.setToUserName(fromUserName);// 设置接收者(客户端用户)
//			videoMessage.setFromUserName(toUserName);// 设置发送者(微信公众平台)
//			videoMessage.setCreateTime(new Date().getTime());// long类型的时间戳
//			videoMessage.setMsgType(msgType);// 数据类型
//			videoMessage.setFuncFlag(0);// 标记成未读
//			videoMessage.setVideo(video);
//			
//			encapsulationXML = WechatUtil.videoMessageToXml(videoMessage);
//			return encapsulationXML;
//		}
//		return null;
//	}
//
//	public String encapsulationMusicMsg(Map<String, String> reqMsg, Music music) {
//		if(music!=null){
//			String encapsulationXML = null;
//			
//			MusicMessage musicMessage = new MusicMessage();
//			
//			// 发送方帐号（open_id）
//			String fromUserName = reqMsg.get("FromUserName");
//			// 公众帐号
//			String toUserName = reqMsg.get("ToUserName");
//			// 消息类型
//			String msgType = MsgType.RESP_MESSAGE_TYPE_MUSIC.getValue();
//			
//			musicMessage.setToUserName(fromUserName);// 设置接收者(客户端用户)
//			musicMessage.setFromUserName(toUserName);// 设置发送者(微信公众平台)
//			musicMessage.setCreateTime(new Date().getTime());// long类型的时间戳
//			musicMessage.setMsgType(msgType);// 数据类型
//			musicMessage.setFuncFlag(0);// 标记成未读
//			musicMessage.setMusic(music);
////		musicMessage.set
//			
//			encapsulationXML = WechatUtil.musicMessageToXml(musicMessage);
//			return encapsulationXML;
//		}
//		return null;
//	}
//	
//
//	
//	public List<Article> parseNewsMsg(Map<String, String> reqMsg,Message message) {
//		/*
//		 * 处理后content的结构是这样的
//		 * [{
//			"title": "标题",
//			"digest": "摘要",
//			"url": "http://mp.weixin.qq.com/s?__biz=MzIwMzI3NzA0Mw==&mid=100000075&idx=1&sn=53b9ceb6685a6de343a7cd90415f3297#rd",
//			"thumb_url": "http://mmbiz.qpic.cn/mmbiz/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B58LBgjl2F5ToMSeMx6Gk1Ox8icwh4mZw3ScL9qrJSCcRibPGsbbZ16aKQ/0?wx_fmt=jpeg"
//		}, {
//			"title": "标题",
//			"digest": "摘要",
//			"url": "http://mp.weixin.qq.com/s?__biz=MzIwMzI3NzA0Mw==&mid=100000075&idx=1&sn=53b9ceb6685a6de343a7cd90415f3297#rd",
//			"thumb_url": "http://mmbiz.qpic.cn/mmbiz/s9jRuWdLw3GmSLzRF0aEicvjoqib2ll3B58LBgjl2F5ToMSeMx6Gk1Ox8icwh4mZw3ScL9qrJSCcRibPGsbbZ16aKQ/0?wx_fmt=jpeg"
//		}]
//		 */
//		
//		//获取数据库中的news数据
//		String content = null;
//		//定义把数据转成json对象的数据
//		//把content中的数据直接转换成json数组
//		JSONArray itemArray = null;
//		
//		List<Article> artList = new ArrayList<Article>();
//		
//		if(message!=null){
//			//获取数据库中存储的news字符串
//			content = message.getContent();
//			if(content!= null){
//				//把news字符串转换成json对象
//				itemArray = JSON.parseArray(content);
//				if(itemArray!=null){
//					for (int i = 0; i < itemArray.size(); i++) {
//						JSONObject item = itemArray.getJSONObject(i); 
//						//创建一个文图消息对象
//						Article art = new Article();
//						art.setTitle(item.getString("title"));//设置title
//						art.setDescription(item.getString("digest"));//设置描述消息
//						art.setPicUrl(item.getString("thumb_url"));//设置封面的连接地址
//						art.setUrl(item.getString("url"));//设置点击后的连接地址
//						
//						artList.add(art);
//					}
//					return artList;
//				}
//			}
//		}else{
//			return null;
//		}
//		return null;
//	}
//
//	public String encapsulationNewsMsg(Map<String, String> reqMsg,List<Article> articles) {
//		if(articles!=null){
//			String encapsulationXML = null;
//	
//			NewsMessage newsMessage = new NewsMessage();
//	
//			// 发送方帐号（open_id）
//			String fromUserName = reqMsg.get("FromUserName");
//			// 公众帐号
//			String toUserName = reqMsg.get("ToUserName");
//			// 消息类型
//			String msgType = MsgType.RESP_MESSAGE_TYPE_NEWS.getValue();
//	
//			newsMessage.setToUserName(fromUserName);// 设置接收者(客户端用户)
//			newsMessage.setFromUserName(toUserName);// 设置发送者(微信公众平台)
//			newsMessage.setCreateTime(new Date().getTime());// long类型的时间戳
//			newsMessage.setMsgType(msgType);// 数据类型
//			newsMessage.setFuncFlag(0);// 标记成未读
//			newsMessage.setArticles(articles);
//			newsMessage.setArticleCount(articles.size());
//			
//			encapsulationXML = WechatUtil.newsMessageToXml(newsMessage);
//			return encapsulationXML;
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	    * @Discription 不反回消息
//	    * @author lason       
//	    * @created 2016年6月2日 上午11:11:29      
//	    * @param reqMsg
//	    * @return     
//	    * @see com.bcdbook.summer.wechatold.service.EncapsulationService#encapsulationDefaultMsg(java.util.Map)
//	 */
//	public String encapsulationNoMsg(Map<String, String> reqMsg) {
//		return "success";
////		/*
////		 * 注意:要保证一条不经过数据库查询就能返回的消息,以避免出现消息调用的死循环
////		 */
////		String defaultMsg = "你发的内容我现在还听不懂,发送help给我,看看有什么我可以帮你的^~^";
////		return encapsulationTextMsg(reqMsg, defaultMsg);
//	}
//	
//	public String encapsulationWellcomeMsg(Map<String, String> reqMsg) {
//		String key = "wellcome";
//		Message message = messageService.getMsgByKeyword(key);
//		return encapsulationMsg(reqMsg, message);
//	}
//	
//	public String encapsulationUnknowableMsg(Map<String, String> reqMsg) {
//		String key = "unknow";
//		Message message = messageService.getMsgByKeyword(key);
//		return encapsulationMsg(reqMsg, message);
//	}
//	
//	public String encapsulationHelpMsg(Map<String, String> reqMsg) {
//		String key = "help";
//		Message message = messageService.getMsgByKeyword(key);
//		return encapsulationMsg(reqMsg, message);
//	}
//
//}
