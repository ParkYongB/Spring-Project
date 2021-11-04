package kr.spring.store.service;

import java.util.List;
import java.util.Map;

import kr.spring.store.vo.StoreVO;

public interface StoreService {

	public void productRegister(StoreVO storeVO);
	public List<StoreVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	public StoreVO selectProduct(Integer prod_num);
	public void updateProduct(StoreVO storeVO);
	public void deleteThumbnail(Integer prod_num);
	public void deleteProduct(Integer prod_num);
	
	
	// =============== 통합검색 =============== //
	public int selectStoreAllSearchRowCount(Map<String,Object> map);		// 통합 검색 결과 게시글 수
	public List<StoreVO> selectStoreAllSearchList(Map<String,Object> map);		// 통합 검색 결과 게시글 리스트
}
