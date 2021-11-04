<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script type = "text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<style>
	#con_main {
		width : 1000px;
		height : 250px;
		border : 1px solid grey;
		margin-top : 30px;
	}
	#con_text {
		margin-top : 30px;
		font-family: 'Gowun Dodum', sans-serif;
		text-decoration: none;
	}
	#con_button {
		margin-top : 50px;
	}
</style>
<h2>비회원 주문 조회</h2>
<div class = "align-center">
	<div class = "container" id = "con_main">
		<div id = "con_text">
			<h3>주문하신 분의 전화번호를 입력해주세요!</h3>
		</div>
		<form:form id = "non_check" action = "${pageContext.request.contextPath}/order/orderNonCheck.do" modelAttribute = "orderVO">
			<div id = "con_button">
				전화번호 : <form:input path = "receiver_phone" /><br>
				<form:errors path = "receiver_phone" cssClass = "error-color"/><br>
				<br><form:button class = "btn btn-outline-dark" path = "checkBtn">조회하기</form:button>
			</div>
		</form:form>
	</div>
</div>