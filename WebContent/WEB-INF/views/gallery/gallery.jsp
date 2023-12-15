<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그래픽 기반의 실시간 AI서비스를 활용한 cross-platform 개발자 양성과정</title>
</head>
<body>
	<div id="galleryArea">
		<c:forEach var="i" items="${images }">
			<table class="anImg">
				<tr>
					<td align="center">
						<img src="resources/img/${i.gg_file }">
					</td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${i.gg_uploader == sessionScope.loginMember.gm_id }">
							<td align="center" ondblclick="galleryDelete(${i.gg_no});" style="cursor: pointer;">
								${i.gg_title }
							</td>
						</c:when>
						<c:otherwise>
							<td align="center">
								${i.gg_title }
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</c:forEach>
	</div>
	<table id="snsControlArea">
		<tr>
			<td align="center">
				<table style="width:500px;">
					<tr>
						<td colspan="2" align="center">
							<img src="resources/img/menu.png" id="snsControlAreaHandle">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">
				<form action="gallery.upload" method="post" 
					enctype="multipart/form-data"
					onsubmit="return galleryUploadCheck(this);">
					<input name="token" value="${token }" type="hidden">
					<table id="snsWriteArea">
						<tr>
							<td><input name="gg_title" class="textType" placeholder="제목" maxlength="40" autocomplete="off" style="padding: 7px;"></td>
							<td rowspan="3">
								<input type="image" src="resources/img/addImage.png" style="width:40px;">
							</td>
						</tr>
						<tr>
							<td style="padding: 7px;">
								<input type="file" name="gg_file">
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>













