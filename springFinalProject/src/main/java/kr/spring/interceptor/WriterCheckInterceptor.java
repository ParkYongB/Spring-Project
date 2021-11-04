package kr.spring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.houseBoard.service.HouseBoardService;
import kr.spring.houseBoard.vo.HouseBoardVO;

public class WriterCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WriterCheckInterceptor.class);
	
	@Autowired
	private HouseBoardService houseBoardService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.debug("===== 로그인 회원번호와 작성자 회원번호 일치 여부 체크 =====");
		
		// 작성자 회원번호 구하기
		int house_num = Integer.parseInt(request.getParameter("house_num")); // 전송되는 데이터 뽑아내기
		HouseBoardVO houseBoard = houseBoardService.selectHBoard(house_num); // 한 건의 레코드 구하기
		
		// 로그인 회원번호 구하기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		logger.debug("<<작성자 회원번호>> : " + houseBoard.getMem_num());
		logger.debug("<<로그인 회원번호>> : " + user_num);
		logger.debug("<<작성자 권한>>" + user_auth);
		
		// 회원번호 일치 여부 체크
		if(user_num != houseBoard.getMem_num() && user_auth!=4) {
			logger.debug("<<로그인 회원번호와 작성자 회원번호 불일치>>");
			
			request.setAttribute("accessMsg", "로그인 회원번호와 작성자 회원번호 불일치");
			request.setAttribute("accessBtn", "목록");
			request.setAttribute("accessUrl", request.getContextPath() + "/houseBoard/list.do");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			
			dispatcher.forward(request, response);
			
			return false;
		} // end of if
		
		logger.debug("<<로그인 회원번호와 작성자 회원번호 일치>>");
		
		return true;
	}

}
