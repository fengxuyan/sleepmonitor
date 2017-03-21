<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%-- <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> --%>
<script src="/SleepMonitor/JS/wdDatePicker/src/jquery.js" type="text/javascript"></script>
<link href="/SleepMonitor/JS/wdDatePicker/sample-css/page.css" rel="stylesheet" type="text/css">
<link href="/SleepMonitor/JS/wdDatePicker/css/dp.css" rel="stylesheet" type="text/css">
<script src="/SleepMonitor/JS/wdDatePicker/src/Plugins/datepicker_lang_US.js" type="text/javascript"></script>
<script src="/SleepMonitor/JS/wdDatePicker/src/Plugins/jquery.datepicker.js" type="text/javascript"></script>
<script src="/SleepMonitor/JS/highlighter/scripts/shCore.js" type="text/javascript"></script>
<script src="/SleepMonitor/JS/highlighter/scripts/shBrushJScript.js" type="text/javascript"></script>
<script src="/SleepMonitor/JS/highlighter/scripts/shBrushCss.js" type="text/javascript"></script>
<link href="/SleepMonitor/JS/highlighter/styles/shCore.css" rel="stylesheet" type="text/css">
<link href="/SleepMonitor/JS/highlighter/styles/shThemeDefault.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
        SyntaxHighlighter.config.clipboardSwf = "/SleepMonitor/JS/highlighter/scripts/clipboard.swf";
    	  SyntaxHighlighter.all();
</script>
<script type="text/javascript">
        $(document).ready(function() {           
            $("#datetime").datepicker({ picker: "<button>Select</button>" });
           $("#temptime").datepicker({ picker: "<img class='picker' align='middle' src='/SleepMonitor/JS/wdDatePicker/sample-css/cal.gif' alt=''/>" });
            /* function rule(id) {
                if (id == 'datetime') {
                    var v = $("#endtime").val();
                    if (v == "") {
                        return null;
                    }
                    else {
                        var d = v.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
                        if (d != null) {
                            var nd = new Date(parseInt(d[1], 10), parseInt(d[3], 10) - 1, parseInt(d[4], 10));
                            return { enddate: nd };
                        }
                        else {
                            return null;
                        }
                    }
                }
                else {
                    var v = $("#datetime").val();
                    if (v == "") {
                        return null;
                    }
                    else {
                        var d = v.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
                        if (d != null) {
                            var nd = new Date(parseInt(d[1], 10), parseInt(d[3], 10) - 1, parseInt(d[4], 10));
                            return { startdate: nd };
                        }
                        else {
                            return null;
                        }
                    }

                }
            } */
        });
    </script>
</head>
<body>
	<input class="bbit-dp-input" id="temptime" type="text">
	<input class="bbit-dp-input" id="datetime" type="text"><!-- <button >Select</button> -->
</body>
</html>