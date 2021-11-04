package kr.spring.houseBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.houseBoard.dao.HouseBoardMapper;
import kr.spring.houseBoard.vo.HCommentVO;
import kr.spring.houseBoard.vo.HMarkVO;
import kr.spring.houseBoard.vo.HouseBoardVO;

/**
 * @Package Name   : kr.spring.houseBoard.service
 * @FileName  : HouseBoardServiceImpl.java
 * @작성일       : 2021. 10. 10. 
 * @작성자       : 이현지
 * @프로그램 설명 : 집들이 게시판 서비스임플리먼트
 */

@Service
@Transactional
public class HouseBoardServiceImpl implements HouseBoardService {
	
	@Autowired
	private HouseBoardMapper houseBoardMapper;
	
	// ========== 게시판 ========== //
	@Override
	public List<HouseBoardVO> selectHBoardList(Map<String, Object> map) {
		return houseBoardMapper.selectHBoardList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return houseBoardMapper.selectRowCount(map);
	}

	@Override
	public void insertHBoard(HouseBoardVO houseBoard) {
		houseBoardMapper.insertHBoard(houseBoard);
	}

	@Override
	public HouseBoardVO selectHBoard(Integer house_num) {
		return houseBoardMapper.selectHBoard(house_num);
	}

	@Override
	public void updateHBoardHits(Integer house_num) {
		houseBoardMapper.updateHBoardHits(house_num);
	}
	
	@Override
	public void updateHBoard(HouseBoardVO houseBoard) {
		houseBoardMapper.updateHBoard(houseBoard);
	}
	
	@Override
	public void deleteHBoard(Integer house_num) {
		// 부모글 삭제 전 댓글,추천,스크랩 먼저 삭제 후 해당 부모글 삭제
		houseBoardMapper.deleteCommByHouseNum(house_num);
		houseBoardMapper.deleteHeartByHouseNum(house_num);
		houseBoardMapper.deleteScrapByHouseNum(house_num);
		houseBoardMapper.deleteHBoard(house_num);
	}
	
	@Override
	public void deleteFile(Integer house_num) {
		houseBoardMapper.deleteFile(house_num);	
	}
	
	// ========== 마이페이지 - 내가 쓴 글 ========== //
	@Override
	public List<HouseBoardVO> selectMyBoardList(Map<String, Object> map) {
		return houseBoardMapper.selectMyBoardList(map);
	}

	@Override
	public int selectMyBoardRowCount(Integer mem_num) {
		return houseBoardMapper.selectMyBoardRowCount(mem_num);
	}
	
	// ========== 댓글 ========== //
	@Override
	public List<HCommentVO> selectListComm(Map<String, Object> map) {
		return houseBoardMapper.selectListComm(map);
	}

	@Override
	public int selectRowCountComm(Map<String, Object> map) {
		return houseBoardMapper.selectRowCountComm(map);
	}
	
	@Override
	public int countComm(Integer house_num) {
		return houseBoardMapper.countComm(house_num);
	}

	@Override
	public void insertComm(HCommentVO hComment) {
		houseBoardMapper.insertComm(hComment);
	}

	@Override
	public void updateComm(HCommentVO hComment) {
		houseBoardMapper.updateComm(hComment);
	}

	@Override
	public void deleteComm(Integer comm_num) {
		houseBoardMapper.deleteComm(comm_num);
	}
	
	// ========== 추천 ========== //
	@Override
	public int countHeart(Integer house_num) {
		return houseBoardMapper.countHeart(house_num);
	}

	@Override
	public String checkHeart(HMarkVO hMark) {
		return houseBoardMapper.checkHeart(hMark);
	}

	@Override
	public void insertHeart(HMarkVO hMark) {
		houseBoardMapper.insertHeart(hMark);
	}

	@Override
	public void deleteHeart(HMarkVO hMark) {
		houseBoardMapper.deleteHeart(hMark);
	}
	
	// ========== 스크랩 ========== //
	@Override
	public int countScrap(Integer house_num) {
		return houseBoardMapper.countScrap(house_num);
	}

	@Override
	public String checkScrap(HMarkVO hMark) {
		return houseBoardMapper.checkScrap(hMark);
	}

	@Override
	public void insertScrap(HMarkVO hMark) {
		houseBoardMapper.insertScrap(hMark);
		
	}

	@Override
	public void deleteScrap(HMarkVO hMark) {
		houseBoardMapper.deleteScrap(hMark);
	}

	@Override
	public int selectAllSearchRowCount(Map<String, Object> map) {
		return houseBoardMapper.selectAllSearchRowCount(map);
	}

	@Override
	public List<HouseBoardVO> selectAllSearchList(Map<String, Object> map) {
		return houseBoardMapper.selectAllSearchList(map);
	}
	
}
