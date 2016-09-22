package com.bcdbook.summer.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcdbook.summer.common.util.DateUtil;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.wechat.pojo.WechatMaterial;
import com.bcdbook.summer.wechat.pojo.message.WechatMessage;
import com.bcdbook.summer.wechat.pojo.message.resp.Article;
import com.bcdbook.summer.wechat.pojo.message.resp.Image;
import com.bcdbook.summer.wechat.pojo.message.resp.ImageMessage;
import com.bcdbook.summer.wechat.pojo.message.resp.NewsMessage;
import com.bcdbook.summer.wechat.pojo.message.resp.TextMessage;
import com.bcdbook.summer.wechat.pojo.message.resp.Video;
import com.bcdbook.summer.wechat.pojo.message.resp.VideoMessage;
import com.bcdbook.summer.wechat.pojo.message.resp.Voice;
import com.bcdbook.summer.wechat.pojo.message.resp.VoiceMessage;
import com.bcdbook.summer.wechat.pojo.message.resp.WechatRespMessage;
import com.bcdbook.summer.wechat.service.WechatMaterialService;
import com.bcdbook.summer.wechat.service.WechatMessageService;
import com.bcdbook.summer.wechat.util.WechatUtil;

/**
 * @Description: 微信消息处理的控制器
 * @author lason
 * @date 2016年9月20日
 */
@Controller
@RequestMapping("/wechat/message")
public class WechatMessageController {
	private static Logger logger = Logger.getLogger(WechatMessageController.class);
	
	@Resource
	private WechatMaterialService wechatMaterialService;
	@Resource
	private WechatMessageService wechatMessageService;
	
	/*
	 * 解析并获取返回素材的方法-------开始
	 */
	/**
	 * @Description: 处理消息的接口
	 * @param @param req
	 * @param @param resp   
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("进入消息处理的接口  /wechat/message");
		//定义返回值
		String backMsg = WechatRespMessage.SUCCESS;
		
		@SuppressWarnings("unchecked")
		Map<String,String> reqMapMsg = (Map<String, String>) req.getAttribute("reqMapMsg");
		
		//获取消息的类型
		String msgType = reqMapMsg.get("MsgType");
		//根据不同的消息类型,选用不同的处理器进行处理
		switch (msgType) {
			case WechatMessage.TEXT:
				//解析文本消息
				backMsg = processTextMsg(reqMapMsg);
				break;
			case WechatMessage.IMAGE:
				//解析图片消息
				backMsg = processImageMsg(reqMapMsg);
				break;
			case WechatMessage.VOICE:
				//解析语音消息
				backMsg = processVoiceMsg(reqMapMsg);
				break;
			case WechatMessage.VIDEO:
				//解析视频消息
				backMsg = processVideoMsg(reqMapMsg);
				break;
			case WechatMessage.SHORT_VIDEO:
				//解析短视频消息
				backMsg = processShortvideoMsg(reqMapMsg);
				break;
			case WechatMessage.LOCATION:
				//解析位置消息(通过系统自带的地理位置发送方式发送的地理位置)
				backMsg = processLocationMsg(reqMapMsg);
				break;
			case WechatMessage.LINK:
				//解析连接消息
				backMsg = processLinkMsg(reqMapMsg);
				break;
			default:
				//解析无法识别类型的消息
				backMsg = WechatRespMessage.SUCCESS;
				break;
		}
		
		// 响应消息
		PrintWriter out = null;
		try {
			logger.info("WechatMessageController 中最终要返回的值:");
			logger.info(backMsg);
			//获取流并输出要返回的信息
			out = resp.getWriter();
			out.print(backMsg);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.flush();
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * @Description: 处理文本消息的方法
	 * @param @param reqMapMsg
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String processTextMsg(Map<String, String> reqMapMsg) {
		if(reqMapMsg==null)
			return WechatRespMessage.SUCCESS;
		
		//获取文本消息中的内容(作为查询的keyword)
		String key = reqMapMsg.get("Content");
		//验证文本内容是否为空
		if(StringUtils.isNull(key))
			return WechatRespMessage.SUCCESS;
		
		//创建素材对象,用来封装查询参数
		WechatMaterial wm = new WechatMaterial();
		wm.setKeyword(key);//设置查询条件
		
		//为避免出现数据库中有多条同key值的数据,特使用list方式进行查询
		List<WechatMaterial> dbWMList = wechatMaterialService.findList(wm);
		//检查返回值的合法性
		if(dbWMList==null||dbWMList.size()<1)
			return WechatRespMessage.SUCCESS;
		
		//获取第一条为返回数据
		WechatMaterial dbWM = dbWMList.get(0);
		if(dbWM==null)
			return WechatRespMessage.SUCCESS;
		
		//调用封装返回数据的方法,获取返回数据
		String backMsg = parseMsg(dbWM,reqMapMsg);
		
//		TextMessage msg = new TextMessage();
//		msg.setToUserName(reqMapMsg.get("FromUserName"));
//		msg.setFromUserName(reqMapMsg.get("ToUserName"));
//		msg.setCreateTime(DateUtil.getTime());
//		//msg.setFuncFlag(0);// 标记成未读
//		msg.setMsgType(WechatMessage.TEXT);
//		msg.setContent("用于测试的回复的文本内容");
		
		return backMsg;
	}
	

	private String processImageMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processVoiceMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processVideoMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processShortvideoMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processLocationMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String processLinkMsg(Map<String, String> reqMapMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 解析并获取返回素材的方法-------结束
	 */
	
	
	
	
	/*
	 * 根据素材封装消息,并根据不同的返回消息消息封装成返回的xml消息的方法-------开始
	 */
	/**
	 * @Description: 根据素材对象,获取返回的消息字符串的方法
	 * @param @param dbWM
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String parseMsg(WechatMaterial wechatMaterial,Map<String, String> reqMapMsg) {
		//定义最终的返回值
		String backMsg = WechatRespMessage.SUCCESS;
		
		//验证参数的合法性
		if(wechatMaterial==null)
			return backMsg;
		
		//获取返回消息的类型,并验证合法性
		String type = wechatMaterial.getMsgType();
		if(StringUtils.isNull(type))
			return backMsg;
		
		//根据不同的消息类型,选用不同的处理器进行处理
		switch (type) {
			case WechatMessage.TEXT:
				//封装文本消息
				backMsg = parseTextMsg(wechatMaterial,reqMapMsg);
				break;
			case WechatMessage.IMAGE:
				//封装图片消息
				backMsg = parseImageMsg(wechatMaterial,reqMapMsg);
				break;
			case WechatMessage.VOICE:
				//封装语音消息
				backMsg = parseVoiceMsg(wechatMaterial,reqMapMsg);
				break;
			case WechatMessage.VIDEO:
				//封装视频消息
				backMsg = parseVideoMsg(wechatMaterial,reqMapMsg);
				break;
			case WechatMessage.MUSIC:
				//封装短视频消息
				backMsg = parseMusicMsg(wechatMaterial,reqMapMsg);
				break;
			case WechatMessage.NEWS:
				//封装位置消息(通过系统自带的地理位置发送方式发送的地理位置)
				backMsg = parseNewsMsg(wechatMaterial,reqMapMsg);
				break;
			default:
				//封装无法识别类型的消息
				backMsg = WechatRespMessage.SUCCESS;
				break;
		}
		
		return StringUtils.isNull(backMsg)?WechatRespMessage.SUCCESS:backMsg;
	}

	/**
	 * @Description: 封装用于返回的文本消息
	 * @param @param wechatMaterial
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String parseTextMsg(WechatMaterial wechatMaterial,Map<String, String> reqMapMsg) {
		//验证参数的合法性
		if(wechatMaterial==null||reqMapMsg==null)
			return null;
		
		//验证返回的消息类型是否合法
		if(!wechatMaterial.getMsgType().equals(WechatMessage.TEXT))
			return null;
		
		/*
		 * 获取发送者和接收者
		 * 注意:这里发送者和接收者发生了对调
		 */
		String FromUserName = reqMapMsg.get("ToUserName");//获取接收者
		String ToUserName = reqMapMsg.get("FromUserName");//获取发送者
		//验证参数的合法性
		if(StringUtils.isNull(FromUserName)
				||StringUtils.isNull(ToUserName))
			return null;
		
		//创建文本对象,以方便封装
		TextMessage textMsg = new TextMessage();
		textMsg.setToUserName(ToUserName);//设置接收者
		textMsg.setFromUserName(FromUserName);//设置发送者
		textMsg.setCreateTime(DateUtil.getTime());//设置创建时间
		textMsg.setMsgType(wechatMaterial.getMsgType());//设置返回的消息类型
		textMsg.setContent(wechatMaterial.getContent());//设置文本消息内容
		
		//转成xml格式的消息,并返回
		String backMsg = WechatUtil.packageXML(textMsg);
		
		return backMsg;
	}

	/**
	 * @Description: 封装用于返回的图片消息
	 * @param @param wechatMaterial
	 * @param @param reqMapMsg
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String parseImageMsg(WechatMaterial wechatMaterial,Map<String, String> reqMapMsg) {
		//验证参数的合法性
		if(wechatMaterial==null||reqMapMsg==null)
			return null;
		
		//验证返回的消息类型是否合法
		if(!wechatMaterial.getMsgType().equals(WechatMessage.IMAGE))
			return null;
		
		/*
		 * 获取发送者和接收者
		 * 注意:这里发送者和接收者发生了对调
		 */
		String FromUserName = reqMapMsg.get("ToUserName");//获取接收者
		String ToUserName = reqMapMsg.get("FromUserName");//获取发送者
		//验证参数的合法性
		if(StringUtils.isNull(FromUserName)
				||StringUtils.isNull(ToUserName))
			return null;
		
		//创建返回的图片消息对象,以便封装返回的图片消息
		ImageMessage imageMessage = new ImageMessage();
		
		imageMessage.setToUserName(ToUserName);//设置接收者
		imageMessage.setFromUserName(FromUserName);//设置发送者
		imageMessage.setCreateTime(DateUtil.getTime());//设置创建时间
		imageMessage.setMsgType(wechatMaterial.getMsgType());//设置返回的消息类型
		
		//创建图片对象,用于封装返回的图片
		Image image = new Image();
		image.setMediaId(wechatMaterial.getMediaId());//设置图片的mediaId
		imageMessage.setImage(image);//设置图片对象
		
		//转成xml格式的消息并返回
		String backMsg = WechatUtil.packageXML(imageMessage);
		return backMsg;
	}

	/**
	 * @Description: 封装用于返回的语音消息
	 * @param @param wechatMaterial
	 * @param @param reqMapMsg
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String parseVoiceMsg(WechatMaterial wechatMaterial,Map<String, String> reqMapMsg) {
		//验证参数的合法性
		if(wechatMaterial==null||reqMapMsg==null)
			return null;
		
		//验证返回的消息类型是否合法
		if(!wechatMaterial.getMsgType().equals(WechatMessage.VOICE))
			return null;
		
		/*
		 * 获取发送者和接收者
		 * 注意:这里发送者和接收者发生了对调
		 */
		String FromUserName = reqMapMsg.get("ToUserName");//获取接收者
		String ToUserName = reqMapMsg.get("FromUserName");//获取发送者
		//验证参数的合法性
		if(StringUtils.isNull(FromUserName)
				||StringUtils.isNull(ToUserName))
			return null;
		
		//创建返回的语音消息对象,以便封装返回的语音消息
		VoiceMessage voiceMessage = new VoiceMessage();
		
		voiceMessage.setToUserName(ToUserName);//设置接收者
		voiceMessage.setFromUserName(FromUserName);//设置发送者
		voiceMessage.setCreateTime(DateUtil.getTime());//设置创建时间
		voiceMessage.setMsgType(wechatMaterial.getMsgType());//设置返回的消息类型
		
		//创建语音对象,用于封装返回的语音
		Voice voice = new Voice();
		voice.setMediaId(wechatMaterial.getMediaId());//设置图片的mediaId
		voiceMessage.setVoice(voice);;//设置语音对象
		
		//转成xml格式的消息并返回
		String backMsg = WechatUtil.packageXML(voiceMessage);
		return backMsg;
	}

	/**
	 * @Description: 封装用于返回的视频消息
	 * @param @param wechatMaterial
	 * @param @param reqMapMsg
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String parseVideoMsg(WechatMaterial wechatMaterial,Map<String, String> reqMapMsg) {
		//验证参数的合法性
		if(wechatMaterial==null||reqMapMsg==null)
			return null;
		
		//验证返回的消息类型是否合法
		if(!wechatMaterial.getMsgType().equals(WechatMessage.VIDEO))
			return null;
		
		/*
		 * 获取发送者和接收者
		 * 注意:这里发送者和接收者发生了对调
		 */
		String FromUserName = reqMapMsg.get("ToUserName");//获取接收者
		String ToUserName = reqMapMsg.get("FromUserName");//获取发送者
		//验证参数的合法性
		if(StringUtils.isNull(FromUserName)
				||StringUtils.isNull(ToUserName))
			return null;
		
		//创建返回的视频消息对象,以便封装返回的语音消息
		VideoMessage videoMessage = new VideoMessage();
		
		videoMessage.setToUserName(ToUserName);//设置接收者
		videoMessage.setFromUserName(FromUserName);//设置发送者
		videoMessage.setCreateTime(DateUtil.getTime());//设置创建时间
		videoMessage.setMsgType(wechatMaterial.getMsgType());//设置返回的消息类型
		
		//创建视频对象,用于封装返回的视频
		Video video = new Video();
		video.setMediaId(wechatMaterial.getMediaId());//设置图片的mediaId
		video.setTitle(wechatMaterial.getTitle());//设置对象的名称
		video.setDescription(wechatMaterial.getDescription());//设置素材的简介信息
		videoMessage.setVideo(video);
		
		//转成xml格式的消息并返回
		String backMsg = WechatUtil.packageXML(videoMessage);
		return backMsg;
	}

	/**
	 * @Description: 封装用于返回的音乐素材
	 * @param @param wechatMaterial
	 * @param @param reqMapMsg
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String parseMusicMsg(WechatMaterial wechatMaterial,Map<String, String> reqMapMsg) {
		// TODO 由于音乐素材获取的问题,暂时不返回音乐素材
		return null;
	}

	/**
	 * @Description: 封装用于返回的文图消息
	 * @param @param wechatMaterial
	 * @param @param reqMapMsg
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	private String parseNewsMsg(WechatMaterial wechatMaterial,Map<String, String> reqMapMsg) {
		//验证参数的合法性
		if(wechatMaterial==null||reqMapMsg==null)
			return null;
		
		//验证返回的消息类型是否合法
		if(!wechatMaterial.getMsgType().equals(WechatMessage.NEWS))
			return null;
		
		/*
		 * 获取发送者和接收者
		 * 注意:这里发送者和接收者发生了对调
		 */
		String FromUserName = reqMapMsg.get("ToUserName");//获取接收者
		String ToUserName = reqMapMsg.get("FromUserName");//获取发送者
		//验证参数的合法性
		if(StringUtils.isNull(FromUserName)
				||StringUtils.isNull(ToUserName))
			return null;
		
		//创建返回的语音消息对象,以便封装返回的语音消息
		NewsMessage newsMessage = new NewsMessage();
		
		newsMessage.setToUserName(ToUserName);//设置接收者
		newsMessage.setFromUserName(FromUserName);//设置发送者
		newsMessage.setCreateTime(DateUtil.getTime());//设置创建时间
		newsMessage.setMsgType(wechatMaterial.getMsgType());//设置返回的消息类型
		
		//创建一个素材对象,用于封装查询文图消息中文章的条件
		WechatMaterial conditionWM = new WechatMaterial();
		conditionWM.setParentMediaId(wechatMaterial.getMediaId());//设置其parentId
		//获取文章素材集合
		List<WechatMaterial> wechatMaterialList = wechatMaterialService.findList(conditionWM);
		//验证返回值的合法性
		if(wechatMaterialList==null
				||wechatMaterialList.size()<1
				||wechatMaterialList.size()>10)
			return null;
		
		//定义文章的集合,用于封装文章的相关信息
		List<Article> articleList = new ArrayList<Article>();
		//循环获取到的所有文章素材,并封装成文章消息的对象
		for (WechatMaterial dbWechatMaterial : wechatMaterialList) {
			//创建文章对象,用来封装文章消息
			Article article = new Article();
			article.setTitle(dbWechatMaterial.getTitle());//设置title
			article.setDescription(dbWechatMaterial.getDescription());//设置简介
			article.setPicUrl(dbWechatMaterial.getPicUrl());//获取封面图片的地址信息
			article.setUrl(dbWechatMaterial.getUrl());//设置点击后跳转的连接
			
			articleList.add(article);//添加封装好的单个文章到文图列表
		}
		
		newsMessage.setArticleCount(articleList.size());//添加文章数量到news消息中
		newsMessage.setArticles(articleList);//添加文章列表到news消息中
		
		//转成xml格式的消息并返回
		String backMsg = WechatUtil.packageNewsXML(newsMessage);
		return backMsg;
	}
	/*
	 * 根据素材封装消息,并根据不同的返回消息消息封装成返回的xml消息的方法-------结束
	 */
}
