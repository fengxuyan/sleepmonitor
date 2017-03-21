<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <head>
    <base href="<%=basePath%>">
    <title>Login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function refresh(obj){
			obj.src = "/Project1101/Verification.jsp?date=" + new Date();//加上一个随机数,防止重复  
		}
		function checkLogin(){
			var x1 = document.forms["login"]["Email"].value;
			var x2 = document.forms["login"]["password"].value;
			var x3 = document.forms["login"]["verification"].value;
			if(x1==null||x1==""||x2==null||x2==""){
				alert("Email or Password should not Be Empty!");
				return false;
			}
		}
	</script>
</head>
  <link rel="stylesheet" type="text/css" href="/Project1101/CSS/login.css">
  <body class="login">
  		<div class="login_frame">
  		<s:form name="login" action="login" method="post" namespace="/login" onsubmit="return checkLogin()">
  			<h3 class="login_title">Sign In</h3>
  			<h5 class="error"><s:property value="#parameters.Result"/></h5>
  			<div class="login_class">
  			<table> 
  				<tr>
  					<td colspan="2"><input type="text" placeholder="Email" name="Email" class="Email_input" value="<s:property value="#parameters.Email"/>"></td>
  				</tr>
  				<tr>
  					<td colspan="2"><input type="password" placeholder="Password" name="password" class="pass_input" ></td>
  				</tr>
  				<tr>
  					<td>
  						<input type="text" placeholder="Verification Code" name="verification"  class="ver_input" >
  					</td>
  					<td>
  					<img alt="" src="/Project1101/Verification.jsp"
  							onclick="refresh(this)" id="img" height=39px width=100px>
  					</td>
  				</tr>
  				<tr>
  					<td colspan="2">
  						<input class="login_submit" type="submit" value="LOGIN" >   
  						<a href="Forget.jsp" class="forget">Forgot Password?</a>
  					</td>
  				</tr>
  			</table>
  			</div>
  		</s:form>
  		</div>
  		<div class="form_register">
  			<a href="register.jsp" class="register_front" id="register_page">REGISTER AN ACCOUNT</a>
  		</div>
  </body>
</html>
