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
<title>Family concern</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".searchByName").click(function(){
			$.ajax({
				type:"POST",
				url:"http://localhost:8080/Project1101/concern/query",
				data:{
					name:document.getElementById("name").value,
					},
				dataType:"json",
				success:function(data){
					$("#result_name").remove();
					$("#result_Email").remove();
					$(".apply").remove();
					$.each(data,function(index,content){
						$(".search_result").append("<h4 id='result_name'>name:"+content.User+"</h4><h4 id='result_Email'>Email:"+
							content.Email+"</h4>"+"<input class='apply' type='button' value='Apply' onclick='apply("+index+")'>");
						document.cookie="User"+index+"="+content.User;
						document.cookie="Email"+index+"="+content.Email;
					});
					},
				error:function(){
					alert("Unkonw Error.Please Try Again!");
					}
				});
			});
		});
</script>
<script type="text/javascript">
	$(document).ready(function(){
		var Info = document.cookie.match(new RegExp("(^| )Info=([^;]*)(;|$)"));
		var info = JSON.parse(Info[2]);
		if(info.code==3014){
			alert("Error in Get Info");
		}else{
			var applyInfo = info.myAttention;
			var concernMe = info.attentionMe;
			var apply = info.apply;
			document.cookie="myEmail="+info.myEmail;
			$("#myform").remove();
			if(applyInfo!=""&&applyInfo!=null&&applyInfo!=" ")
			var cancel1 ="cancel('true')";
			var cancel2 ="cancel('false')";
			$.each(applyInfo,function(index,content){
						$(".my_form").append("<input id='myform' type='checkbox' readonly='true' name='Concern' value='"+content+"'>"+content);
					});
			$(".my_form").append("<input type='button' name='concern' readonly='true' value='Cancel Concern' onclick="+cancel1+">");
			$("#meform").remove();
			if(concernMe!=""&&concernMe!=null)
			$.each(concernMe,function(index,content){
						$(".me_form").append("<input id='meform' type='checkbox' readonly='true' name='Concern' value='"+content+"'>"+content);
					});
			$(".me_form").append("<input type='button' name='concern' value='Cancel Concern' onclick="+cancel2+">");
			$("#apply_news").remove();
			$.each(apply,function(index,content){
						var function1 = "agree('agree'"+","+index+")";
						var function2 = "agree('disagree'"+","+index+")";
						$(".apply_news").append("<form id='apply_news'><h4>Name:<input readonly='true' class='apply_name' name='Name' type='text' value='"+content.applyConcern+"'>"+
								" <h4>Email:<input readonly='true' id='apply_Email"+index+"' class='apply_email' type='text' name='Email' value='"+content.Email+"'</h4>"+
								"<input class='agree' type='button' value='Agree' onclick="+function1+">"+
								"<input class='disagree' type='button' value='Disagree' onclick="+function2+"></form>");
					});
			
			
		}
		});
</script>
<script type="text/javascript">
	function cancel(flog){
		var email = document.cookie.match(new RegExp("(^| )myEmail=([^;]*)(;|$)"));
		var names = [];
		$("input:checkbox[name='Concern']:checked").each(function(index,obj){
			names[index] = obj.value;
		});
		$.ajax({
			type:"post",
			url:"http://localhost:8080/Project1101/concern/cancelConcern",
			data:{
				flog:flog,
				myEmail:email[2],
				name:names
				},
			dataType:"json",
			success:function(data){
					alert(data.Message);
					},
			error:function(){
					alert("Unkonw Error.Please Try Again!");
					}
		});
	}
</script>
<script type="text/javascript">
	//处理接受到的申请
	function agree(flog,index){
			var info = document.cookie.match(new RegExp("(^| )Info=([^;]*)(;|$)"));
			var dealInfo = JSON.parse(info[2]);
			$.ajax({
				type:"POST",
				url:"http://localhost:8080/Project1101/concern/dealApply",
				data:{
					myEmail:dealInfo.myEmail,
					otherEmail:$("#apply_Email"+index).val(),
					deal:flog
					},
				dataType:"json",
				success:function(data){
					alert(data.Message);
					},
				error:function(){
					alert("Unkonw Error.Please Try Again!");
					}
				});
			};
</script>
<script type="text/javascript">
	function apply(index){
			var user = document.cookie.match(new RegExp("(^| )User"+index+"=([^;]*)(;|$)"));
			var email = document.cookie.match(new RegExp("(^| )Email"+index+"=([^;]*)(;|$)"));
			var info = document.cookie.match(new RegExp("(^| )Info=([^;]*)(;|$)"));
			var concernInfo = JSON.parse(info[2]);
			$.ajax({
				type:"POST",
				url:"http://localhost:8080/Project1101/concern/apply",
				data:{
					name:user[2],
					myEmail:concernInfo.myEmail,
					otherEmail:email[2],
					},
				dataType:"json",
				success:function(data){
					alert(data.Message);
					},
				error:function(){
					alert("Error in Apply!");
					}
				});
			};
</script>
<script type="text/javascript">

</script>
</head>
<body class="concern">
	<div class="serach">
		<h4>Search by Name:</h4><input type="text" placeholder="name" id="name">
		<input class="searchByName" type="button" value="Search">
	</div>
	<div class="search_result"></div>
	<div class="myConcern"><h3>myConcern</h3>
		<form class="my_form"></form>
	</div>
	<div class="concernMe" ><h3>concernMe</h3>
		<form class="me_form"></form>
	</div>
	<div class="apply_news"><h3 class="apply_info">Apply</h3>
	</div>
</body>
</html>