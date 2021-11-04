<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
 * 작성일 : 2021. 10. 12.
 * 작성자 : 나윤경
 * 설명 : 자주묻는 질문 삭제
 * 수정일 : 
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>글 삭제</h2>
	<form:form action="qnaDelete.do" modelAttribute="qnaVO">
		<form:hidden path="qna_num"/>
		<div class="align-center">
			<input type="submit" value="삭제">
			<input type="button" value="홈으로" onclick="location.href='qnaList.do'">
		</div>
	</form:form>
</div>
</body>
</html>