<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>

<!-- 메인 시작 -->
<!-- main 스타일 적용 -->
<style>

.main {
	display: flex;
	justify-content: center;
	align-item: center;
}

.main-container {
	display: grid;
	grid-template-columns: repeat(4, 5fr);
	grid-auto-rows: minmax(180px, auto);
	grid-row-gap: 4 rem;
	grid-column-gap: 2rem;
}

.main-item:nth-child(1) {
	grid-column: 1/4;
	grid-row: 1/4;
}

.main-item:nth-child(2) {
	grid-column: 4/5;
	grid-row: 1/4;
}

.main-item:nth-child(3) {
	grid-column: 1/5;
	grid-row: 3/5;
}
.main-item:nth-child(4) {
	grid-column: 1/4;
	grid-row: 5/7;
}

.main-item:nth-child(5) {
	grid-column: 4/5;
	grid-row: 5/7;
}

.main-item:nth-child(6) {
	grid-column: 1/5;
	grid-row: 7/9;
}

.main-item:nth-child(7) {
	grid-column: 1/5;
	grid-row: 9/11;
}
.main-story{
	display:flex;
	flex-direction:column;
	align-items: stretch;
}
.main-story-link{
	height: 90px;
	width:397px;
	border : 1px solid white;
	background-color:rgb(250,250,250);
	border-radius:10px;
}
.description{
	padding : 25px 0px 0px 25px;
	font-weight : 700;
	font-color: gray;
	font-size: 9px;
}
.title{
	padding : 10px 0px 0px 25px;
	font-weight : 700;
	font-color: gray;
	font-size: 13px;
}
.rankfeedli{
display:inline;
}
.rankfeedul{
	text-align:center;
}
.rankfeed{
position:sticky;
}
.rankitemul{
float::inline-block ;
display:flex;
text-align:center;
width: 300px;
height: 280px;
background-color:black;
}
.rankcontent{
width: 1083px;
height : 150px;
text-align : center;
}

/* 베스트아이템 */
.bestitemli {
	display: flex;
	flex-direction: column;
	margin-bottom: 2rem;
}
.bestimage {
	height: 0;
	padding-bottom: 60%;
	background-color: white;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}
.bestimage img {
	display: none;
}
.bestdesc {
	flex: 1 1 auto;
	padding: 5px;
	background: white;
}

@media (min-width: 600px) {
	.bestitemul {
		display: flex;
		flex-wrap: wrap;
		margin: 0 -1rem;
	}
	.bestitemli {
		width: 50%;
		padding: 0 1rem;
	}
}

@media (min-width: 1200px) {
	.bestitemli {
		width: 25%;
	}
	.carousel slide{
 	visibility : hidden ;  
	}
}

/* 인기사진 */
.popularli {
	display: flex;
	flex-direction: column;
	margin-bottom: 2rem;
}
.popularimage {
	height: 0;
	padding-bottom: 60%;
	background-color: white;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}
.popularimage img {
	display: none;
}
.populardesc {
	flex: 1 1 auto;
	padding: 5px;
	background: white;
}

@media (min-width: 600px) {
	.popularul {
		display: flex;
		flex-wrap: wrap;
		margin: 0 -1rem;
	}
	.popularli {
		width: 50%;
		padding: 0 1rem;
	}
}

@media (min-width: 600px) {
	.categoryul {
		display: flex;
		flex-wrap: wrap;
		margin: 0 -1rem;
	}
	.categoryli {
		width: 50%;
		padding: 0 -1rem;
	}
}

@media (min-width: 1200px) {
	.popularli {
		width: 25%;
	}
}
/*인기사진 끝*/
/*매일의 스토리 */
.storyli {
	display: flex;
	flex-direction: column;
	margin-bottom: 2rem;
}
.storyimage {
	height: 0;
	padding-bottom: 60%;
	background-color: white;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}
.storyimage img {
	display: none;
}
.storydesc {
	flex: 1 1 auto;
	padding: 5px;
	background: white;
}

@media (min-width: 600px) {
	.storyul {
		display: flex;
		flex-wrap: wrap;
		margin: 0 -1rem;
	}
	.storyli {
		width: 50%;
		padding: 0 1rem;
	}
}

@media (min-width: 1200px) {
	.storyli {
		width: 33.33333%;
	}
}

	
.cateul {
    float: right;
    position: relative;
    left: -50%;
}
.cateli{
    float: left;
    position: relative;
    left: 50%;
    margin: 0 auto;
}

</style>

<script type="text/javascript">
	$(function(){
		//메뉴클릭시
		$('.rankfeedli').click(function(){
			var activeTab = $(this).attr('value');
			$('li').css('text-color','orange');
			$.getJSON("/main/main.do"+"?activeTab"+activeTab, function(data){
				var str="";
				$(data).each(function(){
					console.log(data);
					str +="<li"
				})
			})
		});
		
	$('.mainimgwrapper').hover(function() {
		  $("#mainimg1").css("transform", "scale(1.2)"),$("#mainimgbutton").css("color","blue");
		}, function(){
		  $("#mainimg1").css("transform", "scale(1.1)"),$("#mainimgbutton").css("color","");
		});
	});
</script>
<!-- 메인 끝 -->
<div class="main">
	<div class="main-container">
<!-- 메인 이미지 박스 -->		
		<div class="main-item">
			<div class="mainimgwrapper" style="height:560px; width:1126x; ">
			<div class="main-img" id="main-img" style="overflow: hidden; cursor: pointer;">
				<img src="${pageContext.request.contextPath}/resources/images/mainimg1.png" id="mainimg1" style="border-radius:1%;  transition: all 0.2s linear;"
				onclick="location.href='${pageContext.request.contextPath}/houseBoard/detail.do?house_num=1'">
			</div>
			<div class="mainimgtext" style="position: relative; bottom:90px; cursor: pointer;">
			<p style="font-size:40px; padding-left: 55px; font-family: 'Gowun Dodum', sans-serif; font-weight:bolder; color:white;">어디든 환하게 밝혀주는 조명</p><button type="button" class="btn btn-light btn-lg" id="mainimgbutton" style="float:right; margin-right:70px; font-family: 'Gowun Dodum', sans-serif; position: relative; bottom:50px;"
			onclick="location.href='${pageContext.request.contextPath}/houseBoard/detail.do?house_num=1'">보러가기</button>
			</div>
			</div>
		</div>
		<div class="main-item">
<!-- 메인 이미지박스 -->
<!-- 이벤트 캐러셀 시작 -->
<div id="carouselExampleControls" class="carousel slide" style="cursor: pointer;" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/event1.png" alt="First slide" style="border-radius:1%;"
      onclick="location.href='${pageContext.request.contextPath}/event/eventDetail.do?event_num=1'">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/event2.png" alt="Second slide" style="border-radius:1%;"
      onclick="location.href='${pageContext.request.contextPath}/event/eventDetail.do?event_num=2'">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/event3.png" alt="Third slide" style="border-radius:1%;"
      onclick="location.href='${pageContext.request.contextPath}/event/eventDetail.do?event_num=3'">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/event4.png" alt="Four slide" style="border-radius:1%;"
      onclick="location.href='${pageContext.request.contextPath}/event/eventDetail.do?event_num=4'">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
<!-- 이벤트 캐러셀끝 -->
<!-- 카테고리 이미지 시작 -->
		<div class="main-item my-5" style="text-align :center; font-size:20px; font-weight:bold;"><br>
			<section class="popular" style="cursor: pointer;">
			<ul class="cateul">
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/onclick1.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do'" style = "width : 240px;"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/onclick2.png"
				onclick="location.href='${pageContext.request.contextPath}/houseBoard/list.do'" style = "width : 240px;"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/onclick3.png"
				onclick="location.href='${pageContext.request.contextPath}/houseBoard/list.do'" style = "width : 240px;"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/onclick4.png"
				onclick="location.href='${pageContext.request.contextPath}/event/eventDetail.do?event_num=2'" style = "width : 240px;"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/onclick5.png"
				onclick="location.href='${pageContext.request.contextPath}/qna/qnaList.do'" style = "width : 240px;"></a></li>
			</ul>
			</section>
		</div>
<!-- 카테고리 이미지 끝 -->		
<!-- 사진 게시판 최신순 미리보기 시작 -->
		<div class="main-item"style="text-align :center;"><p class="mb-2" style="text-align:left; margin-left:70px; font-size:17px; font-weight:bold;">매일의 스토리</p>
			<section class="everydaystory">
			<ul class="storyul">
	  	  	<c:forEach var="houseBoard" items="${ HList }">
				<li class="card mx-2">
					<div align="center" style="cursor: pointer;"  onclick="location.href='${pageContext.request.contextPath}/houseBoard/detail.do?house_num=${houseBoard.house_num}'">
						<figure class="storyimage" style="height: 200px; width :250px;">
					<c:if test="${ empty houseBoard.thumbnail_filename }">
		            	<img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/basic.jpg" style="border-radius:1%; width:300px; height:206px;"/>
		            </c:if>
		            <!-- 사진파일이 있는 경우 -->
		            <c:if test="${ !empty houseBoard.thumbnail_filename }">
			            <img class="d-block w-100" src="${pageContext.request.contextPath}/houseBoard/imageView.do?house_num=${houseBoard.house_num}" style="border-radius:1% width:300px; height:206px;" >
		            </c:if>
						</figure>
						<div class="storydesc">
							<p>${houseBoard.nickname}</p>
						</div>
	        		</div>
				</li>
        	</c:forEach>
			</ul>
		</section>
		</div>
<!-- 사진 게시판 미리보기 끝 -->
<!-- 두번째 쭐 오른쪽 바로가기 --><!--  -->
		<div class="main-item" id="main-item-link">
		<br>
			<div class="main-story">
			<div class="main-story-link" style="cursor: pointer;" onclick="location.href='${pageContext.request.contextPath}/houseBoard/list.do';">
				<div class="description">예쁜집 구경하기</div>
				<div class="title">
					<span class="text">>집들이</span>
				</div>
			</div> 
			<div class="main-story-link" style="cursor: pointer;" onclick="location.href='${pageContext.request.contextPath}/store/storeMain.do';">
				<div class="description">매일 자정, 새로운 특가상품</div>
				<div class="title">
					<span class="text">>스토어</span>
				</div>
			</div>
			<div class="main-story-link" style="cursor: pointer;" onclick="location.href='${pageContext.request.contextPath}/event/eventList.do';">
				<div class="description">매일의 집은 처음이세요?</div>
				<div class="title" >
					<span class="text">>이벤트</span>
				</div>
			</div>
			</div>
		</div>
<!-- 두번째 줄 오른쪽 바로가기끝 -->
<!-- 카테고리 이미지 시작 -->
		<div class="main-item my-5" style="text-align:left; cursor: pointer; font-size:20px; font-weight:bold;">카테고리별 상품 찾기<br>
			<ul class="cateul">
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category1.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=furniture'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category2.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=fabric'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category3.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=lamp'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category4.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=electronic'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category5.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=kitchen'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category6.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=deco'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category7.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=storage'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category8.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=daily_necessities'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category9.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=necessities'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category10.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=tool_diy'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category11.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=interior'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category12.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=pet'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category13.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=camping'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category14.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=indoor'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category15.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=baby_pro'"></a></li>
				<li class="cateli"><a><img src ="${pageContext.request.contextPath}/resources/images/category16.png"
				onclick="location.href='${pageContext.request.contextPath}/store/storeCategory.do?prod_cate=rental'"></a></li>
			</ul>
		</div>
<!-- 카테고리 이미지 끝 -->		
		<!-- 스토어 시작 -->
		<div class="main-item mt-5" style="text-align :center;"><p class="mb-2" style="text-align:left; margin-left:70px; font-size:20px; font-weight:bold;">매일의딜</p>
			<section class="bestitem">
			<ul class="bestitemul">
	  	  	<c:forEach var="store" items="${ SList }">
	  	  		<div align="center" style="cursor: pointer;"  onclick="location.href='${pageContext.request.contextPath}/store/storeDetail.do?prod_num=${store.prod_num}'">
				<li class="card mx-3">
					<figure class="bestimage p-3" style="height: 300px; width : 280px;">
				<!-- 사진 파일이 없는 경우 -->
					<c:if test="${ empty store.thumbnail_filename }">
	            		<img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/gift.png" style="border-radius:1%; width:280; height:280px;"/>
	            	</c:if>
	            <!-- 사진파일이 있는 경우 -->
	            	<c:if test="${ !empty store.thumbnail_filename }">
		            	<img class="d-block w-100" src="${pageContext.request.contextPath}/store/imageView.do?prod_num=${store.prod_num}" style="border-radius:1%; width:280px; height: 280px;" >
	            	</c:if>
					
					</figure>
					<div class="bestdesc">
						${store.prod_name}<br>
						<span id = "prod_price"><fmt:formatNumber value = "${store.prod_price}" type = "number"/>원<br></span>
					</div>
				</li>
				</div>
			</c:forEach>
			</ul>
		</section>		
		</div>
		<!-- 스토어 끝 -->
		<!-- 사진 게시판 추천순 시작 -->
		</div>
		</div>
		<!-- 사진 게시판 추천순 끝 -->
<!-- 메인 끝 -->