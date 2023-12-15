<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그래픽 기반의 실시간 AI서비스를 활용한 cross-platform 개발자 양성과정</title>
</head>
<body>
		<table id="memberJoinTbl">
	<form action="member.info.update" method="post" 
		enctype="multipart/form-data"
		onsubmit="return memberUpdateCheck(this);">
			<tr>
				<th>${sessionScope.loginMember.gm_id }</th>
			</tr>
			<tr>
				<td align="center">
					<input name="gm_pw" value="${sessionScope.loginMember.gm_pw }" class="textType" type="password" placeholder="PW" maxlength="10">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="gm_pwChk" value="${sessionScope.loginMember.gm_pw }" class="textType" type="password" placeholder="PW확인" maxlength="10">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="gm_name" value="${sessionScope.loginMember.gm_name }" class="textType" placeholder="이름" maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td style="padding-left: 35px; height: 55px;">
					생년월일 : <fmt:formatDate value="${sessionScope.loginMember.gm_birth }" type="date" dateStyle="long"/> 
				</td>
			</tr>
			<tr>
				<td align="center">
					<input id="memberJoinAddr1" readonly="readonly" name="gm_address1" value="${addr1 }" class="textType" placeholder="우편번호" autocomplete="off"><br>
					<input id="memberJoinAddr2" readonly="readonly" name="gm_address2" value="${addr2 }" class="textType" placeholder="주소" autocomplete="off"><br>
					<input name="gm_address3" value="${addr3 }" class="textType" placeholder="상세주소" maxlength="50" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td style="padding-left: 35px; height: 55px;">
					<img src="resources/img/${sessionScope.loginMember.gm_photo }">
					<input name="gm_photo" type="file">
				</td>
			</tr>
			<tr>
				<td align="center">
					<button>수정</button>
	</form>
					<button onclick="memberBye();">탈퇴</button>
				</td>
			</tr>
		</table>
</body>
</html>



