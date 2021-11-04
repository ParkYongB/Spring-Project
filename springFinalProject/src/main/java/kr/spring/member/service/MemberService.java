package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.houseBoard.vo.HouseBoardVO;
import kr.spring.member.vo.MemberBuisVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.store.vo.StoreVO;

/**
 * @Package Name   : kr.spring.member.service
 * @FileName  : MemberService.java
 * @작성일       : 2021. 10. 13. 
 * @작성자       : 오상준
 * @프로그램 설명 : 회원 서비스부분
 */

public interface MemberService {

	public void insertMember(MemberVO member);						// 회원가입 트랜잭션 실행
	public MemberVO selectCheckMember(String mem_id);				// 회원가입 아이디 중복 체크
	public MemberVO selectMember(Integer mem_num);					// 마이페이지 - 회원정보 호출
	public void updateProfile(MemberVO member);						// 프로필 이미지 업데이트
	public void updateMember(MemberVO member);						// 회원정보 수정
	public void deleteMember(Integer mem_num);						// 회원정보 삭제
	public void insertSeller(MemberBuisVO memberBuisVO);			// 마이페이지 판매자 정보 등록
	public List<MemberVO> SelectIdSearch(Map<String, Object> map);	// 아이디 찾기
	public void updateMemberPasswd(MemberVO member); 				// 마이페이지 - 회원 비밀번호 변경	
	public int selectCountSeller(Integer mem_num);					// 마이페이지 판매자 신청 및 등록 내역 체크
	
	// 마이페이지 - 좋아요 버튼 부분
	public List<HouseBoardVO> myRecommBoardNum(Map<String, Object> map); 	// 마이페이지 - 내가 추천 누른 글 번호 구하기
	public int myRecommBoardCount(Map<String, Object> map);					// 마이페이지 - 내가 추천 누른 글의 게시글 수 구하기 (글번호가 일치한 게시물)
	
	public HouseBoardVO myRecommScrapBoardList(Map<String, Object> map);	// 마이페이지 - 내가 추천 및 북마크 누른 게시글 구하기 (글번호가 일치한 게시물)
	
	// 마이페이지 - 스크랩 버튼 부분
	public List<HouseBoardVO> myScrapBooksNum(Map<String, Object> map);		// 마이페이지 - 내가 누른 북마크 글 번호 구하기
	public int myScrapBookBoardCounts(Map<String, Object> map);				// 마이페이지 - 내가 누른 북마크 글의 게시글 수 구하기
	
	// 판매자 페이지
	public int myProductCount(Integer mem_num);							// 판매자 페이지 - 내가 등록한 상품 전체 수 구하기
	public List<StoreVO> myProductList(Map<String, Object> map);		// 판매자 페이지 - 내가 등록한 상품 리스트 출력
	
	// 관리자 페이지
	public List<MemberVO> selectMemberList(Map<String, Object> map);			// 관리자 페이지 - 회원 정보 전체 출력
	public List<MemberBuisVO> selectMemberBuisList(Map<String, Object> map);	// 관리자 페이지 - 판매자 신청 내역 조회	
	public int selectMemberCount(Map<String, Object> map);						// 관리자 페이지 - 회원 전체 수 구하기
	public int selectMemberBuisCount(Map<String, Object> map);					// 관리자 페이지 - 판매자 신청 전체 수 구하기
	public void updateMemberStop(Integer mem_num);			// 관리자 페이지 - 회원 정지
	public void updateMemberStopCancel(Integer mem_num);	// 관리자 페이지 - 회원 정지 해제
	public void updateMemberSellerAuth(Integer mem_num);	// 관리자 페이지 - 회원등급 판매자 변경
	public void updateMemberPasswdReset(Integer mem_num);	// 관리자 페이지 - 회원 비밀번호 초기화
	public void updateSellerMember(Integer mem_num);		// 관리자 페이지 - 판매자 신청 등록 (사업자테이블 상태 변경) (회원 등급 변경)
	public void deleteSellerMember(Integer mem_num);		// 관리자 페이지 - 판매자 신청 취소 (사업자 테이블 해당 ROW 삭제)
	
	// 쿠폰 부분
	public void insertCoupon(MemberVO memberVO);							// 관리자 페이지 쿠폰 등록
	public int selectCouponAllCount(); 										// 관리자 페이지 쿠폰 전체 개수 구하기
	public List<MemberVO> selectCouponAllList(Map<String, Object> map); 	// 관리자 페이지 쿠폰 목록 구하기
	public MemberVO couponSelect(Integer coupondetail_num);					// 관리자 페이지 쿠폰 정보 가져오기
	public void deleteCoupon(Integer coupondetail_num);						// 관리자 페이지 쿠폰 삭제
	public void updateCoupon(MemberVO memberVO);							// 관리자 페이지 쿠폰 수정
	public void insertMemberCouponReg(Integer mem_num, Integer coupondetail_num);		// 관리자 페이지 회원 쿠폰 배정
	public List<MemberVO> selectGetCouponList(Map<String, Object> map);		// 회원 나의 쿠폰 전체 리스트 출력
	public int selectGetCouponCount(Integer mem_num);					// 마이페이지 - 회원 쿠폰 수 반환
}
