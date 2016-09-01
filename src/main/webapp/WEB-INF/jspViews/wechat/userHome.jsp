<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>个人主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="initial-scale=1, user-scalable=0, minimal-ui">

<link rel='shortcut icon' href='${ctx}/public/imgs/logo32.png' >

<link rel="stylesheet" type="text/css" href="${ctx}/public/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/lib/css/buttons.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/lib/iconfont/iconfont.css">

<link rel="stylesheet/less" type="text/css" href="${ctx}/public/css/main.less">

<link rel="stylesheet/less" type="text/css" href="${ctx}/public/css/wechat/login.less">
<%-- 
 --%>
</head>


<body>
	登录成功
	<%-- <div class="container">
		<div class="row">
			<div class="col-xs-1 col-xs-offset-5"></div>
			<div class="container col-xs-5 logo-cont">
				<i class="icon icon-logo logo"></i>
			</div>
		</div>

		<form class="form">
			<div class="form-group form-group-lg">
				<div class="container col-xs-12 input-group">
					<i class="icon icon-user login-icon input-group-addon"></i>
					<input type="text" class="form-control" id="username" placeholder="用户名" />
				</div>
			</div>
			<div class="form-group form-group-lg">
				<div class="container col-xs-12 input-group">
					<i class="icon icon-key login-icon input-group-addon"></i> 
					<input type="password" class="form-control" id="pwd" placeholder="密码" />
				</div>
			</div>

			<div class="row">
				<div class="col-xs-1 col-xs-offset-5"></div>
				<div class="col-xs-5 button-cont">
					<button href="javascript:s_user.login()"
						class="button button-border-thin button-primary login-button">登录</button>
				</div>
			</div>
		</form>
		<input type="text" id="openid" value="${openid}" />
		<input type="text" id="code" value="${code}" />
		<input type="text" id="state" value="${state}" />
		<input type="text" id="nickname" value="${nickname}" />
		<input type="text" id="sex" value="${sex}" />
		<input type="text" id="city" value="${city}" />
		<input type="text" id="country" value="${country}" />
		<input type="text" id="headimgurl" value="${headimgurl}" />
	</div>

		
	<script type="text/javascript">
	</script> --%>
	<script type="text/javascript">var ctx = "${ctx}";</script>
	<script type="text/javascript" src="${ctx}/public/lib/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/public/lib/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/public/lib/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/public/lib/js/less.min.js"></script>
	
	<%-- <script type="text/javascript" src="${ctx}/public/js/wechat/login.js"></script> --%>
</body>

</html>