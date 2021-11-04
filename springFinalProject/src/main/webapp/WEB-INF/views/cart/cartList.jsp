<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ include file="/WEB-INF/views/member/common/myPageHeader.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<style>
	.cart-container {
		width : 1136px;
		margin : 0 auto;
	}
	.link-item {
		margin : 0 auto;
		width : 100%;
	}
	.container {
		margin : 0 auto;
		width : 1138px;
	}
	.title {
		font-size : 18px;
		weight : 800;
		padding : 10px 10px 10px 10px;
		text-align : left;
		display : flex;
	}
	.reg_date {
		font-size : 15px;
		padding : 0px 10px 20px 10px;
		text-align : right;
	}
	.paging {
		text-align : center;
		padding : 50px;
		margin-top : 40px;
	}
	h2 {
		text-align : left;
		margin : 70px 0px 50px 30px;
	}
	.write-button {
		text-align: right;
		margin : 40px 100px 0px 30px;
	}
</style>
<script type="text/javascript">
	$(function() {
		$('#modify_btn').on("click", function() {
			var check = confirm('장바구니 수량을 수정하시겠습니까?');
			
			if($('#cart_quan').val().trim() == '') {
				alert('수량을 입력하세요!');
				$('#cart_quan').focus();
				$('#cart_quan').val('');
				return false;
			}
			
			if(check) {
				return;
			}else {
				return false;
			}
		});
	});
</script>
<h2>장바구니</h2>
<div class="container">
 	<div class = "cart-container">
 		<c:if test = "${cart.count == 0}">
			<div class="container mt-5">
	 			<div class="link-item mb-5 mb-5" style = "text-align:center;">장바구니가 비어 있습니다.</div>
	 		</div>
 		</c:if>
 		<c:if test = "${cart.count > 0}">
		<form:form id = "cartUpdate" action="cartUpdate.do">
			<table class = "table table-bordered">
				<tr>
					<th scope = "col" class = "align-center">상품 정보</th>
					<th scope = "col" class = "align-center">수량</th>
					<th scope = "col" class = "align-center">가격</th>
					<th scope = "col" class = "align-center">배송</th>
					<th scope = "col" class = "align-center">주문 금액</th>
					<th scope = "col" class = "align-center">주문 관리</th>
				</tr>
				<c:forEach var = "prod" items="${cart.list}">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/store/storeDetail.do?prod_num=${prod.prod_num}">
						<c:if test = "${empty prod.thumbnail_filename}">
							<img src = "${pageContext.request.contextPath}/resources/images/gift.png" style = "width:60px; height:60px; max-width:60px; max-height:60px;">				
						</c:if>
						<c:if test = "${!empty prod.thumbnail_filename}">
							<img src = "imageView.do?prod_num=${prod.prod_num}" style = "width:60px; height:60px; max-width:60px; max-height:60px;">
						</c:if>
						<span style = "font-weight : bold;">[${prod.buis_name}]</span>
						<input type = "hidden" id = "buis_name" name = "buis_name" value = "${prod.buis_name}">
						${prod.prod_name}
						<input type = "hidden" id = "prod_name" name = "prod_name" value = "${prod.prod_name}">		
						</a>
					</td>
					<td class = "align-center">
						<input type="number" style="width: 50px; height: 34px;". id = "cart_quan" name="cart_quan" value="${prod.cart_quan}" min="1">
					</td>
					<td class = "align-center">
						<fmt:formatNumber pattern="###,###,###" value="${prod.prod_price}"/>원
					</td>
					<td class = "align-center">
						${prod.delive_type}
						<input type = "hidden" id = "delive_type" name = "delive_type" value = "${prod.delive_type}">
						<br>
						<fmt:formatNumber pattern="###,###,###" value="${prod.delive_price}"/>원
						<input type = "hidden" id = "delive_price" name = "delive_price" value = "${prod.delive_price}">
					</td>
					<td class = "align-center">
						<fmt:formatNumber pattern="###,###,###" value="${prod.money}"/>원
						<input type = "hidden" id = "money" name = "money" value = "${prod.money}">
					</td>
					<td class = "align-center">
						<a href="${pageContext.request.contextPath}/cart/cartDelete.do?cart_num=${prod.cart_num}" class="btn btn-outline-dark">삭제</a>
					</td>
				</tr>
	            <input type="hidden" name="prod_num" value="${prod.prod_num}">
	 		</c:forEach>
			</table>
	        <input type="submit" id = "modify_btn" class="btn btn-outline-dark" value="수정">
		</form:form>
		</c:if>
	</div>
</div>
