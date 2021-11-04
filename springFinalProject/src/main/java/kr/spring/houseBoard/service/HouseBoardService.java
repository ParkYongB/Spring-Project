package kr.spring.houseBoard.service;

import java.util.List;
import java.util.Map;

import kr.spring.houseBoard.vo.HCommentVO;
import kr.spring.houseBoard.vo.HMarkVO;
import kr.spring.houseBoard.vo.HouseBoardVO;

/**
 * @Package Name   : kr.spring.houseBoard.service
 * @FileName  : HouseBoardService.java
 * @작성일       : 2021. 10. 10. 
 * @작성자       : 이현지
 * @프로그램 설명 : 집들이 게시판 서비스
 */

public interface HouseBoardService {
	// =============== 집들이 게시판 & 내가 쓴 글 =============== //
	public List<HouseBoardVO> selectHBoardList(Map<String,Object> map); // 글 목록
	public int selectRowCount(Map<String,Object> map); // 글의 개수
	public List<HouseBoardVO> selectMyBoardList(Map<String, Object> map); // 내가쓴 글 목록
	public int selectMyBoardRowCount(Integer mem_num); // 내가 쓴 글의 개수
	public void insertHBoard(HouseBoardVO houseBoard); // 글쓰기
	public HouseBoardVO selectHBoard(Integer house_num); // 글 상세
	public void updateHBoardHits(Integer house_num); // 조회수
	public void updateHBoard(HouseBoardVO houseBoard); // 글 수정
	public void deleteHBoard(Integer house_num); // 글 삭제
	// 썸네일 삭제(업데이트)
	public void deleteFile(Integer house_num);
	
	// =============== 댓글 =============== //
	public List<HCommentVO> selectListComm(Map<String,Object> map); // 댓글 목록
	public int selectRowCountComm(Map<String,Object> map); // 댓글 개수
	public int countComm(Integer house_num); // 댓글 개수 조회
	public void insertComm(HCommentVO hComment); // 댓글 등록
	public void updateComm(HCommentVO hComment); // 댓글 수정
	public void deleteComm(Integer comm_num); // 댓글 삭제
	
	// =============== 추천 =============== //
	public int countHeart(Integer house_num); // 추천수 조회
	public String checkHeart(HMarkVO hMark); // 추천 중복 체크
	public void insertHeart(HMarkVO hMark); // 테이블에 추천수 저장
	public void deleteHeart(HMarkVO hMark); // 테이블에서 추천수 제거
	
	// =============== 스크랩 =============== //
	public int countScrap(Integer house_num); // 스크랩수 조회
	public String checkScrap(HMarkVO hMark); // 스크랩 중복 체크
	public void insertScrap(HMarkVO hMark); // 테이블에 스크랩 수 저장
	public void deleteScrap(HMarkVO hMark); // 테이블에서 스크랩수 제거
	
	// =============== 통합검색 =============== //
	public int selectAllSearchRowCount(Map<String,Object> map);		// 통합 검색 결과 게시글 수
	public List<HouseBoardVO> selectAllSearchList(Map<String,Object> map);		// 통합 검색 결과 게시글 리스트
	
}
