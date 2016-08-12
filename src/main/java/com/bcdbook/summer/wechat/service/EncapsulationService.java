package com.bcdbook.summer.wechat.service;

import java.util.List;
import java.util.Map;

import com.bcdbook.summer.wechat.message.pojo.Message;
import com.bcdbook.summer.wechat.message.pojo.resp.Article;
import com.bcdbook.summer.wechat.message.pojo.resp.Image;
import com.bcdbook.summer.wechat.message.pojo.resp.Music;
import com.bcdbook.summer.wechat.message.pojo.resp.Video;
import com.bcdbook.summer.wechat.message.pojo.resp.Voice;

public interface EncapsulationService {
	/**
	 * 
	    * @Discription 封装消息的总控制器,用来分发封装任务
	    * @author lason       
	    * @created 2016年5月31日 下午7:20:00     
	    * @param reqMsg
	    * @param message
	    * @return
	 */
	public String encapsulationMsg(Map<String, String> reqMsg,Message message);
	

	/**
	 * 
	 * @Discription 封装文本类型的返回值
	 * @author lason
	 * @created 2016年5月25日 下午2:16:56
	 * @param reqMsg
	 * @param respContent
	 * @return
	 */
	public String encapsulationTextMsg(Map<String, String> reqMsg,
			String respContent);

	/**
	 * 
	 * @Discription 封装图片消息
	 * @author lason
	 * @created 2016年5月27日 上午11:10:21
	 * @param reqMsg
	 * @param image
	 * @return
	 */
	public String encapsulationImageMsg(Map<String, String> reqMsg, Image image);

	/**
	 * 
	 * @Discription 封装语音消息
	 * @author lason
	 * @created 2016年5月27日 上午11:10:34
	 * @param reqMsg
	 * @param voice
	 * @return
	 */
	public String encapsulationVoiceMsg(Map<String, String> reqMsg, Voice voice);

	/**
	 * 
	 * @Discription 封装视频消息
	 * @author lason
	 * @created 2016年5月27日 上午11:10:49
	 * @param reqMsg
	 * @param video
	 * @return
	 */
	public String encapsulationVideoMsg(Map<String, String> reqMsg, Video video);

	/**
	 * 
	 * @Discription 封装音乐消息
	 * @author lason
	 * @created 2016年5月27日 上午11:11:05
	 * @param reqMsg
	 * @param music
	 * @return
	 */
	public String encapsulationMusicMsg(Map<String, String> reqMsg, Music music);

	/**
	 * 
	 * @Discription 封装文图消息
	 * @author lason
	 * @created 2016年5月27日 上午11:11:20
	 * @param reqMsg
	 * @param articles
	 * @return
	 */
	public String encapsulationNewsMsg(Map<String, String> reqMsg,
			List<Article> articles);
	/**
	 * 
	 * @Discription 不返回消息
	 * @author lason
	 * @created 2016年5月27日 上午10:58:24
	 * @param reqMsg
	 * @return
	 */
	public String encapsulationNoMsg(Map<String, String> reqMsg);
	
	/**
	 * 
	 * @Discription 封装欢迎信息
	 * @author lason
	 * @created 2016年5月27日 上午10:57:42
	 * @param reqMsg
	 * @return
	 */
	public String encapsulationWellcomeMsg(Map<String, String> reqMsg);

	/**
	 * 
	 * @Discription 封装不能识别的消息的返回数据
	 * @author lason
	 * @created 2016年5月27日 上午10:57:54
	 * @param reqMsg
	 * @return
	 */
	public String encapsulationUnknowableMsg(Map<String, String> reqMsg);


	/**
	 * 
	 * @Discription 封装帮助消息
	 * @author lason
	 * @created 2016年5月27日 上午10:58:34
	 * @param reqMsg
	 * @return
	 */
	public String encapsulationHelpMsg(Map<String, String> reqMsg);

}
