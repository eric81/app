<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>北京市预约挂号统一平台</title>
<link rel="icon" href="/comm/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/comm/images/favicon.ico" type="image/x-icon" />
<link href="http://img.bjguahao.gov.cn/css/all_v2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/comm/css/jquery.autocomplete.css" type="text/css" />
<link href="/comm/css/content.css" rel="stylesheet" type="text/css" />
<!--<script type="text/javascript" src="/comm/css/jquery-1.4.2.min.js"></script>-->
<!--<script type="text/javascript" src="/comm/css/sousuo.js"></script>-->
<script type="text/javascript" src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<!--<script src="css/jquery.min.js"></script>-->
<script type="text/javascript" src="/comm/css/hospitalslist.js?d=121116"></script>
<script type="text/javascript" src="/comm/css/guahao5.js"></script>
</head>

<body>
	<div style="DISPLAY: none" id=goTopBtn>
		<img border=0 src="http://img.bjguahao.gov.cn/images/v2_top.gif" alt="回到顶部" width="32" height="21">
	</div>
	<SCRIPT type="text/javascript">goTopEx();</SCRIPT>
	<div class="top" id='top_loginform'></div>
	<div class="header">
		<div class="searchright">
			<form method='get' name='search' action='/comm/list.php' onsubmit='return checkwords();'>
				<div class="search">

					<div class="option" onclick="wValDisp('h','yyk');">
						<span id="wn_h">医院</span>
					</div>
					<div class="sbox">
						<input name="words" type="text" id="words" maxlength="30" />
					</div>
					<div class="sbtn">
						<input type="submit" value="" style="letter-spacing: -1px;" />
					</div>


					<div id="sbArea_h" style="display:none;">
						<a target="_self" href="javascript:wValChg('ksk','h');"><div class="dselObj" id="wv_h_2">科室</div> </a> <a target="_self"
							href="javascript:wValChg('jbk','h');"><div class="dselObj" id="wv_h_4">疾病</div> </a> <a target="_self"
							href="javascript:wValChg('yyk','h');"><div class="dselObj" id="wv_h_1">医院</div> </a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="menu">
		<div class="submenu">
			<ul id="menuList">
				<li><a href="/comm/index.html" class="bg02">首页</a>
				</li>
				<li style="width: 1px;"><img src="http://img.bjguahao.gov.cn/images/v2_menuline.gif" width="1" height="39" />
				</li>
				<li><a href="/comm/list.html" class="bg02">按医院预约</a>
				</li>
				<li style="width: 1px;"><img src="http://img.bjguahao.gov.cn/images/v2_menuline.gif" width="1" height="39" />
				</li>
				<li><a href="/comm/ksfl.html" class="bg02">按科室预约</a>
				</li>
				<li style="width: 1px;"><img src="http://img.bjguahao.gov.cn/images/v2_menuline.gif" width="1" height="39" />
				</li>
				<li><a href="/comm/list/disease.html" class="bg02">按疾病预约</a>
				</li>
				<li style="width: 1px;"><img src="http://img.bjguahao.gov.cn/images/v2_menuline.gif" width="1" height="39" />
				</li>
				<li><a href="/comm/yyzn.php" class="bg02">预约指南</a>
				</li>
				<li style="width: 1px;"><img src="http://img.bjguahao.gov.cn/images/v2_menuline.gif" width="1" height="39" />
				</li>
				<li><a href="/comm/notice/zxgg.html" class="bg02">最新公告</a>
				</li>
				<li style="width: 1px;"><img src="http://img.bjguahao.gov.cn/images/v2_menuline.gif" width="1" height="39" />
				</li>
				<li><a href="/comm/yjfk.php" class="bg02">意见反馈</a>
				</li>
			</ul>
		</div>
		<div class="help">
			<div id="nav">
				<ul>
					<li class="hmenu2" onmouseover="this.className='hmenu1'" onmouseout="this.className='hmenu2'"><a href="/comm/help.php"
						target="_blank"><img src="http://img.bjguahao.gov.cn/images/v2_help.gif" width="110" height="26" border="0" /> </a>
						<div class="hlist">
							<a href="/comm/zczn.php">注册指南</a><br /> <a href="/comm/yyzn.php">预约指南</a><br /> <a href="/comm/zhgl.php">帐号管理</a><br /> <a
								href="/comm/cjwt.php">常见问题</a><br />

						</div></li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$(function() {
		//首部登陆
		$.get("/comm/plus/ajax_user.php", {
			"act" : "top_loginform"
		}, function(msg) {
			var data=msg.split('|');
			$("#top_loginform").html(data[1]);
			$("#denglu").click(function(){
				$("#apple").overlay().load();
			})
		});
		complete(hospitals);
		$("#apple").overlay({mask:{
	        color: '#000',
	        loadSpeed: 200,
	        opacity: 0.3
	      },
		effect: 'apple'});
})
</script>



	<div class="apple_overlay" id="apple">
		<div class="register_box">
			<div class="register_ts" id="result"></div>
			<ul>
				<li><p>姓 名：</p> <span class="register_bg"><input id="truename" type="text" class="register_input" /> </span>
				</li>
				<li><p>证件号：</p> <span class="register_bg"><input id="sfzhm" type="text" class="register_input" /> </span>
				</li>
				<li><p>验证码：</p> <span class="register_bg03"><input id="yzm" type="text" class="register_inputa" /> <img id="yzmdl"
						src="/comm/code.php" width="50" height="19" align="absmiddle" class="register_yzm" /> <a href="javascript:void(0)" class="red"
						onclick="return  refreshlog(document.getElementById('yzmdl'));">换一张</a> </span>
				</li>
				<li style="margin-top:20px;"><a href="javascript:void(0)" onclick="sublogin()"><img
						src="http://img.bjguahao.gov.cn/images/v2_logindl.gif" width="90" height="30" /> </a> <a href="reg.php"><img
						src="http://img.bjguahao.gov.cn/images/v2_loginzc.gif" width="90" height="30" /> </a>
				</li>
			</ul>
		</div>
	</div>
	<div class="blank8"></div>
	<script>
function testt(){
	var fa = document.ti;
	var dxcode = $("#dxcode1").val();
	if(dxcode == "" || dxcode == null){
		alert('短信验证码为空');
		fa.dxcode1.focus();
	}else{
		fa.submit();
	}
}
function getcode(t){
	
	var i=1; 
	t.disabled=true; 
	var timer=setInterval(function(){t.value="等待中"+i;i++;
	if(i>180){t.disabled=false;i=1;t.value="点击获取";clearInterva(timer)}},1000) 
	

	var jiuz = $("#jiuz").val();
	var ybkh = $("#ybkh").val();
	var baoxiao = $("#baoxiao").val();
	var hpid = $("#code_hpid").val();
	var ksid = $("#code_ksid").val();
	var datid = $("#code_datid").val();
	$.get("shortmsg/dx_code.php?hpid="+hpid+"&ksid="+ksid+"&datid="+datid+"&jiuz="+jiuz+"&ybkh="+ybkh+"&baoxiao="+baoxiao,null,callback);
}

function callback(data){
			var a = $("#tian");
			a.html(data);
}

function applyContent(a){
	$.ajax({
		url:'/comm/favhosp.php',
		type:'GET',
		data:"type=1&hpid="+a,
		success:function(msg){
			alert(msg);
		}
	})	
}
</script>
	<div class="listtop">
		<span>
			<ul id="menuList1">
				<li><a href="yyks.php?hpid=122" class="bg03">医院信息</a>
				</li>
				<li><a href="yyxz.php?hpid=122" class="bg04">预约须知</a>
				</li>
				<li><a href="yycx.php?hpid=122" class="bg04">查询/取消预约</a>
				</li>
				<li><a href="stop.php?hpid=122" class="bg04">停诊信息</a>
				</li>
			</ul> </span><label><a href="#" onclick="applyContent(122)" class="org1">设为关注医院</a> <a href="list.html" class="blue1 b">[选择其他医院]</a> </label>
	</div>
	<script type="text/javascript">
var currentUrl = location.href;
$(document).ready(function(){
	$("#menuList1 a").attr("class","bg04");
	$("#menuList1 a").each(function(){
		var tmpUrl = $(this).attr("href") ;
		if(-1 != currentUrl.indexOf(tmpUrl) ){
			$(this).attr("class","bg03");
			return;
		}
	});
});
</script>
	<div class="listbox">

		<div class="hospital">
			<p>中国中医科学院广安门医院――心理科</p>
			<label><strong>等级：</strong>三级甲等<strong>区域：</strong>西城区<strong>分类：</strong>中国中医科学院</label>
		</div>
		<div class="notes_gh">
			<div class="notes_ght">重要提示:</div>
			<div class="notes_ghn">
				<!--<strong>1.预约周期：</strong>91天       <strong>2.放号时间：</strong>每日9:15<br />-->
				<strong>1.预约周期：</strong>91天<strong> 2.放号时间：</strong>09:15<br /> <strong>3.停挂时间：</strong>下午15:00停止次日预约挂号<br /> <strong>4.退号时间：</strong>就诊前一日15:00前取消<br />
				<strong>5.特殊规则：</strong><br /> ①&nbsp; 普通号可预约3月内号源，专家号暂可预约4天内号源（专家号提前3天预约，不能预约当天的号源），以后将逐步延长预约时间；<br /> ②&nbsp; 国际医疗部及特需门诊不实行预约；<br />
				③&nbsp; 预约挂号患者的就诊顺序号如与当天门诊挂号条显示号序不符，则以医院当天现场门诊挂号条显示号序为准；<br /> ④&nbsp; 没有我院就诊卡患者，请取号前先办理就诊卡。
			</div>
		</div>


		<form action="ghdown.php" method="post" name="ti">
			<div class="guahao">
				<p class="guahaol">
					选择的医院:中国中医科学院广安门医院<br /> 选择的科室:心理科<br /> 选择的医生:副主任医师<br /> 挂号费:7.00<br /> 就诊日期:2013-03-29 上午
				</p>


				<input type="hidden" name="tpost" value="d3c813b876eef842a6c7245ea831a04d" /> <span class="guahaor"> <b id="tian"
					style="color:red"></b><br />就诊卡：<input type="text" name="jiuz" id="jiuz" value="" / style="width:160px;height:18px;margin-left:28px;">
					<br />医保卡号：<input type="text" name="ybkh" id="ybkh" value="" / style="width:160px;height:18px;margin-left:14px;"> <br />报销类型：<select
					name="baoxiao" id="baoxiao" style="width:160px;height:18px;margin-left:14px;"><option value="0" selected>==请选择==</option>
						<option value="1">医疗保险</option>
						<option value="2">商业保险</option>
						<option value="3">公费医疗</option>
						<option value="4">新农合</option>
						<option value="5">异地医保</option>
						<option value="6">红本医疗</option>
						<option value="7">工伤</option>
						<option value="8">一老一小</option>
						<option value="9">超转</option>
						<option value="10">自费</option>
						<option value="11">其他</option>
				</select><br />短信验证码：<input type="text" name="dxcode" id="dxcode1" / style="width:160px;height:18px;"><br /> <input type="button"
					value="点击获取" onclick="getcode(this)"> </span> <input type="hidden" name="hpid" id="code_hpid" value="122"> <input
					type="hidden" name="ksid" id="code_ksid" value="1108"> <input type="hidden" name="datid" id="code_datid" value="97769">
			</div>

			<div class="guahaotj">
				<img src="images/v2_queren.gif" onclick="testt()" width="115" height="27" />
			</div>
	</div>

	</form>
	<div class="footbg">
		<div class="foot">
			<div class="foot_logo">
				<div class="foot_logo1">
					<a href="http://www.bjhb.gov.cn/" target="_blank"></a>
				</div>
				<div class="foot_logo2">
					<a href="http://www.114menhu.com/" target="_blank"></a>
				</div>
			</div>
			<p>
				<a href="/comm/lxwm.php">联系我们</a> ┊ <a href="/comm/hzhb.php">合作伙伴</a> ┊ <a href="/comm/flsm.php">法律声明</a> ┊ <a href="/comm/fwxy.php">服务协议</a>
				┊ <a href="/comm/yjfk.php">意见反馈</a> Copyright 2011-2013版权所有. <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备05056889号</a>
			</p>
		</div>
	</div>
	<span id="scrolltop" onclick="window.scrollTo('0','0')" class="hidden"></span>

	<script type='text/javascript'>

			var _hmt = _hmt || [];
			(function() {
				
				// baidu
				var hm = document.createElement("script");hm.type = 'text/javascript'; hm.async = true;
				hm.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'hm.baidu.com/hm.js?13e29334f151c8514bf6cf2533b9d9af';
				var s = document.getElementsByTagName("script")[0]; 
				s.parentNode.insertBefore(hm, s);
				
				//miaozhen
				var c = document.createElement('script'); c.type = 'text/javascript';c.async = true;
				c.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'sitemonitor.cn.miaozhen.com/boot/45359';
				var h = document.getElementsByTagName('script')[0];
				h.parentNode.insertBefore(c, h);
			
			})();

</script>

</body>
</html>