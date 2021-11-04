-- 21.10.12 16:30 회원 테이블 카카오 닉네임 추가
alter table member add kakao_nickname varchar2(30);

-- 21.10.12 17:00 회원테이블 제거 및 회원 상세테이블로 이동
alter table member drop column kakao_nickname;
alter table mem_detail add nickname varchar2(30);

-- 21.10.13 00 회원테이블 비밀번호 KEY, 비밀번호 답 삭제
alter table mem_detail drop column passwdkey;
alter table mem_detail drop column passwdq_uestion;

-- 21.10.13 01:00 카테고리 테이블 추가 및 상품 테이블 카테고리 컬럼 추가
alter table product add prod_cate varchar2(50) not null;
create table category (
    prod_cate varchar2(50) not null,
    cate_name varchar2(50) not null
);

-- 21.10.13 16:55 회원 팔로우 테이블 추가
CREATE TABLE follow
(
    follow_num          NUMBER    NOT NULL, 
    follow_mem_num      NUMBER    NOT NULL, 
    follower_mem_num    NUMBER    NOT NULL, 
    CONSTRAINT PK_follow_num PRIMARY KEY (follow_num)
);
/

CREATE SEQUENCE follow_SEQ
START WITH 1
INCREMENT BY 1;
/

--21.10.14 12:07 service_board 테이블 drop 후 create 해주세요
DROP TABLE service_board;

CREATE TABLE service_board
(
    service_num         NUMBER           NOT NULL, 
    service_title       VARCHAR2(150)    NOT NULL, 
    service_nickname    VARCHAR2(30)     NOT NULL, 
    service_content     CLOB             NOT NULL, 
    service_email       VARCHAR2(150)    NOT NULL, 
    service_keyword     VARCHAR2(50)     NOT NULL, 
    service_filename    VARCHAR2(150)    NULL, 
    service_file        BLOB             NULL, 
    mem_num             NUMBER           NULL, 
    CONSTRAINT PK_service_num PRIMARY KEY (service_num)
);

-- 21.10.14 12:20 집들이게시판 테이블 컬럼명 변경 및 컬럼 제거
ALTER TABLE house_board RENAME COLUMN house_modi TO house_mod_date;
ALTER TABLE house_board DROP COLUMN house_color;

-- 21.10.15 16:00 집들이게시판 테이블 컬럼 제거
ALTER TABLE house_board DROP COLUMN house_img;
ALTER TABLE house_board DROP COLUMN upload_filename;

-- 21.10.16 23:30 상품 테이블 칼럼 변경
ALTER TABLE product MODIFY selec_product null;
ALTER TABLE product add prod_option3 VARCHAR2(150) null;
ALTER TABLE product add prod_option4 VARCHAR2(150) null;
ALTER TABLE product add prod_option5 VARCHAR2(150) null;
ALTER TABLE product add prod_option6 VARCHAR2(150) null;
ALTER TABLE product add prod_option7 VARCHAR2(150) null;
ALTER TABLE product add prod_option8 VARCHAR2(150) null;
ALTER TABLE product add prod_option9 VARCHAR2(150) null;
ALTER TABLE product add prod_option10 VARCHAR2(150) null;

-- 21.10.17 02:50 댓글 테이블 컬럼명 변경
ALTER TABLE comments RENAME COLUMN comm_modi TO comm_mod_date;

-- 21.10.17 17:30 상품 테이블 칼럼 이름 오류 수정
ALTER TABLE product RENAME COLUMN product_option3 TO prod_option3;
ALTER TABLE product RENAME COLUMN product_option4 TO prod_option4;
ALTER TABLE product RENAME COLUMN product_option5 TO prod_option5;
ALTER TABLE product RENAME COLUMN product_option6 TO prod_option6;
ALTER TABLE product RENAME COLUMN product_option7 TO prod_option7;
ALTER TABLE product RENAME COLUMN product_option8 TO prod_option8;
ALTER TABLE product RENAME COLUMN product_option9 TO prod_option9;
ALTER TABLE product RENAME COLUMN product_option10 TO prod_option10;


-- 21.10.17 23:30 결제 테이블 수정
ALTER TABLE orders ADD prod_num number;
ALTER TABLE orders ADD pay_quan number(5, 0);
ALTER TABLE orders ADD pay_price number(14, 0);
ALTER TABLE orders ADD coupon_num number null;
ALTER TABLE orders ADD point number(12,0) null;
ALTER TABLE orders ADD buis_name varchar2(70);

-- 21.10.18. 01:00 판매자 테이블 컬럼 변경 및 고객센터 외래키 제약조건 삭제
ALTER TABLE service_board DROP CONSTRAINT FK_SERVICE_BOARD_MEM_NUM_MEMBE;

ALTER TABLE buis_detail DROP COLUMN BUIS_EMAIL;
ALTER TABLE buis_detail DROP COLUMN BUIS_PROFILE;
ALTER TABLE buis_detail DROP COLUMN PROFILE_FILENAME;
ALTER TABLE buis_detail DROP COLUMN BUIS_REG_DATE;
ALTER TABLE buis_detail DROP COLUMN BUIS_PASSWD;
ALTER TABLE buis_detail add application_state VARCHAR2(10) DEFAULT '1' not null;
ALTER TABLE buis_detail MODIFY BUIS_NUM VARCHAR2(40);

-- 21.10.18 20:20 댓글 테이블 데이터 크기 및 기본값 변경
ALTER TABLE comments MODIFY comm_content VARCHAR2(900);
ALTER TABLE comments MODIFY comm_reg_date DATE DEFAULT sysdate;
ALTER TABLE comments MODIFY comm_mod_date DATE DEFAULT sysdate;

-- 21.10.19 03 30 사업자 테이블 삭제 후 재생성 (순서대로 실행)
DROP TABLE BUIS_DETAIL;
CREATE TABLE buis_detail
(
    buis_count           NUMBER          NOT NULL, 
    buis_num             NUMBER          NOT NULL, 
    mem_num              NUMBER          NOT NULL, 
    ceo_name             VARCHAR2(25)    NOT NULL, 
    buis_name            VARCHAR2(70)    NOT NULL, 
    opening_date         DATE            NOT NULL, 
    buis_item            VARCHAR2(70)    NOT NULL, 
    buis_zipcode         VARCHAR2(20)    NULL, 
    buis_address1        VARCHAR2(90)    NULL, 
    buis_address2        VARCHAR2(90)    NULL, 
    application_state    VARCHAR2(10)    DEFAULT '1' NULL, 
    CONSTRAINT buis_num PRIMARY KEY (buis_count, buis_num, mem_num)
);

CREATE SEQUENCE buis_detail_SEQ START WITH 1 INCREMENT BY 1;

-- 21.10.20 16:40 집들이 테이블 컬럼 제거
ALTER TABLE house_board DROP COLUMN house_recom;

--21.10.21 1:10 이벤트 게시판 컬럼 추가
alter table event add event_day varchar2(50);

-- 21.10.21.02.50 쿠폰테이블 삭제 및 분할	(순서대로 실행)
DROP TABLE coupon;

CREATE TABLE coupon_detail
(
    coupondetail_num    NUMBER           NOT NULL, 
    coupon_name         VARCHAR2(300)    NOT NULL, 
    coupon_content      VARCHAR2(600)    NOT NULL, 
    discount_price      NUMBER(14, 0)    NOT NULL, 
    CONSTRAINT PK_coupon_num PRIMARY KEY (coupondetail_num)
);

CREATE SEQUENCE coupon_detail_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TABLE coupon
(
    coupon_num          NUMBER    NOT NULL, 
    mem_num             NUMBER    NULL, 
    coupondetail_num    NUMBER    NULL, 
     PRIMARY KEY (coupon_num)
);

-- 21.10.21 12:00 카테고리 테이블 제거
drop table category;

-- 21.10.21 17:00 팔로우 테이블 제거
DROP TABLE follow;

-- 21.10.22 15:00 주문 테이블 외래키 제거
alter table orders drop constraint FK_ORDERS_MEM_NUM_MEMBER_MEM_N;

