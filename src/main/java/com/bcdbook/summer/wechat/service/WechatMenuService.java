package com.bcdbook.summer.wechat.service;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.wechat.util.WechatUtil;

@Service("wechatMenuService")
public class WechatMenuService {
	/**
	 * 
	    * @Discription 创建菜单的方法
	    * @author lason
	    * @created 2016年6月1日 下午1:59:15
	    * @return
	    * @see com.bcdbook.summer.wechatold.service.ConnectService#createMenu()
	 */
	public String createMenu(String accessToken){
		
		//获取配置文件中定义的栏目
		String menu = WechatUtil.getMenu();
		if(menu==null)
			return null;

       //拼接请求路径
       String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
       
       //定义返回值
       return WechatUtil.wechatPost(url, menu);
	}

	/**
	 * @Description: 查看菜单的方法
	 * @param @param accessToken
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月19日
	 */
	public String getMenu(String accessToken) {
		//拼接请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+accessToken;
		//执行请求,并返回微信的返回值
		return WechatUtil.wechatGet(url);
	}
}
