package kr.spring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.store.service.StoreService;
import kr.spring.store.vo.StoreVO;

public class AdminAndSellerCheckInterceptor extends HandlerInterceptorAdapter{
	
private static final Logger logger = LoggerFactory.getLogger(WriterCheckInterceptor.class);
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.debug("===== 로그인한 회원이 판매자거나 관리자인지 확인 =====");
		
		HttpSession session = request.getSession();
		
		// 로그인한 회원번호 구하기
		Integer user_num = (Integer)session.getAttribute("user_num");
		MemberVO memberVO = memberService.selectMember(user_num);
		
		Integer auth = memberVO.getMem_auth();

		// 상품 정보 확인
		int prod_num = Integer.parseInt(request.getParameter("prod_num")); // 전송되는 데이터 뽑아내기
		StoreVO storeVO = storeService.selectProduct(prod_num); // 한 건의 레코드 구하기

		logger.debug("<<작성자 회원번호>> : " + storeVO.getMem_num());
		logger.debug("<<로그인 회원번호>> : " + user_num);
		logger.debug("<<회원 등급 확인>> : " + auth);
		
		// 멤버 등급이 판매자 보다 낮거나 게시글을 등록한 판매자가 아닐 경우
		if(auth < 3 || user_num != storeVO.getMem_num()) {
			
			logger.debug("<<관리자 또는 등록한 판매자 계정이 아닙니다.>>");
			
			request.setAttribute("accessMsg", "잘못된 접근입니다.");
			request.setAttribute("accessBtn", "로그아웃");
			request.setAttribute("accessUrl", request.getContextPath() + "/member/logout.do");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			
			dispatcher.forward(request, response);
			
			return false;
		}	// end of if
		
		logger.debug("<<관리자 또는 등록한 판매자 계정 입니다.>>");
		
		return true;
	}
}
