package com.bcdbook.summer.common.tools.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.summer.common.backmsg.BackMsg;
import com.bcdbook.summer.common.tools.service.MailService;
import com.bcdbook.summer.common.util.IdGen;
import com.bcdbook.summer.common.util.StringUtils;
import com.bcdbook.summer.system.pojo.User;
import com.bcdbook.summer.system.service.UserService;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Resource
	private MailService mailService;
	@Resource
	private UserService userService;
	
	/**
	    * @Discription 用于发送邮件的接口
	    * @author lason       
	    * @created 2016年9月16日 下午6:38:25     
	    * @param req
	    * @param resp
	    * @param mail
	    * @return
	 */
	@RequestMapping(value="/sendVerifyEmail",method = {RequestMethod.POST}) 
	@ResponseBody
	public String sendVerifyEmail(HttpServletRequest req,HttpServletResponse resp){
		//获取前台传入的用户id
		String userId = req.getParameter("userId");
		//获取前台传入的,用于绑定的邮箱
		String emailAddr = req.getParameter("emailAddr");
		
		//判断获取到的参数是否合法,如果不合法,直接返会错误信息
		if(StringUtils.isNull(userId)
				||StringUtils.isNull(emailAddr))
			return BackMsg.error("request userId is null or emailAddr is null");
		
		//通过传入的用户id,获取对应的用户
		User user = userService.get(userId);
		//判断对应的用户是否存在,如果不存在,直接返回错误信息
		if(user==null)
			return BackMsg.error("user is not exit");
		
		//获取一个用于验证的编码
		String accessToken = IdGen.uuid();
		//设置用于验证的邮箱到用户的绑定邮箱字段
		user.setEmail(emailAddr);
		//设置验证编码到用户的备注字段,以备验证
		user.setRemark(accessToken);
		//更新用户相关信息,以便于后期验证时调用
		userService.update(user);
		
		return mailService.sendVerifyEmail(user)?BackMsg.success(true, "send verify email success"):BackMsg.error("send verify email fail");
	}
}
