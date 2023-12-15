<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시니어 월드</title>
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/dataroom.css">
<link rel="stylesheet" href="resources/css/gallery.css">
<link rel="stylesheet" href="resources/css/member.css">
<link rel="stylesheet" href="resources/css/sns.css">
<link rel="stylesheet" href="resources/css/euna.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="resources/js/jQuery.js"></script>
<script type="text/javascript" src="resources/js/kwonValidChecker.js"></script>
<script type="text/javascript" src="resources/js/gbraucp2Check.js"></script>
<script type="text/javascript" src="resources/js/gbraucp2Move.js"></script>
<script type="text/javascript" src="resources/js/gbraucp2_jQuery.js"></script>
</head>
<body>
	<table id="siteTitleArea">
		<tr>
			<td align="center" style="height:60px;">
				<jsp:include page="${loginPage }"></jsp:include>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table id="siteTitle">
					<tr>
						<td align="right"><a href="index.do"><img src="resources/img/senior.png" id="siteTitleImg"></a></td>
						<td style="height:50px;" align="left"><a href="index.do">시니어 월드</a></td>
					</tr>
					<tr>
						<td align="right" style="height:30px;"><span id="resultArea">${result }</span></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table id="siteContentArea">
		<tr>
			<td align="center">
				<jsp:include page="${contentPage }"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>