package kr.spring.qna.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.qna.service.QnaService;
import kr.spring.qna.vo.QnaVO;
import kr.spring.serviceBoard.service.ServiceBoardService;
import kr.spring.serviceBoard.vo.ServiceBoardVO;
import kr.spring.util.PagingUtil;

@Controller
public class QnaController {
		//로그 처리 (로그 대상 지정)
		private static final Logger log = LoggerFactory.getLogger(QnaController.class);
		private int rowCount = 20;
		private int pageCount = 10;
		
		@Autowired
		private ServiceBoardService serviceBoardService;
		
		@Autowired
		private JavaMailSender mailSender;	//자바 메일 전송 객체 생성
		
		@Autowired
		private QnaService qnaService;
		
		//자바빈 초기화
		@ModelAttribute
		public QnaVO initCommand() {
			return new QnaVO();
		}
		
		@GetMapping("/qna/qnaInsert.do")
		//글쓰기 폼 호출
		public ModelAndView qnaInsert(@RequestParam(value="pageNum",defaultValue="1") int currentPage) {
			
			log.debug("<<글쓰기 폼 호출>>");
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			log.debug("<<currentPage>>: " + currentPage);
			
			//글의 총갯수 또는 검색된 글의 갯수
			int count = serviceBoardService.selectRowCount(map);
			
			//페이지 처리
			PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount, "qnaInsert.do");
			
			//목록 호출
			List<ServiceBoardVO> list = null;
			if(count>0) {
				map.put("start", page.getStartCount());
				map.put("end", page.getEndCount());
				
				list = serviceBoardService.getServiceBoardList(map);
			}
			
			//전달 객체 생성
			ModelAndView mav = new ModelAndView();
			//뷰 이름 설정
			mav.setViewName("qnaInsert");
			//데이터 저장
			mav.addObject("count",count);
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			
			
			return mav;
		}
		//글쓰기 처리
		@PostMapping("/qna/qnaInsert.do")
		public String qnaInsertSubmit(QnaVO vo) {
			//유효성 체크 결과 오류가 있으면 폼 호출
			
			//글쓰기
			qnaService.qnaInsert(vo);
			
			return "redirect:/qna/qnaList.do";
		}

		
		//qnaServiceList 게시판 목록
		@RequestMapping("/qna/qnaServiceList.do")
		public ModelAndView getQnaServiceList(
			@RequestParam(value="pageNum", defaultValue="1")int currentPage) {
			
			log.debug("<<currentPage>>: " + currentPage);
			
			//총 레코드 수
			int count = qnaService.getQnaServiceCount();
			
			//페이지 처리
			PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "qnaServiceList.do");
			
			//목록 호출
			List<QnaVO> list = null;
			if(count>0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("start", page.getStartCount());
				map.put("end", page.getEndCount());
				
				list = qnaService.getQnaServiceList(map);
			}
			
			ModelAndView mav = new ModelAndView();
			
			//뷰 이름 설정
			mav.setViewName("qnaServiceList");
			
			//데이터 저장
			mav.addObject("count",count);
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			
			return mav;
			
		}
		
		
		//qnaList 게시판 목록
		@RequestMapping("/qna/qnaList.do")
		public ModelAndView getQnaList(
				@RequestParam(value="pageNum", defaultValue="1")int currentPage,
				@RequestParam(value="keyword", defaultValue="") String keyword,
				@RequestParam(value="keyfield",defaultValue = "1") String keyfield) {
			
			log.debug("<<currentPage>>: " + currentPage);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", keyword);
			map.put("keyfield", keyfield);
			
			//총 레코드 수
			int count = qnaService.getQnaCount(map);
			
			//페이지 처리
			PagingUtil page = new PagingUtil(null, keyfield, currentPage, count, 50, 5, "qnaList.do");
			
			//목록 호출
			List<QnaVO> list = null;
			if(count>0) {
				
				map.put("start", page.getStartCount());
				map.put("end", page.getEndCount());
				
				
				list = qnaService.getQnaList(map);
			}
			ModelAndView mav = new ModelAndView();
			//뷰 이름 설정
			mav.setViewName("qnaList");
			
			//데이터 저장
			mav.addObject("count",count);
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			
			return mav;
			
		}
	
		//수정폼
		@GetMapping("/qna/qnaUpdate.do")
		public ModelAndView qnaUpdate(
				@RequestParam(value="pageNum",defaultValue="1") int currentPage,
				@RequestParam int qna_num) {
			
			log.debug("<<글 수정 폼 호출>>");
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			//글의 총갯수 또는 검색된 글의 갯수
			int count = serviceBoardService.selectRowCount(map);
			
			//페이지 처리
			PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount, "qnaUpdate.do");
			
			//목록 호출
			List<ServiceBoardVO> list = null;
			if(count>0) {
				map.put("start", page.getStartCount());
				map.put("end", page.getEndCount());
				
				list = serviceBoardService.getServiceBoardList(map);
			}
			
			// 문의 정보 가져오기
			QnaVO qna = qnaService.getQna(qna_num);
			
			//전달 객체 생성
			ModelAndView mav = new ModelAndView();
			//뷰 이름 설정
			mav.setViewName("qnaUpdate");
			//데이터 저장
			mav.addObject("count",count);
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			mav.addObject("qnaVO", qna);
			
			return mav;
			
		}
		//수정 처리
		@PostMapping("/qna/qnaUpdate.do")
		public String qnaUpdateSubmit(QnaVO vo) {
			
			log.debug("<<글 수정 객체>> : " + vo);
			qnaService.qnaUpdate(vo);
			
			return "redirect:/qna/qnaList.do";
		}
		
		//글 삭제 처리
		@PostMapping("/qna/qnaDelete.do")
		@ResponseBody()
		public Map<String, String> qnaDeleteSubmit(@RequestParam String output) {
			String[] stopChecked = output.split(",");
			Map<String,String> mapAjax = new HashMap<String,String>();
			
			for(String qna_num : stopChecked) {
				qnaService.qnaDelete(Integer.parseInt(qna_num));		// 바꿀부분
				
				mapAjax.put("result", "success");
			}
			
			return mapAjax;
		}
		

		//serviceBoard---------------------------------------------
		//자바빈 초기화
		@ModelAttribute
		public ServiceBoardVO initCommand1() {
			return new ServiceBoardVO();
		}
		
		@GetMapping("/qna/serviceBoardInsert.do")
		//글쓰기 폼 호출
		public String serviceBoardInsertForm() {
			log.debug("<<글쓰기 폼 호출>>");
			
			return "serviceBoardInsert";
		}
		//글쓰기 처리
		@PostMapping("/qna/serviceBoardInsert.do")
		public String submit(@Valid ServiceBoardVO serviceboard, BindingResult result,
				HttpSession session, HttpServletRequest request) {
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
				return serviceBoardInsertForm();
			}
			//글쓰기
			serviceBoardService.serviceBoardInsert(serviceboard);
			
			return "redirect:/qna/qnaList.do";
		}
		
		
		
		// 이메일 문의 목록
		@RequestMapping("/qna/serviceBoardList.do")
		public ModelAndView getServiceBoardList(
				@RequestParam(value="pageNum",defaultValue="1") int currentPage,
				@RequestParam(value="keyfield",defaultValue="") String keyfield,
				@RequestParam(value="keyword",defaultValue="") String keyword) {
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			
			log.debug("<<currentPage>>: " + currentPage);
			
			//글의 총갯수 또는 검색된 글의 갯수
			int count = serviceBoardService.selectRowCount(map);
			
			//총 레코드 수
			//int count = serviceBoardService.getServiceBoardCount();
			
			//페이지 처리
			PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount, "serviceBoardList.do");
			
			//목록 호출
			List<ServiceBoardVO> list = null;
			if(count>0) {
				map.put("start", page.getStartCount());
				map.put("end", page.getEndCount());
				
				list = serviceBoardService.getServiceBoardList(map);
			}
			
			//전달 객체 생성
			ModelAndView mav = new ModelAndView();
			//뷰 이름 설정
			mav.setViewName("serviceBoardList");
			//데이터 저장
			mav.addObject("count",count);
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			
			return mav;
			
		}
		//글 상세
		@RequestMapping("/qna/serviceBoardDetail.do")
		public ModelAndView serviceBoardDetail(@RequestParam int service_num) {
			ServiceBoardVO serviceboard = serviceBoardService.getServiceBoard(service_num);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("serviceBoardDetail"); // 타일스 식별자
			mav.addObject("serviceboard", serviceboard);
			
			return mav;
		}
		
		//이미지 출력
		@RequestMapping("/qna/imageView.do")
		public ModelAndView viewImage(@RequestParam int service_num) {
			
		ServiceBoardVO serviceboard = serviceBoardService.getServiceBoard(service_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		                //속성명         속성값(byte[]의 데이터)     
		mav.addObject("imageFile", serviceboard.getService_file());
		mav.addObject("filename", serviceboard.getService_filename());
				
		return mav;
	}
		
		//이메일 전송
		@PostMapping("/qna/sendEmail.do")
		public String sendMail(String service_email, String service_reply)throws Exception{
			
			/* 뷰(View)로부터 넘어온 데이터 확인 */
	        log.info("이메일 데이터 전송 확인");
	        log.info("이메일 : " + service_email);
	        
	        String title = "Re: 매일의 홈에 문의 주셔서 감사합니다.";	
	        String content = "안녕하세요. 항상 매일의 홈에 관심을 갖고 이용해 주셔서 감사드립니다.<br>"
			        		+ "요청하신 사항은 담당 부서로 전달하도록 하겠습니다.<br>"
			        		+ "앞으로 이용에 불편함이 없도록 노력하는 매일의 홈이 되겠습니다.<br>"
			        		+ "오늘도 좋은 하루 보내시길 바랍니다.<br>"
			        		+ "감사합니다.<br><hr size='1' noshade='noshade'><br>" 
			        		+ "Re: " + service_reply;
	        
	        String fromEmail = "매일의 홈 <springtest1010@gmail.com>";
	        String toEmail = service_email;
	        
	        
	        try {
	            MimeMessage mail = mailSender.createMimeMessage();
	            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
	            
	            
	            /*
	             * Multipart 기능을 사용하기 위해서는 아래의 코드로 사용 가능 
	             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
	             * true는 멀티파트 메세지를 사용하겠다는 의미
	             */
	            
	            mailHelper.setFrom(fromEmail);	// 보내는이
	            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
	            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용
	            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
	            mailHelper.setTo(toEmail);
	            mailHelper.setSubject(title);
	            mailHelper.setText(content, true);	// 단순한 텍스트만 사용
	            // true는 html을 사용하겠다는 의미.
	            
	            // html 불허용 : mailHelper.setText(content);
	            mailSender.send(mail);		// 메일 전송
	            
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
			return "redirect:/qna/qnaList.do";
	        
	    }
}
