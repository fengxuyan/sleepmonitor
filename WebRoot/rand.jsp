<%@ page contentType="image/JPEG"       
        import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"       
        pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function rand(){
		<% 
			//生成随机类        
			Random random = new Random();  
			String sRand = "";        
			for (int i = 0; i < 4; i++) {        
			String rand = String.valueOf(random.nextInt(10));        
			sRand += rand;  
			} 
			session.setAttribute("rand",sRand);//放入session中 
		%>
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>