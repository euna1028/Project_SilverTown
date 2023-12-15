<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그래픽 기반의 실시간 AI서비스를 활용한 cross-platform 개발자 양성과정</title>
</head>
<body>
	<form action="member.find" method="post" 
		onsubmit="return memberFindCheck(this);">
		<table id="memberJoinTbl">
			<tr>
				<th>비밀번호 찾기</th>
			</tr>

			<tr>
				<td align="center"><input id="memberFindID" name="gm_id"
					class="textType" placeholder="ID" maxlength="10" autocomplete="off"
					autofocus="autofocus"><div id="error2"></div></td>
			</tr>
			<tr>
				<td align="center"><input name="gm_name" class="textType"
					placeholder="이름" maxlength="10" autocomplete="off">
				<div id="error111"></div></td>
			</tr>
			<tr>
				<td align="center"><input name="gm_email" class="textType email-input"
							placeholder="email" maxlength="30" autocomplete="off"
							autofocus="autofocus">
					<div id="error111"></div>
				</td>
			</tr>
			<tr>
				<div>
					<td align="center">
						<button type="submit" id="btnSignup">찾기</button>
						<button type="button" id="btnCancle">취소</button>
					</td>
				</div>
			</tr>
		</table>
	</form>
</body>
</html>