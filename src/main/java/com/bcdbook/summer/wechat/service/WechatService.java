package com.bcdbook.summer.wechat.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.wechat.dao.WechatDao;
import com.bcdbook.summer.wechat.pojo.Wechat;

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
	public String getParameter(String wechatKey){
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
	public int save(Wechat wechat){
		//验证参数
		if(wechat==null)
			return 0;
		//验证key是否为空
		String wechatKey = wechat.getWechatKey();
		if(wechatKey==null)
			return 0;
		
//		Wechat dbWecaht = getWechatByKey(wechatKey);
//		if(dbWecaht==null){
//			return add(wechat);
//		}else{
//			return update(wechat);
//		}
		//如果数据库中存在此key,则执行添加,否则执行更新
		return getWechatByKey(wechatKey)==null?add(wechat):update(wechat);
	}
	
	/**
	    * @Discription 通过key值获取微信对象的方法
	    * @author lason       
	    * @created 2016年9月18日 下午9:33:27     
	    * @param wechatKey
	    * @return
	 */
	public Wechat getWechatByKey(String wechatKey){
		//参数合法性验证
		if(wechatKey==null)
			return null;
		
		//创建新的wechat对象,用于封装查询条件
		Wechat wechat = new Wechat();
		wechat.setWechatKey(wechatKey);
		
		//返回获取到的微信对象
		return getByCondition(wechat);
	}
}
