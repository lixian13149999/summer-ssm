package com.bcdbook.summer.wechat.service;

import java.util.List;
import java.util.Map;

import com.bcdbook.summer.wechat.message.pojo.Message;

/**
 * 
 * @Title: MessageService.java
 * @Description: 处理用户发来的消息的service
 * @author lason
 * @created 2016年5月27日 上午10:37:01
 */
public interface MessageService {

	/**
	 * 
	 * @Discription 处理文本消息的方法
	 * 解析文本消息并返回要发送的message对象
	 * @author lason
	 * @created 2016年5月25日 下午1:23:25
	 * @param msg
	 * @return
	 */
	public Message processTextMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理图片消息
	 * 解析图片消息并返回要发送的message对象
	 * @author lason
	 * @created 2016年5月25日 下午1:27:04
	 * @param msg
	 * @return
	 */
	public Message processImageMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理语音消息
	 * 解析语音消息并返回要发送的message对象
	 * @author lason
	 * @created 2016年5月25日 下午1:27:16
	 * @param msg
	 * @return
	 */
	public Message processVoiceMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理视频消息
	 * 解析视频消息并返回要发送的message对象
	 * @author lason
	 * @created 2016年5月25日 下午1:27:38
	 * @param msg
	 * @return
	 */
	public Message processVideoMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理短视频消息
	 * 解析短视频消息并返回要发送的message对象
	 * @author lason
	 * @created 2016年5月25日 下午1:27:47
	 * @param msg
	 * @return
	 */
	public Message processShortvideoMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理地里位置消息
	 * @author lason
	 * @created 2016年5月25日 下午1:29:16
	 * @param msg
	 * @return
	 */
	public Message processLocationMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理连接消息
	 * 解析连接消息并返回要发送的message对象
	 * @author lason
	 * @created 2016年5月25日 下午1:28:01
	 * @param msg
	 * @return
	 */
	public Message processLinkMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 处理不能识别类型的消息
	 * @author lason
	 * @created 2016年5月25日 下午1:30:26
	 * @param msg
	 * @return
	 */
	public Message processUnknowMsg(Map<String, String> msg);

	/**
	 * 
	 * @Discription 根据传入的keyword获取message对象
	 * @author lason
	 * @created 2016年5月31日 上午9:40:23
	 * @param keyword
	 * @return
	 */
	public Message getMsgByKeyword(String keyword);
	
	/**
	 * 
	    * @Discription 根据传入的keyword获取对应的message信息,同时过滤掉当前id下的消息
	    * @author lason       
	    * @created 2016年6月1日 上午9:07:22     
	    * @param id
	    * @param keyword
	    * @return
	 */
	public Message getMsgByKeyword(int id,String keyword);
	
	/**
	 * 
	    * @Discription 根据名字查询出message对象
	    * @author lason       
	    * @created 2016年5月31日 上午9:49:48     
	    * @param name
	    * @return
	 */
	public Message getMsgByName(String name);

	/**
	 * 
	 * @Discription 根据传入的id获取message对象
	 * @author lason
	 * @created 2016年5月31日 上午9:40:50
	 * @param id
	 * @return
	 */
	public Message getMsgById(int id);
	
	/**
	 * 
	    * @Discription 根据传入的name和msgType查询message信息并过滤当前id的信息
	    * @author lason       
	    * @created 2016年6月1日 上午9:02:31     
	    * @param id
	    * @param name
	    * @param msgType
	    * @return
	 */
	public Message getMsgByNameAndType(int id,String name,String msgType);

	/**
	 * 
	 * @Discription 添加消息的方法
	 * @author lason
	 * @created 2016年5月30日 下午10:09:59
	 * @param message
	 * @return
	 */
	public String addMsg(Message message);

	/**
	 * 
	 * @Discription 更新消息的方法
	 * @author lason
	 * @created 2016年5月30日 下午10:11:32
	 * @param message
	 * @return
	 */
	public String updateMsg(Message message);

	/**
	 * 
	 * @Discription 根据传入的名字,查询有无对应的信息
	 * @author lason
	 * @created 2016年5月30日 下午10:13:49
	 * @param msgName
	 * @return
	 */
	public boolean hasMsgByName(String msgName);

	/**
	 * 
	 * @Discription 根据关键字,查询有无对应的信息
	 * @author lason
	 * @created 2016年5月30日 下午10:14:55
	 * @param keyword
	 * @return
	 */
	public boolean hasMsgByKeyword(String keyword);
	
	/**
	 * 
	    * @Discription 根据传入的消息类型和消息名称查询是否有这两个关键字一致的数据
	    * 注意:传入的id是用来过滤的信息,即除了id等于传入id的message对象以外,是否还存在关键字一致的数据
	    * @author lason       
	    * @created 2016年5月31日 下午11:02:52     
	    * @return
	 */
	public boolean hasMsgByTypeAndName(int msgId,String msgType, String name);
	
	
	
	//附加的方法，用来获取message列表
	public List<Message> getAllMsg();
	
	//按照id删除message
	 
	public String removeById(Integer id);

	/**
	 * 根据传入的条件，以及页码，查询符合条件的message的集合。并分页
	 * @param page
	 * @param size
	 * @param typeString
	 * @param keyword
	 * @return
	 */
	public List<Message> listMsgByCondition(int page, int size,
			String typeString, String keyword);
	
	public int countPage(int size,String typeString, String keyword);
}
