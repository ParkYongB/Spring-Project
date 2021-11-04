<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
 * 작성일 : 2021. 10. 22.
 * 작성자 : 오상준
 * 설명 : 통합검색 출력
 * 수정일 : 
--%>
<!-- 중앙 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style>
.card {
	border:none;
}

.count {
	margin-top:15px;
	margin-bottom:25px;
}

ul {
	margin-right:5px;
}

</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<div class="houseBoard-list">
<div class="container">
	<!-- 집들이 게시물 출력 시작 -->
	<!-- 등록된 게시물이 없을 경우 -->
	<c:if test="${count == 0}">
	<div class="result-display">
		등록된 게시물이 없습니다.
	</div>
	</c:if>
	<!-- 등록된 게시물이 있을 경우 -->
	<c:if test="${houseCount > 0}">
	<b>[집들이]</b><br>
	<div class="count">
		총 <b style="color:#8c996b">${houseCount}</b>개의 글
	</div>
	<%-- 반복문 시작 --%>
	<div class="container">
	<div class="row mb-5 mr-7">
	<c:forEach var="houseBoard" items="${houseList}">
		<div class="col-3">
			<div class="card" style="width:255px; height:450px; text-align:center;">
				<div>
					<div style="float:left; cursor:pointer;" onclick="location.href='${pageContext.request.contextPath}/houseBoard/detail.do?house_num=${houseBoard.house_num}'">
						<%-- 회원 프로필 사진이 없는 경우 --%>
						<c:if test="${empty houseBoard.profile_filename}">
							<img src="${pageContext.request.contextPath }/resources/images/basic.jpg" style="width:33px; height:33px; margin-bottom:15px;" class="my-photo">
						</c:if>
						<%-- 회원 프로필 사진이 있는 경우 --%>
						<c:if test="${!empty houseBoard.profile_filename}">
							<img src="${pageContext.request.contextPath}/houseBoard/boardProfile.do?mem_num=${houseBoard.mem_num}" style="width:33px; height:33px; margin-bottom:15px;" class="my-photo">
						</c:if>
						<span style="font-size:16px;">&nbsp;${houseBoard.nickname}</span>
					</div>
				</div>
				<div class="imgTransition" style="cursor:pointer; overflow:hidden;" onclick="location.href='${pageContext.request.contextPath}/houseBoard/detail.do?house_num=${houseBoard.house_num}'">
					<%-- 사진파일이 없는 경우 --%>
					<c:if test="${empty houseBoard.thumbnail_filename}">
						<figure class="embed-responsive embed-responsive-1by1">
		            	<img class="card-img-top embed-responsive-item" id="thumbnail" src="${pageContext.request.contextPath}/resources/images/basic_house.png" style="width:100%; border-radius:1%;"/>
						</figure>
					</c:if>
					<%-- 사진파일이 있는 경우 --%>
					<c:if test="${!empty houseBoard.thumbnail_filename}">
		            	<figure class="embed-responsive embed-responsive-1by1">
		            	<img class="card-img-top embed-responsive-item" id="thumbnail" src="${pageContext.request.contextPath}/houseBoard/imageView.do?house_num=${houseBoard.house_num}" style="width:100%; border-radius:1%;"/>
		            	</figure>
		            </c:if>
	            	<div class="card-body" align="center">
	            	</div>
	            	<div class="card-title" align="center">
						<h5><a href="${pageContext.request.contextPath}/houseBoard/detail.do?house_num=${houseBoard.house_num}" class="btn btn-outline-dark">${houseBoard.house_title}</a></h5>
					</div>
				</div>
			</div>&nbsp;
		</div>
	</c:forEach>
	<%-- 반복문 끝 --%>
	</div>
	</div>
	</c:if>
	<div class="paging" align="center">	
		<span>${housePagingHtml}</span>
	</div>
</div>
</div>
	<!-- 집들이 게시물 출력 끝 -->
<hr size="1" width="100%" style="color:#bfbfbf; noshade;"><br>


<div class="event-list">
<div class="container">
	<!-- 이벤트 게시물 출력 시작 -->
	<!-- 등록된 게시물이 없을 경우 -->
	<c:if test="${eventCount == 0}">
	<div class="result-display">
		등록된 게시물이 없습니다.
	</div>
	</c:if>
	<!-- 등록된 게시물이 있을 경우 -->
	<c:if test="${eventCount > 0}">
	<b>[이벤트]</b><br>
	<div class="count">
		총 <b style="color:#8c996b">${eventCount}</b>개의 글
	</div>
	<%-- 반복문 시작 --%>
	<div class="container">
	<div class="row mb-5 mr-7">
	<c:forEach var="event" items="${eventList}">
		<div class="col-3">
			<div class="card" style="width:255px; height:450px; text-align:center;">
				<div class="imgTransition" style="cursor:pointer; overflow:hidden;" onclick="location.href='${pageContext.request.contextPath}/event/eventDetail.do?event_num=${event.event_num}'">
					<%-- 사진파일이 없는 경우 --%>
					<c:if test="${empty event.event_filename}">
						<figure class="embed-responsive embed-responsive-1by1">
		            	<img class="card-img-top embed-responsive-item" id="thumbnail" src="${pageContext.request.contextPath}/resources/images/basic_house.png" style="width:100%; border-radius:1%;"/>
						</figure>
					</c:if>
					<%-- 사진파일이 있는 경우 --%>
					<c:if test="${!empty event.event_filename}">
		            	<figure class="embed-responsive embed-responsive-1by1">
		            	<img class="card-img-top embed-responsive-item" id="thumbnail" src="${pageContext.request.contextPath}/event/eventPhotoView.do?event_num=${event.event_num}" style="width:100%; border-radius:1%;"/>
		            	</figure>
		            </c:if>
	            	<div class="card-title" align="center">
						<h5><a href="${pageContext.request.contextPath}/event/eventDetail.do?event_num=${event.event_num}" class="btn btn-outline-dark">${event.event_title}</a></h5>
					</div>
				</div>
			</div>&nbsp;
		</div>
	</c:forEach>
	<%-- 반복문 끝 --%>
	</div>
	</div>
	</c:if>
	<div class="paging" align="center">	
		<span>${eventPagingHtml}</span>
	</div>
</div>
</div>
<hr size="1" width="100%" style="color:#bfbfbf; noshade;"><br>
<!-- 이벤트 게시물 출력 끝 -->
	
	
<div class="store-list">
<div class="container">
	<!-- 스토어 게시물 출력 시작 -->
	<!-- 등록된 게시물이 없을 경우 -->
	<c:if test="${eventCount == 0}">
	<div class="result-display">
		등록된 게시물이 없습니다.
	</div>
	</c:if>
	<!-- 등록된 게시물이 있을 경우 -->
	<c:if test="${productCount > 0}">
	<b>[스토어]</b><br>
	<div class="count">
		 총 <b style="color:#8c996b">${productCount}</b>개의 글
	</div>
	<%-- 반복문 시작 --%>
	<div class="container">
	<div class="row mb-5 mr-7">
	<c:forEach var="storeVO" items="${productList}">
		<div class="col-3">
			<div class="card" style="width:255px; height:450px; text-align:center;">
				<div class="imgTransition" style="cursor:pointer; overflow:hidden;" onclick="location.href='${pageContext.request.contextPath}/store/storeDetail.do?prod_num=${storeVO.prod_num}'">
					<%-- 사진파일이 없는 경우 --%>
					<c:if test="${empty storeVO.thumbnail_filename}">
						<figure class="embed-responsive embed-responsive-1by1">
		            	<img class="card-img-top embed-responsive-item" id="thumbnail" src="${pageContext.request.contextPath}/resources/images/gift.png" style="width:100%; border-radius:1%;"/>
						</figure>
					</c:if>
					<%-- 사진파일이 있는 경우 --%>
					<c:if test="${!empty storeVO.thumbnail_filename}">
		            	<figure class="embed-responsive embed-responsive-1by1">
		            	<img class="card-img-top embed-responsive-item" id="thumbnail" src="${pageContext.request.contextPath}/store/imageView.do?prod_num=${storeVO.prod_num}" style="width:100%; border-radius:1%;"/>
		            	</figure>
		            </c:if>
	            	<div class="card-body" align="center">
	            	</div>
	            	<div class="card-title" align="center">
						<h5><a href="${pageContext.request.contextPath}/store/storeDetail.do?prod_num=${storeVO.prod_num}" class="btn btn-outline-dark">${storeVO.prod_name} / <fmt:formatNumber pattern="###,###,###" value="${storeVO.prod_price}"/>원</a></h5>
					</div>
				</div>
			</div>&nbsp;
		</div>
	</c:forEach>
	<%-- 반복문 끝 --%>
	</div>
	</div>
	</c:if>
	<div class="paging" align="center">	
		<span>${productPagingHtml}</span>
	</div>
</div> 
</div>
<!-- 스토어 게시물 출력 끝 -->
