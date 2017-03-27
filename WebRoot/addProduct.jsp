<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add Product</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script src="/SleepMonitor/JS/rerequest.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	<%--$("#add").click(function(){--%>

	$("#add_product").click(function(){
		$.ajax({
		type:"post",
		url:"http://localhost:8080/SleepMonitor/product/add",
		data:{
			SN:document.getElementById("SN").value,
			Email:document.getElementById("Email").value
		},
		dataType:"json",
		success:function(data){
						rerequest();
						alert(data.Message);
						},
		error:function(){
					alert("Unkonw Error.Please Try Again!");
					}
		});
	});
});
</script>
</head>
<body>
	<form>
	<input type="text" id="SN" placeholder="SN">
	<input type="button" id="add" value="add">
	</form>
</body>
</html>