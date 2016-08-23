package com.bcdbook.summer.wechat.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.enums.BaseEnum.BackMsg;
import com.bcdbook.summer.common.util.DateUtil;
import com.bcdbook.summer.wechat.common.util.WechatRequest;
import com.bcdbook.summer.wechat.pojo.message.Message;
import com.bcdbook.summer.wechat.service.ConnectService;
import com.bcdbook.summer.wechat.service.MaterialService;

/**
 * 
     * @Title: MaterialServiceImpl.java    
     * @Description: 素材管理的实现类
     * @author lason       
     * @created 2016年5月30日 上午10:21:45
 */
@Service("materialService")
public class MaterialService {
	@Resource
	private ConnectService connectService;
	@Resource
	private MessageService messageService;
	
	/**
	 * 
	    * @Discription 统计素材总量的方法
	    * @author lason       
	    * @created 2016年6月1日 下午4:48:06      
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.MaterialService#countMaterials()
	 */
	public String countMaterials() {
		//获取ACCESS_TOKEN
		String ACCESS_TOKEN = connectService.getAccessToken();
		//定义请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token="+ACCESS_TOKEN;
		//定义返回的数据字符串
		String countMsg = null;
		try {
			//调用wechatGet,执行微信端的调用
			countMsg = WechatRequest.wechatGet(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回获取到的数据
		return countMsg;
	}
	
	/**
	 * 
	    * @Discription 获取素材列表
	    * @author lason       
	    * @created 2016年5月30日 上午10:22:25      
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.MaterialService#listMaterial()
	 */
	public String listMaterial(String materialType,int offset,int count) {
		String ACCESS_TOKEN = connectService.getAccessToken();
		//定义请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+ACCESS_TOKEN;
		String postValue = "{\"type\":\""+materialType+"\",\"offset\":"+offset+",\"count\":"+count+"}";
		
//		JSONObject tokenJson = null;
		//定义获取返回值的字符串
		String tokenBack = null;
		try {
			//调用微信接口,获取数据列表(json格式的素材数据)
			tokenBack = WechatRequest.wechatPost(url, postValue);
			return tokenBack;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tokenBack;
	}
	
	/**
	 * 
	    * @Discription 根据传入的素材id获取对应的素材
	    * @author lason       
	    * @created 2016年5月31日 下午1:44:54      
	    * @param mediaId
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.MaterialService#getMaterial(java.lang.String)
	 */
	public String getMaterial(String mediaId) {
		//获取accessToken
		String ACCESS_TOKEN = connectService.getAccessToken();
		//定义请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+ACCESS_TOKEN;
//		定义要传到后台的微信需要的的数据(素材的id)
		String postValue = "{\"media_id\":\""+mediaId+"\"}";
		
		//定义用来解析返回的json格式的数据
		String tokenBack = null;
		try {
			//根据url和postValue从微信获取素材对象
			tokenBack = WechatRequest.wechatPost(url, postValue);
//			return tokenBack;
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回获取到的json格式的素材字符串
		return tokenBack;
	}

	/**
	 * 
	    * @Discription 更新微信端的素材到本地数据库
	    * @author lason       
	    * @created 2016年6月1日 下午4:47:08      
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.MaterialService#refreshMaterialToLocal()
	 */
	public String refreshMaterialToLocal() {
		//获取素材库中的统计信息
		String countMaterials = countMaterials();
		
		int voiceCount = 0;
		int videoCount = 0;
		int imageCount = 0;
		int newsCount = 0;
		
		JSONObject materialsJson = null;
		//如果获取到的信息不为空
		if(countMaterials!=null){
			materialsJson = JSON.parseObject(countMaterials);
			String errcode = materialsJson.getString("errcode");
			//如果有errcode说明出错了,则返回错误信息
			if(errcode!=null){
				return BackMsg.ERROR.getValue();
			}
//			为了避免返回数据出现空值而报异常,做了一下处理
			String voiceCountString = materialsJson.getString("voice_count");
			String videoCountString = materialsJson.getString("video_count");
			String imageCountString = materialsJson.getString("image_count");
			String newsCountString = materialsJson.getString("news_count");
			
			//如果获取到的素材计数值不为空,并且素材数量大于0时执行刷新方法
			if(voiceCountString!=null){
				voiceCount = Integer.parseInt(voiceCountString);
				if(voiceCount>0){
					refreshVoiceMaterialToLocal(voiceCount);
				}
			}
			if(videoCountString!=null){
				videoCount = Integer.parseInt(videoCountString);
				if(videoCount>0){
					refreshVideoMaterialToLocal(videoCount);
				}
			}
			if(imageCountString!=null){
				imageCount = Integer.parseInt(imageCountString);
				if(imageCount>0){
					refreshImageMaterialToLocal(imageCount);
				}
			}
			if(newsCountString!=null){
				newsCount = Integer.parseInt(newsCountString);
				if(newsCount>0){
					refreshNewsMaterialToLocal(newsCount);
				}
			}
			return BackMsg.SUCCESS.getValue();
		}else{
			return BackMsg.DATA_NOT_EXIST.getValue();
		}
	}
	
	public String refreshVoiceMaterialToLocal(int materialCount) {
		int pace = 20;
		for (int i = 0; i < materialCount; i+=pace) {
			
			String voices = listMaterial("voice",i,pace);
			
			//把获取到的语音列表的转换成json对象
			JSONObject itemsJson = JSON.parseObject(voices);
			//获取语音列表
			String items = itemsJson.getString("item");
			//把json格式的字符串,转换成json数组,以便于遍历处理
			JSONArray itemArray = JSON.parseArray(items);
			JSONObject item = null;
			
			Message msg = null;
			for (int j = 0; j < itemArray.size(); j++) {
				item = itemArray.getJSONObject(j);
				msg = messageService.getMsgByNameAndType("0", item.getString("name"), "voice");
				if(msg!=null){
					//获取本地的消息更新时间
					String msgUpdateTiem = DateUtil.toLongTimeString(msg.getUpdateTime());
					//因为微信把最后的三位去除掉了,所以为了时间转换,需要加上任意三位数值
					String itemUpdateTime = item.getString("update_time")+"123";
					
					//如果本地更新时间和微信端的一致,说明微信端没有更新消息,则不需要执行更新操作
					if(msgUpdateTiem.equals(itemUpdateTime)){
						
					}else{
						System.out.println("voice执行了更新");
						msg.setMediaId(item.getString("media_id"));
						msg.setUpdateTime(DateUtil.toTimeString(itemUpdateTime));
						messageService.updateMsg(msg);
					}
				}
			}
		}
		return BackMsg.SUCCESS.getValue();
	}
	
	public String refreshVideoMaterialToLocal(int materialCount) {
		int pace = 20;
		for (int i = 0; i < materialCount; i+=pace) {
			
			String videos = listMaterial("video",i,pace);
			
			//把获取到的语音列表的转换成json对象
			JSONObject itemsJson = JSON.parseObject(videos);
			//获取语音列表
			String items = itemsJson.getString("item");
			//把json格式的字符串,转换成json数组,以便于遍历处理
			JSONArray itemArray = JSON.parseArray(items);
			JSONObject item = null;
			
			Message msg = null;
			for (int j = 0; j < itemArray.size(); j++) {
				item = itemArray.getJSONObject(j);
				msg = messageService.getMsgByNameAndType("0", item.getString("name"), "video");
				if(msg!=null){
					//获取本地的消息更新时间
					String msgUpdateTiem = DateUtil.toLongTimeString(msg.getUpdateTime());
					//因为微信把最后的三位去除掉了,所以为了时间转换,需要加上任意三位数值
					String itemUpdateTime = item.getString("update_time")+"123";
					
					//如果本地更新时间和微信端的一致,说明微信端没有更新消息,则不需要执行更新操作
					if(msgUpdateTiem.equals(itemUpdateTime)){
						
					}else{
						System.out.println("video执行了更新");
						msg.setMediaId(item.getString("media_id"));
						msg.setUpdateTime(DateUtil.toTimeString(itemUpdateTime));
						messageService.updateMsg(msg);
					}
				}
			}
		}
		return BackMsg.SUCCESS.getValue();
	}
	
	public String refreshImageMaterialToLocal(int materialCount) {
		int pace = 20;
		for (int i = 0; i < materialCount; i+=pace) {
			
			String images = listMaterial("image",i,pace);
			
			//把获取到的语音列表的转换成json对象
			JSONObject itemsJson = JSON.parseObject(images);
			//获取语音列表
			String items = itemsJson.getString("item");
			//把json格式的字符串,转换成json数组,以便于遍历处理
			JSONArray itemArray = JSON.parseArray(items);
			JSONObject item = null;
			
			Message msg = null;
			for (int j = 0; j < itemArray.size(); j++) {
				item = itemArray.getJSONObject(j);
				msg = messageService.getMsgByNameAndType("0", item.getString("name"), "image");
				if(msg!=null){
					//获取本地的消息更新时间
					String msgUpdateTiem = DateUtil.toLongTimeString(msg.getUpdateTime());
					//因为微信把最后的三位去除掉了,所以为了时间转换,需要加上任意三位数值
					String itemUpdateTime = item.getString("update_time")+"123";
					
					//如果本地更新时间和微信端的一致,说明微信端没有更新消息,则不需要执行更新操作
					if(msgUpdateTiem.equals(itemUpdateTime)){
						
					}else{
						System.out.println("image执行了更新");
						msg.setMediaId(item.getString("media_id"));
						msg.setUpdateTime(DateUtil.toTimeString(itemUpdateTime));
						messageService.updateMsg(msg);
					}
				}
			}
		}
		return BackMsg.SUCCESS.getValue();
	}
	//todo news
	/**
	 * 
	    * @Discription 刷新文图消息的方法
	    * 注:此类的备注中NEWS表示整条的文图消息
	    * article表示整个文图消息的其中一个文图(最多可以有10个)
	    * @author lason       
	    * @created 2016年6月2日 下午5:49:47      
	    * @param materialCount
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.MaterialService#refreshNewsMaterialToLocal(int)
	 */
	public String refreshNewsMaterialToLocal(int materialCount) {
		int pace = 20;//设置获取NEWS的步长
		//循环获取NEWS
		for (int i = 0; i < materialCount; i+=pace) {
			String news = listMaterial("news",i,pace);//根据起点和步长获取一个json格式的NEWS数组
			JSONObject itemsJson = JSON.parseObject(news);//把获取到的NEWS数组转换成json对象
			//获取NEWS消息的JSON数组对象
			JSONArray itemArray = itemsJson.getJSONArray("item");

			JSONObject item = null;//定义一个json格式的NEWS对象,用来处理NEWS
			Message msg = null;//定义一个message对象,承接查询出的message并配合更新
			
			//如果NEWS数组不为空
			if(itemArray!=null){
				//循环NEWS的json数组,以处理每一个NEWS对象
				for (int j = 0; j < itemArray.size(); j++) {
					
					item = itemArray.getJSONObject(j);//获取其中一条NEWS对象
					//如果NEWS对象不为空
					if(item!=null){
						
						//获取其中一个NEWS消息,并获取NEWS中的json格式的article,同时转成JSONArray
						JSONArray articleArray = item.getJSONObject("content").getJSONArray("news_item");
						//获取NEWS的第一个article的title,并把这个title作为唯一识别
						String name = articleArray.getJSONObject(0).getString("title");
						
						//根据NEWS中第一条article的title,和message类型,获取一条message对象
						msg = messageService.getMsgByNameAndType("0",name,"news");
						
						if(msg!=null){
							String mediaId = item.getString("media_id");//获取NEWS的id
							String itemUpdateTime = item.getString("update_time")+"123";//获取NEWS的更新时间
							//定义一个集合,用来封装处理article
							List<JSONObject> msgContents = new ArrayList<JSONObject>();
							
							JSONObject sourceArt = null;//定义整个NEWS对象,用来承接articles,同时用于后期的封装
							
							//循环获取到article集合
							for (int k = 0; k < articleArray.size(); k++) {
								JSONObject msgArt = new JSONObject();//定义一个article,用来封装内容
								sourceArt = articleArray.getJSONObject(k);//获取微信素材库中的一条article
								if(sourceArt!=null){
									msgArt.put("title", sourceArt.getString("title"));
									msgArt.put("digest", sourceArt.getString("digest"));
									msgArt.put("url", sourceArt.getString("url"));
									msgArt.put("thumb_url", sourceArt.getString("thumb_url"));
								}
								msgContents.add(msgArt);
							}
							msg.setUpdateTime(DateUtil.toTimeString(itemUpdateTime));//设置更新时间到message对象
							msg.setMediaId(mediaId);//设置素材id到message对象
							msg.setContent(JSON.toJSONString(msgContents));//设置content到message对象
							messageService.updateMsg(msg);//更新message
						}
					}
				}
			}
		}
		return BackMsg.SUCCESS.getValue();
	}
}
