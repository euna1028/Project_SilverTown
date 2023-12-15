<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그래픽 기반의 실시간 AI서비스를 활용한 cross-platform 개발자 양성과정</title>
</head>
<body>
	<form action="member.login" method="post"
		onsubmit="return memberLoginCheck(this);">
		<table id="loginTbl">
			<tr>
				<td><input name="gm_id" placeholder="id" maxlength="10"
					autocomplete="off" required
					oninvalid="this.setCustomValidity('아이디를 입력하세요.')"
					oninput="setCustomValidity('')"> <input name="gm_pw"
					oninvalid="this.setCustomValidity('비밀번호를 입력하세요.')" required
					oninput="setCustomValidity('')" type="password" placeholder="pw"
					maxlength="10">&nbsp;&nbsp;
					<button>로그인</button>&nbsp;&nbsp; <a href="member.join.go">회원가입</a>&nbsp;&nbsp;<a
					href="member.find.go">비밀번호 찾기</a> <a class="p-2"
					href="https://kauth.kakao.com/oauth/authorize?client_id=80c4a0c24bcf50c71fed5fe920402b1c&redirect_uri=http://localhost:8081/gbraucp2/member.kakaoLogin.go&response_type=code">
						카카오톡 로그인</a></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>