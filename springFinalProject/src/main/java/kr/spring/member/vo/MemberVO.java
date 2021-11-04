package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	private int mem_num;      // 회원번호
	   @Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	   private String mem_id;         // ID
	   private int mem_auth;         // 회원등급
	   @NotEmpty
	   private String mem_name;      // 이름
	   @Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	   private String passwd;      // 비밀번호
	   @NotEmpty
	   private String phone;      // 전화번호
	   @Email
	   @NotEmpty
	   private String email;      // 이메일
	   @Size(min=5, max=5)
	   private String zipcode;      // 우편번호
	   @NotEmpty
	   private String address1;   // 주소
	   @NotEmpty
	   private String address2;   // 나머지 주소
	   private byte[] profile;      // 프로필 사진
	   private String profile_filename;   // 프로필 사진 파일명
	   private Date reg_date;      // 가입일
	   private int point;			// 포인트
	   
	   // 쿠폰
	   private int coupon_num;			 // 쿠폰번호
	   private int coupondetail_num;	 // 쿠폰 상세번호
	   private String coupon_name;		 // 쿠폰명
	   private String coupon_content;	 // 쿠폰내용
	   private int coupon_count;		 // 쿠폰 갯수
	   private int discount_price;		 // 할인가격
	   
	   @NotEmpty
	   private String nickname;		// 닉네임
	   private int scrapbook_count;	// 스크랩 북 숫자
	   private int recommend_count;	// 좋아요 숫자
	   private int house_board_count;	// 사진 글 숫자
	   
	   
	// 비밀번호 변경시 현재 비밀번호를 저장하는 용도로 사용
			@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
			private String now_passwd;
	   
	   //============ 비밀번호 일치 여부 체크 ==============//
	   public boolean isCheckedPassword(String userPasswd) {
	      if(mem_auth > 0 && passwd.equals(userPasswd)) {
	         return true;
	      }
	      return false;
	   }
	   
	   //============= 이미지 BLOB 처리 ==================//
	   public void setUpload(MultipartFile upload) throws IOException{
	      // MultipartFile -> byte[]
	      setProfile(upload.getBytes());
	      // 파일 이름
	      setProfile_filename(upload.getOriginalFilename());
	   }
	   
	   
	public int getCoupondetail_num() {
		return coupondetail_num;
	}

	public void setCoupondetail_num(int coupondetail_num) {
		this.coupondetail_num = coupondetail_num;
	}

	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public byte[] getProfile() {
		return profile;
	}
	public void setProfile(byte[] profile) {
		this.profile = profile;
	}
	public String getProfile_filename() {
		return profile_filename;
	}
	public void setProfile_filename(String profile_filename) {
		this.profile_filename = profile_filename;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	public int getCoupon_num() {
		return coupon_num;
	}
	public void setCoupon_num(int coupon_num) {
		this.coupon_num = coupon_num;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getCoupon_content() {
		return coupon_content;
	}
	public void setCoupon_content(String coupon_content) {
		this.coupon_content = coupon_content;
	}
	public int getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(int discount_price) {
		this.discount_price = discount_price;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}

	public int getScrapbook_count() {
		return scrapbook_count;
	}

	public void setScrapbook_count(int scrapbook_count) {
		this.scrapbook_count = scrapbook_count;
	}

	public int getRecommend_count() {
		return recommend_count;
	}

	public void setRecommend_count(int recommend_count) {
		this.recommend_count = recommend_count;
	}
	public int getHouse_board_count() {
		return house_board_count;
	}

	public void setHouse_board_count(int house_board_count) {
		this.house_board_count = house_board_count;
	}

	public String getNow_passwd() {
		return now_passwd;
	}

	public void setNow_passwd(String now_passwd) {
		this.now_passwd = now_passwd;
	}

	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", mem_name="
				+ mem_name + ", passwd=" + passwd + ", phone=" + phone + ", email=" + email + ", zipcode=" + zipcode
				+ ", address1=" + address1 + ", address2=" + address2 + ", profile_filename=" + profile_filename
				+ ", reg_date=" + reg_date + ", point=" + point + ", coupon_num=" + coupon_num + ", coupondetail_num="
				+ coupondetail_num + ", coupon_name=" + coupon_name + ", coupon_content=" + coupon_content
				+ ", coupon_count=" + coupon_count + ", discount_price=" + discount_price + ", nickname=" + nickname
				+ ", scrapbook_count=" + scrapbook_count + ", recommend_count=" + recommend_count
				+ ", house_board_count=" + house_board_count + ", now_passwd=" + now_passwd + "]";
	}

	

	

}
