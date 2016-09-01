<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<!-- 自定义样式 -->
	<style>
		table,table tr td,table tr{
		border:solid red 1px;
		border-collapse: collapse;
		text-align: center;	
		}
	</style>
	<!-- js  脚本 -->
	<script>	
		//发送ajax请求删除一条msg
		var http_request=new XMLHttpRequest();
		function deleteMsg(msgId){
	
		
		
		sendUrl="${ctx}/wechat/deleteMsg?msgId="+msgId;
		http_request.open("GET", sendUrl,true);
        http_request.send();		
	    //readyState共有5中状态，0未初始化，1已初始化，2发送请求，3开始接收结果，4接收结果完毕。
	    //status服务器响应状态码
	    
     http_request.onreadystatechange = function(){
	 
		  if (http_request.readyState == 4) {		
			    if (http_request.status == 200) {
			      //alert("ajax fanhui jieguo");
			      var str = http_request.responseText;
			      if(str==1){
			      	alert("删除成功！");
			      	window.location.reload();
			      }else{
			     	 alert("删除失败！");
			      }
			    }
			    //发送请求资源不存在
			    else if(http_request.status == 404){
			      alert("请求资源不存在！");
			    }else {
			      alert("Ajax请求失败，错误状态为："+http_request.status);
			    }
		 	 }	
				
				/*
				if(http_request.readyState == 3){
				  alert("3开始接收结果");
				
				}
				if(http_request.readyState == 2){
				  alert("2发送请求");
				}
				if(http_request.readyState == 1){
				  alert("1已初始化");
				}
				if(http_request.readyState == 0){
				  alert("0未初始化");
				}*/				//
			}
		}
	</script>
  </head>
  
  <body>
   
    <center>
	    <div id="myDiv">	    
		    <div id="msgList">	   
		    <hr/>
		    <h1 style="color:red">Message列表页面</h1>
		    <hr width="50%"/>
		    
			    <table style="border:gray solid 1px">
			    	<tr> 
			    		<td>Id</td>
			    		<td>name</td>
			    		<td>keyword</td>
			    		<td>msg_type</td>
			    		<td>content</td>
			    		<td>remark</td>
			    		<td>操作</td>
			    	</tr>
			    	<c:forEach var="msg" items="${msgList }">
			    		<tr > 
				    		<td>${msg.id}</td>
				    		<td>${msg.name}</td>
				    		<td>${msg.keyword}</td>
				    		<td>${msg.msgType}</td>
				    		<td><!-- 这里content太长暂时用其他代替 ${msg.content}-->伪Content</td>
				    		<td>${msg.remark}</td>
				    		<td>
								<input type="button" value="修改" onclick="window.location.href='${ctx}/wechat/toModify?id=${msg.id }'"></input>
				    		    <input type="button" value="删除" onclick="deleteMsg('${msg.id}')"></input>			    		
				    		</td>
			    		</tr>   	
			   		</c:forEach>    	
			    </table>
			</div>
			<div id="paging">
					<!-- 首页和上一页 -->
				<a href="">首页</a>   <a href="">&lt;&lt;上一页</a> 
		    		<!-- 中间的页数 -->
					<a href="">[1]</a>
					<a href="">[2]</a>
					<a href="">[3]</a>
					<a href="">[4]</a>
					<a href="">[5]</a>
					<a href="">[6]</a>
					<a href="">[7]</a>
					<a href="">[8]</a>
					<a href="">[9]</a>
					<a href="">[10]</a>	
					<!-- 尾页和下一页-->							
				<a href="">下一页&gt;&gt;</a>  <a href="">尾页</a>
			</div>
			
		</div>		    
    </center>
  </body>
</html>
