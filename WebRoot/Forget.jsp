<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Forget Password</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var Email = document.getElementById("email").value;
	$.ajax({
		type:"post",
		url:"http://localhost:8080/SleepMonitor/login/forget",
		data:{
			Email:Email
		},
		dataType:"json",
		success:function(data){
					if(data.Code==1007)
						alert(data.Message);
					else{
						alert(data.Message);
						document.getElementById("email").value=Email;
					}
				},
		error:function(){
					alert("Unkonw Error.Please Try Again!");
				}
	});
});
</script>
</head>
<link rel="stylesheet" type="text/css" href="/Project1101/CSS/forget.css">
<body class="forget_page">
	<div class="getPassword">
	<s:form action="getPassword" method="post" namespace="/login">
		<h2 class="title">Forget Password ?</h2>
		<h5 class="notice">Enter your e-mail address to get your password.</h5>
		<input type="text" placeholder="Email" name="Email" class="email" id="email" value="<s:property value="#parameters.Email"/>"><br/>
		<input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Back" class="submit_back">
		<input type="submit" value="Submit" class="submit_submit" onclick="">
	</s:form>
	</div>
</body>
</html>