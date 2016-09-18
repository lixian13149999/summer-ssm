package com.bcdbook.summer.wechat.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcdbook.summer.wechat.service.WechatService;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
	@Resource
	private WechatService wechatService;

	
}
