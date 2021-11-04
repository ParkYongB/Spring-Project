package kr.spring.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.vo.ReviewVO;
@Service
@Transactional
public class ReviewServiewImpl implements ReviewService {
	//객체주입
	@Autowired
		private ReviewMapper reviewMapper;

	@Override
	public void reviewInsert(ReviewVO review) {
		reviewMapper.reviewInsert(review);
	}

	@Override
	public ReviewVO reviewDetail(int rev_num) {
		return reviewMapper.reviewDetail(rev_num);
	}

	@Override
	public void reviewUpdate(ReviewVO review) {
		reviewMapper.reviewUpdate(review);
	}

	@Override
	public void reviewDelete(int rev_num) {
		reviewMapper.reviewDelete(rev_num);
	}



	@Override
	public int reviewMyCount(int mem_num) {
		return reviewMapper.reviewMyCount(mem_num);
	}

	@Override
	public int reviewStoreCount(int prod_num) {
		return reviewMapper.reviewStoreCount(prod_num);
	}

	@Override
	public List<ReviewVO> reviewbuyList(int mem_num) {
		return reviewMapper.reviewbuyList(mem_num);
	}

	@Override
	public List<ReviewVO> reviewList(int mem_num) {
		return reviewMapper.reviewList(mem_num);
	}

	@Override
	public List<ReviewVO> reviewListStore(int prod_num) {
		return reviewMapper.reviewListStore(prod_num);
	}

	@Override
	public void reviewFileDelete(int rev_num) {
		reviewMapper.reviewFileDelete(rev_num);
	}

	@Override
	public int reviewExist(int mem_num, int prod_num) {
		return reviewMapper.reviewExist(mem_num, prod_num);
	}

	@Override
	public int orderExist(int mem_num, int prod_num) {
		return reviewMapper.orderExist(mem_num, prod_num);
	}

	@Override
	public int reviewBuyCount(int mem_num) {
		return reviewMapper.reviewBuyCount(mem_num);
	}

	@Override
	public ReviewVO productDetail(int prod_num) {
		return reviewMapper.productDetail(prod_num);
	}

	@Override
	public void deleteFile(int rev_num) {
		reviewMapper.deleteFile(rev_num);
	}

	@Override
	public ReviewVO reviewImgStore(int prod_num) {
		return reviewMapper.reviewImgStore(prod_num);
	}

	@Override
	public Integer staravg(int prod_num) {
		return reviewMapper.staravg(prod_num);
	}

	
}
