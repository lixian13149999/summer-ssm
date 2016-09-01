package com.bcdbook.summer.common.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.bcdbook.summer.common.config.Global;
import com.bcdbook.summer.system.pojo.Menu;
import com.bcdbook.summer.system.pojo.Power;
import com.bcdbook.summer.system.pojo.User;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.exceptions.JadeCompilerException;

/**
 * @Description: jade模板引擎使用到的一些工具类
 * @author lason
 * @date 2016年9月1日
 */
public class JadeUtil {
	/**
	 * @Description: 通过地址直接获取jade视图的方法
	 * @param @param src
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	public static ModelAndView getView(String src){
		return src==null?null:(new ModelAndView(src));
	}
	/**
	 * @Description: 通过传入的地址和model数据值,封装jade视图
	 * @param @param src
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	public static ModelAndView getView(String src,Map<String, ?> model){
		return src!=null&&model!=null ? (new ModelAndView(src, model)):null;
	}
	
	/**
	 * @Description: 根据传入的request和视图路径,返回封装了session中用户数据的jade模型
	 * @param @param req
	 * @param @param src
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	public static ModelAndView getSealedSessionView(HttpServletRequest req,String src){
		ModelAndView mv = getView(src);
		if(mv==null)
			return null;
		
		return preBackView(req, mv)?mv:null;
	}
	/**
	 * @Description: 根据传入的request和视图路径,返回封装了session中用户数据的jade模型
	 * @param @param req
	 * @param @param src
	 * @param @param model
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	public static ModelAndView getSealedSessionView(HttpServletRequest req,String src,Map<String, ?> model){
		ModelAndView mv = getView(src, model);
		if(mv==null)
			return null;
		
		return preBackView(req, mv)?mv:null;
	}
	
	/*
	 * 把session中的值写入模型对象的方法
	 */
	public static boolean preBackView(HttpServletRequest req,ModelAndView mv){
		if(mv==null)
			return false;
		if(req==null)
			return false;
		
		HttpSession session = req.getSession();
		if(session==null)
			return false;
		
		User user = (User) session.getAttribute("onlineUser");
		List<Menu> menus = (List<Menu>) session.getAttribute("onlineMenus");
		List<Power> powers = (List<Power>) session.getAttribute("onlinePowers");
		
		mv.addObject("onlineUser", user);
		mv.addObject("onlineMenus", menus);
		mv.addObject("onlinePowers", powers);
		
		return true;
	}
	
	/**
	 * @Description: 当视图返回值为html页面时,需要执行的封装方法
	 * @param @param req
	 * @param @param src
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	public static String getBodyView(HttpServletRequest req,String src,Map<String, Object> model){
		String viewPath = Global.getConfig("jade.viewPath");
		if(viewPath!=null)
			return null;
		String absolutePath = req.getSession().getServletContext().getRealPath(viewPath);
		if(absolutePath!=null)
			return null;
		
		String fileName = absolutePath+src;
		
		String html = null;
		try {
			html = Jade4J.render(fileName, model);
		} catch (JadeCompilerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}
	
	/**
	 * @Description: 当视图返回值为html页面时,需要执行的封装方法,同时写入session中的内容
	 * @param @param req
	 * @param @param src
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author lason
	 * @date 2016年9月1日
	 */
	public static String getSealedSessionBodyView(HttpServletRequest req,String src,Map<String, Object> model){
		return preBackBodyView(req, model)?getBodyView(req, src, model):null;
	}
	
	/*
	 * 把session中的值写入模型对象的方法
	 */
	public static boolean preBackBodyView(HttpServletRequest req,Map<String, Object> model){
		if(model==null)
			return false;
		if(req==null)
			return false;
		
		HttpSession session = req.getSession();
		if(session==null)
			return false;
		
		User user = (User) session.getAttribute("onlineUser");
		List<Menu> menus = (List<Menu>) session.getAttribute("onlineMenus");
		List<Power> powers = (List<Power>) session.getAttribute("onlinePowers");
		
		model.put("onlineUser", user);
		model.put("onlineMenus", menus);
		model.put("onlinePowers", powers);
		
		return true;
	}
	
}
