<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function array(){
		var Ohref=window.location.href;
		var arrhref=Ohref.split("?Result=");
		if(arrhref[1].indexOf("http")>-1){
			document.getElementById("file").href=arrhref[1];
			//window.location.href=arrhref[1];
		}else{
			document.getElementById("file").style.display=none;
			document.write(arrhref[1]+"<br/>");
		}
	}
	window.onload = array;
</script>
</head>
<body>
	<s:form action="getSN" method="post" namespace="/random">
		<input type="text" name="number">
		<input type="submit" value="submit">
	</s:form>
	<a href="" id="file" style="display:false" download="SN.txt">SN表</a>
	<!-- <a href="javascript:void(0)" onclick="array();" id="file" style="display:false" download="SN.txt">SN表</a> -->
	
	
</body>
</html>