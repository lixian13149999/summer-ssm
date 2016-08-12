package com.bcdbook.summer.wechat.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.summer.common.enums.BaseEnum.BackMsg;
import com.bcdbook.summer.common.enums.WechatEnum.IWechatEnum;
import com.bcdbook.summer.wechat.common.pojo.Wechat;
import com.bcdbook.summer.wechat.dao.WechatDao;
import com.bcdbook.summer.wechat.service.ConnectService;
import com.bcdbook.summer.wechat.util.SHA1;
import com.bcdbook.summer.wechat.util.WechatRequest;
import com.bcdbook.summer.wechat.util.WechatUtil;

/**
 * 
 * @Title: ConnectService.java
 * @Description: 微信初始验证相关的逻辑处理类
 * @author lason
 * @created 2016年5月24日 上午9:41:51
 */
@Service("connectService")
public class ConnectServiceImpl implements ConnectService {
	@Resource
	private WechatDao wechatDao;//注入wechatservice

	private static String Token = IWechatEnum.TOKEN.getValue();// token
	String signature = null;// 微信发送的加密后的签名
	String timestamp = null;// 时间戳
	String nonce = null;// 随机数
	String echostr = null;// 随机字符串
	
	/**
	 * 
	 * @Discription 验证URL的真实性,真实返回true否则返回false
	 * @author lason
	 * @created 2016年5月24日 上午9:43:49
	 * @param request
	 * @return
	 */
	@Override
	public Boolean safeuuid(HttpServletRequest request) {
		signature = request.getParameter("signature");// 获取加密签名
		timestamp = request.getParameter("timestamp");// 获取时间戳
		nonce = request.getParameter("nonce");// 随机数
		echostr = request.getParameter("echostr");// 随机字符串

		// 以上所有值都不为空,才进入验证,否则没有验证的必要性,直接返回false
		if (signature != null && !signature.equals("") && timestamp != null
				&& !timestamp.equals("") && nonce != null && !nonce.equals("")
				&& echostr != null && !echostr.equals("")) {

			// 创建一个集合,用来存放和排序从端口获取的值
			List<String> params = new ArrayList<String>();
			params.add(Token);
			params.add(timestamp);
			params.add(nonce);

			// 1. 将token、timestamp、nonce三个参数进行字典序排序
			Collections.sort(params, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
			String temp = SHA1.encode(params.get(0) + params.get(1)
					+ params.get(2));

			// 生成的加密值,和传入的加密值相同,返回true,否则返回false
			if (temp.equals(signature)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 
	    * @Discription 通过调用微信端的接口,获取accessToken的方法
	    * @author lason       
	    * @created 2016年6月1日 下午1:33:42      
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.ConnectService#makeAccessToken()
	 */
	@Override
	public String makeAccessToken() {
		//定义请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ IWechatEnum.APP_ID.getValue() + "&secret=" + IWechatEnum.APP_SECRET.getValue();
		//定义用来返回的accessToken,
		String accessToken = null;
		try {
			String tokenBack = WechatRequest.wechatGet(url);
			if(tokenBack!=null){
				JSONObject tokenJson = JSON.parseObject(tokenBack);
				accessToken = tokenJson.getString("access_token");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	/**
	 * 
	    * @Discription 把微信公众平台获取到的存入到本地数据库
	    * @author lason       
	    * @created 2016年5月30日 下午7:34:59      
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.ConnectService#addAccessToken()
	 */
	@Override
//	@Transactional
	public String addAccessToken() {
		/*
		 * 1. 调用本类的makeAccessToken获取accessToken
		 * 2. 调用connectDao.insert方法插入到数据库
		 * 3. 数据插入成功后返回成功信息(BaseEnum类中的BackMsg的SUCCESS)
		 * 4. 数据插入错误返回BackMsg的ERROR
		 * 5. 注:
		 * 		1. id为自动更新
		 * 		2. addtime和update为当前系统时间
		 * 		3. refresh_token暂时为空
		 * 		4. access_token为makeAccessToken的返回值
		 * 6. 特别注意:不同处理功能的代码最好有空行,以做区分.(相应的代码块写上注释)
		 */
		
		Wechat wechat=new Wechat();
		String accessToken = this.makeAccessToken();//--此处为正式上线时的写法，下方为测试写法
//		String accessToken="asfdasdbhrie最新";
		Date addTime=new Date();
		Date updateTime=new Date();
		//<<===================以此为界,上方为创建或获取值的处理,下面为填充设置wechat对象的处理,所以此处应该加一行空格
		wechat.setAccessToken(accessToken);
		wechat.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS").format(addTime));
		wechat.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS").format(updateTime));
		//<<===================以此为界,上方为填充设置wechat对象的处理,下面为输入插入的处理,所以此处应该加一行空格
		wechatDao.insert(wechat);
		
		return BackMsg.SUCCESS.getValue();
	}
	
	/**
	 * 
	    * @Discription 更新accessToken的方法
	    * @author lason       
	    * @created 2016年5月30日 下午11:39:05      
	    * @param id
	    * @param newAccessToken
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.ConnectService#updateAccessToken(int, java.lang.String)
	 */
	@Override
//	@Transactional//这里涉及到查询操作,为避免脏读和并发带来的问题,这里使用了事物处理和线程锁定(synchronized)
	public String updateAccessToken(int id) {
//		System.out.println("进入了service层的updateAccessToken");
		/*
		 * 1. 根据传入的id获取对应的wechat对象(调用WechatDao中的selectById)
		 * 2. 检查updateTime字段的时间,与现在的时间相比是否大于90分钟
		 * 		1. 如果大于90分钟,调用此类的makeAccessToken获取一个新的accessToken,并执行更新
		 * 		2. 否则 不执行
		 * 3. 执行更新时,updatetime设置成当前系统时间
		 * 4. 成功返回SUCCESS,否则返回ERROR
		 */
		
		//从数据库中获取wechat对象
		Wechat wechat=wechatDao.selectById(id);
		
		//如果数据存在
		if(wechat!=null){
			//获取存入的时间
			String uTime = wechat.getUpdateTime();
//			System.out.println(uTime);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
			try {
				//把更新时间转换成data类型
				Date updateTime= (uTime==null)?new Date(1464159131):sf.parse(uTime);
				//获取一个当前系统时间
				Date now = new Date();
				long min = (now.getTime()-updateTime.getTime())/(1000*60);
//				System.out.println(Min);
				if(min > 90){
//					System.out.println("将要执行更新方法");
					String accessToken= this.makeAccessToken();
					
					wechat.setAccessToken(accessToken);
					wechat.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS").format(now));
					
					wechatDao.refreshToken(wechat);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return BackMsg.SUCCESS.getValue();
		}else{
			return BackMsg.DATA_NOT_EXIST.getValue();
		}
	}
	
	/**
	 * 
	    * @Discription 从数据库中获取最新的accessToken的方法
	    * @author lason       
	    * @created 2016年5月30日 下午8:50:10      
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.ConnectService#getAccessToken()
	 */
	@Override
	public String getAccessToken() {
		
//		==================重新处理=====================
		/*
		 * 1. 这里没有传入值,所以也不可能是从controller层获取主
		 * 2. 一般service层获取值的方式
		 * 		1. 由controller层传入具体的值(处理过后的特定对象的特定数据)
		 * 		2. 从request中获取,由controller层传入的request对象,调用对应的方法,获取想要得到的值
		 */

		
		
//		==================处理方式=====================
		/*
		 * 1. 调用WechatDao中的selectRecent方法,获取最新的wechat对象,或者直接获取accessToken值
		 * 2. 直接返回查询出来的accessToken
		 */
		Wechat wechat=wechatDao.selectRecent();
		String accessToken = null;
		if(wechat!=null){
			accessToken = wechat.getAccessToken();
		}
		
		return accessToken;
	}
	
	/**
	 * 
	    * @Discription 创建菜单的方法
	    * @author lason
	    * @created 2016年6月1日 下午1:59:15
	    * @return
	    * @see com.bcdbook.summer.wechat.service.ConnectService#createMenu()
	 */
	@Override
	public Boolean createMenu(){
		WechatUtil wechatUtil = new WechatUtil();
		
		//定义栏目的样式
		String menu = wechatUtil.getMenu();

        //此处改为自己想要的结构体，替换即可
        String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
        
        Boolean createOk = false;
        try {
			String backValue = WechatRequest.wechatPost(url, menu);
			if(backValue!=null){
				JSONObject backJson = JSON.parseObject(backValue);
				System.out.println(JSON.toJSONString(backJson));
				int errcode = backJson.getIntValue("errcode");
				if(errcode==0){
					createOk = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return createOk;
	}
	
	@Override
	public String getCode() {
		//微信公众号的app_id
		String APP_ID = IWechatEnum.APP_ID.getValue();
		//定义回调的连接地址
		String REDIRECT_URI = "http://strom.bcdbook.com/wechat/getUserInfo";
		//snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
		String SCOPE = "snsapi_userinfo";
		//自己传过去的参数
		String STATE = "shouquan";
		//定义获取code的url地址
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=APP_ID&"
				+ "redirect_uri=REDIRECT_URI&"
				+ "response_type=code&"
				+ "scope=SCOPE&"
				+ "state=STATE#wechat_redirect";
		url = url.replace("APP_ID", WechatUtil.urlEnodeUTF8(APP_ID));
		url = url.replace("REDIRECT_URI", WechatUtil.urlEnodeUTF8(REDIRECT_URI));
		url = url.replace("SCOPE", WechatUtil.urlEnodeUTF8(SCOPE));
		url = url.replace("STATE", WechatUtil.urlEnodeUTF8(STATE));
		
//		System.out.println(url);
		return url;
	}
	
	/**
	 * 
	    * @Discription 获取openid和相关数据的方法
	    * @author lason       
	    * @created 2016年6月3日 下午3:00:31      
	    * @param code
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.ConnectService#getOpenId(java.lang.String)
	 */
	@Override
	public String exchangeAccessToken(String code) {
		//微信公众号的app_id
		String APP_ID = IWechatEnum.APP_ID.getValue();
		//定义回调的连接地址
		String APP_SECRET = IWechatEnum.APP_SECRET.getValue();
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=APPID&"
				+ "secret=SECRET&"
				+ "code=CODE&"
				+ "grant_type=authorization_code";
		
		url = url.replace("APPID", WechatUtil.urlEnodeUTF8(APP_ID));
		url = url.replace("SECRET", WechatUtil.urlEnodeUTF8(APP_SECRET));
		url = url.replace("CODE", WechatUtil.urlEnodeUTF8(code));
		
		String backData = null;
		try {
			backData = WechatRequest.wechatGet(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return backData;
	}
	
	/**
	 * 
	    * @Discription 根据openid获取用户详细信息的方法
	    * @author lason       
	    * @created 2016年6月3日 下午5:02:36      
	    * @param accessToken
	    * @param openId
	    * @return     
	    * @see com.bcdbook.summer.wechat.service.ConnectService#getWechatUserInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public String getWechatUserInfo(String accessToken, String openId) {
		String url = "https://api.weixin.qq.com/sns/userinfo?"
				+ "access_token=ACCESS_TOKEN&"
				+ "openid=OPENID&"
				+ "lang=zh_CN";
		
		url = url.replace("ACCESS_TOKEN", WechatUtil.urlEnodeUTF8(accessToken));
		url = url.replace("OPENID", WechatUtil.urlEnodeUTF8(openId));
		
		String backData = null;
		try {
			backData = WechatRequest.wechatGet(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return backData;
	}
}
