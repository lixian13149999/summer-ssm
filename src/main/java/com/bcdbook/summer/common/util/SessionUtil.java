package com.bcdbook.summer.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static boolean refresh(HttpServletRequest req,String key,Object obj){
		if(req==null||StringUtils.isNull(key)||obj==null)
			return false;
		
		return add(req, key, obj);
	}

	/**
	 * @Description: 添加session的方法
	 * @param @param req
	 * @param @param key
	 * @param @param obj
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月14日
	 */
	public static boolean add(HttpServletRequest req,String key,Object obj){
		//检查参数是否合法
		if(req==null||StringUtils.isNull(key)||obj==null)
			return false;
		
		//检查此对象在session中是否存在
		if(checkObjIsExist(req, key)){
			//如果存在则从session中删除此对象
			if(remove(req, key)){
				//如果删除成功,则添加新的数据到session
				get(req).setAttribute(key, obj);
			}
		}else{
			//如果session中不存在,则直接添加session
			get(req).setAttribute(key, obj);
		}
		return true;
	}
	
	/**
	 * @Description: 删除session中对应的数据
	 * @param @param req
	 * @param @param key
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月14日
	 */
	public static boolean remove(HttpServletRequest req,String key){
		//若参数不合法,则直接返回false
		if(req==null||StringUtils.isNull(key))
			return false;
		
		//检查此key值是否被使用,
		if(checkObjIsExist(req, key))
			//如果被使用,则删除此key
			get(req).removeAttribute(key);

		//如果不存在,或已删除均返回true
		return true;
	}
	
	/**
	 * @Description: 检查一个key值是否被使用
	 * @param @param req
	 * @param @param key
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author lason
	 * @date 2016年9月14日
	 */
	public static boolean checkObjIsExist(HttpServletRequest req,String key){
		//若有不合法的参数,直接返回false
		if(req==null||StringUtils.isNull(key))
			return false;
		
		//获取session对象
		HttpSession session = get(req);
		//如果session为空,则直接返回false
		if(session==null)
			return false;
		
		//通过传入的key值获取key值对应的对象数据
		Object oldObj = session.getAttribute(key);
		//如果对象不存在,则发挥false
		if(oldObj==null)
			return false;
		
		//如果上面拦截均未进入,则返回存在
		return true;
		
	}
	
	/**
	 * @Description: 获取session的方法
	 * @param @param req
	 * @param @return   
	 * @return HttpSession  
	 * @throws
	 * @author lason
	 * @date 2016年9月14日
	 */
	public static HttpSession get(HttpServletRequest req){
		//如果传入的request对象为空,直接返回null,否则调用返回session对象
		return req==null ? null: req.getSession();
	}
	
	public static Object getObj(HttpServletRequest req,String key){
		HttpSession session = get(req);
//		Object obj = null;
//		if(session!=null){
//			obj = session.getAttribute(key);
//		}
//		return obj;
		
		return session==null?null:session.getAttribute(key);
	}
}




