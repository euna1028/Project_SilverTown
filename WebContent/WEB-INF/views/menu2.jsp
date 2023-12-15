<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그래픽 기반의 실시간 AI서비스를 활용한 cross-platform 개발자 양성과정</title>
</head>
<body>
	<table id="menuTbl">
		<tr>
			<td style="width:50%;">
				<a href="dataroom.go"><img src="resources/img/folder.png"></a>&nbsp;&nbsp;
				<a href="gallery.go"><img src="resources/img/image.png"></a>&nbsp;&nbsp;
			</td>
			<td align="right">
				<table style="width:90%;">
					<tr>
						<td style="width:230px;">&nbsp;</td>
						<td style="width:100px;" align="right">
							<img src="${sessionScope.loginMember.gm_photo }">
						</td>
						<td align="right" valign="top">
							${sessionScope.loginMember.gm_id }
						</td>
						<td valign="bottom" style="width:150px;">
							<a href="member.info.go">회원정보</a>
							<a href="member.kakaoLogout.go">로그아웃</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>