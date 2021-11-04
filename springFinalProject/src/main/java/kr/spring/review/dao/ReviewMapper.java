package kr.spring.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.review.vo.ReviewVO;

public interface ReviewMapper {
	@Insert("INSERT INTO product_review(rev_num, rev_content, rev_grade, rev_reg_date, rev_img, rev_filename, prod_num, mem_num) VALUES(product_review_seq.nextval, #{rev_content}, #{rev_grade}, sysdate, #{rev_img}, #{rev_filename}, #{prod_num}, #{mem_num})")
	public void reviewInsert(ReviewVO review);//리뷰등록
	@Select("SELECT r.rev_num, r.rev_content, r.rev_grade,TO_CHAR(r.rev_reg_date,'YYYY-MM-DD')as rev_reg_date, r.rev_img, r.rev_filename, r.prod_num, r.mem_num, m.mem_name, p.prod_price, p.prod_name, p.prod_num, p.thumbnail_img, p.thumbnail_filename FROM product_review r, mem_detail m, product p WHERE m.mem_num=r.mem_num AND r.prod_num = p.prod_num AND r.rev_num=${rev_num}")
	public ReviewVO reviewDetail(@Param("rev_num")int rev_num);//리뷰읽기
	@Select("SELECT rev_filename, rev_img, p.thumbnail_img, p.thumbnail_filename  FROM product_review r, product p WHERE r.prod_num=p.prod_num AND rev_num = ${rev_num}")
	public ReviewVO reviewImgStore(@Param("rev_num")int rev_num);//리뷰이미지파일
	@Update("UPDATE product_review SET rev_content = #{rev_content}, rev_grade=#{rev_grade}, rev_filename =#{rev_filename}, rev_img = #{rev_img} WHERE rev_num = #{rev_num}")
	public void reviewUpdate(ReviewVO review);//리뷰수정
	@Delete("DELETE FROM product_review WHERE rev_num = #{rev_num}")
	public void reviewDelete(int rev_num); //리뷰삭제
	@Select("SELECT o.prod_num as prod_num, p.prod_name as prod_name, p.prod_option1 as prod_option1, o.order_date as order_date, o.buis_name as buis_name , p.prod_price as prod_price  FROM orders o, product p WHERE  p.prod_num = o.prod_num AND o.mem_num = ${mem_num} ORDER BY o.order_date")
	public List<ReviewVO> reviewbuyList(@Param("mem_num")int mem_num);
	@Select("SELECT r.rev_num, r.rev_content, r.rev_grade,TO_CHAR(r.rev_reg_date,'YYYY-MM-DD')as rev_reg_date,"
			+ " r.rev_img, r.rev_filename, r.prod_num, r.mem_num, m.mem_name,  p.prod_price, p.prod_name, p.prod_num, p.thumbnail_img, p.thumbnail_filename "
			+ "FROM product_review r, mem_detail m, product p WHERE m.mem_num=r.mem_num AND r.prod_num = p.prod_num  AND r.mem_num=${mem_num}")
	public List<ReviewVO> reviewList(@Param("mem_num")int mem_num);//리뷰리스트
	@Select("SELECT r.rev_num, r.rev_content, r.rev_grade,TO_CHAR(r.rev_reg_date,'YYYY-MM-DD')as rev_reg_date, r.rev_img, r.rev_filename, r.prod_num, r.mem_num, m.mem_name FROM product_review r, mem_detail m WHERE m.mem_num=r.mem_num AND prod_num =${prod_num}")
	public List<ReviewVO> reviewListStore(@Param("prod_num")int prod_num);//리뷰 스토어에서 보게하기
	@Select("SELECT count(*) FROM product_review WHERE mem_num=#{mem_num}")
	public int reviewMyCount(int mem_num);//내 리뷰 총 개수 세기
	@Select("SELECT count(*) FROM product_review WHERE prod_num=#{prod_num}")
	public int reviewStoreCount(int prod_num);//상품 리뷰 총 개수 세기
	@Update("UPDATE product_review SET rev_filename = '', rev_img = '' WHERE rev_num = #{rev_num}")
	public int reviewFileDelete(int rev_num);//리뷰 파일 삭제
	@Select("SELECT COUNT(*) FROM orders o WHERE mem_num = #{mem_num}")
	public int reviewBuyCount(int mem_num);
	@Select("select count(*) FROM product_review WHERE mem_num = #{mem_num} AND prod_num= #{prod_num}")
	public int reviewExist(@Param("mem_num")int mem_num, @Param("prod_num")int prod_num);//리뷰쓴적잇는지 확인
	@Select("SELECT COUNT(*) FROM orders o WHERE mem_num =#{mem_num} AND prod_num =#{prod_num}")
	public int orderExist(@Param("mem_num")int mem_num,@Param("prod_num") int prod_num);
	@Select("select p.prod_name as prod_name, p.prod_price as prod_price, b.buis_name as buis_name, p.thumbnail_img as thumbnail_img, p.thumbnail_filename as thumbnail_filename FROM product p, buis_detail b WHERE p.mem_num = b.mem_num AND prod_num=${prod_num}")
	public ReviewVO productDetail(@Param("prod_num")int prod_num);
	@Update("UPDATE product_review SET rev_filename = '', rev_img = '' WHERE rev_num = #{rev_num}")
	public void deleteFile(@Param("rev_num")int rev_num);
	@Select("SELECT ROUND(AVG(rev_grade),2) as star FROM product_review WHERE prod_num = #{prod_num}")
	public Integer staravg(int prod_num);
}
