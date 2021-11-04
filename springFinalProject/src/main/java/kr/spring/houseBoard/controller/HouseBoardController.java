package kr.spring.houseBoard.controller;

import java.io.File;
import java.util.Collections;
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

import kr.spring.houseBoard.service.HouseBoardService;
import kr.spring.houseBoard.vo.HCommentVO;
import kr.spring.houseBoard.vo.HMarkVO;
import kr.spring.houseBoard.vo.HouseBoardVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.HousePagingUtil;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

/**
 * @Package Name   : kr.spring.houseBoard.controller
 * @FileName  : HouseBoardController.java
 * @작성일       : 2021. 10. 10. 
 * @작성자       : 이현지
 * @프로그램 설명 : 집들이 게시판 컨트롤러
 */

@Controller
public class HouseBoardController {
	
	// 로그 처리(로그 대상 지정)
	private static final Logger logger = LoggerFactory.getLogger(HouseBoardController.class);
	
	// 게시물, 페이지 카운트 지정
	private int rowCount = 12;
	private int pageCount = 7;
	
	@Autowired
	private HouseBoardService houseBoardService;
	
	@Autowired
	private MemberService memberService;
	
	// 자바빈(VO) 초기화
	@ModelAttribute
	public HouseBoardVO initCommand() { // 속성명 지정 안 하면 houseBoardVO로 알아서 앞글자가 소문자로 변환
		return new HouseBoardVO();
	}
	
	// 글쓰기 - 폼 호출
	@GetMapping("/houseBoard/write.do")
	public String form() {
		return "houseBoardWrite"; // 타일스 식별자
	}
	
	// 글쓰기 - 전송된 데이터 처리
	@PostMapping("/houseBoard/write.do")
	public String submit(@Valid HouseBoardVO houseBoardVO, BindingResult result, HttpSession session) {
		
		logger.debug("<<글쓰기>> : " + houseBoardVO);
		
		// 유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return form();
		}
		
		// 회원번호 세팅
		houseBoardVO.setMem_num((Integer)session.getAttribute("user_num"));
		// 글쓰기
		houseBoardService.insertHBoard(houseBoardVO);
		
		return "redirect:/houseBoard/list.do";
	}
	
	// 글 목록
	@RequestMapping("/houseBoard/list.do")
	public ModelAndView Process(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
								@RequestParam(value="size", defaultValue="") String size,
								@RequestParam(value="residence", defaultValue="") String residence,
								@RequestParam(value="style", defaultValue="") String style,
								@RequestParam(value="space", defaultValue="") String space) {
		
		String keyfield = "";
		String keyword = "";
		
		// 출력 메시지
		String sizeOutput = "";
		String residenceOutput = "";
		String styleOutput = "";
		String spaceOutput = "";
		
		if(!size.equals("")) {
			keyfield = "1";
			// select size 처리
			switch (size) {
				case "0":
					keyword = "10평 미만";
					break;
				case "1":
					keyword = "10평대";
					break;
				case "2":
					keyword = "20평대";
					break;
				case "3":
					keyword = "30평대";
					break;
				case "4":
					keyword = "40평대";
					break;
				case "5":
					keyword = "50평 이상";
					break;
				default:
					keyword = "";
			}
			
			sizeOutput = keyword;
		}
		
		if(!residence.equals("")) {
			keyfield = "2";
			// select residence 처리
			switch (residence) {
				case "0":
					keyword = "원룸&오피스텔";
					break;
				case "1":
					keyword = "아파트";
					break;
				case "2":
					keyword = "빌라&연립";
					break;
				case "3":
					keyword = "단독주택";
					break;
				case "4":
					keyword = "사무공간";
					break;
				case "5":
					keyword = "상업공간";
					break;
				default:
					keyword = "";
			}
			
			residenceOutput = keyword;
		}
		
		if(!style.equals("")) {
			keyfield = "3";
			// select style 처리
			switch (style) {
				case "0":
					keyword = "모던";
					break;
				case "1":
					keyword = "북유럽";
					break;
				case "2":
					keyword = "빈티지";
					break;
				case "3":
					keyword = "내추럴";
					break;
				case "4":
					keyword = "프로방스&로맨틱";
					break;
				case "5":
					keyword = "클래식&앤틱";
					break;
				case "6":
					keyword = "한국&아시아";
					break;
				case "7":
					keyword = "유니크";
					break;
				default:
					keyword = "";
			}
			
			styleOutput = keyword;
		}
		
		if(!space.equals("")) {
			keyfield = "4";
			// select space 처리
			switch (space) {
				case "0":
					keyword = "원룸";
					break;
				case "1":
					keyword = "거실";
					break;
				case "2":
					keyword = "침실";
					break;
				case "3":
					keyword = "주방";
					break;
				case "4":
					keyword = "욕실";
					break;
				case "5":
					keyword = "아이방";
					break;
				case "6":
					keyword = "드레스룸";
					break;
				case "7":
					keyword = "서재&작업실";
					break;
				case "8":
					keyword = "베란다";
					break;
				case "9":
					keyword = "사무공간";
					break;
				case "10":
					keyword = "상업공간";
					break;
				case "11":
					keyword = "가구&소품";
					break;
				case "12":
					keyword = "현관";
					break;
				case "13":
					keyword = "외관&기타";
					break;
				default:
					keyword = "";
			}
			
			spaceOutput = keyword;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("keyfield", keyfield);
		map.put("sizeOutput", sizeOutput);
		map.put("residenceOutput", residenceOutput);
		map.put("styleOutput", styleOutput);
		map.put("spaceOutput", spaceOutput);
		
		logger.debug("<<사이즈>> : " + keyword + keyfield);
		// 글의 총 개수 또는 검색된 글의 개수
		int count = houseBoardService.selectRowCount(map);
		logger.debug("<<맵>> : " + map.toString());
		logger.debug("<<count>> : " + count);
		
		// 페이지 처리
		HousePagingUtil page = new HousePagingUtil(
				currentPage, count, rowCount, pageCount, 
				"list.do?size="+ size +"&residence="+ residence +"&style="+ style +"&space="+space);
				
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<HouseBoardVO> list = null;
		if(count > 0) {
			list = houseBoardService.selectHBoardList(map);
		}
		
		// 전달 객체
		ModelAndView mav = new ModelAndView();
		mav.setViewName("houseBoardList"); // 타일스 식별자
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		// GET 유지
		mav.addObject("size", size);
		mav.addObject("residence", residence);
		mav.addObject("style", style);
		mav.addObject("space", space);
		
		// 현재 검색어 출력
		mav.addObject("sizeOutput", sizeOutput);
		mav.addObject("residenceOutput", residenceOutput);
		mav.addObject("styleOutput", styleOutput);
		mav.addObject("spaceOutput", spaceOutput);
		
		return mav;
	}
	
	// 글 목록 - 썸네일 이미지 출력
	@RequestMapping("/houseBoard/imageView.do")
	public ModelAndView thumbnailImage(@RequestParam int house_num) {
		
		HouseBoardVO houseBoard = houseBoardService.selectHBoard(house_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView"); // imageView 빈 이름(id) 생성 -> mav가 클래스 파일(빈 객체) 찾아서 실행시켜줌
		//				속성명			속성값(byte[]의 데이터)
		mav.addObject("imageFile", houseBoard.getHouse_thumbnail());
		mav.addObject("filename", houseBoard.getThumbnail_filename());
		
		return mav;
	}
	
	// 글 상세
	@RequestMapping("/houseBoard/detail.do")
	public ModelAndView process(@RequestParam int house_num, HttpSession session) { // 글번호 저장
		// 해당 글의 조회수 증가
		houseBoardService.updateHBoardHits(house_num);
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		// 추천 중복 체크 변수
		int heartCheckNum = 0;
		int countHeart = 0;
		
		// 스크랩 중복 체크 변수
		int scrapCheckNum = 0;
		int countScrap = 0;
		
		// selectHBoard에 글번호 전달
		HouseBoardVO houseBoard = houseBoardService.selectHBoard(house_num);
		
		// HTML 태그 불허용
		houseBoard.setHouse_title(StringUtil.useNoHtml(houseBoard.getHouse_title()));
		// HTML 태그 불허, 줄바꿈 허용
	    // CK에디터 사용시 주석 처리
	    // houseBoard.setHouse_content(StringUtil.useBrNoHtml(houseBoard.getHouse_content()));
		
		ModelAndView mav = new ModelAndView();
		
		// 추천수
		countHeart = houseBoardService.countHeart(house_num);
		// 스크랩수
		countScrap = houseBoardService.countScrap(house_num);
		// 댓글수
		int countComm = houseBoardService.countComm(house_num);
		
		// 추천 버튼 실행
		if(user_num == null) {
			// 로그인 되어있지 않음
	    	heartCheckNum = 0;
		}else {
			// 로그인 되어있음
			HMarkVO hMark = new HMarkVO();
			hMark.setHouse_num(house_num);
			hMark.setMem_num(user_num);
			
			// 추천 중복 체크
			String already = houseBoardService.checkHeart(hMark);
			if(already == null) { // 추천버튼 누른 적 없음
				
			}else { // 추천버튼 누른 적 있음
				heartCheckNum = 1;
			}
		}
		
		// 스크랩 버튼 실행
		if(user_num == null) {
			// 로그인 되어있지 않음
	    	heartCheckNum = 0;
		}else {
			// 로그인 되어있음
			HMarkVO hMark = new HMarkVO();
			hMark.setHouse_num(house_num);
			hMark.setMem_num(user_num);
			
			// 스크랩 중복 체크
			String already = houseBoardService.checkScrap(hMark);
			if(already == null) { // 스크랩버튼 누른 적 없음
				
			}else { // 스크랩버튼 누른 적 있음
				scrapCheckNum = 1;
			}
		}
	    
	    mav.setViewName("houseBoardDetail"); // 타일스 식별자
	    // Model(컨테이너)에 데이터 담기
	    //				속성명(key)	속성값(value)
	    mav.addObject("houseBoard", houseBoard);
	    mav.addObject("heartCheckNum", heartCheckNum);
	    mav.addObject("countHeart", countHeart);
	    mav.addObject("scrapCheckNum", scrapCheckNum);
	    mav.addObject("countScrap", countScrap);
	    mav.addObject("countComm", countComm);
	    mav.addObject("user_auth", user_auth);
	     
		return mav;
	}
	
	// 글 수정 - 폼 호출
	@GetMapping("/houseBoard/update.do")
	public String formUpdate(@RequestParam int house_num, Model model) {
		
		HouseBoardVO houseBoardVO = houseBoardService.selectHBoard(house_num);
		
		model.addAttribute("houseBoardVO", houseBoardVO);
		
		return "houseBoardModify"; // 타일스 식별자
	}
	
	// 글 수정 - 전송된 데이터 처리
	@PostMapping("/houseBoard/update.do")
	public String submitUpdate(@Valid HouseBoardVO houseBoardVO, BindingResult result, HttpServletRequest request, Model model) {
		
		logger.debug("<<글 수정>> : " + houseBoardVO);
		
		// 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "houseBoardModify";
		}
		
		// 글 수정
		houseBoardService.updateHBoard(houseBoardVO);
		// View에 표시할 메시지
		model.addAttribute("message", "수정이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/houseBoard/list.do"); // url은 전체 경로를 명시해주는 것이 좋음
		
		return "common/resultView";
	}
	
	// 글 수정 - 썸네일 삭제 (ajax방식)
	@RequestMapping("/houseBoard/deleteFile.do")
	@ResponseBody
	public Map<String,String> processFile(int house_num, HttpSession session) {
		
		Map<String,String> map = new HashMap<String,String>();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {
			map.put("result", "logout");
			
		}else {
			houseBoardService.deleteFile(house_num);
			map.put("result", "success");
		}
		
		return map;
	}
	
	// CKEditor를 이용한 이미지 업로드
	@RequestMapping("/houseBoard/imageUploader.do")
	@ResponseBody
	public Map<String,Object> uploadImage(MultipartFile upload, HttpSession session, HttpServletRequest request) throws Exception {
		
		// 업로드와 절대 경로 구하기
		String realFolder = session.getServletContext().getRealPath("/resources/image_upload");
		
		// 업로드한 파일 이름
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + org_filename;
		
		logger.debug("<<원본 파일명>> : " + org_filename);
		logger.debug("<<저장할 파일명>> : " + str_filename);
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		String filepath = realFolder + "\\" + user_num + "\\" + str_filename;
		logger.debug("<<파일 경로>> : " + filepath);
		
		File f = new File(filepath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		upload.transferTo(f);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploaded", true);
		map.put("url", request.getContextPath() + "/resources/image_upload/" + user_num + "/" + str_filename);
		
		return map;
	}

	// 글 삭제
	@RequestMapping("/houseBoard/delete.do")
	public String submitDelete(@RequestParam int house_num) {
		
		logger.debug("<<글 삭제>> : " + house_num);
		
		// 글 삭제
		houseBoardService.deleteHBoard(house_num);
		
		return "redirect:/houseBoard/list.do";
	}
	
	// =============== 댓글(ajax) =============== //
	// 댓글 등록
	@RequestMapping("/houseBoard/writeComm.do")
	@ResponseBody
	public Map<String,String> writeComm(HCommentVO hCommentVO, HttpSession session) {
		
		logger.debug("<<댓글 등록>> : " + hCommentVO);
		
		Map<String,String> map = new HashMap<String,String>();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			// 로그인 안 됨
			map.put("result", "logout");
			
		}else {
			// 로그인 됨
			// 댓글 등록
			houseBoardService.insertComm(hCommentVO);
			map.put("result", "success");
		}
		
		return map;
	}
	
	// 댓글 목록(ajax)
	@RequestMapping("/houseBoard/listComm.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
									  @RequestParam int house_num) {
		
		logger.debug("<<currentPage>> : " + currentPage);
		logger.debug("<<house_num>> : " + house_num);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("house_num", house_num); // 하나의 객체에 데이터 담아서 보냄
		
		// 총 글의 개수
		int count = houseBoardService.selectRowCountComm(map);
		
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null); // url 명시x
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<HCommentVO> list = null;
		if(count > 0) {
			list = houseBoardService.selectListComm(map);
		}else {
			list = Collections.emptyList(); // count가 0일 때 빈 리스트 전달
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		// mapJson으로 반환
		return mapJson;
	}
	
	// 회원 프로필 사진 출력
	@GetMapping("/houseBoard/boardProfile.do")
	public ModelAndView boardProfileImage(@RequestParam int mem_num) {
		
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", memberVO.getProfile());
		mav.addObject("filename", memberVO.getProfile_filename());
		
		return mav;
	}
	
	// 댓글 수정
	@RequestMapping("/houseBoard/updateComm.do")
	@ResponseBody
	public Map<String,String> updateComm(HCommentVO hCommentVO, HttpSession session) {
		
		logger.debug("<<댓글 수정>> : " + hCommentVO);
		
		Map<String,String> map = new HashMap<String,String>();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null) {
			// 로그인 되어있지 않음
			map.put("result", "logout");
		}else if(user_num != null && user_num == hCommentVO.getMem_num() || user_auth == 4) {
			// 로그인 되어있고 로그인한 회원번호와 작성자 회원번호가 일치
			// 댓글 수정
			houseBoardService.updateComm(hCommentVO);
			map.put("result", "success");
		}else {
			// 로그인한 회원번호와 작성자 회원번호 불일치
			map.put("result", "wrongAcess");
		}
		
		return map;
	}
	
	// 댓글 삭제
	@RequestMapping("/houseBoard/deleteComm.do")
	@ResponseBody
	public Map<String,String> deleteComm(@RequestParam int comm_num,
										 @RequestParam int mem_num,
										 HttpSession session) {
		
		logger.debug("<<comm_num>> : " + comm_num);
		logger.debug("<<mem_num>> : " + mem_num);
		
		Map<String,String> map = new HashMap<String,String>();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null) {
			// 로그인 되어있지 않음
			map.put("result", "logout");
		}else if(user_num != null && user_num == mem_num || user_auth == 4) {
			// 로그인 되어있고 로그인한 회원번호와 작성자 회원번호가 일치
			houseBoardService.deleteComm(comm_num);
			map.put("result", "success");
		}else {
			// 로그인한 회원번호와 작성자 회원번호 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	
	// ========== 추천(좋아요) 버튼 ========== //
	@RequestMapping("/houseBoard/heart.do")
	@ResponseBody
	public Map<String,String> heartButton(@RequestParam int house_num, HttpSession session) {
		
		logger.debug("<<house_num>> : " + house_num);
		
		Map<String,String> map = new HashMap<String,String>();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		logger.debug("<<user_num>> : " + user_num);
		
		int countHeart = houseBoardService.countHeart(house_num);
		
		if(user_num == null) {
			// 로그인 되어있지 않음
			map.put("result", "logout");
		}else {
			// 로그인 되어있음
			// 추천 중복 체크
			HMarkVO hMark = new HMarkVO();
			hMark.setHouse_num(house_num);
			hMark.setMem_num(user_num);
			
			String already = houseBoardService.checkHeart(hMark);
			
			if(already == null) { // 추천버튼 누른 적 없음
				// 좋아요
				// 테이블에 추천수 저장
				houseBoardService.insertHeart(hMark);
				countHeart += 1;
				map.put("result", "success");
				map.put("countHeart", String.valueOf(countHeart));
				
			}else { // 추천버튼 누른 적 있음
				// 좋아요 취소
				houseBoardService.deleteHeart(hMark);
				countHeart -= 1;
				map.put("result", "cancel");
				map.put("countHeart", String.valueOf(countHeart));
			}
		}
		
		return map;
	}
	
	// ========== 스크랩 ========== //
	@RequestMapping("/houseBoard/scrap.do")
	@ResponseBody
	public Map<String,String> scrapButton(@RequestParam int house_num, HttpSession session) {
		
		logger.debug("<<house_num>> : " + house_num);
		
		Map<String,String> map = new HashMap<String,String>();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		logger.debug("<<user_num>> : " + user_num);
		
		int countScrap = houseBoardService.countScrap(house_num);
		
		if(user_num == null) {
			// 로그인 되어있지 않음
			map.put("result", "logout");
		}else {
			// 로그인 되어있음
			// 스크랩 중복 체크
			HMarkVO hMark = new HMarkVO();
			hMark.setHouse_num(house_num);
			hMark.setMem_num(user_num);
			
			String already = houseBoardService.checkScrap(hMark);
			
			if(already == null) { // 스크랩버튼 누른 적 없음
				// 테이블에 스크랩 저장
				houseBoardService.insertScrap(hMark);
				countScrap += 1;
				map.put("result", "success");
				map.put("countScrap", String.valueOf(countScrap));
				
			}else { // 스크랩버튼 누른 적 있음
				// 스크랩 취소
				houseBoardService.deleteScrap(hMark);
				countScrap -= 1;
				map.put("result", "cancel");
				map.put("countScrap", String.valueOf(countScrap));
			}
		}
		
		return map;
	}
	
}
