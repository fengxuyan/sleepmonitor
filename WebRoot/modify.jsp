<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Modify Information</title>
	<script type="text/javascript">
		function checkForm(){
		var x1 = document.forms["re_form"]["firstName"].value;
		var x2 = document.forms["re_form"]["lastName"].value;
		var x3 = document.forms["re_form"]["age"].value;
		var select = document.getElementById("gender");
		if(select[0].selected==true){
			alert("The Gender is not Filled");
			return false;
		}
		var x5 = document.forms["re_form"]["phone"].value;
		var x7 = document.forms["re_form"]["password"].value;
		if(x1==null||x1==""||x2==null||x2==""){
			alert("Some Option is not Filled");
			return false;
		}
		if(x5==null||x5==""||x7==null||x7==""){
			alert("Some Option is not Filled");
			return false;
		}
		
		var x9 = document.forms["re_form"]["repassword"].value;
		if(x7!=x9){
			alert("Please Check Your Password!Keep the Same");
			return false;
		}
	}
	</script>
	<script type="text/javascript">
	function userBrowser(){
    		var browserName=window.navigator.userAgent.toLowerCase();
    		if(browserName.indexOf('gecko') > -1 && browserName.indexOf('firefox') > -1)
        		document.getElementById("register_form").style.height = "825px";
		}
	window.onload = userBrowser;
	function warn(){
		alert("You have select to clear your data.This operate will clear all your data");
		var flog = document.getElementById("flog");
		flog.value = "clear";
	}
	function save(){
		var flog = document.getElementById("flog");
		flog.value = "clear";
	}
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
			width:120px;
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
			width:60px;
			float:left;
		}
		.sn1_input{
			width:120px;
			float:left;
		}
		.sn1_link{
			width:40px;
			float:left;
		}
		.sn1_clear{
			width:60px;
			float:right;
			margin-top:-5px;
			margin-right:15px;
		}
		.sn{
			float:left;
			width:60px;
			padding:6px;
			background-color:#dde3ec;
			border:1px solid #dde3ec;
		}
		.sn_input{
			float:left;
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
	<div class="register_form" style="height:780px" id="register_form">
		<s:form action="modifyUser" method="post" namespace="/getInfo" name="re_form" onsubmit="return checkForm()">
			<h3 class="register_title">MODIFY</h3>
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
					<td class="gender_input" id="Gender" ><select class="gender_style" id="gender" >
							<option value ="0"></option>
							<option value="1" >Male</option>
							<option value="2" >Female</option>
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
					<td class="sn1_input" ><input type="text" placeholder="Serial Number" id="sn" name="sn" class="sn_input" value="<s:property value="#parameters.SN"/>" ></td>
					<td class="sn1_clear">
						<input type="radio" name ="clear" value="save" checked="checked" onclick="save()">save<br/>
						<input type="radio" name ="clear" value="clear" onclick="warn()">clear
					</td>
				</tr>
				<tr>
					<td class="register_message">Email: </td>
				</tr>
				<tr>
					<%-- <td class="regiseter_input" style="text-align:left" name="Email"><s:property value="#parameters.Email"/></td> --%>
					<td class="regiseter_input"><input type="text" name="Email" class="regiseter_input" value="<s:property value="#parameters.Email"/>" readonly="false"></td>
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
			<input type="text" id = "flog" value="save" name="flog" style="display:none">
			<input type="button" name="Submit" onclick="javascript:history.back(-1);" value="Back" class="submit_back">
			<input type="submit" value="Submit" onclick="checkform(register_form)" class="submit_submit">
		</s:form>
	</div>
</body>
</html>