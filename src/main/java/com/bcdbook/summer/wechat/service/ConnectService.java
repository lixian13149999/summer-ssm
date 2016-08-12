package com.bcdbook.summer.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Title: ConnectService.java
 * @Description: 用于微信连接和一些基础操作的接口
 * @author lason
 * @created 2016年5月25日 下午12:38:50
 */
public interface ConnectService {
	/**
	 * 
	 * @Discription 微信连接时用于验证
	 * @author lason
	 * @created 2016年5月25日 下午12:39:29
	 * @param request
	 * @return
	 */
	public Boolean safeuuid(HttpServletRequest request);

	/**
	 * 
	 * @Discription 获取AccessToken
	 * @author lason
	 * @created 2016年5月25日 下午12:40:00
	 * @return
	 */
	public String makeAccessToken();
	
	/**
	 * 
	    * @Discription 添加accessToken到数据库(t_wechat表)
	    * @author lason       
	    * @created 2016年5月30日 上午9:34:35     
	    * @return
	 */
	public String addAccessToken();
	
	/**
	    * @Discription 更新accessToken到数据库(t_wechat表)
	    * @author lason       
	    * @created 2016年5月30日 上午9:35:04     
	    * @return
	 */
	public String updateAccessToken(int id);
	
	/**
	 * 
	    * @Discription 从数据库中查询出最新的(时间最接近当前)accessToken数据
	    * @author lason       
	    * @created 2016年5月30日 上午9:35:40     
	    * @return
	 */
	public String getAccessToken();
	

	/**
	 * 
	 * @Discription 创建微信下方的菜单
	 * @author lason
	 * @created 2016年5月25日 下午12:40:17
	 * @return
	 */
	public Boolean createMenu();

	/**
	 * 
	 * @Discription 用户验证时用于获取code
	 * @author lason
	 * @created 2016年5月27日 上午10:33:15
	 * @return
	 */
	public String getCode();
	
	/**
	 * 
	    * @Discription 获取openid的方法
	    * @author lason       
	    * @created 2016年6月3日 下午2:48:20     
	    * @return
	 */
	public String exchangeAccessToken(String code);

	/**
	 * 
	    * @Discription 根据用户id和accesstoken获取用户信息的方法
	    * @author lason       
	    * @created 2016年6月3日 下午5:00:44     
	    * @param accessToken
	    * @param openId
	    * @return
	 */
	public String getWechatUserInfo(String accessToken,String openId);
}
