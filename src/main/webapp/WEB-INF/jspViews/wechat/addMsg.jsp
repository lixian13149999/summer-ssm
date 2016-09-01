<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	//添加验证填入信息的前端验证，只验证是否为空，全部为字符串
	function checkData(){
	//alert("1111111111111111111111111");
	//alert(document.getElementById("name").value.trim()=="");
	if(document.getElementById("name").value.trim()==""){
		return false;
	}
	if(document.getElementById("keyword").value.trim()==""){
		return false;
	}
	if(document.getElementById("msg_type").value.trim()==""){
		return false;
	}
	if(document.getElementById("content").value.trim()==""){
		return false;
	}
	if(document.getElementById("remark").value.trim()==""){
		return false;
	}
	
	}
	
	</script>

  </head>
  
  <body>
  <center>
    
	  <h3 style="color:red">添加Message页面</h3>
	  <hr/>
	  <form action="${ctx}/wechat/addMsg" method="post" onsubmit="return checkData()">
		  <table>
		  	<tr>
		  		<td>name:</td>
		  		<td><input type="text" name="name" id="name"></td>
		  	</tr>
		  	<tr>
		  		<td>keyword:</td>
		  		<td><input type="text" name="keyword" id="keyword"></td>
		  	</tr>
		  	<tr>
		  		<td>msg_type:</td>
		  		<td><input type="text" name="msgType" id="msg_type"></td>
		  	</tr>
		  	<tr>
		  		<td>content:</td>
		  		<td><input type="text" name="content" id="content"></td>
		  	</tr>
		   <tr>
		  		<td>remark:</td>
		  	    <td><input type="text" name="remark" id="remark"></td>
		   </tr>
		   <tr>	  		
		  	    <td colspan="2" align="right"><input type="submit" value="addMessage"></td>
		   </tr>
		  </table>
	  </form>
  </center>
  </body>
</html>
