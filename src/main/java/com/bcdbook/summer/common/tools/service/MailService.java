package com.bcdbook.summer.common.tools.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.summer.common.persistence.service.CrudService;
import com.bcdbook.summer.common.tools.dao.MailDao;
import com.bcdbook.summer.common.tools.pojo.Mail;
import com.bcdbook.summer.common.tools.util.MailUtil;
import com.bcdbook.summer.common.util.JadeUtil;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.dao.UserDao;
import com.bcdbook.summer.system.pojo.User;

/**
 * @Description: 邮件相关处理的service
 * @author lason
 * @date 2016年9月18日
 */
@Service("mailService")
public class MailService extends CrudService<MailDao,Mail> {
	@Resource
	private MailDao mailDao;
	
	
	public static final String VERIFY_EMAIL = "verifyEmail";
	

	/**
	 * @Description: 发送绑定邮件的验证信息的邮件
	 * @param @param user
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月18日
	 */
	public boolean sendVerifyEmail(User user) {
		if(user==null)
			return false;
		//获取邮件的接收者
		String toAddress = user.getEmail();
		
//		//获取对应的邮件对象
//		Mail dbMail = getMailByKey(VERIFY_EMAIL);
//		
//		//创建modal对象,用来替换jade页面中的变量
//		Map<String, Object> modal = new HashMap<String, Object>();
//		//获取想要发送的页面
//		String mailContent = JadeUtil.getBodyView(dbMail.getModalUrl(), modal);
//		
//		//把获取到的页面设置到mail对象中
//		dbMail.setContent(mailContent);
		
		
		
		Mail dbMail = new Mail();
		dbMail.setSubject("bcdbook用户绑定 用户验证邮件");
		dbMail.setText("邮箱验证的文本内容");
		dbMail.setContent("localhost:8888/summer/user/verifyEmail?id="+user.getId()+"&email="+user.getEmail()+"&remark="+user.getRemark());
		
		//执行发送操作
		return MailUtil.send(toAddress, dbMail);
	}
	
	/**
	 * @Description: 根据key值获取相应的邮件对象
	 * @param @param key
	 * @param @return   
	 * @return Mail  
	 * @throws
	 * @author lason
	 * @date 2016年9月18日
	 */
	public Mail getMailByKey(String key){
		if(StringUtils.isNull(key))
			return null;
					
		Mail mail = new Mail();
		mail.setMailKey(key);
		
		return mailDao.getByCondition(mail);
	}
	
}
