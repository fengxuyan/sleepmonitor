function checkForm(){
		var x1 = document.forms["re_form"]["firstName"].value;
		var x2 = document.forms["re_form"]["lastName"].value;
		var x3 = document.forms["re_form"]["yearOfBirth"].value;
		var select = document.getElementById("gender");
		if(select[0].selected==true){
			alert("The Gender is not Filled");
			return false;
		}
		var x5 = document.forms["re_form"]["phone"].value;
		var x6 = document.forms["re_form"]["Email"].value;
		var x7 = document.forms["re_form"]["password"].value;
		if(x1==null||x1==""||x2==null||x2==""||x3==null||x3==""){
			alert("Some Option is not Filled");
			return false;
		}
		if(x5==null||x5==""||x6==null||x6==""||x7==null||x7==""){
			alert("Some Option is not Filled");
			return false;
		}
		
		var x9 = document.forms["re_form"]["repassword"].value;
		if(x7!=x9){
			alert("Please Check Your Password!Keep the Same");
			return false;
		}
		var x10 = document.getElementById("checkService");
		if(!x10.checked){
			alert("Please Accept the Terms of Service,or You Can Read it");
			return false;
		}
}