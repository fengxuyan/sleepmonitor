<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<s:form action="modify" enctype="multipart/form-data" namespace="/login">

<input type="file" name="head" />
<input type="text" name="Email" />
<input type="submit" value="Upload" />

</s:form>
	
</body>
</html>