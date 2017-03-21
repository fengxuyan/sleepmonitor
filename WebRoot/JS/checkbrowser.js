function checkbrowser(){
    var userAgent = navigator.userAgent; //È¡µÃä¯ÀÀÆ÷µÄuserAgent×Ö·û´®
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    }; //ÅĞ¶ÏÊÇ·ñOperaä¯ÀÀÆ÷
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    }; //ÅĞ¶ÏÊÇ·ñFirefoxä¯ÀÀÆ÷
	if (userAgent.indexOf("Edge") > -1){
		return "Edge";
	};//ÅĞ¶ÏMicrosoft edge ä¯ÀÀÆ÷
	if (userAgent.indexOf("Chrome") > -1){
		return "Chrome";
	};	//ÅĞ¶ÏChromeä¯ÀÀÆ÷
	if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    }; //ÅĞ¶ÏÊÇ·ñSafariä¯ÀÀÆ÷
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
        return "IE";
    }; //ÅĞ¶ÏÊÇ·ñIEä¯ÀÀÆ÷
}