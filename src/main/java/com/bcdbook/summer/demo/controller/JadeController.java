package com.bcdbook.summer.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.summer.system.pojo.User;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.exceptions.JadeCompilerException;

/**
 * 常用的Demo的控制器
 * @author lason
 *
 */
@Controller
@RequestMapping("/demo/jade")
public class JadeController {
	/*
	 * 获取index.jade页面
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String getUser(HttpServletRequest request,Model imodel){
		List<User> users = new ArrayList<User>();
//		users.add(new User("The Hitchhiker's Guide to the Galaxy","password", 5));
//		users.add(new User("Life, the Universe and Everything","password", 5));
//		users.add(new User("The Restaurant at the End of the Universe","password", 5));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("users", users);
		model.put("pageName", "My Bookshelf");
		
		String html = null;
		try {
			html = Jade4J.render("/Users/lason/java/tomcat7/webapps/summer/static/demo/index.jade", model);
		} catch (JadeCompilerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return html;
	}
	
}
