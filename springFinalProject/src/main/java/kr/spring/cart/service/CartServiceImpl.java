package kr.spring.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cart.dao.CartMapper;
import kr.spring.cart.vo.CartVO;
@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void cartInsert(CartVO cartVO) {
		cartMapper.cartInsert(cartVO);
	}

	@Override
	public List<CartVO> cartList(int mem_num) {
		return cartMapper.cartList(mem_num);
	}

	@Override
	public void cartDelete(int cart_num) {
		cartMapper.cartDelete(cart_num);
	}

	@Override
	public void cartUpdate(CartVO cartVO) {
		cartMapper.cartUpdate(cartVO);
	}

	@Override
	public void currentUpdate(CartVO cartVO) {
		cartMapper.currentUpdate(cartVO);
	}
	
	@Override
	public CartVO cartImg(int prod_num) {
		return cartMapper.cartImg(prod_num);
	}

	@Override
	public int cartCount(CartVO cartVO) {
		return cartMapper.cartCount(cartVO);
	}

}
