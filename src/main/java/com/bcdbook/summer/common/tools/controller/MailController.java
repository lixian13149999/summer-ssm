package com.bcdbook.summer.common.tools.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.summer.common.tools.pojo.Mail;
import com.bcdbook.summer.common.tools.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Resource
	private MailService mailService;
	
	/**
	    * @Discription 用于发送邮件的接口
	    * @author lason       
	    * @created 2016年9月16日 下午6:38:25     
	    * @param req
	    * @param resp
	    * @param mail
	    * @return
	 */
	@RequestMapping(value="/send",method = {RequestMethod.GET}) 
	@ResponseBody
	public String send(HttpServletRequest req,HttpServletResponse resp,Mail mail){
		//检查传入的mail对象是否为空,如果为空,直接返回发送失败
//		if(mail==null)
//			return BackMsg.error("request mail is null");
//		
//		//检查收件者邮箱,或想要发送的邮件类型是否为空,如果为空则直接返回错误信息
//		if(StringUtils.isNull(mail.getReceiver())||StringUtils.isNull(mail.getMailKey()))
//			return BackMsg.error("receiver is null or key is null");
//		
//		//调用发送方法,并获取发送是否成功的返回值
//		boolean sendOk = mailService.sendMail(mail.getReceiver(),mail.getMailKey());
//		
//		//根据前台传入的用户对象,从数据库中获取user
//		Mail dbMail = mailService.getByCondition(mail);
////		System.out.println(user);
////		System.out.println(dbUser);
//		//如果用户不存在,返回true,否则返回false
//		return dbMail==null?"true":"false";
		return "false";
	}
}
