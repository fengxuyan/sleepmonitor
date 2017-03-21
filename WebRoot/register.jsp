<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<title>Register Account</title>
	<script src="/Project1101/JS/checkForm.js" type="text/javascript"></script>
	<script type="text/javascript">
		checkForm();
	</script>
	<script type="text/javascript">
	function userBrowser(){
    		var browserName=window.navigator.userAgent.toLowerCase();
    		if(browserName.indexOf('gecko') > -1 && browserName.indexOf('firefox') > -1)
        		document.getElementById("register_form").style.height = "905px";
		}
		window.onload = userBrowser;
	</script>
	<style type="text/css">
		.register_age{
			float:left;
			width:142px;
			padding:6px;
			background-color:#cacdd2;
			border:1px solid #cacdd2;
		}
		.register_gender{
			float:right;
			width:140px;
			padding:6px;
			background-color:#cacdd2;
			border:1px solid #cacdd2;
			margin-right: 20px;
		}
		.age_input{
			width:150px;
			float:left;
			background-color:#dde3ec;
			border:1px solid #dde3ec;
		}
		.gender_input{
			float:right;
			width:140px;
			padding:6px;
			background-color:#dde3ec;
			border:1px solid #dde3ec;
			margin-right: 20px;
		}
		.age_style{
			float:left;
			width:140px;
			padding:6px;
			background-color:#dde3ec;
			border:1px solid #dde3ec;
			margin-right:20px
		}
		.gender_style{
			width:70px;
			background-color:#9ca9bd;
			border:1px solid #9ca9bd;
		}
		.sn1{
			width:120px;
			float:left;
		}
		.sn1_input{
			width:140px;
			float:right;
		}
		.sn1_link{
			width:40px;
			float:left;
		}
		.sn{
			float:left;
			width:120px;
			padding:6px;
			background-color:#dde3ec;
			border:1px solid #dde3ec;
		}
		.sn_input{
			float:right;
			width:130px;
			padding:6px;
			margin-right:20px;
			background-color:#dde3ec;
			border:1px solid #dde3ec;
		}
		.sn_link{
			width:40px;
			float:left;
			padding:4px;
			margin-left:5px;
		}
	</style>
</head>

<link id="css_style" rel="stylesheet" type="text/css" href="/Project1101/CSS/register.css">
<body class="register_page" >
	<div class="register_form" style="height:850px" id="register_form">
		<s:form action="register" method="post" namespace="/login" name="re_form" onsubmit="return checkForm()">
			<h3 class="register_title">REGISTER</h3>
			<h5 class="error"><s:property value="#parameters.Result"/></h5>
			<h7 style="margin-left:-6px;color:#0000FF;">Attention:The message with * must be filled out!</h7>
			<table width="340" class="register_table">
				<tr>
					<td class="register_message">First Name: *</td>
				</tr>
				<tr>
					<td><input type="text" placeholder="First Name" name="firstName" class="regiseter_input" value="<s:property value="#parameters.first"/>"></td>
				</tr>
				<tr>
					<td class="register_message">Middle Name:</td>
				</tr>
				<tr>
					<td><input type="text" placeholder="Middle Name" name="middleName" class="regiseter_input" value="<s:property value="#parameters.middle"/>"></td>
				</tr>
				<tr>
					<td class="register_message">Last Name: *</td>
				</tr>
				<tr>
					<td><input type="text" placeholder="Last Name" name="lastName" class="regiseter_input" value="<s:property value="#parameters.last"/>"></td>
				</tr>
				<tr>
					<td class="register_age">Age: *</td>
					<td class="register_gender">Gender: *</td>
				</tr>
				<tr>
					<td class="age_input"><input type="text" placeholder="Age" name="age" class="age_style" value="<s:property value="#parameters.age"/>"></td>
					<td class="gender_input" id="Gender" ><select class="gender_style" id="gender" name ="gender">
							<option value ="0"></option>
							<option value="Male" >Male</option>
							<option value="Female" >Female</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="register_message">Phone Number: *</td>
				</tr>
				<tr>
					<td><input type="text" placeholder="Phone Number" name="phone" class="regiseter_input" value="<s:property value="#parameters.phone"/>"></td>
				</tr>
				<tr>
					<td class="register_message">Address:</td>
				</tr>
				<tr>
					<td><input type="text" placeholder="Address" name="address" class="regiseter_input" value="<s:property value="#parameters.address"/>"></td>
				</tr>
				<tr>
					<td class="register_message">Serial Number:</td>
				</tr>
				<tr>
					<td class="sn1" ><div class="sn">0010A0</div></td>
					<td class="sn1_link"><div class="sn_link">-</div></td>
					<td class="sn1_input" ><input type="text" placeholder="Serial Number" name="sn" class="sn_input" value="<s:property value="#parameters.sn"/>"></td>
				</tr>
				<tr>
					<td class="register_message">Email: *</td>
				</tr>
				<tr>
					<td><input type="text" placeholder="Email" name="Email" class="regiseter_input" value="<s:property value="#parameters.Email"/>"></td>
				</tr>
				<tr>
					<td class="register_message">Confirm Email: *</td>
				</tr>
				<tr>
					<td><input type="text" placeholder="Re-type Your Email" name="reEmail" class="regiseter_input"></td>
				</tr>
				<tr>
					<td class="register_message">Password: *</td>
				</tr>
				<tr>
					<td><input type="password" placeholder="Password" name="password" class="regiseter_input"></td>
				</tr>
				<tr>
					<td class="register_message">Confirm Password:*</td>
				</tr>
				<tr>
					<td><input type="password" placeholder="Re-type Your Password" name="repassword" class="regiseter_input"></td>
				</tr>
			</table>
			<label class="checkService">
				<input type="checkbox" id="checkService">I agree to the <a href="UserService.jsp" target="_blank">Terms of Service</a>
			</label><br/>
			<input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Back" class="submit_back">
			<input type="submit" value="Submit" onclick="checkform(register_form)" class="submit_submit">
		</s:form>
	</div>
</body>
</html>