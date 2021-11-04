package kr.spring.notice.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.notice.service.NoticeService;
import kr.spring.notice.vo.NoticeVO;
import kr.spring.util.PagingUtil;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute
	public NoticeVO initCommand() {

		return new NoticeVO();
	}
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	//공지 리스트 출력
	@RequestMapping("notice/noticeList.do")
	public ModelAndView noticeList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,HttpSession session) {
		
		//총 레코드 수
		int count = noticeService.noticeTotalCount();
		//페이징 처리
		PagingUtil page = new PagingUtil(currentPage, count ,10, 10, "/EverydayHome/notice/noticeList.do");
		//목록출력
		List<NoticeVO> list = null;
		//만약 글이 있을 시
		if(count > 0) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			list = noticeService.noticeGetList(map);
		}
		
		//권한정보 받아오기
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//전달 객체 생성
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("noticeList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("user_auth",user_auth);
		
		return mav;
	}
	//공지 상세정보가기
	@GetMapping("notice/noticeDetail.do")
	public ModelAndView noticeDetail(@RequestParam(value="notice_num") int notice_num,HttpSession session) {
		//조회수 증가 처리
		noticeService.noticeGetHits(notice_num);
		//상세정보 저장
		NoticeVO notice = noticeService.noticeDetail(notice_num);
		//권한정보저장
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		//mav객체생성
		ModelAndView mav= new ModelAndView();
		//갈 페이지 앨리어스
		mav.setViewName("noticeDetail");
		//보낼 데이터 정보
		mav.addObject("notice", notice);
		mav.addObject("user_auth",user_auth);
		
		return mav;
	}
	
	//공지 작성하기
	@GetMapping("/notice/noticeWrite.do")
	//글쓰기 폼 호출
	public String form() {
	
		return "noticeWrite";
	
	}
	//공지 INSERT
	@PostMapping("/notice/noticeWrite.do")
	public String submit(@Valid NoticeVO noticeVo, BindingResult result, HttpSession session) {
		
		logger.debug("<<공지정보>> : " + noticeVo);
		//오류 있을 시 다시 폼호출
		if(result.hasErrors()) {
			return form();
		}
		//세션에서 로그인한 유저 정보를 받아서 저장
		Integer mem_num = (Integer)session.getAttribute("user_num");
		Integer user_auth =(Integer)session.getAttribute("user_auth");
		
		//세션이 종료되거나 유저 권한이 관리자가 아닐경우 리스트로 이동
		if(user_auth!=4) {
			return "redirect:noticeList.do";
		}
		//멤버 정보 vo에 넣기
		noticeVo.setMem_num(mem_num);
		// 글작성
		noticeService.noticeWrite(noticeVo);
		
		
		return "redirect:noticeList.do";
	}
	
	//공지 수정폼 호출
	@GetMapping("/notice/noticeUpdate.do")
	public String updateForm(@RequestParam int notice_num, Model model, HttpSession session ) {
		
		NoticeVO noticeVO=noticeService.noticeDetail(notice_num);
		
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//모델에 VO랑 권한이랑 같이 넣어서 전달
		model.addAttribute("noticeVO", noticeVO);
		model.addAttribute("user_auth",user_auth);
		//세션이 종료되거나 유저 권한이 관리자가 아닐경우 리스트로 이동
		if(user_auth!=4) {
			return "redirect:noticeList.do";
		}
		
		return "noticeUpdate";
	}
	
	
	//공지수정
	@PostMapping("/notice/noticeUpdate.do")
	public String submitUpdate(@Valid NoticeVO noticeVO, BindingResult result) {
		//오류가 있을 경우 다시 폼호출
		if(result.hasErrors()) {
			return form();
		}
		noticeService.noticeUpdate(noticeVO);
		
		return "redirect:noticeList.do";
	}
	
	// CKEditor를 이용한 이미지 업로드
	@RequestMapping("/notice/imageUploader.do")
	@ResponseBody
	public Map<String,Object> uploadImage(MultipartFile upload, HttpSession session, HttpServletRequest request) throws Exception {
		
		// 업로드할 절대 경로 구하기
		String realFolder = session.getServletContext().getRealPath("/resources/image_upload");
		
		// 업로드한 파일 이름
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + org_filename; // long타입으로 밀리세컨드를 가져와서 중복되지 않은 파일명을 만듦
		
		logger.debug("<<원본 파일명>> : " + org_filename);
		logger.debug("<<저장할 파일명>> : " + str_filename);
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		String filepath = realFolder + "\\" + user_num + "\\" + str_filename;
		logger.debug("<<파일 경로>> : " + filepath);
		
		File f = new File(filepath);
		if(!f.exists()) { // 폴더가 존재하지 않는다면 만들자
			f.mkdirs();
		}
		
		upload.transferTo(f); // 경로가 있다면 파일 업로드
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploaded", true);
		map.put("url", request.getContextPath() + "/resources/image_upload/" + user_num + "/" + str_filename); // 리퀘스트를 사용해서 경로를 읽어옴
		
		return map;
	}
	//공지 삭제하기
	@GetMapping("/notice/noticeDelete.do")
	public String deleteForm(@RequestParam int notice_num, HttpSession session) {
		//권한저장
		Integer user_auth = (Integer)session.getAttribute("user_auth");		
		//세션이 종료되거나 유저 권한이 관리자가 아닐경우 리스트로 이동
		if(user_auth!=4) {
			return "redirect:noticeList.do";
		}
		noticeService.noticeDelete(notice_num);
		
		return "redirect:noticeList.do";
		}
	
}
