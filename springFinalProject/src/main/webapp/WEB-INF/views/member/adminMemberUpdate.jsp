<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
 * 작성일 : 2021. 10. 19.
 * 작성자 : 오상준
 * 설명 : 관리자 - 회원정보 수정!!
 * 수정일 : 
--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"><script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<div class = "container-fluid" style = "width:700px; border: 1px solid #737373">
	<div align = "left">
			<h4>관리자 - 회원 정보수정</h4>
	</div>
	<div class="text-center col-sm-12 my-5">
	<form:form id="adminMemberUpdate_form" action="adminMemberUpdate.do" modelAttribute="memberVO" enctype="multipart/form-data">
		<form:hidden path="mem_num"/>
		<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="mem_name">이름</label>
				<form:input path="mem_name"/>
				<form:errors path="mem_name" cssClass="error-color"/>
		</div>
		<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="email">이메일</label>
				<form:input path="email" readonly="true"/>
				<span style="font-weight: bold; color: red;">변경 불가능</span>
		</div>
		<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="nickname">별명</label>
				<form:input path="nickname"/>
				<form:errors path="nickname" cssClass="error-color"/>
		</div>
		<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="phone">전화번호</label>
				<form:input path="phone"/>
				<form:errors path="phone" cssClass="error-color"/>
		</div>
		<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="zipcope">우편번호</label>
				<form:input path="zipcode" maxlength="5" readonly="true"/>
				<span style="font-weight: bold; color: red;">변경 불가능</span>
			</div>
			<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="address1">주소</label>
				<form:input path="address1" readonly="true"/>
				<span style="font-weight: bold; color: red;">변경 불가능</span>
			</div>
			<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="address2">나머지주소</label>
				<form:input path="address2" maxlength="30" placeholder="상세주소" readonly="true"/>
				<span style="font-weight: bold; color: red;">변경 불가능</span>
			</div>
			<div class = "form-group row">
				<label class = "col-sm-2 col-form-label" for="profile">프로필 사진</label>
				<c:if test="${empty memberVO.profile_filename}">
				<img src="${pageContext.request.contextPath}/resources/images/basic.jpg" width="150" height="150" class="my-photo">
				</c:if>
				<c:if test="${!empty memberVO.profile_filename}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="150" height="150" class="my-photo">
				</c:if>
				<div class="align-center" style="padding-left: 10em;">
					<input type="button" class = "btn btn-outline-dark" value="수정" id="photo_btn">
				</div>
				<br><br>
				<div id="photo_choice" style="display:none;">
					<input type="file" class = "btn btn-outline-dark" id="upload" accept="image/gif,image/png,image/jpeg">
					<input type="button" class = "btn btn-outline-dark" value="전송" id="photo_submit">
					<input type="button" class = "btn btn-outline-dark" value="취소" id="photo_reset">
				</div>
				<%-- 이미지 처리 자바스크립트 부분 --%>
				<script type="text/javascript">
					$(function(){
						//MyPage 프로필 사진 수정
						$('#photo_btn').click(function(){
							$('#photo_choice').show();
							$(this).hide();
						});
						
						// 회원정보 저장
						var mem_num = $("#mem_num").val();
						
						//파일 미리 보기
						var photo_path;
						var my_photo;
						$('#upload').change(function(){	// 업로드 파일이 바뀌면
							var upload = document.getElementById('upload');
							my_photo = upload.files[0];
							if(my_photo){
								var reader = new FileReader();
								
								reader.readAsDataURL(my_photo);
								
								reader.onload = function(){
									photo_path = $('.my-photo').attr('src');
									$('.my-photo').attr('src',reader.result);
								};
							}
						});
						
						//ajax를 이용한 파일 전송
						$('#photo_submit').click(function(){
							if($('#upload').val()==''){
								alert('파일을 선택하세요!');
								$('#upload').focus();
								return;
							}
							
							//파일 전송
							var form_data = new FormData();
							form_data.append('upload',my_photo);
							form_data.append('mem_num', mem_num);
							$.ajax({
								data:form_data,
								type:'post',
								url:'updateMyPhoto.do',
								dataType:'json',
								cache:false,
								contentType:false,
								enctype:'multipart/form-data',
								processData:false,
								success:function(param){
									if(param.result == 'logout'){
										alert('로그인 후 사용하세요');
									}else if(param.result == 'success'){
										alert('프로필 사진이 수정되었습니다.');
										$('#upload').val('');
										$('#photo_choice').hide();
										$('#photo_btn').show();
									}else{
										alert('파일 전송 오류 발생');
									}
								},
								error:function(){
									alert('네트워크 오류 발생');
								}
							});
							
						});
						
						// 프로필 사진 전송 취소
						$('#photo_reset').click(function() {
							$('.my-photo').attr('src',photo_path);
							$('#upload').val('');
							$('#photo_choice').hide();
							$('#photo_btn').show();
						});
					});
					
				</script>
				</div><br>
				<div class = "form-group row">
					<div class = "text-center col-sm-10">
						<form:button class = "btn btn-outline-dark">회원정보수정</form:button>&nbsp;&nbsp;
						<input type="button" class = "btn btn-outline-dark" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
					</div>
				</div>
		
		
	</form:form>
	</div>
</div>