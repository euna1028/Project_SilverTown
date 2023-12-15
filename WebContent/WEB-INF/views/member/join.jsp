<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그래픽 기반의 실시간 AI서비스를 활용한 cross-platform 개발자 양성과정</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/join.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/def66b134a.js" crossorigin="anonymous"></script>
<script type="text/javascript">
var AuthCode = 0;
	function sendEmailVerification() {
		var email = document.querySelector('input[name="gm_email"]').value;
		console.log(email);
		// 이메일 주소가 유효한지 검사 (예: 간단한 형식 검사)
		if (!validateEmail(email)) {
			document.getElementById("error1").innerHTML = "유효한 이메일 주소를 입력하세요.";
			return;
		}

		// 이메일 주소를 서버로 보내서 이메일을 발송하는 Ajax 요청 보내기
		$.ajax({
					url : 'member.email', // 이메일을 처리하는 서버 엔드포인트 주소
					type : 'POST',
					data : {
						gm_email : email
					},
					success : function(response) {
						if (response > 999) {
							document.getElementById("verificationInputDiv").style.display = "block";
							document.getElementById("error1").innerHTML = "이메일이 발송되었습니다.";
							//document.getElementById("verificationCode").value = Number(response);
							AuthCode = response;
						} else {
							document.getElementById("error1").innerHTML = "이메일 발송 실패: "
									+ response.message;
						}
					},
					error : function() {
						document.getElementById("error1").innerHTML = "서버와의 통신 중 오류가 발생했습니다.";
					}
				});
	}

	function validateEmail(email) {
		// 간단한 이메일 유효성 검사 (여기서 더 엄격한 유효성 검사를 추가할 수 있음)
		var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
		return emailPattern.test(email);
	}
	function verifyEmail(response) {
	    var verificationCode = document.getElementById("verificationCode").value; //내가 입력한 인증번호
	    console.log(AuthCode);
		console.log(verificationCode);
	    if (verificationCode == AuthCode) {
	        document.getElementById("verificationInputDiv").style.display = "none"; // 입력창 숨기기
	        document.getElementById("error1").innerHTML = "인증이 완료되었습니다!";
	        document.getElementById("error1").style.color = "green"; // 초록색 글자로 변경
	    } else {
	        document.getElementById("error1").innerHTML = "인증 번호가 일치하지 않습니다.";
	        document.getElementById("error1").style.color = "red"; // 빨간색 글자로 변경
	    }
	}
</script>
</head>
<body>
	<form action="member.join" method="post" enctype="multipart/form-data"
		onsubmit="return memberJoinCheck(this);">
		<table id="memberJoinTbl">
			<tr>
				<th>회원가입</th>
			</tr>
			<tr id="verificationInputDiv" style="display: none;">
				<td align="center"><input id="verificationCode"
					class="textType" placeholder="인증 번호 입력" maxlength="6">
					<button type="button" id="btnVerify" onclick="verifyEmail()">확인</button>
				</td>
			</tr>
			<tr>
				<td align="center"><i class="fas fa-id-card fa-lg icon" style="color: #005eff;"></i><input id="memberJoinID" name="gm_id"
					class="textType" placeholder="ID" maxlength="12" autocomplete="off"
					autofocus="autofocus">
				<div id="error2"></div></td>
				
			</tr>
			<tr>
				<td align="center"><i class="fas fa-key fa-lg icon" style="color: #005eff;"></i><input id="memberJoinPW" name="gm_pw"
					class="textType" type="password" placeholder="PW" maxlength="16">
					<div id="error3"></div></td>
			</tr>
			<tr>
				<td align="center"><i class="fas fa-lock fa-lg icon" style="color: #005eff;"></i><input id="memberJoinPW2" name="gm_pwChk"
					class="textType" type="password" placeholder="PW확인" maxlength="16">
					<div id="error4"></div></td>
			</tr>
			<tr>
				<td align="center"><i class="fas fa-file-signature fa-lg icon" style="color: #005eff;"></i><input name="gm_name" class="textType"
					placeholder="이름" maxlength="12" autocomplete="off">
					<div id="error111"></div></td>
			</tr>
			<tr>
				<td align="center">
					<div class="email-container" style = "display: flex;">
						<i class="fas fa-envelope fa-lg icon" style="color: #005eff; vertical-align: middle;"></i><input name="gm_email" class="textType email-input"
							placeholder="Email" maxlength="30" autocomplete="off"
							autofocus="autofocus" style = "width:60%;">
						<button type="button" id="btnMail" class="email-button"
							onclick="sendEmailVerification()">메일인증</button>
					</div>
					<div id="error1"></div>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 35px; height: 55px;"><i class="fas fa-birthday-cake fa-lg icon" style="color: #005eff;"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: black;">주민등록번호</span> &nbsp; <input
					name="gm_jumin1" class="jumin1" placeholder="XXXXXX" maxlength="6"
					autocomplete="off"><span style="color: black;"> - </span><input name="gm_jumin2"
					class="jumin2" placeholder="X" maxlength="1" autocomplete="off"><span style="color: black;">XXXXXX</span>&nb;
					<div id="error111"></div>
				</td>
			</tr>
			<tr>
				<td align="center"><i class="fas fa-map-marked-alt fa-lg icon" style="color: #005eff;"></i><input id="memberJoinAddr1"
					readonly="readonly" name="gm_address1" class="textType"
					placeholder="우편번호" autocomplete="off"><br> &nbsp;&nbsp;&nbsp;&nbsp;<input
					id="memberJoinAddr2" readonly="readonly" name="gm_address2"
					class="textType" placeholder="주소" autocomplete="off"><br>
					&nbsp;&nbsp;&nbsp;&nbsp;<input name="gm_address3" class="textType" placeholder="상세주소"
					maxlength="50" autocomplete="off">
					<div id="error111"></div></td>
			</tr>
			<tr>
				<td style="padding-left: 35px; height: 55px;"><i class="fas fa-images fa-lg" style="color: #005eff;"></i>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: black;">프사</span>&nbsp;&nbsp; <input
					name="gm_photo" type="file">
					<div id="error1111"></div>
				</td>
			</tr>
			<tr>
				<div>
					<td align="center">
						<button type="submit" id="btnSignup">회원가입</button>
						<button type="button" id="btnCancle">취소</button>
					</td>
				</div>
			</tr>
		</table>
	</form>
</body>
</html>