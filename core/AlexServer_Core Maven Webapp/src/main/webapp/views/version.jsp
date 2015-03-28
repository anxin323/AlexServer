<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>产品列表页面_AmaAdmin后台管理系统模板 - 源码之家</title>
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/productlist.js"></script>
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

<body class="withvernav">
<div class="bodywrapper">
  <jsp:include page="header.jsp"/>
    
    <div class="vernav2 iconmenu">
    	<ul>
        	<li><a href="#formsub" class="editor">Forms</a>
            	<span class="arrow"></span>
            	<ul id="formsub">
               		<li><a href="forms.html">Basic Form</a></li>
                    <li><a href="wizard.html">Wizard</a></li>
                    <li><a href="editor.html">WYSIWYG</a></li>
                </ul>
            </li>
            <!--<li><a href="filemanager.html" class="gallery">File Manager</a></li>-->
            <li><a href="elements.html" class="elements">Elements</a></li>
            <li><a href="widgets.html" class="widgets">Widgets</a></li>
            <li><a href="calendar.html" class="calendar">Calendar</a></li>
            <li><a href="support.html" class="support">Customer Support</a></li>
            <li><a href="typography.html" class="typo">Typography</a></li>
            <li><a href="tables.html" class="tables">Tables</a></li>
			<li><a href="buttons.html" class="buttons">Buttons &amp; Icons</a></li>
            <li><a href="#error" class="error">Error Pages</a>
            	<span class="arrow"></span>
            	<ul id="error">
               		<li><a href="notfound.html">Page Not Found</a></li>
                    <li><a href="forbidden.html">Forbidden Page</a></li>
                    <li><a href="internal.html">Internal Server Error</a></li>
                    <li><a href="offline.html">Offline</a></li>
                </ul>
            </li>
            <li class="current"><a href="#addons" class="addons">Addons</a>
            	<span class="arrow"></span>
            	<ul id="addons">
               		<li><a href="newsfeed.html">News Feed</a></li>
                    <li><a href="profile.html">Profile Page</a></li>
                    <li class="current"><a href="productlist.html">Product List</a></li>
                    <li><a href="photo.html">Photo/Video Sharing</a></li>
                </ul>
            </li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
        
    <div class="centercontent">
    
        <div class="pageheader notab">
            <h1 class="pagetitle">Product List</h1>
            <span class="pagedesc">This could be your portfolio list, product list, image list, etc.</span>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper nopadding">
         
         <div class="prodwrapper">   
         
        	<div class="prodhead">
            	<ul class="prodhead_menu">
                    <li><a class="prev prev_disabled"></a></li>
                    <li><a class="next"></a></li>
                    <li class="right"><span class="pagenuminfo">Showing 1 - 20 of 69</span></li>
                </ul>
                <span class="clearall"></span>
            </div><!--prodhead-->
         
         	<div class="prodcontent">
            
                <ul class="prodlist">
                    <li class="one_third">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/0.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                    <li class="one_third">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/1.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                    <li class="one_third last">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/2.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                                        <li class="one_third">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/3.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                    <li class="one_third">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/4.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                    <li class="one_third last">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/5.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                    <li class="one_third">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/6.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                    <li class="one_third">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/7.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>
                    <li class="one_third last">
                        <div class="thumb"><a href=""><img src="images/kozers/thumb/8.jpg" alt="" /></a></div>
                        <div class="content">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">$15</span>
                            		<a href="" class="title">Lorem Ipsum</a>
                                </div>
                                <div class="by">By: <a href="">themepixels</a></div>
                                <p class="desc">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>

                </ul>
                
            
            </div><!--prodcontent-->
            
            <br clear="all" /><br /><br />
            
         </div><!--prodwrapper-->
            
        </div><!--contentwrapper-->
                
	</div><!-- centercontent -->
    
    
</div><!--bodywrapper-->

</body>
</html>
