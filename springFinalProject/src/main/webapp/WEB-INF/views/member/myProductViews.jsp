<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
 * 작성일 : 2021. 10. 21.
 * 작성자 : 오상준
 * 설명 : 
 * 수정일 : 
--%>
<%@ include file="/WEB-INF/views/member/common/myPageHeader.jsp" %>
<div style="display: inline-block;" align = "right">
	<h2 class="admin-page-h2">내가 등록한 상품 ( ${count} )</h2>
</div>
<div class = "content col-10 col-md-12">
				<div class = "row" style="padding-top: 0px; padding-bottom: 50px; transform: translateY(0px);">
					<%-- 등록된 상품이 없는 경우 --%>
					<c:if test="${count == 0}">
						<div class="result-display">
							등록된 상품이 없습니다.
						</div>
					</c:if>			
					<%-- 등록된 상품이 있는 경우 --%>
					<c:if test="${count > 0}">
						<c:forEach var = "storeVO" items = "${list}">
						<div class = "col-8 col-md-3">
							<article id = "product" class = "product-item">
								<div id = "page_font" class = "align-center">
									<c:if test = "${empty storeVO.thumbnail_filename}">
										<!-- 썸네일 이미지가 존재하지 않을 경우 -->
										<a class = "item-overlay" href = "${pageContext.request.contextPath}/store/storeDetail.do?prod_num=${storeVO.prod_num}">
										<img src = "${pageContext.request.contextPath}/resources/images/gift.png" style = "width:240px; height:240px; max-width:240px; max-height:240px;"><br>
											[${storeVO.buis_name}]
											${storeVO.prod_name}<br>
											#${storeVO.prod_keyword}<br>
											${storeVO.prod_price}원
										</a>
									</c:if>
									<c:if test = "${!empty storeVO.thumbnail_filename}">
										<!-- 썸네일 이미지가 존재 -->
										<a class = "item-overlay" href = "storeDetail.do?prod_num=${storeVO.prod_num}">
										<img src = "${pageContext.request.contextPath}/store/imageView.do?prod_num=${storeVO.prod_num}" style = "width:240px; height:240px; max-width:240px; max-height:240px;"><br>
										[${storeVO.buis_name}]
										${storeVO.prod_name}<br>
										#${storeVO.prod_keyword}<br>
										${storeVO.prod_price}원
										</a>
									</c:if>	
								</div>
							</article>
						</div>
						</c:forEach>
					</c:if>
				</div>
				<div class = "align-center">${pagingHtml}</div>
			</div>