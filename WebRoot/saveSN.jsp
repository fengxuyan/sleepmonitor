<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>子服务器的页面</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var href = window.location.href;
		var Info = href.split("data=");
		var data1 = tihuan(Info[1]);
		var data = JSON.parse(data1);
		$.ajax({
			type:"post",
			url:"http://localhost:8080/SleepMonitor/random/saveSN",
			data:{
				type:data.type,
				level:data.level,
				number:data.number
				},
			dataType:"json",
			success:function(data){
					if(data.Code==1)
						document.write("<a href='"+data.Message+"' download='w3logo'>下载</a>");
					else
						document.writer(data.message);
					},
			error:function(){
					alert("Unkonw Error.Please Try Again!");
					}
		
		});
	});
</script>
<script type="text/javascript">
		function tihuan(href){
			href = href.replace(/%22/g,"\"");
			return href; 
		} 
</script>
</head>
<body>

</body>
</html>