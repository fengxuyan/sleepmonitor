<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Information Check</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
		var url = location.href;
		var urlInfo = url.split("Email=");
		var Email = urlInfo[1];
		$("#family").click(function(){
			//document.cookie="user_Email"+Email;
			$.ajax({
				type:"POST",
				url:"http://localhost:8080/Project1101/concern/getConcern",
				data:{
					Email:Email,
					},
				dataType:"json",
				success:function(data){
					if(data.Code==3014){
						alert("Error in get Info");
						document.getElementById("family").href="#";
					}else{
						var data1 = JSON.stringify(data);
						document.cookie="Info="+data1;
						window.location.href="http://localhost:8080/Project1101/concern.jsp";
					}
					},
				error:function(){
					alert("Error in Enter Family Concern!");
					}
				});
			});
		});
</script>
</head>
<link rel="stylesheet" type="text/css" href="/Project1101/CSS/frame.css">
<body class="information">
	
	<div class="info_frame">
		<dir class="info_img">
			<img src="/Project1101/images/head_test.jpg" text-align="center">
		</dir>
		<dir class="info_user">
			<h3>Welcome ${param.User}</h3>
		</dir>
		<div class="info_select"> 
			<table class="info_list" style="padding:5px;" cellspacing="10">
				<tr>
					<td class="info_row" style="background-color:#747c86;border:1px solid #747c86;"><a href="" class="info_info" >Online Detection</a></td>
					<td class="info_row"><a href="healthy.jsp" class="info_info">Health Records</a></td>
					<td class="info_row"><a href="#" class="info_info" id="family">Family Concern</a></td>
				</tr>
				<tr>
					<td class="info_row" style="background-color:#747c86;border:1px solid #747c86;"><a href="" class="info_info" ">Doctor Diagnosis</a></td>
					<td class="info_row"><a href="upgrade.jsp" class="info_info">Product Upgrade</a></td>
					<td class="info_row"><a href="getInfo/getUser?email=${param.Email}" class="info_info">Modify Information</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>