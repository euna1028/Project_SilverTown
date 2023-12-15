<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그래픽 기반의 실시간 AI서비스를 활용한 cross-platform 개발자 양성과정</title>
</head>
<body>
	<table id="menuTbl">
		<tr>
			<td style="width: 50%;"><a href="dataroom.go"><img
					src="resources/img/folder.png"></a>&nbsp;&nbsp; <a
				href="gallery.go"><img src="resources/img/image.png"></a>&nbsp;&nbsp;
			</td>
			<td align="right">
				<table style="width: 90%;" border="1">
					<tr>
						<td style="width: 230px;">&nbsp;</td>
						<td style="width: 100px;" align="right">
						
						<c:if test="${not empty sessionScope.access_token}">
    						<img src="${sessionScope.loginMember.gm_photo}">
						</c:if>
						
						<c:if test="${empty sessionScope.access_token}">
    						<img src="resources/img/${sessionScope.loginMember.gm_photo}">
						</c:if>
						</td>
						<td align="right" valign="top" style="color:#FFB300; padding-left:10px; ">
							${sessionScope.loginMember.gm_id }</td>
						<td valign="bottom" style="width: 250px;"><a
							href="member.info.go">회원정보</a> <a href="member.logout">로그아웃</a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>