<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
 * 작성일 : 2021. 10. 18.
 * 작성자 : 오상준
 * 설명 : 전체회원조회
 * 수정일 : 
--%>
<%@ include file="/WEB-INF/views/member/common/myPageHeader.jsp" %>
<script type ="text/javascript">
	$(document).ready(function() {
		// 검색 유효성 체크
		$("#search_form").submit(function(){
			if($("#keyword").val().trim() == ""){
				alert("검색어를 입력하세요!!");
				$("#keyword").val("").focus();
				return false;	// 서밋은 false 클릭은 ;
			}
		});
		
		$('input[type=checkbox]:checked').prop("checked", false);
		
		$('#stop_btn').click(function() {		// 회원정지 클릭시
		   	var output = '';
		   
			$('input[type=checkbox]:checked').each(function(index,item){
			    if(index !=0 ){
			    	output += ',';
			    }
					output += $(this).val();
			  	});
			
			  	var cnt = 0;
			  	cnt = output;
		      	if(cnt == 0){
		         	 alert("선택한 회원이 없습니다!");
		         	 return false;
		      	}else{
		    		if(confirm("선택한 회원의 권한을 변경 하시겠습니까?")){
			    
						$.ajax({
							url: 'stopAdminMember.do',
						   	type: 'post',
						   	data: {output : output},
						   	dataType : 'json',
							cache : false,
							timeout : 30000,
						    success: function(param) {
						    	alert('변경 완료!');
						    	location.reload();
						    	$('input[type=checkbox]:checked').prop("checked", false);
						    },
						    error : function() {
						    	alert('네트워크 오류!');
						    }
						});
		    		}else {
		    			$('input[type=checkbox]:checked').prop("checked", false);
		    		}
		    	}
		});	// 회원정지 끝
		
		$('#up_btn').click(function() {	// 일반회원 등급변경 클릭시
		   	var output = '';
		   
			$('input[type=checkbox]:checked').each(function(index,item){
			    if(index !=0 ){
			    	output += ',';
			    }
					output += $(this).val();
			  	});
			    
				var cnt = 0;
			  	cnt = output;
		      	if(cnt == 0){
		         	 alert("선택한 회원이 없습니다!");
		         	 return false;
		      	}else{
		    		if(confirm("선택한 회원의 권한을 변경 하시겠습니까?")){
			   
						$.ajax({
							url: 'upAdminMember.do',
						   	type: 'post',
						   	data: {output : output},
						   	dataType : 'json',
							cache : false,
							timeout : 30000,
						    success: function(param) {
						    	alert('변경 완료!');
						    	location.reload();
						    	$('input[type=checkbox]:checked').prop("checked", false);
						    },
						    error : function() {
						    	alert('네트워크 오류!');
						    }
						});
		    		}else {
		    			$('input[type=checkbox]:checked').prop("checked", false);
		    		}
		      	}
		}); //일반회원 등급변경 끝
		
		$('#seller_btn').click(function() {		// 판매자 회원 변경 클릭시
		   	var output = '';
		   
			$('input[type=checkbox]:checked').each(function(index,item){
			    if(index !=0 ){
			    	output += ',';
			    }
					output += $(this).val();
			  	});
			    
				var cnt = 0;
			  	cnt = output;
		      	if(cnt == 0){
		         	 alert("선택한 회원이 없습니다!");
		         	 return false;
		      	}else{
		    		if(confirm("선택한 회원의 권한을 변경 하시겠습니까?")){
			   
						$.ajax({
							url: 'upSellerMember.do',
						   	type: 'post',
						   	data: {output : output},
						   	dataType : 'json',
							cache : false,
							timeout : 30000,
						    success: function(param) {
						    	alert('변경 완료!');
						    	location.reload();
						    	$('input[type=checkbox]:checked').prop("checked", false);
						    },
						    error : function() {
						    	alert('네트워크 오류!');
						    }
						});
		    		}else {
		    			$('input[type=checkbox]:checked').prop("checked", false);
		    		}
		      	}
		});		// 판매자 회원 변경 끝
		
		$('#delete_btn').click(function() {			// 회원탈퇴 클릭시
		   	var output = '';
		   
			$('input[type=checkbox]:checked').each(function(index,item){
			    if(index !=0 ){
			    	output += ',';
			    }
					output += $(this).val();
			  	});
				var cnt = 0;
		  		cnt = output;
	      		if(cnt == 0){
	         		alert("선택한 회원이 없습니다!");
	         		return false;
	      		}else{
	    			if(confirm("선택한 회원의 권한을 변경 하시겠습니까?")){
		   
						$.ajax({
							url: 'deleteAdminMember.do',
						   	type: 'post',
						   	data: {output : output},
						   	dataType : 'json',
							cache : false,
							timeout : 30000,
						    success: function(param) {
						    	alert('변경 완료!');
						    	location.reload();
						    	$('input[type=checkbox]:checked').prop("checked", false);
						    },
						    error : function() {
						    	alert('네트워크 오류!');
						    }
						});
	    			}else {
	    				$('input[type=checkbox]:checked').prop("checked", false);
	    			}
	    		}
		});	// 회원탈퇴 끝
		
		$('#resetPasswd_btn').click(function() {			// 비밀번호 초기화 클릭시
		   	var output = '';
		   
			$('input[type=checkbox]:checked').each(function(index,item){
			    if(index !=0 ){
			    	output += ',';
			    }
					output += $(this).val();
			  	});
				var cnt = 0;
		  		cnt = output;
	      		if(cnt == 0){
	         		alert("선택한 회원이 없습니다!");
	         		return false;
	      		}else{
	    			if(confirm("선택한 회원의 비밀번호를 변경 하시겠습니까?")){
		   
						$.ajax({
							url: 'resetPasswdMember.do',
						   	type: 'post',
						   	data: {output : output},
						   	dataType : 'json',
							cache : false,
							timeout : 30000,
						    success: function(param) {
						    	alert('비밀번호 초기화 완료!');
						    	location.reload();
						    	$('input[type=checkbox]:checked').prop("checked", false);
						    },
						    error : function() {
						    	alert('네트워크 오류!');
						    }
						});
	    			}else {
	    				$('input[type=checkbox]:checked').prop("checked", false);
	    			}
	    		}
		});	// 비밀번호 초기화 끝
		
		// 쿠폰 전체 리스트 팝업창 띄우기  couponView_btn
		function openPopup() {
			  var popup = window.open('../member/aminCouponAllView.do', "쿠폰전체조회", "width=700px,height=800px,scrollbars=yes");
			  return popup;
		}
		
		$('#couponView_btn').click(function() {			// 쿠폰 전체조회 클릭
			openPopup();	
		});	// 쿠폰 전체조회 클릭 끝
		
		$('#coupon_btn').click(function() {			
		   	var output = '';
		   	var cou_num = prompt("배정할 쿠폰번호를 입력해주세요");
		    
			
			$('input[type=checkbox]:checked').each(function(index,item){
			    if(index !=0 ){
			    	output += ',';
			    }
					output += $(this).val();
			  	});
				var cnt = 0;
		  		cnt = output;
	      		if(cnt == 0){
	         		alert("선택한 회원이 없습니다!");
	         		return false;
	      		}else{
	      			
	    			if(confirm("선택한 회원에게 쿠폰을 배정 하시겠습니까?")){
		   
						$.ajax({
							url: 'memberCouponRegister.do',
						   	type: 'post',
						   	data: {
						   		  output : output,
						   		  coupondetail_num : cou_num
						   		  },
						   	dataType : 'json',
							cache : false,
							timeout : 30000,
						    success: function(param) {
						    	alert('쿠폰배정 완료!');
						    	location.reload();
						    	$('input[type=checkbox]:checked').prop("checked", false);
						    },
						    error : function(request,status,error){		// 에러메세지 반환
								alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
						    }
						});
	    			}else {
	    				$('input[type=checkbox]:checked').prop("checked", false);
	    			}
	    		}
			
		});	// 쿠폰배정 끝
	});
	
</script>

<c:if test="${!empty user_num && user_auth == 4}">
<div class = "container-fluid contents-wrap" style = "width:95%">
	<div class="text-center col-sm-30 my-5">
		<div align = "left">
			<h2 class="admin-page-h2">관리자 - 전체 회원 조회</h2>
		</div>
	<c:if test = "${count == 0}">
	<div class = "text-center">
		회원 가입한 회원이 없습니다.
	</div>
	</c:if>
	<c:if test = "${count > 0}">
		<div class = "text-right">
			<input type = "button" class = "btn btn-outline-dark" value = "회원관리메인" onclick = "location.href='myPage.do'">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "회원 정지" id = "stop_btn">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "일반회원" id = "up_btn">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "판매자 등급변경" id = "seller_btn">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "회원 탈퇴" id = "delete_btn">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "비밀번호 초기화" id = "resetPasswd_btn">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "쿠폰배정" id = "coupon_btn">&nbsp;
			<input type = "button" class = "btn btn-outline-dark" value = "쿠폰조회" id = "couponView_btn">
		</div>
		<br>
	<table class="table table-sm">
			<tr>
				<th scope="col">회원 번호</th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">전화번호</th>
				<th scope="col">우편번호</th>
				<th scope="col">주소</th>
				<th scope="col">상세주소</th>
				<th scope="col">가입일</th>
				<th scope="col">회원 등급</th>
				<th scope="col">선택</th>
				<th scope="col">수정</th>
			</tr>
			<c:forEach var = "member" items = "${list}">
			<tr>
				<th scope = "row">${member.mem_num}</th>
				<td>${member.mem_id}</td>
				<td>${member.mem_name}</td>
				<td>${member.email}</td>
				<td>${member.phone}</td>
				<td>${member.zipcode}</td>
				<td>${member.address1}</td>
				<td>${member.address2}</td>
				<td>${member.reg_date}</td>
				<td>
					<c:if test="${member.mem_auth == 0}">탈퇴회원</c:if>
				    <c:if test="${member.mem_auth == 1}">정지회원</c:if>
				    <c:if test="${member.mem_auth == 2}">일반회원</c:if>
				    <c:if test="${member.mem_auth == 3}">판매회원</c:if>
				    <c:if test="${member.mem_auth == 4}">관리자</c:if>
				</td>
				<td>
				    <c:if test="${member.mem_auth != 4 && member.mem_auth != 0}">
				    	<input type = "checkbox" aria-label="Checkbox for following text input" value = "${member.mem_num}">
				    </c:if>
				</td>
				<td>
					<c:if test="${member.mem_auth != 4 && member.mem_auth != 0}"><input class = "btn btn-outline-dark" type="button" value="회원수정" onclick="location.href='${pageContext.request.contextPath}/member/adminMemberUpdate.do?mem_num=${member.mem_num}'"></c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div align = "center">
			${pagingHtml}
		</div>
	</c:if>
	</div>
	<div align="center" style="background-color: #f5f5ff; width :100%; height: 100%;">
	<br>  
	<form action="memberList.do" method="get" id="search_form">
	<div class=" col-lg-5">
		<input style="float: right;  position: relative; right: 84px;  bottom : 1px; height: 36px;" type="submit" class = "btn btn-outline-dark" value="검색">
		<div class=" col-lg-6">
		<input class="form-control input-lg" type="search" name="keyword" id = "keyword" style="height: 30px;" placeholder="검색어를 입력하세요."><br>
		<div class=" col-sm-7">
				<select style="position: relative; right: 185px; bottom : 55px; height: 36px;" class="form-control input-sm" name="keyfield">
					<option value="1">아이디</option>
					<option value="2">이름</option>
				</select>
		</div>
		</div>
	</div>
	<input style="float: right; position: relative; right: 17px; bottom : 55px;" type="button" class = "btn btn-outline-dark" value="목록" onclick="location.href='${pageContext.request.contextPath}/member/memberList.do'">
	</form>
	</div>
</div>
	
</c:if>



<%-- 비정상적인 접근 --%>
<c:if test="${empty user_num || user_auth != 4}">
	<h1>관리자만 접근할 수 있습니다.</h1>
	<script type="text/javascript">
	$(function(){
		alert("관리자만 접근할 수 있습니다.");
		location.href="${pageContext.request.contextPath}/main/main.do";
	});
	</script>
</c:if>
