package kr.spring.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;

/*
 * 파일명 : StoreController
 * 작성자 : 박용복
 * 설명 : 쇼핑몰(장바구니) 컨트롤러 
 */

@Controller
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;
	
	// 자바빈 초기화
	@ModelAttribute
	public CartVO initCartVO() {
		return new CartVO();
	}
	
	//장바구니 목록
	@RequestMapping("/cart/cartList.do")
	public ModelAndView cartList(CartVO cartVO, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		// 로그인한 회원의 정보를 가져 옴
		Integer user_num = (Integer)session.getAttribute("user_num");
		logger.debug("<<로그인한 회원 회원 번호>> : " + user_num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CartVO> list = cartService.cartList(user_num);
		
		map.put("list", list);
		map.put("count", list.size());
		
		mav.addObject("cart", map);
		mav.setViewName("cartList");
		logger.debug("<<장바구니 갯수 확인>> : " + list.size());
		
		return mav;
	}
	
	// 장바구니 추가
	@RequestMapping("/cart/cartInsert.do")
	@ResponseBody
	public Map<String, String> cartInsert(CartVO cart, HttpSession session) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		cart.setMem_num((Integer)session.getAttribute("user_num"));
		
		logger.debug("<<mem_num이 저장 되었는지 확인>> : " + cart.getMem_num());
		
		// 장바구니에 지금 추가하려는 상품이 있는지 확인
		int count = cartService.cartCount(cart);
		
		logger.debug("<<카트 카운트 확인>> : " + count);

		if(count == 0) {
			// 없으면 insert
			cartService.cartInsert(cart);
			map.put("result", "add_success");
		}else {
			// 있으면 수량 증가
			cartService.currentUpdate(cart);
			map.put("result", "cart_update");
		}
		return map;
	}
	
	// 장바구니 삭제
	@RequestMapping("/cart/cartDelete.do")
	public String delete(@RequestParam int cart_num) {
		cartService.cartDelete(cart_num);
		
		return "redirect:/cart/cartList.do";
	}
	
	// 장바구니 수정
	@RequestMapping("/cart/cartUpdate.do")
	public String cartUpdate(@RequestParam int[] cart_quan, @RequestParam int[] prod_num, HttpSession session) {

		// 로그인한 회원의 정보를 가져 옴
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		for(int i = 0; i < prod_num.length; i++) {
			
			CartVO cart = new CartVO();
			
			cart.setMem_num(user_num);
			cart.setProd_num(prod_num[i]);
			cart.setCart_quan(cart_quan[i]);
			
			cartService.cartUpdate(cart);
			logger.debug ("<<장바구니 업데이트>> : " + cart);
		}
		
		return "redirect:/cart/cartList.do";
	}
	
	// 장바구니 이미지 출력
	@GetMapping("/cart/imageView.do")
	public ModelAndView viewImage(@RequestParam int prod_num) {
			
		CartVO cart = cartService.cartImg(prod_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		                // 속성명         속성값(byte[]의 데이터)     
		mav.addObject("imageFile", cart.getThumbnail_img());
		mav.addObject("filename", cart.getThumbnail_filename());
			
		return mav;
	}
}

