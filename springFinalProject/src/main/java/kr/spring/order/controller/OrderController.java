package kr.spring.order.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderVO;
import kr.spring.store.service.StoreService;
import kr.spring.store.vo.StoreVO;
import kr.spring.util.PagingUtil;

/*
 * 파일명 : StoreController
 * 작성자 : 박용복
 * 설명 : 쇼핑몰(주문) 컨트롤러 
 */

@Controller
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	// 한 페이지에 표시할 수량
	private int rowCount = 20;
	// 페이지 카운트
	private int pageCount = 10;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private StoreService storeService;
	
	// 자바빈 초기화
	@ModelAttribute
	public OrderVO initCommand() {
		return new OrderVO();
	}
	
	// 주문 메인 호출
	@GetMapping("/order/orderMain.do")
	public String orderPage(OrderVO order, MemberVO member, StoreVO storeVO, HttpServletRequest request, HttpSession session, Model model) {
		logger.debug("<<주문 페이지 호출>>");
		
		// 로그인한 회원의 정보를 세팅
		Integer check = (Integer)session.getAttribute("user_num");
		
		if(check != null) {
			// 로그인한 회원의 정보를 주문 테이블에 세팅
			member.setMem_num((Integer)session.getAttribute("user_num"));
			order.setMem_num(member.getMem_num());
			member = memberService.selectMember(member.getMem_num());
			model.addAttribute("memberVO", member);
		}
		// 수량 정보를 가져 옴
		Integer quan = Integer.parseInt(request.getParameter("quan"));
		// 상품 옵션 정보를 가져 옴
		String prod_option = request.getParameter("prod_option");
		
		logger.debug("<<회원 정보>> : " + member.getMem_num());
		
		storeVO = storeService.selectProduct(storeVO.getProd_num());
		
		// 수량 정보 세팅
		storeVO.setQuan(quan);
		// 상품 옵션 세팅
		storeVO.setCommit_option(prod_option);
		 
		logger.debug("<<주문 내용 삽입 멤버 정보>> : " + member);
		logger.debug("<<주문 내용 삽입 상품 정보>> : " + storeVO);
		 
		model.addAttribute("storeVO", storeVO);
		
		// 타일스 식별자
		return "orderMain";
	}
	
	// 카카오 결제 컨트롤러
	@RequestMapping("/order/kakao.do")
	@ResponseBody
	public String kakaopay(OrderVO orderVO, HttpServletRequest request) {
		
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			
			String receiver_name = request.getParameter("receiver_name");
			orderVO.setReceiver_name(receiver_name);
			String receiver_phone = request.getParameter("receiver_phone");
			orderVO.setReceiver_phone(receiver_phone);
			String order_zipcode = request.getParameter("order_zipcode");
			orderVO.setOrder_zipcode(order_zipcode);
			String order_address1 = request.getParameter("order_address1");
			orderVO.setOrder_address1(order_address1);
			String order_address2 = request.getParameter("order_address2");
			orderVO.setOrder_address2(order_address2);
			String buis_name = request.getParameter("buis_name");
			orderVO.setBuis_name(buis_name);
			String prod_name = request.getParameter("prod_name");
			orderVO.setProd_name(prod_name);
			int pay_price = Integer.parseInt(request.getParameter("final_price"));
			orderVO.setPay_price(pay_price);
			int quan = Integer.parseInt(request.getParameter("quan"));
			orderVO.setPay_quan(quan);
			String prod_opt = request.getParameter("commit_option");
			orderVO.setProd_opt(prod_opt);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK 023a91daaf2d3e17aca5d8af0d4739a8");
			conn.setRequestProperty("Accept", "APPLICATION_JSON_UTF8_VALUE");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("cid", "TC0ONETIME");
			map.put("partner_order_id", "partner_order_id");
			map.put("partner_user_id", orderVO.getBuis_name());
			map.put("item_name", orderVO.getProd_name());
			map.put("quantity", String.valueOf(orderVO.getPay_quan()));
			map.put("total_amount", String.valueOf(orderVO.getPay_price()));
			map.put("tax_free_amount", "0");
			map.put("approval_url", "http://localhost:8081/EverydayHome/order/orderSuccess.do");
			map.put("fail_url", "http://localhost:8081/EverydayHome/member/login.do");
			map.put("cancel_url", "http://localhost:8081/EverydayHome/store/storeCategory.do");
			
			String params = new String();
			for(Map.Entry<String, String> emap : map.entrySet()) {
				params += (emap.getKey() + "=" + emap.getValue() + "&");
			}
			
			OutputStream os = conn.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeBytes(params);
			dos.flush();
			dos.close();
			
			int result = conn.getResponseCode();
			logger.debug("result" + result);
			
			InputStream is;
			
			if(result == 200) {
				is = conn.getInputStream();
			}else {
				is = conn.getErrorStream();
			}
			
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			return br.readLine();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "{\"result\":\"NO\"}";
	}
	
	// 주문내역 DB에 등록
	@RequestMapping("/order/orderInsert.do")
	@ResponseBody
	public Map<String, String> orderInsert(OrderVO orderVO, HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		logger.debug("<<받는 사람 정보>>" + orderVO);
		
		Integer prod_num = Integer.parseInt(request.getParameter("prod_num"));
		orderVO.setProd_num(prod_num);
		
		orderService.insertOrder(orderVO);
		map.put("result", "success");
		
		return map;
	}
	
	// 카카오 페이 주문 완료 페이지 호출(바로 닫힘)
	@GetMapping("/order/orderSuccess.do")
	public String orderSuccess() {
		
		return "orderSuccess";
	}
	
	// 주문 성공 후 이동할 페이지
	@GetMapping("/order/orderComplete.do")
	public String orderComplete() {
		
		logger.debug("주문 성공 페이지 호출");
		
		return "orderComplete";
	}
	
	// 비회원 주문 조회 페이지
	@GetMapping("/order/nonCheck.do")
	public String nonCheck() {
		
		logger.debug("비회원 주문 조회");
		
		return "nonCheck";
	}
	
	// 비회원 주문 조회
	@RequestMapping("/order/orderNonCheck.do")
	public ModelAndView process(@Valid OrderVO orderVO, BindingResult result, @RequestParam(value="pageNum", defaultValue="1") int currentPage, @RequestParam String receiver_phone) {
		
		logger.debug("<<비회원 주문 조회>>");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receiver_phone", receiver_phone);
		
		int count = orderService.selectNonRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		// 페이지 처리
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "orderNonCheck.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<OrderVO> list = null;
		ModelAndView mav = new ModelAndView();
		
		if(count > 0) {
			list = orderService.selectNonList(map);
			logger.debug("<<list>> : " + list);
		}
		
		if(result.hasErrors()) {
			mav.setViewName("nonCheck");
			return mav;
		}
		
		mav.setViewName("orderNonCheck");
		mav.addObject("orderVO", orderVO);
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	// 나의 주문 내역 조회
	@RequestMapping("/order/myOrder.do")
	public ModelAndView process(OrderVO orderVO, @RequestParam(value="pageNum", defaultValue="1") int currentPage, HttpSession session) {
		
		logger.debug("<<회원 주문 조회>>");
		
		Integer mem_num = (Integer)session.getAttribute("user_num");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_num", mem_num);
		
		int count = orderService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		// 페이지 처리
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "myOrder.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<OrderVO> list = null;
		ModelAndView mav = new ModelAndView();
		
		if(count > 0) {
			list = orderService.selectList(map);
			logger.debug("<<list>> : " + list);
		}
		
		mav.setViewName("myOrder");
		mav.addObject("orderVO", orderVO);
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
}
