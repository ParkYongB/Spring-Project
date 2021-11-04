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

public class SellerCheckInterceptor extends HandlerInterceptorAdapter{
	
private static final Logger logger = LoggerFactory.getLogger(WriterCheckInterceptor.class);
	
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.debug("===== 로그인한 회원이 판매자인지 확인 =====");
		
		HttpSession session = request.getSession();
		
		Integer mem_num = (Integer)session.getAttribute("user_num");
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		Integer auth = memberVO.getMem_auth();
		
		logger.debug("<<회원 등급 확인>> : " + auth);
		
		// 회원등급이 존재하지 않을 경우
		if(auth == 0) {
			logger.debug("<<로그인이 되어 있지 않습니다.>>");
			
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			
			return false;
		} 	// end of if
		if(auth != 3) {
			logger.debug("<<로그인한 회원이 판매자가 아닙니다.>>");
			
			request.setAttribute("accessMsg", "잘못된 접근입니다.");
			request.setAttribute("accessBtn", "목록");
			request.setAttribute("accessUrl", request.getContextPath() + "/store/storeCategory.do");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			
			dispatcher.forward(request, response);
			
			return false;
		}	// end of if
		
		logger.debug("<<로그인한 회원이 판매자 입니다.>>");
		
		return true;
	}
}
