<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>News & Events</title>
</head>
<link rel="stylesheet" type="text/css" href="/Project1101/CSS/news.css">
<body class="news_page">
	<div class="news_top">
		<h1 class="logo">LOGO</h1>
		<h3 class="company">
			<a href="" style="text-decoration:none;color:#ffffff;">Company Profile&nbsp|</a>
			<a href="" style="text-decoration:none;color:#ffffff;">Contact Us</a></h3>
	</div>
	<div class="news_left">
		<jsp:include page="news_list.jsp"></jsp:include>
	</div>
	<div class="news_right">
		<h1 style="border-bottom:2px solid #FFAF47">NEWS & EVENT</h1>
		<ul>
			<li><a href="" style="text-decoration:none"><p class="news_introduction">news title1 test news title1 test</p></a></li>
			<li><a href="" style="text-decoration:none"><p class="news_introduction">news title1 test news title1 test</p></a></li>
			<li><a href="" style="text-decoration:none"><p class="news_introduction">news title1 test news title1 test</p></a></li>
			<li><a href="" style="text-decoration:none"><p class="news_introduction">news title1 test news title1 test</p></a></li>
			<li><a href="" style="text-decoration:none"><p class="news_introduction">news title1 test news title1 test</p></a></li>
		</ul>
	</div>
	<div class="news_foot">
	
	</div>
	
</body>
</html>