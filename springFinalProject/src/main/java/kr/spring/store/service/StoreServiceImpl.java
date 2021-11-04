package kr.spring.store.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.store.dao.StoreMapper;
import kr.spring.store.vo.StoreVO;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreMapper storeMapper;
	
	@Override
	public void productRegister(StoreVO storeVO) {
		storeMapper.productRegister(storeVO);
	}

	@Override
	public List<StoreVO> selectList(Map<String, Object> map) {
		return storeMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return storeMapper.selectRowCount(map);
	}

	@Override
	public StoreVO selectProduct(Integer prod_num) {
		return storeMapper.selectProduct(prod_num);
	}

	@Override
	public void updateProduct(StoreVO storeVO) {
		storeMapper.updateProduct(storeVO);
	}

	@Override
	public void deleteThumbnail(Integer prod_num) {
		storeMapper.deleteThumbnail(prod_num);
	}

	@Override
	public void deleteProduct(Integer prod_num) {
		storeMapper.deleteCart(prod_num);
		storeMapper.deleteReview(prod_num);
		storeMapper.deleteProduct(prod_num);
	}

	@Override
	public int selectStoreAllSearchRowCount(Map<String, Object> map) {
		return storeMapper.selectStoreAllSearchRowCount(map);
	}

	@Override
	public List<StoreVO> selectStoreAllSearchList(Map<String, Object> map) {
		return storeMapper.selectStoreAllSearchList(map);
	}
}
