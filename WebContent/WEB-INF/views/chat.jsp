<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/jQuery.js"></script>
<script type="text/javascript" src="jQuery.js"></script>
<script type="text/javascript" src="http://121.160.41.35:3456/socket.io/socket.io.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="https://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
$(function(){
	//연결하면 자동으로 서버쪽으로 메세지를 보냄
	// 제목 : connection, 내용 : socket 객체 
	var socket = io.connect("http://localhost:3456");
	var n;
	$("#msg").keyup(function(e){
		if(e.keyCode == 13){
			var m = $("#msg").val();
			n = $("#nickname").val();
			//복잡한 거는 자바 객체로 만들어서 보내기 
			var msg = { nn : n, txt : m	}; 
			
			socket.emit("clientMsg", msg);
			$(this).val("");
		}
	});
	socket.on("serverMsg", function(m){
		var br = $("<br>");	
		var br2 = $("<br>");
		var who = "[" + m.nn + "]";
		var td1 = $("<td></td>").append(who, br, br2, m.txt);
		var td2 = $("<td></td>").attr("style", "width:40%;");
		var td3 = $("<td></td>").attr("style", "width:30%;");
		var tr;

		if (n == m.nn) {
				//내 메세지 
			    td1 = $(td1).attr("class", "myMsg").attr("align", "right");
			    tr = $("<tr></tr>").append(td3, td2, td1);
			}else {
				//남의 메세지
				td1 = $(td1).attr("class", "yourMsg");
				tr = $("<tr></tr>").append(td1, td2, td3);
			}
		   $("table").append(tr);
		   var htmlHeight = $(document).height();
			$(window).scrollTop(htmlHeight);
	      });//소켓
	});//서버
</script>
</head>
<body>
	<div data-role="page" id="main">
		<div data-role="header" data-theme="e" data-position="fixed">
			<h1>채팅</h1>
		</div>

		<div data-role="content">
			<a href="#chat" data-transition="fade" data-role="button"> 
			<img src="resources/img/panda.jpg"></a> 
				<input id="nickname" placeholder="ID">
			    
			<a href="#chat" data-role="button">입장</a>
		</div>

		<div data-role="footer" data-position="fixed" data-theme="e">
			<h1></h1>
		</div>
	</div>
	<!--페이지 -->
	<div data-role="page" id="chat">
		<div data-role="header" data-theme="e" data-position="fixed">
			<a href="#main" data-icon="arrow-l">뒤로 가기</a>
			<h1>채팅</h1>
		</div>

		<div data-role="content">
        <table style="width: 100%;"id="chatTable"></table>
		</div>

		<div data-role="footer" data-position="fixed" data-theme="e">
			<input id="msg">
		</div>
	</div>
</body>
</html>