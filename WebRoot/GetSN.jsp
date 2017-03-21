<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>GetSN</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".getSN").click(function(){
		$.ajax({
			type:"POST",
			url:"http://118.178.181.188:8080/SleepMonitor/random/getSN",
			data:{
				number:document.getElementById("number").value,
				type:document.getElementById("type").value,
				level:document.getElementById("level").value
			},
			dataType:"json",
			success:function(data){
					if(data.Code==1){
						document.getElementById("download").href=data.Message;
						alert(data.Message);
					}
					else{
						alert(data.Message);
					}
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
<form >
	数量:<input type="text" name="number" id="number">
	类型:<input type="text" name="type" placeholder="1/2/3" id="type">
	类别:<input type="text" name="level" placeholder="000/001/002..." id="level">
	<input type="button" value="GetSN" class="getSN">
</form>
<a href="" id="download" download="w3logo">下载</a>

</body>
</html>