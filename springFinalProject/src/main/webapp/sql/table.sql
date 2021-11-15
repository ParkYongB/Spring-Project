-- member Table Create SQL
CREATE TABLE member
(
    mem_num     NUMBER          NOT NULL, 
    mem_id      VARCHAR2(20)    NOT NULL, 
    mem_auth    NUMBER(1, 0)    DEFAULT 2 NOT NULL, 
    CONSTRAINT PK_mem_num PRIMARY KEY (mem_num)
);

CREATE SEQUENCE member_SEQ START WITH 1 INCREMENT BY 1;

CREATE UNIQUE INDEX UQ_member_1 ON member(mem_id);

-- event Table Create SQL
CREATE TABLE event
(
    event_num         NUMBER           NOT NULL, 
    event_title       VARCHAR2(150)    NOT NULL, 
    event_content     CLOB             NOT NULL, 
    event_type        VARCHAR2(100)    NULL, 
    event_hits        INT              DEFAULT 0 NULL, 
    event_reg_date    DATE             DEFAULT SYSDATE NULL, 
    event_modi        DATE             DEFAULT SYSDATE NULL, 
    event_photo       BLOB             NULL, 
    event_filename    VARCHAR2(100)    NULL, 
    mem_num           NUMBER           NOT NULL, 
    event_day         VARCHAR2(50)     NULL, 
    CONSTRAINT PK_event_num PRIMARY KEY (event_num)
);

CREATE SEQUENCE event_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE event
    ADD CONSTRAINT FK_event_mem_num_member_mem_nu FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- house_board Table Create SQL
CREATE TABLE house_board
(
    house_num             NUMBER           NOT NULL, 
    house_type            VARCHAR2(30)     NOT NULL, 
    house_space           VARCHAR2(30)     NULL, 
    house_area            VARCHAR2(30)     NOT NULL, 
    house_style           VARCHAR2(30)     NULL, 
    house_title           VARCHAR2(150)    NOT NULL, 
    house_content         CLOB             NOT NULL, 
    house_hits            INT              DEFAULT 0 NULL, 
    house_reg_date        DATE             DEFAULT SYSDATE NULL, 
    house_mod_date        DATE             DEFAULT SYSDATE NULL, 
    house_thumbnail       BLOB             NULL, 
    thumbnail_filename    VARCHAR2(100)    NULL, 
    house_img             BLOB             NULL, 
    upload_filename       VARCHAR2(300)    NULL, 
    mem_num               NUMBER           NOT NULL, 
    CONSTRAINT PK_house_num PRIMARY KEY (house_num)
);

CREATE SEQUENCE house_board_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE house_board
    ADD CONSTRAINT FK_house_board_mem_num_member_ FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- product Table Create SQL
CREATE TABLE product
(
    prod_num              NUMBER           NOT NULL, 
    prod_name             VARCHAR2(150)    NOT NULL, 
    prod_price            NUMBER(14, 0)    NOT NULL, 
    delive_price          NUMBER(10,0)     DEFAULT 0 NOT NULL, 
    delive_type           VARCHAR2(30)     NOT NULL, 
    selec_product         VARCHAR2(150)    NOT NULL, 
    prod_option1          VARCHAR2(150)    NULL, 
    prod_option2          VARCHAR2(150)    NULL, 
    prod_content          CLOB             NOT NULL, 
    prod_img              BLOB             NULL, 
    prod_filename         VARCHAR2(100)    NULL, 
    thumbnail_img         BLOB             NULL, 
    thumbnail_filename    VARCHAR2(300)    NULL, 
    prod_quan             NUMBER(5, 0)     DEFAULT 1 NOT NULL, 
    add_product           VARCHAR2(150)    NULL, 
    prod_reg_date         DATE             DEFAULT SYSDATE NOT NULL, 
    prod_keyword          VARCHAR2(24)     NULL, 
    mem_num               NUMBER           NOT NULL, 
    prod_cate             VARCHAR2(50)     NOT NULL, 
    prod_option3          VARCHAR2(150)    NULL, 
    prod_option4          VARCHAR2(150)    NULL, 
    prod_option5          VARCHAR2(150)    NULL, 
    prod_option6          VARCHAR2(150)    NULL, 
    prod_option7          VARCHAR2(150)    NULL, 
    prod_option8          VARCHAR2(150)    NULL, 
    prod_option9          VARCHAR2(150)    NULL, 
    prod_option10         VARCHAR2(150)    NULL, 
    CONSTRAINT PK_prod_num PRIMARY KEY (prod_num)
);

CREATE SEQUENCE product_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE product
    ADD CONSTRAINT FK_product_mem_num_member_mem_ FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- coupon_detail Table Create SQL
CREATE TABLE coupon_detail
(
    coupondetail_num    NUMBER           NOT NULL, 
    coupon_name         VARCHAR2(300)    NOT NULL, 
    coupon_content      VARCHAR2(600)    NOT NULL, 
    discount_price      NUMBER(14, 0)    NOT NULL, 
    CONSTRAINT PK_coupon_num PRIMARY KEY (coupondetail_num)
);

CREATE SEQUENCE coupon_detail_SEQ START WITH 1 INCREMENT BY 1;

-- mem_detail Table Create SQL
CREATE TABLE mem_detail
(
    mem_num             NUMBER           NOT NULL, 
    mem_name            VARCHAR2(25)     NOT NULL, 
    passwd              VARCHAR2(65)     NOT NULL, 
    nickname            VARCHAR2(30)     NULL, 
    email               VARCHAR2(50)     NOT NULL, 
    phone               VARCHAR2(20)     NOT NULL, 
    zipcode             VARCHAR2(20)     NOT NULL, 
    address1            VARCHAR2(90)     NOT NULL, 
    address2            VARCHAR2(90)     NOT NULL, 
    reg_date            DATE             DEFAULT SYSDATE NULL, 
    profile             BLOB             NULL, 
    profile_filename    VARCHAR2(100)    NULL, 
    point               NUMBER(12,0)     DEFAULT 0 NOT NULL, 
    CONSTRAINT PK_mem_detail PRIMARY KEY (mem_num)
);

ALTER TABLE mem_detail
    ADD CONSTRAINT FK_mem_detail_mem_num_member_m FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- coupon Table Create SQL
CREATE TABLE coupon
(
    coupon_num          NUMBER    NOT NULL, 
    mem_num             NUMBER    NULL, 
    coupondetail_num    NUMBER    NULL, 
     PRIMARY KEY (coupon_num)
);

CREATE SEQUENCE coupon_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE coupon
    ADD CONSTRAINT FK_coupon_mem_num_mem_detail_m FOREIGN KEY (mem_num)
        REFERENCES mem_detail (mem_num);

ALTER TABLE coupon
    ADD CONSTRAINT FK_coupon_coupondetail_num_cou FOREIGN KEY (coupondetail_num)
        REFERENCES coupon_detail (coupondetail_num);

-- qna_list Table Create SQL
CREATE TABLE qna_list
(
    qna_num         INT              NOT NULL, 
    qna_category    VARCHAR2(30)     NULL, 
    qna_content     VARCHAR2(300)    NULL, 
    qna_reply       VARCHAR2(300)    NULL, 
    CONSTRAINT PK_qna_num PRIMARY KEY (qna_num)
);

CREATE SEQUENCE qna_list_SEQ START WITH 1 INCREMENT BY 1;

-- cart Table Create SQL
CREATE TABLE cart
(
    cart_num     NUMBER          NOT NULL, 
    prod_num     NUMBER          NOT NULL, 
    mem_num      NUMBER          NOT NULL, 
    cart_quan    NUMBER(5, 0)    DEFAULT 1 NOT NULL, 
    CONSTRAINT PK_cart_num PRIMARY KEY (cart_num, prod_num)
);

CREATE SEQUENCE cart_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE cart
    ADD CONSTRAINT FK_cart_prod_num_product_prod_ FOREIGN KEY (prod_num)
        REFERENCES product (prod_num);

ALTER TABLE cart
    ADD CONSTRAINT FK_cart_mem_num_member_mem_num FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- product_review Table Create SQL
CREATE TABLE product_review
(
    rev_num         NUMBER           NOT NULL, 
    rev_content     CLOB             NOT NULL, 
    rev_grade       NUMBER(1, 0)     DEFAULT 1 NOT NULL, 
    rev_reg_date    DATE             DEFAULT SYSDATE NOT NULL, 
    rev_img         BLOB             NULL, 
    rev_filename    VARCHAR2(100)    NULL, 
    prod_num        NUMBER           NOT NULL, 
    mem_num         NUMBER           NOT NULL, 
    CONSTRAINT PK_rev_num PRIMARY KEY (rev_num)
);

CREATE SEQUENCE product_review_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE product_review
    ADD CONSTRAINT FK_product_review_prod_num_pro FOREIGN KEY (prod_num)
        REFERENCES product (prod_num);

ALTER TABLE product_review
    ADD CONSTRAINT FK_product_review_mem_num_memb FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- orders Table Create SQL
CREATE TABLE orders
(
    order_num         NUMBER           NOT NULL, 
    order_date        DATE             DEFAULT SYSDATE NOT NULL, 
    order_zipcode     VARCHAR2(20)     NOT NULL, 
    order_address1    VARCHAR2(90)     NOT NULL, 
    order_address2    VARCHAR2(90)     NOT NULL, 
    receiver_name     VARCHAR2(25)     NOT NULL, 
    receiver_phone    VARCHAR2(20)     NOT NULL, 
    receiver_email    VARCHAR2(50)     NOT NULL, 
    mem_num           NUMBER           NULL, 
    prod_num          NUMBER           NULL, 
    pay_quan          NUMBER(5, 0)     NULL, 
    pay_price         NUMBER(14, 0)    NULL, 
    coupon_num        NUMBER           NULL, 
    point             NUMBER(12, 0)    NULL, 
    buis_name         VARCHAR2(70)     NULL, 
    CONSTRAINT PK_order_num PRIMARY KEY (order_num)
);

CREATE SEQUENCE orders_SEQ START WITH 1 INCREMENT BY 1;

-- recommend Table Create SQL
CREATE TABLE recommend
(
    house_num    NUMBER    NULL, 
    mem_num      NUMBER    NOT NULL
);

ALTER TABLE recommend
    ADD CONSTRAINT FK_recommend_house_num_house_b FOREIGN KEY (house_num)
        REFERENCES house_board (house_num);

ALTER TABLE recommend
    ADD CONSTRAINT FK_recommend_mem_num_member_me FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- scrapbook Table Create SQL
CREATE TABLE scrapbook
(
    scrap_num    NUMBER    NOT NULL, 
    house_num    NUMBER    NULL, 
    mem_num      NUMBER    NOT NULL, 
    CONSTRAINT PK_scrap_num PRIMARY KEY (scrap_num)
);

CREATE SEQUENCE scrapbook_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE scrapbook
    ADD CONSTRAINT FK_scrapbook_house_num_house_b FOREIGN KEY (house_num)
        REFERENCES house_board (house_num);

ALTER TABLE scrapbook
    ADD CONSTRAINT FK_scrapbook_mem_num_member_me FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- comments Table Create SQL
CREATE TABLE comments
(
    comm_num         NUMBER           NOT NULL, 
    comm_reg_date    DATE             DEFAULT SYSDATE NULL, 
    comm_modi        DATE             DEFAULT SYSDATE NULL, 
    comm_content     VARCHAR2(900)    NOT NULL, 
    mem_num          NUMBER           NOT NULL, 
    house_num        NUMBER           NULL, 
    event_num        NUMBER           NULL, 
    CONSTRAINT PK_comments PRIMARY KEY (comm_num)
);

CREATE SEQUENCE comments_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE comments
    ADD CONSTRAINT FK_comments_mem_num_member_mem FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

ALTER TABLE comments
    ADD CONSTRAINT FK_comments_house_num_house_bo FOREIGN KEY (house_num)
        REFERENCES house_board (house_num);

ALTER TABLE comments
    ADD CONSTRAINT FK_comments_event_num_event_ev FOREIGN KEY (event_num)
        REFERENCES event (event_num);

-- notice Table Create SQL
CREATE TABLE notice
(
    notice_num         NUMBER           NOT NULL, 
    notice_title       VARCHAR2(150)    NOT NULL, 
    notice_content     CLOB             NOT NULL, 
    notice_reg_date    DATE             DEFAULT SYSDATE NULL, 
    notice_hits        INT              DEFAULT 0 NULL, 
    mem_num            NUMBER           NOT NULL, 
    CONSTRAINT PK_notice_num PRIMARY KEY (notice_num)
);

CREATE SEQUENCE notice_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE notice
    ADD CONSTRAINT FK_notice_mem_num_member_mem_n FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);

-- service_board Table Create SQL
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

CREATE SEQUENCE service_board_SEQ START WITH 1 INCREMENT BY 1;

-- buis_detail Table Create SQL
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

ALTER TABLE buis_detail
    ADD CONSTRAINT FK_buis_detail_mem_num_member_ FOREIGN KEY (mem_num)
        REFERENCES member (mem_num);