package com.bcdbook.summer.wechat.service;

public interface MaterialService {
	/**
	 * 
	    * @Discription 获取各种素材的总数
	    * @author lason       
	    * @created 2016年5月31日 下午8:54:26     
	    * @return
	 */
	public String countMaterials();
	
	/**
	 * 
	 * @Discription 获取素材列表
	 * @author lason
	 * @created 2016年5月30日 上午10:19:09
	 * @return
	 */
	public String listMaterial(String materialType,int offset,int count);
	

	/**
	 * 
	 * @Discription 根据素材id获取素材
	 * @author lason
	 * @created 2016年5月30日 上午10:57:04
	 * @param mediaId
	 * @return
	 */
	public String getMaterial(String mediaId);

	/**
	 * 
	    * @Discription 更新素材到本地数据库的方法
	    * @author lason       
	    * @created 2016年6月1日 下午4:44:34     
	    * @return
	 */
	public String refreshMaterialToLocal();
	
	/**
	 * 
	    * @Discription 更新语音消息的方法
	    * @author lason       
	    * @created 2016年6月1日 下午5:13:31     
	    * @param materialCount
	    * @return
	 */
	public String refreshVoiceMaterialToLocal(int materialCount);
	/**
	 * 
	    * @Discription 更新视频消息的方法
	    * @author lason       
	    * @created 2016年6月1日 下午5:13:51     
	    * @param materialCount
	    * @return
	 */
	public String refreshVideoMaterialToLocal(int materialCount);
	/**
	 * 
	    * @Discription 更新图片消息的方法
	    * @author lason       
	    * @created 2016年6月1日 下午5:14:05     
	    * @param materialCount
	    * @return
	 */
	public String refreshImageMaterialToLocal(int materialCount);
	/**
	 * 
	    * @Discription 更新文图消息的方法
	    * @author lason       
	    * @created 2016年6月1日 下午5:14:31     
	    * @param materialCount
	    * @return
	 */
	public String refreshNewsMaterialToLocal(int materialCount);
}
