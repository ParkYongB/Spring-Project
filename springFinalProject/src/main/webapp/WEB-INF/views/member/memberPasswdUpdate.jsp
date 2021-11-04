<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- 
 * 작성일 : 2021. 10. 17.
 * 작성자 : 오상준
 * 설명 : 회원 비밀번호 변경
 * 수정일 : 
--%>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%@ include file="/WEB-INF/views/member/common/myPageHeader.jsp" %>
<%@ include file="/WEB-INF/views/member/common/myPageProfile.jsp" %>
<script type="text/javascript">
	$(function() {
		// 비밀번호 수정 체크
		$('#passwd').keyup(function() {
			if($('#confirm_passwd').val() != '' && $('#confirm_passwd').val()!= $(this).val()){
				$('#message_id').text('비밀번호 불일치').css('color','red');
			}else if($('#confirm_passwd').val() != '' && $('#confirm_passwd').val() == $(this).val()){
				$('#message_id').text('비밀번호 일치').css('color','blue');
			}
		});
		
		$('#confirm_passwd').keyup(function() {
			if($('#passwd').val() != '' && $('#passwd').val()!= $(this).val()){
				$('#message_id').text('비밀번호 불일치').css('color','red');
			}else if($('#passwd').val() != '' && $('#passwd').val() == $(this).val()){
				$('#message_id').text('비밀번호 일치').css('color','blue');
			}
		});
		
		$('#UpdatePasswdForm').submit(function() {
			if($('#now_passwd').val().trim()==''){
				alert('현재 비밀번호를 입력하세요!');
				$('#now_passwd').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('변경할 비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#confirm_passwd').val().trim()==''){
				alert('변경할 비밀번호 확인을 입력하세요!');
				$('#confirm_passwd').val('').focus();
				return false;
			}
			if($('#passwd').val() != $('#confirm_passwd').val()){
				$('#message_id').text('비밀번호 불일치').css('color','red');
				return false;
			}
		});
		
	});
</script> 
<!-- 중앙 내용 시작 -->
<div class = "container-fluid" style = "width:500px; font-family: 'Gowun Dodum', sans-serif; ">
	<div align = "left">
			<h3>비밀번호 변경</h3>
	</div>
	<div class="text-center col-sm-12 my-5">
	<form:form id="UpdatePasswdForm" action="memberPasswdUpdate.do" modelAttribute="memberVO">
		
		<div class = "form-group row">	
				<label for="now_passwd">현재 비밀번호&nbsp;</label>
				<form:password path="now_passwd" placeholder="4~12 영문,숫자만 허용"/>
				<form:errors path="now_passwd" cssClass="error-color"/>
		</div>	
		<div class = "form-group row">	
				<label for="passwd">변경 비밀번호&nbsp;</label>
				<form:password path="passwd" placeholder="4~12 영문,숫자만 허용"/>
				<form:errors path="passwd" cssClass="error-color"/>
		</div>	
		<div class = "form-group row">	
				<label for="confirm_passwd">비밀번호 확인&nbsp;</label>
				<input type="password" id="confirm_passwd" placeholder="4~12자 영문,숫자만 허용"/>
				<span id="message_id" class="error-color"></span>
		</div>	
			<div class = "form-group row">
				<div class = "text-center col-sm-10">
					<form:button class = "btn btn-outline-dark">전송</form:button>
					<input class = "btn btn-outline-dark" type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</div>
			</div>
	</form:form>
	
	</div>
</div>
<!-- 중앙 내용 끝 -->