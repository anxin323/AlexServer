<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%--引入JSTL核心标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>控制台页面_AmaAdmin后台管理系统模板 - 源码之家</title>
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.slimscroll.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/dashboard.js"></script>


<script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/custom/tables.js"></script>

<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/plugins/excanvas.min.js"></script><![endif]-->
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/plugins/css3-mediaqueries.js"></script>
<![endif]-->

<!-- <style type="text/css">
            table,td{
                border: 1px solid;
                border-collapse: collapse;
            }
        </style> -->

</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="header.jsp" />

		<div class="vernav2 iconmenu">
			<ul>
				<li><a href="#formsub" class="editor">表单提交</a> <span
					class="arrow"></span>
					<ul id="formsub">
						<li><a href="forms.html">基础表单</a></li>
						<li><a href="wizard.html">表单验证</a></li>
						<li><a href="editor.html">编辑器</a></li>
					</ul></li>
				<!--<li><a href="filemanager.html" class="gallery">文件管理</a></li>-->
				<li><a href="elements.html" class="elements">网页元素</a></li>
				<li><a href="widgets.html" class="widgets">网页插件</a></li>
				<li><a href="calendar.html" class="calendar">日历事件</a></li>
				<li><a href="support.html" class="support">客户支持</a></li>
				<li><a href="typography.html" class="typo">文字排版</a></li>
				<li><a href="tables.html" class="tables">数据表格</a></li>
				<li><a href="buttons.html" class="buttons">按钮 &amp; 图标</a></li>
				<li><a href="#error" class="error">错误页面</a> <span class="arrow"></span>
					<ul id="error">
						<li><a href="notfound.html">404错误页面</a></li>
						<li><a href="forbidden.html">403错误页面</a></li>
						<li><a href="internal.html">500错误页面</a></li>
						<li><a href="offline.html">503错误页面</a></li>
					</ul></li>
				<li><a href="#addons" class="addons">其他页面</a> <span
					class="arrow"></span>
					<ul id="addons">
						<li><a href="newsfeed.html">新闻订阅</a></li>
						<li><a href="profile.html">资料页面</a></li>
						<li><a href="productlist.html">产品列表</a></li>
						<li><a href="photo.html">图片视频分享</a></li>
						<li><a href="gallery.html">相册</a></li>
						<li><a href="invoice.html">购物车</a></li>
					</ul></li>
			</ul>
			<a class="togglemenu"></a> <br /> <br />
		</div>
		<!--leftmenu-->

		<div class="centercontent">
			<!-- <table align="center"  border="1" cellpadding="3" cellspacing="0" style="width: 100%;margin:auto"> -->
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="dyntable2">
				<colgroup>
					<col class="con0" style="width: 4%" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0 nosort"><input type="checkbox" /></th>
						<th class="head0">用户ID</th>
						<th class="head1">用户名</th>
						<th class="head0">用户生日</th>
						<th class="head1">工资</th>
						<th class="head0">CSS grade</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th colspan="6">
						
					 			<ul class="pagination pagination2" style="border-right: 5">
									<li class="first"><a href="getUser.do?pageNum=1" class="disable">&laquo;</a></li>
									<li class="previous"><a href="" class="disable">&lsaquo;</a></li>
									<li><a href="user.do?online&pageNum=1" class="current">1</a></li>
									<li><a href="user.do?online&pageNum=2">2</a></li>
									<li><a href="user.do?online&pageNum=3">3</a></li>
									<li><a href="user.do?online&pageNum=4">4</a></li>
									<li><a href="user.do?online&pageNum=5">5</a></li>
									<li class="next"><a href="">&rsaquo;</a></li>
									<li class="last"><a href="">&raquo;</a></li>
								</ul> 
								</th>
	
					</tr>
				</tfoot>
				<tbody>
					<c:forEach var="user" items="${lstUsers}">
						<tr class="gradeX">
							<td align="center"><span class="center"> <input
									type="checkbox" />
							</span></td>
							<td>${user.userId}</td>
							<td>${user.userName}</td>
							<td>${user.userBirthday}</td>
							<td class="center">${user.userSalary}</td>
							<td class="center">${user.userSalary}</td>
						</tr>
					</c:forEach>
				</tbody>
				<!-- <tr>
					<td>用户ID</td>
					<td>用户名</td>
					<td>用户生日</td>
					<td>工资</td>
				</tr> -->
				<%--遍历lstUsers集合中的User对象 --%>
				<%-- <c:forEach var="user" items="${lstUsers}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.userName}</td>
						<td>${user.userBirthday}</td>
						<td>${user.userSalary}</td>
						<td>${user.userSalary}</td>
					</tr>
				</c:forEach> --%>
			</table>
		</div>
	</div>
</body>