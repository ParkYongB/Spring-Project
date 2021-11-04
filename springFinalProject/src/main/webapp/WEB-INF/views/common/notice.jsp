<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
 * 작성일 : 2021. 10. 14.
 * 작성자 : 이현지
 * 설명 : 로그인 회원번호와 작성자 회원번호 일치 여부 관련 안내메시지
 * 수정일 : 
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
</head>
<body>
<div class="page-main">
	<h2>안내</h2>
	<div class="result-display">
		<div class="align-center">
			${accessMsg}
			<p>
			<input type="button" value="${accessBtn}" onclick="location.href='${accessUrl}'">
			<input type="button" value="메인" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</p>
		</div>
	</div>
</div>
</body>
</html>