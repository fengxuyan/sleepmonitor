/**
 * Created by Yao on 2017/1/6.
 */
$(function () {


    // siblings()用于初始化，在后续的点击事件中不应该使用siblings(),
    //$(".real ul li").eq(0).addClass("on").siblings().removeClass("on");
    var that=$(".left_nav ul li").eq(0)[0];
    $(".left_nav ul li").click(function(event) {
// $(this).addClass('on').siblings('span').removeClass('on');
        if(typeof(that)!="undefined"){
            $(that).removeClass("on");
        }
        $(this).addClass('on');
        that=this;
    });
        $(".concern_list li").each(function(index,item){
            $(item).bind("click",function(){
                $(".concern_item0").hide();
            });
        });







    $('.search_pic').click(
        function () {
        	$("#search_result").remove();
            var approval_box0_email = $(".apply_txt").val();
            var ajaxurl="/SleepMonitor/concern/query";
            if ($.trim(approval_box0_email) == '') {
            	search_gb();
                alert("input email!");
                return false;
            }else{
            $.ajax({
                type:"POST",
                url:ajaxurl,
                data:{
                	Email:approval_box0_email
                	},
                dataType:"json",
                success:function(data) {
                	if(data.Code==1){
                		$(".apply_concern").append("<div id='search_result'><h4 id='result_Name'>&nbsp;&nbsp;&nbsp;Name:"+data.data
                				+"</h4><br/><h4 id='result_Email'>&nbsp;&nbsp;&nbsp;Email:"+data.Email+"<input id='apply_apply' type='button' value='Apply' onclick='apply_apply()'>"
    							+"<input id='cancel_apply' type='button' value='Cancel' onclick='cancel_apply()'></div>");
                		search_gb();
                		document.cookie="applyPeopleEmail="+data.Email;
                		document.cookie="applyPeopleName="+data.data;
                	}else{
                		alert("User id not Exist!");
                		javascript:location.reload();
                	}
                },
                error: function (jqXHR, textStatus, errorThrown,Code) {
                	alert("Unkonw Error.Please Try Again!");
                }
            }
        );}
        }
    );
   
    
    
    
    	
 
    
    
    $('.approval_agree').click(
        function () {
        var approval_box0_email=$(".approval_box0_email").html();
            alert(approval_box0_email);
        var ajaxurl="/SleepMonitor/concern/query";
        $.ajax({
                type:"POST",
                url:ajaxurl,
                data:{"approval_box0_email":approval_box0_email,"approval_agree":1},
                async: false,
                dataType:"json",
                jsonp:'callback',
                success:function(data) {
                    //alert("success"); location.href = "{php echo url('wechat/card/')}" + 'f=post&do=' + type;
   var div='<div class="a"><div class="head-pic" style="display: inline-block"> <img src="../images/0.jpg"> </div> <div style="display: inline-block"> <ul> <li><span>name</span> <span class="approval_box1_email">email</span><button type="button" class="unfollow">鍙栨秷鍏虫敞</button></li> </ul> <ul> <li><span>鏅鸿兘搴婂灚</span> <button type="button" class="now">鏌ョ湅瀹炴椂</button> <button type="button" class="history"> 鏌ョ湅鍘嗗彶</button></li> </ul> </div></div><div class="clear"></div>';
                    $(".approval_box1").after(div);

                    $('.now').click(
                        function(){
                            window.location.href='product/id=123&now=1';
                        });
                    $('.history').click(
                        function(){
                            window.location.href='id=123&now=0';
                        }
                    );
                },
                error: function (jqXHR, textStatus, errorThrown,Code) {
                	alert("Unkonw Error.Please Try Again!");
                }
            }
        );
        return false;
    });

    $('.approval_disagree').click(
        function () {
            var approval_box0_email=$('.approval_box0_email').html();
            var ajaxurl="/SleepMonitor/concern/query";
            //var ajaxurl="test.php";
            $.ajax({
                    type:"POST",
                    url:ajaxurl,
                    data:{"approval_box0_email":approval_box0_email,"approval_agree":0},
                    async: false,
                    dataType:"json",
                    jsonp:'callback',
                    success:function(data) {
                        //alert("success");
                    },
                    error: function (jqXHR, textStatus, errorThrown,Code) {
                    	alert("Unkonw Error.Please Try Again!");
                    }
                }
            );
            return false;
        });
    $('.unfollow').click(
        function(){
            var approval_box1_email=$(".approval_box1_email").html();
            var ajaxurl="/SleepMonitor/concern/query";
            $.ajax({
                    type:"POST",
                    url:ajaxurl,
                    data:{"approval_box1_email":approval_box1_email,"unfollow":1},
                    async: false,
                    dataType:"json",
                    jsonp:'callback',
                    success:function(data) {
                        $('.approval_box1').remove();
                    },
                    error: function (jqXHR, textStatus, errorThrown,data) {
                    	alert("Unkonw Error.Please Try Again!");
                    }
                }
            );
            return false;

        });

    $('#disable').click(
        function () {
            var approval_box2_email=$(".approval_box2_email").html();
            var ajaxurl="/SleepMonitor/concern/query";
            //var ajaxurl="test.php";
            $.ajax({
                    type:"POST",
                    url:ajaxurl,
                    data:{"approval_box0_email":approval_box2_email,"disable":1},
                    async: false,
                    dataType:"json",
                    jsonp:'callback',
                    success:function(data) {
                        //alert("success");
                    },
                    error: function (jqXHR, textStatus, errorThrown,Code) {
                    	alert("Unkonw Error.Please Try Again!");
                    }
                }
            );
            return false;
        });

});