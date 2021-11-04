package kr.spring.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.cart.vo.CartVO;

public interface CartMapper {

	// 장바구니 추가
	@Insert("INSERT INTO cart(cart_num, prod_num, mem_num, cart_quan) VALUES(cart_seq.nextval, #{prod_num}, #{mem_num}, #{cart_quan})")
	public void cartInsert(CartVO cartVO);
	// 장바구니 조회
	@Select("SELECT c.cart_num, d.buis_name, c.mem_num, p.prod_num, m.mem_name, p.prod_name, p.delive_price, p.delive_type, c.cart_quan, p.prod_price,(prod_price * cart_quan + p.delive_price) money, p.thumbnail_img, p.thumbnail_filename FROM mem_detail m, product p, cart c, buis_detail d WHERE m.mem_num = c.mem_num AND p.prod_num = c.prod_num AND p.mem_num = d.mem_num AND c.mem_num = #{mem_num} ORDER BY c.cart_num")
	public List<CartVO> cartList(int mem_num);
	// 장바구니 상품 삭제
	@Delete("DELETE FROM cart WHERE cart_num = #{cart_num}")
	public void cartDelete(int cart_num);
	// 장바구니 수량 수정
	@Update("UPDATE cart SET cart_quan = ${cart_quan} WHERE mem_num = #{mem_num} AND prod_num = ${prod_num}")
	public void cartUpdate(CartVO cartVO); //장바구니 수정
	// 장바구니 총 개수 세기
	@Select("SELECT count(*) FROM cart WHERE mem_num = #{mem_num} AND prod_num = #{prod_num}")
	public int cartCount(CartVO cartVO); //장바구니 총 개수 세기
	// 장바구니 수량 증가
	@Update("UPDATE cart SET cart_quan = cart_quan + ${cart_quan} WHERE mem_num = #{mem_num} AND prod_num = ${prod_num}")
	public void currentUpdate(CartVO cartVO); //장바구니 개수 변경
	// 상품 썸네일 이미지 표시
	@Select("SELECT thumbnail_img, thumbnail_filename FROM product WHERE prod_num = #{prod_num}")
	public CartVO cartImg(int prod_num);

}
