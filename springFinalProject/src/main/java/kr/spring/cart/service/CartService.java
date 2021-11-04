package kr.spring.cart.service;

import java.util.List;

import kr.spring.cart.vo.CartVO;

public interface CartService {
	
	public void cartInsert(CartVO cartVO); //장바구니 추가
	public List<CartVO> cartList(int mem_num); //장바구니 목록
	public void cartDelete(int cart_num); //장바구니 삭제
	public void cartUpdate(CartVO cartVO); //장바구니 수정
	public int cartCount(CartVO cartVO); //장바구니 총 개수 세기
	public void currentUpdate(CartVO cartVO); //장바구니 개수 변경
	public CartVO cartImg(int prod_num);
}
