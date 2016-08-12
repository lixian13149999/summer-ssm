package com.bcdbook.summer.wechat.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.enums.BaseEnum.BackMsg;
import com.bcdbook.summer.wechat.dao.WechatMsgDao;
import com.bcdbook.summer.wechat.message.pojo.Message;
import com.bcdbook.summer.wechat.service.EncapsulationService;
import com.bcdbook.summer.wechat.service.MessageService;

/**
 * 
 * @Title: MessageServiceImpl.java
 * @Description: 处理消息的方法
 * @author lason
 * @created 2016年5月27日 上午10:22:28
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
	@Resource
	private EncapsulationService encapsulationService;
	@Resource
	private WechatMsgDao wechatMsgDao;
	@Resource
	private MessageService messageService;

	/**
	 * 
	    * @Discription 解析文本消息的方法
	    * @author lason       
	    * @created 2016年5月31日 下午7:14:50      
	    * @param msg
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.MessageService#processTextMsg(java.util.Map)
	 */
	@Override
	public Message processTextMsg(Map<String, String> msg) {
		System.out.println("接收到文本消息");

		if(msg!=null){
			/**
			 * 用来返回的xml格式的字符串
			 */
			// 获取客户发送过来的文本消息
			String content = msg.get("Content");
			//根据文本消息关键字查询出要返回的消息对象
			Message message = null;
			if(content!=null){
				message = messageService.getMsgByKeyword(content);
			}
			return message;
		}
		return null;
	}

	@Override
	public Message processImageMsg(Map<String, String> msg) {
		System.out.println("接收到图片消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processVoiceMsg(Map<String, String> msg) {
		System.out.println("接收到语音消息");
		if(msg!=null){
//			String respXML = null;
			//获取语音识别后的结果
			String sourceReco = msg.get("Recognition");
			String recognition = sourceReco.substring(0, sourceReco.length()-1);
			//根据识别后的关键字查询出要返回的消息对象
			Message message = null;
			if(recognition!=null){
				message = messageService.getMsgByKeyword(recognition);
			}
			return message;
		}
		return null;
	}

	@Override
	public Message processVideoMsg(Map<String, String> msg) {
		System.out.println("接收到视频消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processShortvideoMsg(Map<String, String> msg) {
		System.out.println("接收到短视频消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processLocationMsg(Map<String, String> msg) {
		System.out.println("接收到地址消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processLinkMsg(Map<String, String> msg) {
		System.out.println("接收到连接消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processUnknowMsg(Map<String, String> msg) {
		
		System.out.println("接收到无法识别类型的消息");
		if(msg!=null){
			//获取语音识别后的结果
			String recognition = "unknown";
			//根据识别后的关键字查询出要返回的消息对象
			Message message = null;
			if(recognition!=null){
				//根据unknown关键字获取对应的消息
				message = messageService.getMsgByKeyword(recognition);
			}
			return message;
		}
		return null;
	}

	/**
	 * 
	 * @Discription 根据传入的keyword,查询并返回message对象
	 * @author lason
	 * @created 2016年5月31日 上午9:42:40
	 * @param keyword
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#getMsgByKeyword(java.lang.String)
	 */
	@Override
	public Message getMsgByKeyword(String keyword) {		
		Message message = null;
		message = wechatMsgDao.selectByCondition(null,keyword, null,null);		
		return message;
	}
	
	/**
	 * 
	 * @Discription 根据传入的keyword获取对应的message信息,同时过滤掉当前id下的消息
	 * @author lason
	 * @created 2016年5月31日 上午9:42:40
	 * @param keyword
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#getMsgByKeyword(java.lang.String)
	 */
	@Override
	public Message getMsgByKeyword(int id, String keyword) {
		//按keyword查询message并排除给定id的记录
		Message message =null;
		message=wechatMsgDao.selectByCondition(id, keyword, null, null);
		return message;
	}

	/**
	 * 
	 * @Discription 根据传入的name,查询并返回message对象
	 * @author lason
	 * @created 2016年5月31日 上午9:50:26
	 * @param name
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#getMsgByName(java.lang.String)
	 */
	@Override
	public Message getMsgByName(String name) {		
		Message message = null;
		message = wechatMsgDao.selectByCondition(null,null, name,null);
		return message;
	}

	/**
	 * 
	 * @Discription 根据传入的id,查询并返回message对象
	 * @author lason
	 * @created 2016年5月31日 上午9:43:19
	 * @param id
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#getMsgById(int)
	 */
	@Override
	public Message getMsgById(int id) {
		Message message = null;
		message = wechatMsgDao.selectById(id);
		return message;
	}

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
	@Override
	public Message getMsgByNameAndType(int id, String name, String msgType) {
		Message message=null;
		
		message=wechatMsgDao.selectByCondition(id,null,name, msgType);
				
		return message;
	}
	
	/**
	 * 
	 * @Discription 添加消息的方法
	 * @author lason
	 * @created 2016年5月31日 上午9:20:07
	 * @param message
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#addMsg(com.bcdbook.summer.wechat.message.pojo.Message)
	 */
	@Override
	public String addMsg(Message message) {
		/*
		 * 1. 检查message中的keyword是否为空 1. 如果为空返回BackMsg中的DATA_IS_NULL 2. 否则执行下一步操作
		 * 2. 调用本类的hasMsgByKeyword检查此关键字下的消息是否存在 1. 如果存在返回BackMsg中的DATA_EXIST 2.
		 * 否则执行插入方法(调用WechatMsgDao中的insert方法) 3.
		 * 根据传入的message,调用WechatMsgDao.insert,执行添加 4. 插入数据成功返回SUCCESS 5.
		 * 否则返回ERROR
		 */
		
		String keyword=message.getKeyword();
		if ( keyword== null) {
			//============这里是否是应该返回error
			return BackMsg.DATA_IS_NULL.getValue();
		} else {
			//判断消息是否存在，是则返回提示信息
			if(this.hasMsgByKeyword(keyword)){
				return BackMsg.DATA_EXIST.getValue();
			}else{
				/*
				 * 规则:(调用hasMsgByTypeAndName())查询是否有msgType和name同时一致的msg对象,如果存在,则返回DATA_EXIST
				 * 否则执行插入添加操作
				 * 注意:这里传入的id为0
				 */
				if(!this.hasMsgByTypeAndName(0, message.getMsgType(), message.getName())){
					//执行插入操作
					wechatMsgDao.insert(message);
					//返回成功信息
					return BackMsg.SUCCESS.getValue();
				}else{
					
					return BackMsg.DATA_EXIST.getValue();
				}
			}
		}
	}

	/**
	 * 
	 * @Discription 更新回复消息的方法
	 * @author lason
	 * @created 2016年5月31日 上午9:33:13
	 * @param message
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#updateMsg(com.bcdbook.summer.wechat.message.pojo.Message)
	 */
	@Override
	public String updateMsg(Message message) {
		/*
		 * 1. 根据传入的message对象中的keyword调用本类的getMsgByKeyword方法查询出数据库中的对象
		 * 1.判断是否有值,如果有执行下一步操作 2. 如果没有值,返回DATA_NOT_EXIST 2.
		 * 根据传入的message对象,更新对应的数据 3. 更新依据keyword
		 */
		
		int msgId=message.getId();
		Message msg = this.getMsgById(msgId);
		// 这里如果修改关键字,则会报错,所以更新应该根据id获取msg对象
		if(msg!=null){	
			/*
			 * 规则:(调用hasMsgByTypeAndName())查询是否有msgType和name同时一致的msg对象,如果存在,则返回DATA_EXIST
			 * 否则执行插入添加操作
			 * 注意过滤本条信息
			 */
			//添加检测
			if((!this.hasMsgByTypeAndName(msgId, message.getMsgType(),message.getName()))&&(this.getMsgByKeyword(msgId, message.getKeyword())==null)){
				
				//执行更新操作
				wechatMsgDao.update(message);
				//这里是否添加返回成功的消息
				return BackMsg.SUCCESS.getValue();
				
				
			}else{
					return BackMsg.DATA_EXIST.getValue();
			}
			
		}else{			
			return BackMsg.DATA_NOT_EXIST.getValue();
		}
	}

	/**
	 * 
	 * @Discription 根据名字,查询有此名字的message是否存在
	 * @author lason
	 * @created 2016年5月31日 上午9:46:28
	 * @param msgName
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#hasMsgByName(java.lang.String)
	 */
	@Override
	public boolean hasMsgByName(String msgName) {
		/*
		 * 1. 调用本类的getMsgByName查询message对象,如果message不为空则此message存在 1.
		 * message存在的时候返回true 2. 否则,返回false;
		 */		
		Message message = this.getMsgByName(msgName);

		if (message == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @Discription 根据keyword查询有此keyword的message是否存在
	 * @author lason
	 * @created 2016年5月31日 上午9:47:05
	 * @param keyword
	 * @return
	 * @see com.bcdbook.summer.wechat.service.MessageService#hasMsgByKeyword(java.lang.String)
	 */
	@Override
	public boolean hasMsgByKeyword(String keyword) {
		/*
		 * 1. 调用本类的getMsgByKeyword查询message对象,如果message不为空则此message存在 1.
		 * message存在的时候返回true 2. 否则,返回false;
		 */
	
		Message message = this.getMsgByKeyword(keyword);

		if (message == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 
	    * @Discription 根据传入的消息类型和消息名称查询是否有这两个关键字一致的数据
	    * 注意:传入的id是用来过滤的信息,即除了id等于传入id的message对象以外,是否还存在关键字一致的数据
	    * @author lason       
	    * @created 2016年5月31日 下午11:06:47      
	    * @param msgId
	    * @param msgType
	    * @param name
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.MessageService#hasMsgByTypeAndName(int, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean hasMsgByTypeAndName(int msgId, String msgType, String name) {
		if(this.getMsgByNameAndType(msgId,name,msgType)!=null){			
			return true;
		}else{
			return false;
		}
	
	}
	//附加的方法，用来获取message列表
	@Override
	public List<Message> getAllMsg() {
		// TODO Auto-generated method stub
		
		return wechatMsgDao.selectAllMsg();
	}

	//处理删除或者添加
	@Override
	public String removeById(Integer id) {
		// TODO Auto-generated method stub
		wechatMsgDao.deleteById(id);
		return BackMsg.SUCCESS.getValue();
	}
	
	
	//根据条件，分页查询
	@Override
	public List<Message> listMsgByCondition(int page, int size,
			String typeString, String keyword) {		
		// TODO Auto-generated method stub
		int begin=(page-1)*size;
		
		return wechatMsgDao.getMsgPageByCondition(begin, size, typeString, keyword);
		//return null;
	}
	
	@Override
	public int countPage(int size, String typeString, String keyword) {

		int countMsg = wechatMsgDao.countMsgByConditon(typeString, keyword);
		int countPage = 0;
		countPage = countMsg%size==0?countMsg/size:(countMsg/size+1);
		
		return countPage;
	}
}
