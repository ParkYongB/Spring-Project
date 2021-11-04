package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.houseBoard.vo.HouseBoardVO;
import kr.spring.houseBoard.vo.HouseBoardVO;
import kr.spring.member.vo.MemberBuisVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.store.vo.StoreVO;

/**
 * @Package Name   : kr.spring.member.dao
 * @FileName  : MemberMapper.java
 * @작성일       : 2021. 10. 10. 
 * @작성자       : 오상준
 * @프로그램 설명 : 회원 DAO 부분
 */

public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")	
	public int seleceMem_num();							// 회원번호 시퀀스 생성
	@Insert("INSERT INTO member (mem_num, mem_id) VALUES(#{mem_num}, #{mem_id})")
	public void insertMember(MemberVO member);			// 회원(member)테이블에 생성
	@Insert("INSERT INTO mem_detail (mem_num,mem_name,passwd,phone,email,zipcode,address1,address2,nickname) VALUES (#{mem_num}, #{mem_name}, #{passwd}, #{phone}, #{email}, #{zipcode}, #{address1}, #{address2}, #{nickname})")
	public void insertMember_datail(MemberVO member);	// 회원가입 - 회원 상세 테이블
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,d.passwd,d.profile,d.email, d.nickname, d.phone FROM member m LEFT OUTER JOIN mem_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckMember(String mem_id);	// 회원가입 중복체크 - 회원존재 여부 체크
	@Select("SELECT m.mem_id FROM member m JOIN mem_detail d ON m.mem_num=d.mem_num WHERE d.mem_name = #{mem_name} AND d.phone = #{phone}")
	public List<MemberVO> SelectIdSearch(Map<String, Object> map);		// 아이디 찾기
	
	@Select("SELECT * FROM member m JOIN mem_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);		// 마이페이지 - 회원 정보 출력
	
	@Select("SELECT count(*) FROM recommend WHERE mem_num=#{mem_num}")
	public int myRecommCount(Integer mem_num);								// 마이페이지 - 내가 누른 총 추천 수 구하기
	public List<HouseBoardVO> myRecommBoardNum(Map<String, Object> map); 	// 마이페이지 - 내가 추천 누른 글 번호 구하기
	public int myRecommBoardCount(Map<String, Object> map);					// 마이페이지 - 내가 추천 누른 글의 게시글 수 구하기 (글번호가 일치한 게시물)
	
	public HouseBoardVO myRecommScrapBoardList(Map<String, Object> map);	// 마이페이지 - 내가 추천 및 북마크 누른 게시글 구하기 (글번호가 일치한 게시물)
	
	@Select("SELECT count(*) FROM scrapbook WHERE mem_num=#{mem_num}")
	public int myScrapBookCount(Integer mem_num);							// 마이페이지 - 내가 누른 총 북마크 수 구하기
	public List<HouseBoardVO> myScrapBooksNum(Map<String, Object> map);		// 마이페이지 - 내가 누른 북마크 글 번호 구하기
	public int myScrapBookBoardCounts(Map<String, Object> map);				// 마이페이지 - 내가 누른 북마크 글의 게시글 수 구하기
	
	
	@Select("SELECT count(*) FROM house_board WHERE mem_num = #{mem_num}")
	public int selectGetHouseBoardCount(Integer mem_num);	// 마이페이지 - 작성글 수 구하기
	@Update("UPDATE mem_detail SET profile=#{profile},profile_filename=#{profile_filename} WHERE mem_num=#{mem_num}") // 프로필 이미지 업데이트
	public void updateProfile(MemberVO member);		 // 마이페이지 - 프로필 사진 업데이트
	@Update("UPDATE mem_detail SET mem_name = #{mem_name}, nickname = #{nickname}, phone = #{phone}, email = #{email}, zipcode = #{zipcode}, address1 = #{address1}, address2 = #{address2} WHERE mem_num = #{mem_num}")
	public void updateMember(MemberVO member);		 // 마이페이지 - 회원 정보 수정
	@Update("UPDATE mem_detail SET passwd = #{passwd} WHERE mem_num=#{mem_num}")
	public void updateMemberPasswd(MemberVO member); // 마이페이지 - 회원 비밀번호 변경
	
	@Update("UPDATE member SET mem_auth=0 WHERE mem_num = #{mem_num}")
	public void deleteMember(Integer mem_num);			// 마이페이지 - 회원 삭제(등급 0 변경)
	@Delete("DELETE FROM mem_detail WHERE mem_num = #{mem_num}")
	public void deleteMemberDetail(Integer mem_num);		// 마이페이지 - 회원상세정보 삭제
	@Insert("INSERT INTO buis_detail (buis_count, mem_num, buis_num, ceo_name, buis_name, buis_item, opening_date, buis_zipcode, buis_address1, buis_address2) VALUES (buis_detail_seq.nextval, #{mem_num}, #{buis_num}, #{ceo_name}, #{buis_name}, #{buis_item}, #{opening_date}, #{buis_zipcode}, #{buis_address1}, #{buis_address2})")
	public void insertSeller(MemberBuisVO memberBuisVO);		// 마이페이지 판매자 정보 등록
	@Select("select count(*) from buis_detail where mem_num = #{mem_num}")
	public int selectCountSeller(Integer mem_num);		// 마이페이지 판매자 신청 및 등록 내역 체크
	
	
	public List<MemberVO> selectMemberList(Map<String, Object> map);	// 관리자 페이지 - 회원 정보 전체 출력
	public int selectMemberCount(Map<String, Object> map);			// 관리자 페이지 - 회원 전체 수 구하기
	@Update("UPDATE member SET mem_auth = 1 WHERE mem_num=#{mem_num}")
	public void updateMemberStop(Integer mem_num);			// 관리자 페이지 - 회원 정지
	@Update("UPDATE member SET mem_auth = 2 WHERE mem_num=#{mem_num}")
	public void updateMemberStopCancel(Integer mem_num);	// 관리자 페이지 - 회원 정지 해제
	@Update("UPDATE member SET mem_auth = 3 WHERE mem_num=#{mem_num}")
	public void updateMemberSellerAuth(Integer mem_num);	// 관리자 페이지 - 회원등급 판매자 변경	판매자 신청 등록 (회원 등급 변경 함께 사용)
	@Update("UPDATE mem_detail SET passwd = '123456789a' WHERE mem_num=#{mem_num}")
	public void updateMemberPasswdReset(Integer mem_num);	// 관리자 페이지 - 회원 비밀번호 초기화
	public List<MemberBuisVO> selectMemberBuisList(Map<String, Object> map);	// 관리자 페이지 - 판매자 신청 내역 조회
	public int selectMemberBuisCount(Map<String, Object> map);					// 관리자 페이지 - 판매자 신청 전체 수 구하기
	
	@Update("UPDATE buis_detail SET application_state = 2 WHERE mem_num=#{mem_num}")
	public void updateSellerMemberState(Integer mem_num);	// 관리자 페이지 - 판매자 신청 등록 (사업자테이블 상태 변경)
	@Delete("DELETE FROM buis_detail WHERE mem_num=#{mem_num}")
	public void deleteSellerMember(Integer mem_num);		// 관리자 페이지 - 판매자 신청 취소 (사업자 테이블 해당 ROW 삭제)
	
	
	// 쿠폰 부분
	
	@Insert("INSERT INTO coupon_detail (coupondetail_num, coupon_name, coupon_content, discount_price) VALUES"
			+ " (coupon_detail_seq.nextval, #{coupon_name}, #{coupon_content}, #{discount_price})")
	public void insertCoupon(MemberVO memberVO);			// 관리자 페이지 쿠폰 등록
	@Select("SELECT COUNT(*) FROM coupon_detail")
	public int selectCouponAllCount(); 				// 관리자 페이지 쿠폰 전체 개수 구하기
	public List<MemberVO> selectCouponAllList(Map<String, Object> map); 	// 관리자 페이지 쿠폰 목록 구하기
	
	// 관리자 페이지 쿠폰 배정
	@Delete("DELETE FROM coupon_detail WHERE coupondetail_num=#{coupondetail_num}")
	public void deleteCoupon(Integer coupondetail_num);			// 관리자 페이지 쿠폰 삭제
	@Select("SELECT * FROM coupon_detail WHERE coupondetail_num=#{coupondetail_num}")
	public MemberVO couponSelect(Integer coupondetail_num);		// 관리자 페이지 쿠폰 정보 가져오기
	@Update("UPDATE coupon_detail SET coupon_name = #{coupon_name}, discount_price=#{discount_price}, coupon_content=#{coupon_content}")
	public void updateCoupon(MemberVO memberVO);				// 관리자 페이지 쿠폰 수정
	@Insert("INSERT INTO coupon (coupon_num, mem_num, coupondetail_num) VALUES (coupon_seq.nextval, #{mem_num}, #{coupondetail_num})")
	public void insertMemberCouponReg(@Param("mem_num") Integer mem_num, @Param("coupondetail_num") Integer coupondetail_num);		// 관리자 페이지 회원 쿠폰 배정
	
	@Select("SELECT count(*) FROM coupon WHERE mem_num=#{mem_num}")
	public int selectGetCouponCount(Integer mem_num);					// 마이페이지 - 회원 쿠폰 수 반환
	public List<MemberVO> selectGetCouponList(Map<String, Object> map);	// 회원 나의 쿠폰 전체 리스트 출력
	
	@Select("SELECT COUNT(*) FROM product WHERE mem_num = #{mem_num}")
	public int myProductCount(Integer mem_num);						// 판매자 페이지 - 내가 등록한 상품 전체 수 구하기
	public List<StoreVO> myProductList(Map<String, Object> map);		// 판매자 페이지 - 내가 등록한 상품 리스트 출력
	
	
	
	
}