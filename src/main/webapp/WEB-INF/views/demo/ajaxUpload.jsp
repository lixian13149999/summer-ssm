<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    test/ajaxUpload.jsp
    <form id="fileUploadForm" method="post" action="demo/upload/doUpload?method=fileUpload" enctype="multipart/form-data">
    	<table>
			<tr>  
				<td>File:</td>  
				<td><input id="file_id" type="file" name="myfiles"></td>  
			</tr>  
			<tr>  
			<td><input type="hidden" name="fileOwner" value="1002" /></td>  
			<td><input type="button" value="ajaxUpload" onclick="ajaxUploadFile()"></td>  
			</tr>  
			<tr>
				<td>
					<input id="asdfasdfsd" type="input" name="aaa"></td>  
				</td>
			</tr>
    	</table>
	</form>
	<script type="text/javascript">var ctx = "${ctx}";</script>
	<script type="text/javascript" src="${ctx}/public/lib/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/public/lib/file/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctx}/public/js/demo/test.js"></script>
  </body>
</html>
