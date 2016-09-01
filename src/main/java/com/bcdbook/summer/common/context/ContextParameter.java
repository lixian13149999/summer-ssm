package com.bcdbook.summer.common.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
     * @Title: ContextParameter.java    
     * @Description: 
     * 	这是一个监听器,在项目启动的时候设置全局变量
     * 	用于设定全局的变量,以避免页面文件的相对路径出错的现象
     * @author lason       
     * @created 2016年5月19日 下午2:11:48
 */
public class ContextParameter implements ServletContextListener {
	public static String ctx = null;
	/**
	    * @Discription 项目注销是的方法重写
	    * @author lason       
	    * @created 2016年5月19日 下午2:13:04      
	    * @param context     
	    * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent context) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	    * @Discription 项目启动的时候执行的方法重写
	    * 	此方法用于设定全局变量
	    * @author lason       
	    * @created 2016年5月19日 下午2:13:58      
	    * @param context     
	    * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent context) {
		ServletContext sc = context.getServletContext();
		System.out.println(sc.getContextPath().toString());
		
		String icontext = sc.getContextPath().toString();
		ctx = icontext;
		sc.setAttribute("ctx", context);
	}

	public static String getContextPath(){
		return ctx;
	}
}
