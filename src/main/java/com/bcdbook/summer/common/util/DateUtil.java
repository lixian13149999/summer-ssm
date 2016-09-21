package com.bcdbook.summer.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	// //如果数据存在
	// if(wechat!=null){
	// //获取存入的时间
	// String uTime = wechat.getUpdateTime();
	// // System.out.println(uTime);
	// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// try {
	// //把更新时间转换成data类型
	// Date updateTime= sf.parse(uTime);
	// //获取一个当前系统时间
	// Date now = new Date();
	// long min = (now.getTime()-updateTime.getTime())/(1000*60);
	// // System.out.println(Min);
	// if(min > 90){
	// // System.out.println("将要执行更新方法");
	// String accessToken= this.makeAccessToken();
	//
	// wechat.setAccessToken(accessToken);
	// wechat.setUpdateTime(new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));
	//
	// wechatDao.refreshToken(wechat);
	// }
	// 传入时间点,获取String类型的时间点
	public static String toLongTimeString(String dateTime) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
		String longTimeString = null;
		if (dateTime != null) {
			Date date = null;
			try {
				date = sf.parse(dateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			longTimeString = String.valueOf(date.getTime());
			return longTimeString;
		} else {
			return String.valueOf(new Date().getTime());
		}
	}

	// 传入时间点,获取String类型的时间对象
	public static String toTimeString(String longTimeString) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
		String timeString = null;
		if (longTimeString != null) {
			long longTime = Long.parseLong(longTimeString);
			Date d = new Date(longTime);
			timeString = sf.format(d);
			return timeString;
		} else {
			return sf.format(new Date());
		}
	}
	

	/**
	 * @Description: 获取时间戳,10位
	 * @param @return   
	 * @return long  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public static long getDateTime10(){
		String dateTimeStr = String.valueOf((new Date()).getTime());
		String dateTimeStrSub = dateTimeStr.substring(0, 10);
		long dateTime = Long.valueOf(dateTimeStrSub);
		return dateTime;
	}
	
	/**
	 * @Description: 获取时间戳,8位
	 * @param @return   
	 * @return long  
	 * @throws
	 * @author lason
	 * @date 2016年9月21日
	 */
	public static long getDateTime8(){
		String dateTimeStr = String.valueOf((new Date()).getTime());
		String dateTimeStrSub = dateTimeStr.substring(0, 8);
		long dateTime = Long.valueOf(dateTimeStrSub);
		return dateTime;
	}
	
	public static String getTimeStr(){
		return String.valueOf(getTime());
	}
	
	public static long getTime(){
		return (new Date()).getTime();
	}
}
