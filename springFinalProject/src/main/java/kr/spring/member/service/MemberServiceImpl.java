package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.houseBoard.vo.HouseBoardVO;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberBuisVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.store.vo.StoreVO;

/**
 * @Package Name   : kr.spring.member.service
 * @FileName  : MemberServiceImpl.java
 * @작성일       : 2021. 10. 10. 
 * @작성자       : 오상준
 * @프로그램 설명 : 회원 부분 서비스 처리
 */

@Service
@Transactional	
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void insertMember(MemberVO member) {
		//회원번호 셋팅
		member.setMem_num(memberMapper.seleceMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_datail(member);
	}
	@Override
	public MemberVO selectCheckMember(String mem_id) {
		// 회원 아이디 중복 체크
		return memberMapper.selectCheckMember(mem_id);
	}
	@Override
	public MemberVO selectMember(Integer mem_num) {
		MemberVO memberVO = memberMapper.selectMember(mem_num);					// 회원정보 저장
		memberVO.setCoupon_count(memberMapper.selectGetCouponCount(mem_num));	// 쿠폰수 저장
		memberVO.setRecommend_count(memberMapper.myRecommCount(mem_num));		// 좋아요 수 저장
		memberVO.setScrapbook_count(memberMapper.myScrapBookCount(mem_num));		// 북마크 수 저장
		memberVO.setHouse_board_count(memberMapper.selectGetHouseBoardCount(mem_num)); // 작성글 수 저장
		
		return memberVO;
	}
	@Override
	public void updateProfile(MemberVO member) {
		memberMapper.updateProfile(member);
	}
	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
		
	}
	@Override
	public void deleteMember(Integer mem_num) {
		memberMapper.deleteMemberDetail(mem_num);
		memberMapper.deleteMember(mem_num);
	}
	@Override
	public void insertSeller(MemberBuisVO memberBuisVO) {
		memberMapper.insertSeller(memberBuisVO);
		
	}
	@Override
	public List<MemberVO> selectMemberList(Map<String, Object> map) {
		return memberMapper.selectMemberList(map);
	}
	@Override
	public int selectMemberCount(Map<String, Object> map) {
		return memberMapper.selectMemberCount(map);
	}
	@Override
	public void updateMemberStop(Integer mem_num) {
		memberMapper.updateMemberStop(mem_num);
		
	}
	@Override
	public void updateMemberStopCancel(Integer mem_num) {
		memberMapper.updateMemberStopCancel(mem_num);
		
	}
	@Override
	public void updateMemberSellerAuth(Integer mem_num) {
		memberMapper.updateMemberSellerAuth(mem_num);
		
	}
	@Override
	public void updateMemberPasswdReset(Integer mem_num) {
		memberMapper.updateMemberPasswdReset(mem_num);
		
	}
	@Override
	public List<MemberBuisVO> selectMemberBuisList(Map<String, Object> map) {
		return memberMapper.selectMemberBuisList(map);
	}
	@Override
	public int selectMemberBuisCount(Map<String, Object> map) {
		return memberMapper.selectMemberBuisCount(map);
	}
	@Override
	public void updateSellerMember(Integer mem_num) {
		memberMapper.updateSellerMemberState(mem_num);
		memberMapper.updateMemberSellerAuth(mem_num);
	}
	@Override
	public void deleteSellerMember(Integer mem_num) {
		memberMapper.deleteSellerMember(mem_num);
		
	}
	@Override
	public List<MemberVO> SelectIdSearch(Map<String, Object> map) {
		return memberMapper.SelectIdSearch(map);
	}
	@Override
	public void updateMemberPasswd(MemberVO member) {
		memberMapper.updateMemberPasswd(member);
	}
	@Override
	public int selectCountSeller(Integer mem_num) {
		return memberMapper.selectCountSeller(mem_num);
	}
	
	// 마이페이지 좋아요 및 스크랩 부분
	@Override
	public List<HouseBoardVO> myRecommBoardNum(Map<String, Object> map) {
		return memberMapper.myRecommBoardNum(map);
	}
	@Override
	public int myRecommBoardCount(Map<String, Object> map) {
		return memberMapper.myRecommBoardCount(map);
	}
	@Override
	public HouseBoardVO myRecommScrapBoardList(Map<String, Object> map) {
		return memberMapper.myRecommScrapBoardList(map);
	}
	@Override
	public List<HouseBoardVO> myScrapBooksNum(Map<String, Object> map) {
		return memberMapper.myScrapBooksNum(map);
	}
	@Override
	public int myScrapBookBoardCounts(Map<String, Object> map) {
		return memberMapper.myScrapBookBoardCounts(map);
	}
	
	// 판매자 페이지 내가 등록한 상품
	@Override
	public int myProductCount(Integer mem_num) {
		return memberMapper.myProductCount(mem_num);
	}
	@Override
	public List<StoreVO> myProductList(Map<String, Object> map) {
		return memberMapper.myProductList(map);
	}
	
	// 관리자 쿠폰 부분
	
	@Override
	public void insertCoupon(MemberVO memberVO) {
		memberMapper.insertCoupon(memberVO);
		
	}
	@Override
	public int selectCouponAllCount() {
		return memberMapper.selectCouponAllCount();
	}
	@Override
	public List<MemberVO> selectCouponAllList(Map<String, Object> map) {
		return memberMapper.selectCouponAllList(map);
	}
	@Override
	public void deleteCoupon(Integer coupondetail_num) {
		memberMapper.deleteCoupon(coupondetail_num);
		
	}
	@Override
	public MemberVO couponSelect(Integer coupondetail_num) {
		return memberMapper.couponSelect(coupondetail_num);
	}
	@Override
	public void updateCoupon(MemberVO memberVO) {
		memberMapper.updateCoupon(memberVO);
		
	}
	@Override
	public void insertMemberCouponReg(Integer mem_num, Integer coupondetail_num) {
		memberMapper.insertMemberCouponReg(mem_num, coupondetail_num);
		
	}
	@Override
	public List<MemberVO> selectGetCouponList(Map<String, Object> map) {
		return memberMapper.selectGetCouponList(map);
	}
	@Override
	public int selectGetCouponCount(Integer mem_num) {
		return memberMapper.selectGetCouponCount(mem_num);
	}
	
	
	
}