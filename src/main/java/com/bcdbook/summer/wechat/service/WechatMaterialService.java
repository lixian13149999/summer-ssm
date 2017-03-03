package com.bcdbook.summer.wechat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.backmsg.Resp;
import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.wechat.dao.WechatMaterialDao;
import com.bcdbook.summer.wechat.pojo.WechatMaterial;
import com.bcdbook.summer.wechat.util.WechatUtil;

/**
 * @Description: 微信素材管理的类
 * @author lason
 * @date 2016年9月20日
 */
@Service("wechatMaterialService")
public class WechatMaterialService extends CrudService<WechatMaterialDao, WechatMaterial>{
	@Resource
	private WechatMaterialDao wechatMaterialDao;
	/**
	 * 
	 * @Description: 获取素材总数的方法
	 * @param @param accessToken
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	public String count(String accessToken) {
		//拼接请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token="+accessToken;
		//执行请求,并返回微信的返回值
		return WechatUtil.wechatGet(url);
	}
	
	/**
	 * @Description: 获取单个素材的方法
	 * @param @param accessToken
	 * @param @param mediaId
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public String get(String accessToken,String mediaId){
		//验证参数的合法性
		if(StringUtils.isNull(accessToken)||StringUtils.isNull(mediaId))
			return null;
		
		//拼接请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+accessToken;
		//封装请求时需要传入的值
		String postValue = "{\"media_id\":\""+mediaId+"\"}";
		//执行请求,获取对应的素材对象
		String materialStr = WechatUtil.wechatPost(url, postValue);
		if(materialStr==null)
			return null;
		System.out.println(materialStr);
		return materialStr;
	}

	/**
	 * @Description: 根据图片的的mediaId获取图片的地址
	 * @param @param string
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月23日
	 */
	public String getPicUrlByMediaId(String accessToken,String mediaId) {
		//参数合法性验证
		if(StringUtils.isNull(accessToken)
				||StringUtils.isNull(mediaId))
			return null;
		
		//设置获取的素材类型
		String type = WechatMaterial.IMAGE;
		int offset = 0;//设置起始值
		int count = 20;//设置获取的数量
		int times = 1;//因为需要通过循环获取相应的信息,这里通过times计数
		
		boolean findedPic = false;
		String picUrl = null;
		
		while (times<10) {//限制最大循环次数(如果200张图片中,没有找到想要的图片,则直接放弃查找)
			System.out.println("进入循环");
			//获取素材列表
			String materialsStr = list(accessToken, type, offset, count);
			//如果获取到的素材列表为空,则直接返回空
			if(StringUtils.isNull(materialsStr))
				return null;
			
			//把获取到的素材集合字符串,转成json
			JSONObject materialListJson = JSON.parseObject(materialsStr);
			
			//检查返回的数据是否有错误
			if(!StringUtils.isNull(materialListJson.getString("errcode")))
				//如果errcode中有值,则说明请求发生错误
				return null;
			
			//获取素材的总数
			int totalCount = materialListJson.getIntValue("total_count");
			if(totalCount<1)//如果素材总数小于1,说明没有素材,则直接返回null
				return null;
			
			//获取素材列表中的素材,并封装成Json数组
			JSONArray materialJsonArr = materialListJson.getJSONArray("item");
			//循环素材列表,以获取相应的值
			for (int i = 0; i < materialJsonArr.size(); i++) {
				//获取单个素材对象
				JSONObject item = materialJsonArr.getJSONObject(i);
				//如果获取到的素材id和传入的素材id一致
				if(mediaId.equals(item.getString("media_id"))){
					findedPic = true;//标记已经找到素材
					picUrl = item.getString("url");//把素材的地址赋予picUrl
					break;
				}
			}
			
			//如果已经找到图片
			if(findedPic)
				return picUrl;//直接返回图片路径
			
			//检查总数是否已经超过了现在获取的值,如果是则说明素材库中没有要找的素材
			if(totalCount<=times*count)
				return null;//直接返回空
			
			times++;//把循环次数累加
			offset+=20;//改变查询的起始值
		}
		
		return null;
	}

	/**
	 * @Description: 用来获取素材列表的方法
	 * @param @param accessToken
	 * @param @param type
	 * @param @param offset
	 * @param @param count
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月20日
	 */
	public String list(String accessToken, String type, int offset, int count) {
		if(StringUtils.isNull(accessToken))
			return Resp.error("accessToken is null");
		
		//对传入的数值参数进行合法化处理
		offset = offset<0 ? 0 : offset;
		count = (count<1||count>20) ? 20 : count;
		
		//拼接请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+accessToken;
		
		//创建map对象,用以封装参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		paramMap.put("offset", offset);
		paramMap.put("count", count);
		//获取封装后的参数字符串
		String postValue = JSON.toJSONString(paramMap);
		
		//执行post请求,并返回请求返回的值
		return WechatUtil.wechatPost(url, postValue);
	}

	/**
	 * @Description: 用于通过项目中的请求,执行关键字绑定微信中素材的方法
	 * 获取微信中对应的素材,
	 * 保存到本地
	 * 此种方法仅适用于保存文图消息和视频消息
	 * @param @param accessToken
	 * @param @param mediaId
	 * @param @param type
	 * @param @param key
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public String getAndSave(String accessToken,String mediaId,String type,String keyword){
		//验证参数的合法性
		if(StringUtils.isNull(accessToken)
				||StringUtils.isNull(mediaId)
				||StringUtils.isNull(type)
				||StringUtils.isNull(keyword))
			return Resp.error("input value has null");
		
		//限定这个方法只处理文图素材和视频素材
		if(!type.equals(WechatMaterial.NEWS)
				&&!type.equals(WechatMaterial.VIDEO))
			return Resp.error("method wechatMaterialService.getAndSave() need backMaterial type is news or video");
		
		//检查关键字是否被占用
		if(keywordIsExist(keyword))
			return Resp.error("keyword is exist");
		
		//调用获取素材的方法,获取素材
		String materialStr = get(accessToken, mediaId);
		if(materialStr==null)
			return Resp.error("get materialStr has error");
		
		//把获取到的String字符串,转成json
		JSONObject materialJson = JSON.parseObject(materialStr);
		
		//检查微信返回的数据是否有错误
		if(!StringUtils.isNull(materialJson.getString("errcode")))
			//如果errcode中有值,则说明请求发生错误
			return materialStr;
		
		//创建新的素材对象(用于保存),并传入通用的属性.
		WechatMaterial wm = new WechatMaterial(type, keyword, mediaId);
		
		//如果获取的不是文图消息,并且不是视频消息
		if(type.equals(WechatMaterial.NEWS))
			//根据保存的成功与否,返回相应的数据
			return saveNewsMaterialFromGet(accessToken,wm, materialJson)?Resp.success("saveNews success") : Resp.error("saveNews error");
		
		if(type.equals(WechatMaterial.VIDEO))
			return saveVideoMaterialFromGet(wm, materialJson)?Resp.success("saveVideo success") : Resp.error("saveVideo error");
		
		return Resp.error("type is not news or video, please use port 'wechat/material/listAndSave'");
	}
	
	
	/**
	 * @Description: 通过请求素材列表接口的方式保存素材到本地
	 * @param @param accessToken
	 * @param @param mediaId
	 * @param @param type
	 * @param @param keyword
	 * @param @param offset
	 * @param @param count
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public String listAndSave(String accessToken,String mediaId,String type,String keyword,int offset, int count){
		//验证参数的合法性
		if(StringUtils.isNull(accessToken)
				||StringUtils.isNull(mediaId)
				||StringUtils.isNull(type)
				||StringUtils.isNull(keyword))
			return Resp.error("input value has null");
		//对传入的数值参数进行合法化处理
		offset = offset<0 ? 0 : offset;
		count = (count<1||count>20) ? 20 : count;
		
		//检查关键字是否被占用
		if(keywordIsExist(keyword))
			return Resp.error("keyword is exist");
		
		//获取素材列表
		String materialsStr = list(accessToken, type, offset, count);
		//把获取到的素材集合字符串,转成json
		JSONObject materialListJson = JSON.parseObject(materialsStr);

		//检查返回的数据是否有错误
		if(!StringUtils.isNull(materialListJson.getString("errcode")))
			//如果errcode中有值,则说明请求发生错误
			return materialsStr;
		
		//获取素材列表中的素材,并封装成Json数组
		JSONArray materialJsonArr = materialListJson.getJSONArray("item");
		boolean saveOk = false;
		//循环数组,获取想要绑定的素材,并根据不同的素材类型进行不同的保存处理
		for (int i = 0; i < materialJsonArr.size(); i++) {
			//从素材数组中获取一个素材对象(items)
			JSONObject materialJson = materialJsonArr.getJSONObject(i);
			//对比此条素材是否是想要保存的素材(根据media_id)
			String itemMediaId = materialJson.getString("media_id");
			if(itemMediaId.equals(mediaId)){
				//创建新的素材对象(用于保存),并传入通用的属性.
				WechatMaterial wm = new WechatMaterial(type, keyword, mediaId);
				
				//如果获取的是文图消息
				if(type.equals(WechatMaterial.NEWS)){
					//保存文图素材的方法
					saveOk = saveNewsMaterialFromList(accessToken,wm, materialJson);

				//如果获取的是其他类型的消息
				}else{
					//保存其他类型的素材
					saveOk = saveOtherMaterialFromList(wm, materialJson);
				}
			}
		}
		
		//根据保存的成功与否,返回相应的数据
		return saveOk?Resp.success("saveImage success") : Resp.error("saveImage error");
	}
	
	/**
	 * @Description: 检查关键字在数据库中是否被占用
	 * @param @param keyword
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public boolean keywordIsExist(String keyword){
		//验证参数的合法性,如果传入的值为空,则直接返回未被使用
		if(StringUtils.isNull(keyword))
			return false;
		
		//创建WechatMaterial对象,用于对数据库的查询
		WechatMaterial wechatMaterial = new WechatMaterial();
		wechatMaterial.setKeyword(keyword);//射入查询条件
		
		//根据WechatMaterial设定的条件,从数据库中查询对应的集合
		List<WechatMaterial> wechatMaterialList = wechatMaterialDao.findList(wechatMaterial);
		if(wechatMaterialList==null||wechatMaterialList.size()==0)
			return false;//如果返回值不存在,则返回未被占用
		
		//若以上未被占用的条件均被排除,则说明被占用
		return true;
	}
	
	/**
	 * @Description: 保存从文图消息素材
	 * 注意:此文图消息素材是get方式获取的单个文图素材
	 * @param @param wm
	 * @param @param materialJson
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	public boolean saveNewsMaterialFromGet(String accessToken,WechatMaterial wechatMaterial,JSONObject materialJson) {
		//验证参数的合法性
		if(wechatMaterial==null||materialJson==null)
			return false;
		
		//1. 保存news素材的父级识别数据
		int addNews = add(wechatMaterial);
		//如果父级news对象保存不成功,直接返回false
		if(addNews!=1)
			return false;
		
		//2. 循环保存news素材中的文章
		JSONArray items = materialJson.getJSONArray("news_item");//获取素材中的单个文章
		//如果获取文章出错,则直接返回false
		if(items == null||items.size()<1)
			return false;
		for (int i = 0; i < items.size(); i++) {
			JSONObject item = items.getJSONObject(i);
			//创建一个新的素材对象,用于保存单个的文章
			WechatMaterial wm = new WechatMaterial();
			wm.setTitle(item.getString("title"));//设置图文消息的标题
			wm.setThumbMediaId((item.getString("thumb_media_id")));//图文消息的封面图片素材id（必须是永久mediaID）
			wm.setShowCoverPic((item.getIntValue("show_cover_pic")));//是否显示封面，0为false，即不显示，1为true，即显示
			wm.setAuthor((item.getString("author")));//作者
			wm.setDigest((item.getString("digest")));//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
			wm.setContent((item.getString("content")));//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
			wm.setUrl((item.getString("url")));//图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
			wm.setContentSourceUrl((item.getString("content_source_url")));//图文消息的原文地址，即点击“阅读原文”后的URL
			
			wm.setParentMediaId(wechatMaterial.getMediaId());//设置其父级的素材id
			wm.setSort(i+1);//设置文章的排序
			
			wm.setPicUrl(getPicUrlByMediaId(accessToken,item.getString("thumb_media_id")));
			
			int addItem = add(wm);
			//如果有一个保存没有成功,则直接返回false
			if(addItem!=1)
				return false;
		}
		
		//3. 返回最终结果
		return true;
	}
	
	/**
	 * @Description: 保存文图素材的方法
	 * 注意:此文图消息素材是list方式获取的文图素材列表
	 * @param @param wechatMaterial
	 * @param @param materialJson
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public boolean saveNewsMaterialFromList(String accessToken,WechatMaterial wechatMaterial,JSONObject materialJson){
		//验证参数的合法性
		if(wechatMaterial==null||materialJson==null)
			return false;
		
		//1. 保存news素材的父级识别数据
		int addNews = add(wechatMaterial);
		//如果父级news对象保存不成功,直接返回false
		if(addNews!=1)
			return false;
		
		//获取文图素材中的内容(文章列表)
		JSONObject materialContent = materialJson.getJSONObject("content");
		//获取内容中的文章列表
		JSONArray items = materialContent.getJSONArray("news_item");//获取素材中的单个文章
		//如果获取文章出错,则直接返回false
		if(items == null||items.size()<1)
			return false;
		
		//2. 循环保存news素材中的文章
		for (int i = 0; i < items.size(); i++) {
			JSONObject item = items.getJSONObject(i);
			//创建一个新的素材对象,用于保存单个的文章
			WechatMaterial wm = new WechatMaterial();
			wm.setTitle(item.getString("title"));//设置图文消息的标题
			wm.setThumbMediaId((item.getString("thumb_media_id")));//图文消息的封面图片素材id（必须是永久mediaID）
			wm.setShowCoverPic((item.getIntValue("show_cover_pic")));//是否显示封面，0为false，即不显示，1为true，即显示
			wm.setAuthor((item.getString("author")));//作者
			wm.setDigest((item.getString("digest")));//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
			wm.setContent((item.getString("content")));//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
			wm.setUrl((item.getString("url")));//图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
			wm.setContentSourceUrl((item.getString("content_source_url")));//图文消息的原文地址，即点击“阅读原文”后的URL
			
			wm.setParentMediaId(wechatMaterial.getMediaId());//设置其父级的素材id
			wm.setSort(i+1);//设置文章的排序
			
			wm.setPicUrl(getPicUrlByMediaId(accessToken,item.getString("thumb_media_id")));
			
			int addItem = add(wm);
			//如果有一个保存没有成功,则直接返回false
			if(addItem!=1)
				return false;
		}
		
		//3. 返回最终结果
		return true;
	}
	
	/**
	 * @Description: 保存视频素材的方式
	 * 注意:此种保存方式是处理通过get方式获取单个视频素材的结果
	 * @param @param wm
	 * @param @param materialJson
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	public boolean saveVideoMaterialFromGet(WechatMaterial wechatMaterial,JSONObject materialJson) {
		//验证参数的合法性
		if(wechatMaterial==null||materialJson==null)
			return false;
		
		//设置素材的title,
		//注意:(列表方式获取素材的时候,返回值中的name即为通常素材中的title,所以这里直接用title来接此字段)
		wechatMaterial.setTitle(materialJson.getString("title"));
		wechatMaterial.setDescription(materialJson.getString("description"));
		wechatMaterial.setUrl(materialJson.getString("down_url"));
		
		//如果保存正常,返回true,否则返回false
		return add(wechatMaterial)==1?true:false;
	}

	/**
	 * @Description: 添加文本素材的方法
	 * @param @param keyword
	 * @param @param content
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月22日
	 */
	public String saveTextMaterial(String keyword, String content) {
		//参数合法性校验
		if(StringUtils.isNull(keyword)
				||StringUtils.isNull(content))
			return Resp.error("addTextMaterial error keyword or content is null");
		
		//检查关键字是否被占用
		if(keywordIsExist(keyword))
			return Resp.error("keyword is exist");
		
		//创建素材对象,用于保存微信的素材
		WechatMaterial wechatMaterial = new WechatMaterial();
		wechatMaterial.setKeyword(keyword);//设置关键字
		wechatMaterial.setContent(content);//设置文本内容
		wechatMaterial.setMsgType(WechatMaterial.TEXT);
		
		//根据保存方法的返回结果,封装并返回相应的提示信息
		return add(wechatMaterial)==1?Resp.success("addTextMaterial success"):Resp.success("addTextMaterial error");
	}
	
	/**
	 * @Description: 保存其他类型素材的方法
	 * 注意:此种保存方式是处理通过list方式获取的除文图消息外的素材的列表
	 * @param @param wechatMaterial
	 * @param @param materialJson
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public boolean saveOtherMaterialFromList(WechatMaterial wechatMaterial,JSONObject materialJson){
		if(wechatMaterial==null||materialJson==null)
			return false;
		
		//设置素材的title,
		//注意:(列表方式获取素材的时候,返回值中的name即为通常素材中的title,所以这里直接用title来接此字段)
		wechatMaterial.setTitle(materialJson.getString("name"));
		wechatMaterial.setUrl(materialJson.getString("url"));
		System.out.println(materialJson.getString("url"));
		//如果保存正常,返回true,否则返回false
		return add(wechatMaterial)==1?true:false;
	}

	
}
