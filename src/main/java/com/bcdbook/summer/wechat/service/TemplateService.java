package com.bcdbook.summer.wechat.service;

/**
 * 
     * @Title: TemplateService.java    
     * @Description: 模板消息的service层
     * @author lason       
     * @created 2016年6月6日 上午9:09:06
 */
public interface TemplateService {
	
	public String sendTemplate(String openId,String templateId,String linkUrl,String data);
	/**
	 * 
	    * @Discription 修改所属行业的方法
	    * @author lason       
	    * @created 2016年6月6日 上午9:16:42     
	    * @param industryId1
	    * @param industryId2
	    * @return
	 */
	public String setIndustry(int industryId1,int industryId2);
	
	/**
	 * 
	    * @Discription 获取设置的行业信息
	    * @author lason       
	    * @created 2016年6月6日 上午9:17:45     
	    * @return
	 */
	public String getIndustryInfo();
	
	/**
	 * 
	    * @Discription 获取模板id的方法
	    * @author lason       
	    * @created 2016年6月6日 上午9:19:18     
	    * @return
	 */
	public String getTemplateId();
	
	/**
	 * 
	    * @Discription 获取模板列表
	    * @author lason       
	    * @created 2016年6月6日 上午9:21:53     
	    * @return
	 */
	public String getAllTemplate();
}
