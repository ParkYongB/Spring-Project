# Every Day Home
Spring 을 이용한 MVC Project

:clipboard:[개인 PPT](https://docs.google.com/presentation/d/1em3nVIRzGQaPTQxAkftDwLV0GyvE1tDw/edit?usp=sharing&ouid=106944710309042006841&rtpof=true&sd=true)

## **바로가기**
1. [개요](https://github.com/ParkYongB/Spring-Project/blob/main/README.md#1-%EA%B0%9C%EC%9A%94)
2. [사용 기술]()
3. [개발기간 & 참여인원]()
4. [역할]()
5. [ERD 설계]()
6. [작성 기능]()
7. [주요 기능]()

## **1. 개요** 
- 온라인 집들이 커뮤니티 및 쇼핑몰 플랫폼인 오늘의 집의 벤치마킹 프로젝트 입니다.
- DB : [SQL 쿼리문](./src/webapp/sql/table.sql)
- src - webapp - sql - table.sql 에 DB 쿼리문 저장되어 있습니다.
- Email : umm_123@naver.com

## **2. 사용 기술**
- OS : Windows10 64bit
- SERVER : Apache Tomcat v9.0
- TOOL : Java JDK-1.7
- Framework : Spring Framework 5.0 Mybatis 3.2.2
- DBMS : Oracle 11g Release2
- Language : Java, HTML, CSS, JavaScript, JSP, jQuery, BootStrap, AJAX

## **3. 개발기간 & 참여인원**
- 2021/10/01 ~ 2021/10/24
- 추가 작업 : 2021/10 ~ 2021/11
- 팀 프로젝트 : 오상준, 신혜지, 이현지, 나윤경, 박용복

## **4. 역할**
- 상품 등록 및 수정
- 장바구니
- 구매 

## **5. ERD 설계**
![image](https://user-images.githubusercontent.com/90167580/145189316-8940ce11-177a-4054-a6fd-f89790bc041e.png)

## **6. 작성 기능**
- 상품 등록 및 상품 목록 출력 기능
- 상품 장바구니 기능
- 상품 주문 기능
- 상품 주문 후 카카오페이 API 연동을 통한 가상 결제
- SHA-256 암호화

## **7. 주요 기능**
- 회원가입 시 메일 API, 카카오 주소 API 적용
- 카카오 로그인 API 적용
- 사진 게시판 카테고리 검색
- 카카오톡, 트위터, 페이스북 공유하기 API 적용
- 판매자 신청
- 게시글 스크랩 및 좋아요 마이페이지에서 확인
- 구매한 상품 상품 리뷰 작성 및 표시
