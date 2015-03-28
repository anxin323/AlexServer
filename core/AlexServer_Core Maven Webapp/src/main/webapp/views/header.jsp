<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- <title>控制台页面_AmaAdmin后台管理系统模板 - 源码之家</title>
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
<script type="text/javascript" src="js/custom/dashboard.js"></script> -->
</head> 

<body class="withvernav">
<div class="bodywrapper">
		<div class="topheader">
			<div class="left">
				<h1 class="logo">
					搜脉.<span>通讯服务器</span>
				</h1>
				<span class="slogan">后台管理系统</span>

				<div class="search">
					<form action="" method="post">
						<input type="text" name="keyword" id="keyword" value="请输入" />
						<button class="submitbutton"></button>
					</form>
				</div>
				<!--search-->

				<br clear="all" />

			</div>
			<!--left-->

			<div class="right">
				<!--<div class="notification">
                <a class="count" href="ajax/notifications.html"><span>9</span></a>
        	</div>-->
				<div class="userinfo">
					<img src="images/thumbs/avatar.png" alt="" /> <span>管理员</span>
				</div>
				<!--userinfo-->

				<div class="userinfodrop">
					<div class="avatar">
						<a href=""><img src="images/thumbs/avatarbig.png" alt="" /></a>
						<div class="changetheme">
							切换主题: <br /> <a class="default"></a> <a class="blueline"></a> <a
								class="greenline"></a> <a class="contrast"></a>
						</div>
					</div>
					<!--avatar-->
					<div class="userdata">
						<h4>江锦良</h4>
						<span class="email">jiang-alex@qq.com</span>
						<ul>
							<li><a href="editprofile.html">编辑资料</a></li>
							<li><a href="accountsettings.html">账号设置</a></li>
							<li><a href="help.html">帮助</a></li>
							<li><a href="login.do?logout">退出</a></li>
						</ul>
					</div>
					<!--userdata-->
				</div>
				<!--userinfodrop-->
			</div>
			<!--right-->
		</div>
		<!--topheader-->


		<div class="header">
			<ul class="headermenu">
				<li><a href="login.do?main"><span
						class="icon icon-flatscreen"></span>系统首页</a></li>
				<li><a href="getUser.do?pageNum=1"><span
						class="icon icon-speech"></span>在线用户</a></li>
				<li><a href="login.do?version"><span
						class="icon icon-pencil"></span>版本升级</a></li>
				<li><a href="login.do?message"><span class="icon icon-message"></span>消息设置</a></li>
				<li><a href="login.do?report"><span class="icon icon-chart"></span>应用数据</a></li>
			</ul>

			<div class="headerwidget">
				<div class="earnings">
					<div class="one_half">
						<h4>在线人数：</h4>
						<h2>37,234,138</h2>
					</div>
					<!--one_half-->
					<!-- <div class="one_half last alignright">
						<h5>集群服务器：</h5>
						<h2>20</h2>
					</div> -->
					<!--one_half last-->
				</div>
				<!--earnings-->
			</div>
			<!--headerwidget-->

		</div>
		<!--header-->
		</div>
</body>