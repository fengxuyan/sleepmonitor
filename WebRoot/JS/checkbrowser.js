function checkbrowser(){
    var userAgent = navigator.userAgent; //ȡ���������userAgent�ַ���
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    }; //�ж��Ƿ�Opera�����
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    }; //�ж��Ƿ�Firefox�����
	if (userAgent.indexOf("Edge") > -1){
		return "Edge";
	};//�ж�Microsoft edge �����
	if (userAgent.indexOf("Chrome") > -1){
		return "Chrome";
	};	//�ж�Chrome�����
	if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    }; //�ж��Ƿ�Safari�����
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
        return "IE";
    }; //�ж��Ƿ�IE�����
}