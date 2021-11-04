<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
 * 작성일 : 2021. 10. 19.
 * 작성자 : 오상준
 * 설명 : 아이디 찾기 결과
 * 수정일 : 
--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<div class = "container-fluid contents-wrap" style = "width:700px; font-family: 'Gowun Dodum', sans-serif; ">
	<div align = "left">
		<h3>아이디 찾기 결과</h3>
	</div>
	<%-- 아이디찾기 실패시 --%>
	<c:if test = "${count == 0}">
	<div class = "text-center">
		정보가 일치하지 않습니다.
	</div>
	</c:if>
	<%-- 아이디 찾기 성공시 --%>
	<c:if test = "${count > 0}">
		
		<br>
	<table class="table table-sm">
			<tr>
				<th scope="col"><div align="center">회원님의 아이디는</div></th>
			</tr>
			<c:forEach var = "member" items = "${list}">
			<tr>
				<td align="center"> 
				<div align="center" style="font-weight:bold; font-size:24px; background-color: #f5f5ff; width :60%; height: 60%;">
				${member.mem_id}</div></td>
			</tr>
			</c:forEach>
			<tr>
				<th scope="col"><div align="center">입니다.</div></th>
			</tr>
		</table>
	</c:if>
	<div class = "text-right">
			<input type = "button" class = "btn btn-outline-dark" value = "뒤로 가기" onclick = "history.go(-1);">
			<input type = "button" class = "btn btn-outline-dark" value = "홈으로" onclick = "location.href='${pageContext.request.contextPath}/main/main.do'">
			<input type = "button" class = "btn btn-outline-dark" value = "로그인하기" onclick = "location.href='${pageContext.request.contextPath}/member/login.do'">
			
	</div>
</div>