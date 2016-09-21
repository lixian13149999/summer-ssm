package com.bcdbook.summer.wechat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.wechat.dao.WechatDao;
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
			return BackMsg.error("accessToken is null");
		
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
			return BackMsg.error("input value has null");
		
		//检查关键字是否被占用
		if(keywordIsExist(keyword))
			return BackMsg.error("keyword is exist");
		
		//调用获取素材的方法,获取素材
		String materialStr = get(accessToken, mediaId);
		if(materialStr==null)
			return BackMsg.error("get materialStr has error");
		
		//把获取到的String字符串,转成json
		JSONObject materialJson = JSON.parseObject(materialStr);
		
		//检查返回的数据是否有错误
		if(!StringUtils.isNull(materialJson.getString("errcode")))
			//如果errcode中有值,则说明请求发生错误
			return materialStr;
		
		//创建新的素材对象(用于保存)
		WechatMaterial wm = new WechatMaterial();
		//添加公用的素材值
		wm.setKeyword(keyword);//设置关键字
		wm.setMsgType(type);//设置消息类型
		wm.setMediaId(mediaId);//设置素材的id
		
		//如果获取的是文图消息
		if(!type.equals(WechatMaterial.NEWS))
			return BackMsg.error("type not is news, plise use port 'wechat/material/listAndSave'");
		
		return saveNews(wm, materialJson)?BackMsg.success("saveNews success") : BackMsg.error("saveNews error");
//		
	}
	
	public String listAndSave(String accessToken,String mediaId,String type,String keyword,int offset, int count){
		//验证参数的合法性
		if(StringUtils.isNull(accessToken)
				||StringUtils.isNull(mediaId)
				||StringUtils.isNull(type)
				||StringUtils.isNull(keyword))
			return BackMsg.error("input value has null");
		//对传入的数值参数进行合法化处理
		offset = offset<0 ? 0 : offset;
		count = (count<1||count>20) ? 20 : count;
		
		//检查关键字是否被占用
		if(keywordIsExist(keyword))
			return BackMsg.error("keyword is exist");
		
		String materialsStr = list(accessToken, type, offset, count);
		
		//把获取到的素材集合字符串,转成json
		JSONObject materialListJson = JSON.parseObject(materialsStr);

		//检查返回的数据是否有错误
		if(!StringUtils.isNull(materialListJson.getString("errcode")))
			//如果errcode中有值,则说明请求发生错误
			return materialsStr;
		
		JSONArray materialJsonArr = materialListJson.getJSONArray("item");
		
		for (int i = 0; i < materialJsonArr.size(); i++) {
			JSONObject materialJson = materialJsonArr.getJSONObject(i);
			String itemMediaId = materialJson.getString("media_id");
			if(itemMediaId.equals(mediaId)){
				//创建新的素材对象(用于保存)
				WechatMaterial wm = new WechatMaterial();
				//添加公用的素材值
				wm.setKeyword(keyword);//设置关键字
				wm.setMsgType(type);//设置消息类型
				wm.setMediaId(mediaId);//设置素材的id
				//如果获取的是文图消息
				if(type.equals(WechatMaterial.NEWS)){
					saveNews(wm, materialJson);
//					wm.setTitle(materialJson.getString("title"));
//					wm.setThumbMediaId((materialJson.getString("title")));
//					wm.setShowCoverPic((materialJson.getIntValue("title")));
//					wm.setAuthor((materialJson.getString("title")));
//					wm.setDigest((materialJson.getString("title")));
//					wm.setContent((materialJson.getString("title")));
//					wm.setUrl((materialJson.getString("title")));
//					wm.setContentSourceUrl((materialJson.getString("title")));
				//如果获取的是其他类型的消息
				}else{
					saveNews(wm, materialJson);
//					wm.setTitle(materialJson.getString("title"));
//					wm.setDescription(materialJson.getString("description"));
//					wm.setUrl(materialJson.getString("url"));
				}
			}
		}
		
		return null;
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
		System.out.println(postValue);
		//执行请求,获取对应的素材对象
		String materialStr = WechatUtil.wechatPost(url, postValue);
		if(materialStr==null)
			return null;
		
		System.out.println(materialStr);
		return materialStr;
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
	
	public boolean saveNews(WechatMaterial wechatMaterial,JSONObject materialJson){
		return true;
	}
	public boolean saveOtherMaterial(WechatMaterial wechatMaterial,JSONObject materialJson){
		return true;
	}
}
