package com.bcdbook.summer.common.enums;

/**
 * 
     * @Title: WechatEnum.java    
     * @Description: 微信公众号的枚举库
     * @author lason       
     * @created 2016年5月25日 下午12:29:29
 */
public class WechatEnum {
	/**
	 * 
	     * @Title: WechatEnum.java    
	     * @Description: 公众号的基础配置的枚举对象
	     * @author lason       
	     * @created 2016年5月25日 下午12:29:50
	 */
	public enum IWechatEnum{
		/**
		 * 个人微信公众号的配置
		 */
//		ACCESS_TOKEN("ijmL7n8L-4oIcNdC9VFj57QKatiIjxwChz7c-Ng2bJtO7Bq0UooZUjfGQ3_gONLdobDvNpzJSb7dmfYaPf7KTg3XWO1FUnsFQdCHmcPXaH8AFQgAAANSD","生成的密钥"),
//		APP_ID("wxd19632a7323cec33","应用ID"),
//		APP_SECRET("c414992afedfe0155c857f907cebd8da","应用密钥"),
//		URL("http://strom.bcdbook.com/wechat","连接地址"),
//		TOKEN("bcdbookweixin","令牌"),
//		ENCODING_AES_KEY("2Uc6oEJuyTmvBGzHNNQWcB61Y1JLAShas56qEaziySE","消息解密密钥");
		
		/**
		 * 绿能宝微信公众号的配置
		 */
//		ACCESS_TOKEN("qkriKF3JTXgn7_JU2iMAkieH3dS-Lf2BPa7V299oLYbG_xaBMYPIWuZHyVd_TTTNshaZxq4rWx2Kngl1OF2urnMhBHJAvGO1vlU22KVjdOx-YWCDTwQbPoOSdBvqzxS_VZOdAEABFU","生成的密钥"),
		ENDORSEMENT("endorsement","已授权关键字"),
		NOT_ENDORSEMENT("notEndorsement","未授权关键字"),
		MY_HOME("myhome","个人中心"),
		APP_ID("wx18d7b3cb67983626","应用ID"),
		APP_SECRET("bd8194a6707b334583b363a8318b59f0","应用密钥"),
		URL("http://strom.bcdbook.com/wechat","连接地址"),
		TOKEN("solar","令牌"),
		ENCODING_AES_KEY("1BIqibAgB4GnC1izNv0wEab0YrErwiv3uaaRCmElBBV","消息解密密钥");
		
		private final String value;
		private final String name;
		//构造器默认也只能是private, 从而保证构造函数只能在内部使用
		private IWechatEnum(String value,String name) {
            this.value = value;
            this.name = name;
        }
        
        public String getValue() {
            return value;
        }
        public String getName(){
        	return name;
        }
	}
	
	/**
	 * 
	     * @Title: WechatEnum.java    
	     * @Description: 公众号消息类型的枚举对象
	     * @author lason       
	     * @created 2016年5月25日 下午12:30:14
	 */
	public enum MsgType{
//		text,image,voice,video,shortvideo,location,link,news;
		RESP_MESSAGE_TYPE_TEXT("text","文本消息"),
		RESP_MESSAGE_TYPE_IMAGE("image","图片消息"),
		RESP_MESSAGE_TYPE_VOICE("voice","语音消息"),
		RESP_MESSAGE_TYPE_VIDEO("video","视频消息"),
		RESP_MESSAGE_TYPE_SHORT_VIDEO("shortvideo","短视频消息"),
		RESP_MESSAGE_TYPE_MUSIC("music","音乐消息"),
		RESP_MESSAGE_TYPE_LOCATION("location","位置消息"),
		RESP_MESSAGE_TYPE_LINK("link","连接消息"),
		RESP_MESSAGE_TYPE_NEWS("news","文图消息");
		
		private final String value;
		private final String name;
		//构造器默认也只能是private, 从而保证构造函数只能在内部使用
		private MsgType(String value,String name) {
            this.value = value;
            this.name = name;
        }
        
        public String getValue() {
            return value;
        }
        public String getName(){
        	return name;
        }
	}
}
