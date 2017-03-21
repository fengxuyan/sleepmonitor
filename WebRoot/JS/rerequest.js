/**
 * 再次发起请求
 */
function rerequest(){
	var userInfo = document.cookie.match(new RegExp("(^| )userData=([^;]*)(;|$)"));
	var info = JSON.parse(userInfo[2]);
	var Email = info.Email;
	var password = info.password;
	$.ajax({
				type:"POST",
				url:"/SleepMonitor/login/login",
				data:{
					Email:Email,
					password:password
					},
				dataType:"json",
				success:function(data){
						if(data.Code!=1){
							alert(data.Message);
						}else{
							document.cookie="userData="+JSON.stringify(data.data);
						}
					},
				error:function(){
					alert("Unkonw Error.Please Try Again!");
					}
				});
}