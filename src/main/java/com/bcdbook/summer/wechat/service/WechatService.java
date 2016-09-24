package com.bcdbook.summer.wechat.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.util.DateUtil;
import com.bcdbook.summer.common.util.MD5Util;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.pojo.User;
import com.bcdbook.summer.wechat.dao.WechatDao;
import com.bcdbook.summer.wechat.pojo.Wechat;
import com.bcdbook.summer.wechat.util.WechatUtil;

/**
     * @Title: WechatService.java    
     * @Description: 微信基础信息对象类的service
     * @author lason       
     * @created 2016年9月18日 下午9:32:04
 */
@Service("wechatService")
public class WechatService extends CrudService<WechatDao, Wechat> {
	@Resource
	private WechatDao wechatDao;
	
	/**
	    * @Discription 根据key值,获取对应的微信参数值
	    * @author lason       
	    * @created 2016年9月18日 下午9:32:29     
	    * @param wechatKey
	    * @return
	 */
	private String getParameter(String wechatKey){
		//验证参数是否合法
		if(wechatKey==null)
			return null;
		
		//调用根据key值或wechat对象的方法,获取对应的微信对象
		Wechat wechat = getWechatByKey(wechatKey);
		//若获取值不为空,则返回相应的value值
		return wechat==null?null:wechat.getWechatValue();
	}
	
	/**
	    * @Discription 对参数值执行保存的方法
	    * @author lason       
	    * @created 2016年9月18日 下午9:33:11     
	    * @param wechat
	    * @return
	 */
	private int save(Wechat wechat){
		//验证参数
		if(wechat==null)
			return 0;
		//验证key是否为空
		String wechatKey = wechat.getWechatKey();
		if(wechatKey==null)
			return 0;

		//如果数据库中存在此key,则执行添加,否则执行更新
		Wechat dbWechat = getWechatByKey(wechatKey);
		if(dbWechat==null)
			return add(wechat);
		
		String dbId = dbWechat.getId();
		wechat.setId(dbId);
		return update(wechat);
	}
	
	/**
	    * @Discription 通过key值获取微信对象的方法
	    * @author lason       
	    * @created 2016年9月18日 下午9:33:27     
	    * @param wechatKey
	    * @return
	 */
	private Wechat getWechatByKey(String wechatKey){
		//参数合法性验证
		if(wechatKey==null)
			return null;
		
		//创建新的wechat对象,用于封装查询条件
		Wechat wechat = new Wechat();
		wechat.setWechatKey(wechatKey);
		
		//返回获取到的微信对象
		return getByCondition(wechat);
	}

	/**
	 * @Description: 用于刷新数据库中accessToken的方法
	 * @param    
	 * @return void  
	 * @throws
	 * @author lason
	 * @date 2016年9月19日
	 */
	public void refreshAccessToken() {
		//获取数据库中的存有accessToken的wechat对象
		Wechat dbWechat = getWechatByKey(Wechat.KEY_ACCESS_TOKEN);
		//判断对象是否存在,,如果对象不存
		if(dbWechat==null){
			//直接进行存储操作
			doRefreshAccessToken();
		}else{
			//获取原accessToken的更新时间
			String updataTimeStr = dbWechat.getUpdateTime();
			//如果更新时间为空,或者accessToken的值为空
			if(StringUtils.isNull(updataTimeStr)||StringUtils.isNull(dbWechat.getWechatValue())){
				//直接进行存储操作
				doRefreshAccessToken();
			}else{
				long updataTime = Long.parseLong(updataTimeStr);//把更新时间的时间戳转换成long类型
				long thisTime = DateUtil.getTime();//获取当前系统时间的时间戳
				//如果距离上次更新超过一个小时,或更新时间大于当前系统时间
				if((thisTime-updataTime)/1000/60>60||(thisTime-updataTime)<0){
					//执行存储操作
					doRefreshAccessToken();
				}
			}
		}
	}
	private boolean doRefreshAccessToken(){
		//创建微信对象
		Wechat wechat = new Wechat();
		//设置key值
		wechat.setWechatKey(Wechat.KEY_ACCESS_TOKEN);
		//设置value值
		wechat.setWechatValue(WechatUtil.getNewAccessTokenFromWechatServer());
		//执行保存操作
		return save(wechat)==1?true:false;
	}
	
	
	public String getAccessToken(){
		return getParameter(Wechat.KEY_ACCESS_TOKEN);
	}

	/**
	    * @Discription 添加微信关注者的相关信息到用户表
	    * 此操作会预存关注者的相关信息
	    * 执行绑定后会做合并用户的操作
	    * @author lason       
	    * @created 2016年9月24日 下午7:58:51     
	    * @param accessToken
	    * @param openId
	    * @return
	 */
	public User getUserInfo(String accessToken,String openId) {
		//验证参数的合法性
		if(StringUtils.isNull(accessToken)
				||StringUtils.isNull(accessToken))
			return null;
		//获取用户的详细信息
		String userInfo = WechatUtil.getUserInfo(accessToken, openId);
		//如果获取到的值为空,则直接返回空
		if(StringUtils.isNull(userInfo))
			return null;
		
		//把获取到的值转换成JSONObject类型
		JSONObject userJson = JSONObject.parseObject(userInfo);
		
		//如果返回值出错,则直接返回null
		//当subscribe=0时表示用户没有关注此公众号,无法获取相关信息
		if(!StringUtils.isNull(userJson.getString("errmsg"))
				||userJson.getIntValue("subscribe")==0)
			return null;
		
		User user = new User();
		user.setOpenId(userJson.getString("openid"));
		//TODO --这里的昵称还没有加入,需要后期加入
//		user.setNickname(userJson.getString("nickname"));
		user.setGender(userJson.getIntValue("sex"));
		//TODO --头像的设置也需要加入
//		user.setPhoto(userJson.getString("headimgurl"));
		user.setPhoneState(User.UNBOUND);//设置手机为未绑定状态
		user.setEmailState(User.UNBOUND);//设置邮箱为未绑定状态
		user.setWechatState(User.UNBOUND);//设置微信为未绑定状态
		user.setIsLock(User.LOCK);//设置用户为锁定状态
		
		return user;
	}
	
	
}
