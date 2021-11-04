package kr.spring.store.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.store.vo.StoreVO;

public interface StoreMapper {
	
	// 상품 등록
	@Insert("INSERT INTO product(prod_num, prod_name, prod_price, delive_price, "
			+ "delive_type, selec_product, prod_option1, prod_option2, "
			+ "prod_option3, prod_option4, prod_option5, prod_option6, "
			+ "prod_option7, prod_option8, prod_option9, prod_option10, "
			+ "prod_content, thumbnail_img, thumbnail_filename, prod_quan, "
			+ "prod_keyword, mem_num, prod_cate) "
			+ "VALUES(product_seq.nextval, #{prod_name}, #{prod_price}, #{delive_price}, "
			+ "#{delive_type}, #{selec_product}, #{prod_option1}, #{prod_option2}, "
			+ "#{prod_option3}, #{prod_option4}, #{prod_option5}, #{prod_option6}, "
			+ "#{prod_option7}, #{prod_option8}, #{prod_option9}, #{prod_option10}, "
			+ "#{prod_content}, #{thumbnail_img}, #{thumbnail_filename}, #{prod_quan}, "
			+ "#{prod_keyword}, #{mem_num}, #{prod_cate})")
	public void productRegister(StoreVO storeVO);
	// 상품 목록 조회
	public List<StoreVO> selectList(Map<String, Object> map);
	// 상품 페이지 설정
	public int selectRowCount(Map<String, Object> map);
	// 상품 조회
	@Select("SELECT * FROM product p JOIN member m ON p.mem_num = m.mem_num JOIN buis_detail d ON d.mem_num = m.mem_num WHERE p.prod_num = #{prod_num}")
	public StoreVO selectProduct(Integer prod_num);
	// 상품 수정
	public void updateProduct(StoreVO storeVO);
	// 썸네일 이미지 제거
	@Update("UPDATE product SET thumbnail_img = '', thumbnail_filename = '' WHERE prod_num = #{prod_num}")
	public void deleteThumbnail(Integer prod_num);
	// 상품 제거
	@Delete("DELETE FROM product WHERE prod_num = #{prod_num}")
	public void deleteProduct(Integer prod_num);
	// 장바구니에 담겨 있는 상품 제거
	@Delete("DELETE FROM cart WHERE prod_num = #{prod_num}")
	public void deleteCart(Integer prod_num);
	// 상품 리뷰 제거
	@Delete("DELETE FROM product_review WHERE prod_num = #{prod_num}")
	public void deleteReview(Integer prod_num);
	
	// =============== 통합검색 =============== //
	public int selectStoreAllSearchRowCount(Map<String,Object> map);		// 통합 검색 결과 게시글 수
	public List<StoreVO> selectStoreAllSearchList(Map<String,Object> map);		// 통합 검색 결과 게시글 리스트
}
