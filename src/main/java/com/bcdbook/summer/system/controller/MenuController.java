package com.bcdbook.summer.system.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcdbook.summer.system.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	private MenuService menuService;
	

}
