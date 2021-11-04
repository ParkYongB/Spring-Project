<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%-- 
 * 작성일 : 2021. 10. 19.
 * 작성자 : 오상준
 * 설명 : 아이디 찾기 폼
 * 수정일 : 
--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<script type ="text/javascript">
	$(document).ready(function() {
		// submit 버튼 클릭시 유효성 체크
		$("#idSearch_form").submit(function(){
			if($("#mem_name").val().trim() == ""){		// 이름 입력 체크
				alert("이름을 입력하세요!!");
				$("#mem_name").val("").focus();
				return false;	// 서밋은 false 클릭은 ;
			}
			if($("#phone").val().trim() == ""){		// 전화번호 입력 체크
				alert("전화번호를 입력하세요!!");
				$("#phone").val("").focus();
				return false;	// 서밋은 false 클릭은 ;
			}
		});
	});
</script>
<div class = "container-fluid" style = "width:700px; font-family: 'Gowun Dodum', sans-serif; ">
	<div align = "left">
			<h3>아이디 찾기</h3>
	</div>
	<div class="text-center col-sm-14 my-5">
	<form:form id="idSearch_form" action="idSearchResult.do" modelAttribute="memberVO">
		<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="mem_name">가입시 입력 이름</label>
				<form:input class = "col-sm-4 form-control" path="mem_name"/>
		</div>
		<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="phone">가입시 입력 전화번호</label>
				<form:input class = "col-sm-4 form-control" path="phone"/>
		</div>
		<div class = "form-group row" style="padding-left: 3em;">
			<form:button class = "btn btn-outline-info">아이디 찾기</form:button>&nbsp;
			<input type = "button" class = "btn btn-outline-info" value = "비밀번호찾기" onclick = "location.href='${pageContext.request.contextPath}/member/passwdSearch.do'">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "로그인으로" onclick = "location.href='${pageContext.request.contextPath}/member/login.do'">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "회원가입으로" onclick = "location.href='${pageContext.request.contextPath}/member/registerUser.do'">
		</div>
	</form:form><br>
	
	</div>
</div>
