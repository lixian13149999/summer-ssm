<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base src="${ctx}<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta charset="UTF-8">
    <title>首页</title>
    <!--引入标签页小图标-->
    <link rel='shortcut icon' href='${ctx}/static/imgs/logo32.png'>

    <!--引入bootstrap主框架-->
    <link type="text/css" rel="stylesheet" href="${ctx}/static/lib/bootstrap/bootstrap.min.css" />
    <!--引入button按钮插件-->
    <link type="text/css" rel="stylesheet" href="${ctx}/static/lib/button/buttons.css" />
    <!--引入时间选择器插件-->
    <link type="text/css" rel="stylesheet" href="${ctx}/static/lib/bootstrap-datepicker/bootstrap-datepicker.min.css" />
    <!--引入阿里字体图标库-->
    <link type="text/css" rel="stylesheet" href="${ctx}/static/lib/iconfont/iconfont.css" />

    <!--引入自己的样式-->
    <link type="text/css" rel="stylesheet/less" href="${ctx}/static/css/index.less" />

  </head>
  
  <body>
    <div class="view-framework">
        <div id="view_top" class="viewFramework-topbar"></div>

        <div id="view_body" class="viewFramework-body viewFramework-sidebar-full">
			<!--栏目区域最外层主框-->
			<div class="viewFramework-sidebar">
			    <!--   用于控制下拉和隐藏滚动条的块域-->
			    <div class="sidebar-content">
			        <!--       方便后台数据控制(保留)-->
			        <div class="sidebar-inner">
			            <!--           用于展开和缩小的按钮-->
			            <div class="sidebar-fold" data-sidebar-fold="flex">
			                <i class="iconright invisible iconfont icon-zhankai"></i>
			                <i class="iconleft iconfont icon-shousuo"></i>
			            </div>
			            
			            <!-- 栏目块的box -->
			            <c:forEach var="userMenu" items="${userMenus}">
				            <div class="sidebar-nav">
				                <div class="sidebar-title" data-sider-title="collapse" data-toggle="collapse" data-target="#${userMenu.id}">
				                    <i class="icon iconfont icon-down" data-sidebar-tips="tips"></i>
				                    <i class="iconup hide iconfont icon-up" data-sidebar-tips="tips"></i>
				                    <span>${userMenu.name}</span>
				                </div>
				                <c:if test="${userMenu.childs.size()>0}">
					                <div class="sidebar-trans collapse" id="${userMenu.id}">
					                	<c:forEach var="userMenuChild" items="${userMenu.childs}">
						                    <a href="${userMenuChild.href }" class="item" data-sidebar-item="item">
						                        <i class="icon iconfont icon-liwu" data-sidebar-tips="tips"></i>
						                        <span>${userMenuChild.name }</span>
						                    </a>
					                	</c:forEach>
					                </div>
				                </c:if>
				            </div>
			            </c:forEach>
			            
			            <!-- 
			            <div class="sidebar-nav">
			                <div class="sidebar-title" data-sider-title="collapse" data-toggle="collapse" data-target="#collapseThree">
			                    <i class="icon iconfont icon-down" data-sidebar-tips="tips"></i>
			                    <i class="iconup hide iconfont icon-up" data-sidebar-tips="tips"></i>
			                    <span>这是菜单三的title</span>
			                </div>
			                <div class="sidebar-trans collapse" id="collapseThree">
			                    <a href="javascript:void(0)" class="item" data-sidebar-item="item">
			                        <i class="icon iconfont icon-caifu" data-sidebar-tips="tips"></i>
			                        <span>二级菜单1点击区域</span></a>
			                    <a href="javascript:void(0)" class="item" data-sidebar-item="item">
			                        <i class="icon iconfont icon-xiaoxi" data-sidebar-tips="tips"></i>
			                        <span>二级菜单2的点击区域</span></a>
			                    <a href="javascript:void(0)" class="item" data-sidebar-item="item">
			                        <i class="icon iconfont icon-lianjie" data-sidebar-tips="tips"></i>
			                        <span>二级菜单3的点击区域</span></a>
			                    <a href="javascript:void(0)" class="item" data-sidebar-item="item">
			                        <i class="icon iconfont icon-yijian" data-sidebar-tips="tips"></i>
			                        <span>二级菜单4的点击区域</span>
			                    </a>
			                </div>
			            </div>
			             -->
			        </div>
			    </div>
			</div>
			
			<div class="viewFramework-product">
			    <div class="product-navbar-container unfold">
			        <div class="viewFramework-product-navbar">
			            <div class="list-title">费用中心</div>
			            <ul>
			                <li class="list-content" data-list-content="content">账户总览</li>
			                <li class="list-content" data-list-content="content">收支明细</li>
			                <li class="list-content" data-list-content="content">消费记录</li>
			                <li class="list-content" data-list-content="content">订单管理</li>
			                <li class="list-content" data-list-content="content">发票管理</li>
			            </ul>
			        </div>
			    </div>
			
			    <i class="iconleft conceal iconfont icon-shousuo" data-product-iconleft="iconleft"></i>
			    <i class="iconright conceal iconfont icon-zhankai" data-product-iconright="iconright"></i>
			
			    <div class="viewFramework-product-body">
			    	<%-- ${userMenus} --%>
			    </div>
			</div>
			
			
			<div class="sidebar-tips">
			    我是小提示
			</div>
        </div>
    </div>
    
    <!-- 引入全局变量 -->
    <script type="text/javascript">
    	var ctx = '${ctx}';
    </script>
    <!--jQuery的js文件引入-->
    <script type="text/javascript" src="${ctx}/static/lib/jquery/jquery.min.js"></script>
    <!--验证的js文件的引入-->
    <script type="text/javascript" src="${ctx}/static/lib/jquery-validation/jquery.validate.min.js"></script>
    <!--bootstrap的js文件引入-->
    <script type="text/javascript" src="${ctx}/static/lib/bootstrap/bootstrap.min.js"></script>
    <!--时间选择器的js文件引入-->
    <script type="text/javascript" src="${ctx}/static/lib/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
    <!--提示消息的js文件引入-->
    <script type="text/javascript" src="${ctx}/static/lib/messenger/js/messenger.min.js"></script>


    <!--开发过程中用到的一些js文件,正式上线时需要删除-->
    <!--   less的js文件引入-->
    <script type="text/javascript" src="${ctx}/static/lib/less/less.min.js"></script>
    <%-- 
    <script type="text/javascript" src="${ctx}/static/js/test/test.js"></script>
 --%>

    <!--自己开发的js文件的引入-->
    <script type="text/javascript" src="${ctx}/static/js/body.js"></script>
    <!--通用工具类的引入-->
    <script type="text/javascript" src="${ctx}/static/js/util/iutil.js"></script>
    <!--小工具的js文件-->
    <script type="text/javascript" src="${ctx}/static/js/util/itools.js"></script>
    <!--用于验证的js文件-->
    <script type="text/javascript" src="${ctx}/static/js/util/ivalid.js"></script>
    <!--主js文件引入-->
    <script type="text/javascript" src="${ctx}/static/js/index.js"></script>
  </body>
</html>
