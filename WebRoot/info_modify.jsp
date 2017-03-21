<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>info page</title>
<style type="text/css">
	.info_page{
		width:100%; 
		height:100%; 
		margin:0 auto; 
		background-color:#364150 !important;
		font-family:Arial;
		padding:30px;
		}
	.info_page .info_message{
		width:350px; 
		height:190px; 
		text-align:center;
		margin:0 auto; 
		background-color:#eceef1;
		padding:30px;
		}
	.info_page .info_message .title{
		width:450px; 
		height:20px; 
		margin-top: 22px;
    	margin-right: auto;
    	margin-bottom: 36px;
    	margin-left: -50px;
    	font-size:1.8em;
    	color:#4db3a5;
		}
	/* .info_page .info_message .message{
		width:300px; 
		height:20px; 
		margin-top: 22px;
    	margin-right: auto;
    	margin-bottom: 6px;
    	margin-left: 10px;
		} */
	.info_page .info_message .return{
		width:120px; 
		height:35px; 
		background-color: #daae2b;
    	text-decoration:none;
    	padding:10px;
		}
</style>
</head>
<body class="info_page">
	<div class="info_message">
	<s:form>
		<h3 class="title">Successfully Modify!</h3><br/>
		<!-- <h3 class="message">Thank for your register.</h3><br/> -->
		<a href="frame.jsp" class="return">
			Return to Information select
		</a>
	</s:form>
	</div>
</body>
</html>