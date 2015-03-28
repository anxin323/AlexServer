<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

request.getSession().setAttribute("ACEGI_SAVED_REQUEST_KEY",null);
String ip=request.getRemoteAddr();

  
String type = request.getParameter("type");
String message = "";
if (type == null) {
 message = "";
} else if (type.equals("1")) {
	message = "帐号或密码错误！";
} else if (type.equals("2")) {
	message = "";
} else if (type.equals("3")) {
	message = "与服务器的连接已断开，请重新登录";
} else if (type.equals("4")) {
	message = "校验码不正确，登录失败！";
}else if (type.equals("6")) {
	message = "该用户已登录,同一帐号不能同时多次登录!";
}else if (type.equals("7")) {
	message = "您不是本系统的正式用户！";
} else if (type.equals("5")) {
	message = "对不起，您当前的帐号暂时没有访问本系统的权限!";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>登录页面_iChat后台管理系统</title>
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/index.js"></script>
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/plugins/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="loginpage">
	<div class="loginbox">
    	<div class="loginboxinner">
        	
            <div class="logo">
            	<h1 class="logo">搜脉.<span>通讯服务器</span></h1>
				<span class="slogan">后台管理系统</span>
            </div><!--logo-->
            
            <br clear="all" /><br />
            
            <div class="nousername">
				<div class="loginmsg">用户名不能为空.</div>
            </div><!--nousername-->
            
            <div class="nopassword">
				<div class="loginmsg">密码不能为空.</div>
                <div class="loginf">
                    <div class="thumb"><img alt="" src="images/thumbs/avatar1.png" /></div>
                    <div class="userlogged">
                        <h4></h4>
                        <a href="index.html">Not <span></span>?</a> 
                    </div>
                </div><!--loginf-->
            </div><!--nopassword-->
            <div id="msg"><font color="red">${message}</font></div>
            <form id="login" action="login.do?main" method="post">
            	
                <div class="username">
                	<div class="usernameinner">
                    	<input type="text" name="userName" id="username" />
                    </div>
                </div>
                
                <div class="password">
                	<div class="passwordinner">
                    	<input type="password" name="password" id="password" />
                    </div>
                </div>
                
                <button>登录</button>
                
                <div class="keep"><input type="checkbox" /> 记住密码</div>
            
            </form>
            
        </div><!--loginboxinner-->
    </div><!--loginbox-->


</body>
</html>
