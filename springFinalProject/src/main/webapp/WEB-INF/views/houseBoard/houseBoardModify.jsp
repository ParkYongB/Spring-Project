<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
 * 작성일 : 2021. 10. 10.
 * 작성자 : 이현지
 * 설명 : 집들이 게시판 글 수정 폼
 * 수정일 : 
--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<style>
	.ck-editor__editable_inline {
		min-height: 250px;
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/uploadAdapter.js"></script>
<script type="text/javascript">
$(function() {
	// 이벤트 연결
	$('#register_form').submit(function() {
		if($('#house_title').val().trim() == '') {
			alert('제목을 입력하세요');
			$('#house_title').val('').focus();
			return false;
		}
				
		if($('#house_area').val() == '') {
			alert('평수를 선택하세요');
			$('#house_area').val('').focus();
			return false;
		}
		
		if($('#house_type').val() == '') {
			alert('주거형태를 선택하세요');
			$('#house_type').val('').focus();
			return false;
		}
		
		if($('#house_style').val() == '') {
			alert('스타일을 선택하세요');
			$('#house_type').val('').focus();
			return false;
		}
		
		if($('#house_space').val() == '') {
			alert('공간을 선택하세요');
			$('#house_type').val('').focus();
			return false;
		}
		
		if($('#house_content').val() == '') {
			alert('내용을 입력하세요');
			$('#house_content').val('').focus();
			return false;
		}
	});
});
</script>
<!-- 중앙 내용 시작 -->
<div class="page-main2">
	<form:form id="register_form" action="update.do" modelAttribute="houseBoardVO" enctype="multipart/form-data">
		<form:hidden path="house_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<!-- 제목 시작 -->
			<li>
				<label for="house_title"></label>
				<form:input path="house_title" class="form-control" placeholder="제목을 입력하세요"/>
				<form:errors path="house_title" cssClass="error-color"/>
			</li>
			<!-- 제목 끝 -->
			<!-- 카테고리 시작 -->
			<li>
				<label for="house_area"></label>
				<select name="house_area" id="house_area" class="form-control">
					<option value="">평수</option>
					<option value="10평 미만" <c:if test="${houseBoardVO.house_area.equals('10평 미만')}">selected</c:if>>10평 미만</option>
					<option value="10평대" <c:if test="${houseBoardVO.house_area.equals('10평대')}">selected</c:if>>10평대</option>
					<option value="20평대" <c:if test="${houseBoardVO.house_area.equals('20평대')}">selected</c:if>>20평대</option>
					<option value="30평대" <c:if test="${houseBoardVO.house_area.equals('30평대')}">selected</c:if>>30평대</option>
					<option value="40평대" <c:if test="${houseBoardVO.house_area.equals('40평대')}">selected</c:if>>40평대</option>
					<option value="50평 이상" <c:if test="${houseBoardVO.house_area.equals('50평 이상')}"> selected </c:if>>50평 이상</option>
				</select>
				<form:errors path="house_area" cssClass="error-color"/>
				<label for="house_type"></label>
				<select name="house_type" id="house_type" class="form-control">
					<option value="">주거형태</option>
					<option value="원룸&오피스텔" <c:if test="${houseBoardVO.house_type.equals('원룸&오피스텔')}">selected</c:if>>원룸&amp;오피스텔</option>
					<option value="아파트" <c:if test="${houseBoardVO.house_type.equals('아파트')}">selected</c:if>>아파트</option>
					<option value="빌라&연립" <c:if test="${houseBoardVO.house_type.equals('빌라&연립')}">selected</c:if>>빌라&amp;연립</option>
					<option value="단독주택" <c:if test="${houseBoardVO.house_type.equals('단독주택')}">selected</c:if>>단독주택</option>
					<option value="사무공간" <c:if test="${houseBoardVO.house_type.equals('사무공간')}">selected</c:if>>사무공간</option>
					<option value="상업공간" <c:if test="${houseBoardVO.house_type.equals('상업공간')}">selected</c:if>>상업공간</option>
				</select>
				<form:errors path="house_type" cssClass="error-color"/>
				<label for="house_style"></label>
				<select name="house_style" id="house_style" class="form-control">
					<option value="">스타일</option>
					<option value="모던" <c:if test="${houseBoardVO.house_style.equals('모던')}">selected</c:if>>모던</option>
					<option value="북유럽" <c:if test="${houseBoardVO.house_style.equals('북유럽')}">selected</c:if>>북유럽</option>
					<option value="빈티지" <c:if test="${houseBoardVO.house_style.equals('빈티지')}">selected</c:if>>빈티지</option>
					<option value="내추럴" <c:if test="${houseBoardVO.house_style.equals('내추럴')}">selected</c:if>>내추럴</option>
					<option value="프로방스&로맨틱" <c:if test="${houseBoardVO.house_style.equals('프로방스&로맨틱')}">selected</c:if>>프로방스&amp;로맨틱</option>
					<option value="클래식&앤틱" <c:if test="${houseBoardVO.house_style.equals('클래식&앤틱')}">selected</c:if>>클래식&amp;앤틱</option>
					<option value="한국&아시아" <c:if test="${houseBoardVO.house_style.equals('한국&아시아')}">selected</c:if>>한국&amp;아시아</option>
					<option value="유니크" <c:if test="${houseBoardVO.house_style.equals('유니크')}">selected</c:if>>유니크</option>
				</select>
				<form:errors path="house_style" cssClass="error-color"/>
				<label for="house_space"></label>
				<select name="house_space" id="house_space" class="form-control">
					<option value="">공간 (필수)</option>
					<option value="원룸" <c:if test="${houseBoardVO.house_space.equals('원룸')}">selected</c:if>>원룸</option>
					<option value="거실" <c:if test="${houseBoardVO.house_space.equals('거실')}">selected</c:if>>거실</option>
					<option value="침실" <c:if test="${houseBoardVO.house_space.equals('침실')}">selected</c:if>>침실</option>
					<option value="주방" <c:if test="${houseBoardVO.house_space.equals('주방')}">selected</c:if>>주방</option>
					<option value="욕실" <c:if test="${houseBoardVO.house_space.equals('욕실')}">selected</c:if>>욕실</option>
					<option value="아이방" <c:if test="${houseBoardVO.house_space.equals('아이방')}">selected</c:if>>아이방</option>
					<option value="드레스룸" <c:if test="${houseBoardVO.house_space.equals('드레스룸')}">selected</c:if>>드레스룸</option>
					<option value="서재&작업실" <c:if test="${houseBoardVO.house_space.equals('서재&작업실')}">selected</c:if>>서재&amp;작업실</option>
					<option value="베란다" <c:if test="${houseBoardVO.house_space.equals('베란다')}">selected</c:if>>베란다</option>
					<option value="사무공간" <c:if test="${houseBoardVO.house_space.equals('사무공간')}">selected</c:if>>사무공간</option>
					<option value="상업공간" <c:if test="${houseBoardVO.house_space.equals('상업공간')}">selected</c:if>>상업공간</option>
					<option value="가구&소품" <c:if test="${houseBoardVO.house_space.equals('가구&소품')}">selected</c:if>>가구&amp;소품</option>
					<option value="현관" <c:if test="${houseBoardVO.house_space.equals('현관')}">selected</c:if>>현관</option>
					<option value="외관&기타" <c:if test="${houseBoardVO.house_space.equals('외관&기타')}">selected</c:if>>외관&amp;기타</option>
				</select>
				<form:errors path="house_space" cssClass="error-color"/>
			</li>
			<!-- 카테고리 끝 -->
			<!-- CKEditor 시작 -->
			<li>
				<label for="house_content"></label>
				<form:textarea path="house_content"/>
				<form:errors path="house_content" cssClass="error-color"/>
				<script>
					function MyCustomUploadAdapterPlugin(editor) {
						editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
							return new UploadAdapter(loader);	
						}
					}
					
					ClassicEditor.create(document.querySelector('#house_content'), {
						extraPlugins:[MyCustomUploadAdapterPlugin]
					}).then(editor => {
						window.editor = editor;
					}).catch(error => {
						console.error(error);
					})
				</script>
			</li>
			<!-- CKEditor 끝 -->
			<!-- 썸네일 시작 -->
			<li>
				<label for="upload">썸네일 파일</label>
				<input type="file" name="upload" id="upload" class = "btn btn-outline-dark" accept="image/gif,image/png,image/jpeg">
				<c:if test="${!empty houseBoardVO.thumbnail_filename}">
					<br>
					<span id="file_detail">(${houseBoardVO.thumbnail_filename}) 파일이 등록되어 있습니다.
					다시 업로드하면 기존 파일은 삭제됩니다.</span>
					<input type="button" class = "btn btn-outline-dark" value="파일삭제" id="file_del">
					<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
					<script type="text/javascript">
						$(function() {
							$('#file_del').click(function() {
								var choice = confirm('삭제하시겠습니까?');
								if(choice) { // true
									$.ajax({
										data: {house_num: ${houseBoardVO.house_num}},
										type: 'post',
										url: 'deleteFile.do',
										dataType: 'json',
										cache: false,
										timeout: 30000,
										success: function(param) {
											if(param.result == 'logout') {
												alert('로그인 후 사용하세요');
											}else if(param.result == 'success') {
												$('#file_detail').hide();
											}else {
												alert('파일삭제 오류🤯');
											}
										},
										error : function(request,status,error) { // 에러메시지 반환
											alert("🤯 code = " + request.status + " message = " + request.responseText + " error = " + error);
										}
									});
								}
							});
						});
					</script>
				</c:if>
			</li>
			<!-- 썸네일 끝 -->
		</ul>
		<!-- 버튼 시작 -->
		<div class="align-center">
			<form:button class="btn btn-outline-dark">수정</form:button>
			<input type="button" class="btn btn-outline-dark" value="목록" onclick="location.href='list.do'">	
		</div>
		<!-- 버튼 끝 -->
	</form:form>
</div>
<!-- 중앙 내용 끝 -->
