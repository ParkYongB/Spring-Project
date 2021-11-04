package kr.spring.store.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.store.service.StoreService;
import kr.spring.store.vo.StoreVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

/*
 * 파일명 : StoreController
 * 작성자 : 박용복
 * 설명 : 쇼핑몰(상품) 컨트롤러
 */

@Controller
public class StoreController {

	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	// 한 페이지에 표시할 수량
	private int rowCount = 12;
	// 페이지 카운트
	private int pageCount = 10;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ReviewService reviewService;
	
	// 자바빈 초기화
	@ModelAttribute
	public StoreVO initCommand() {
		return new StoreVO();
	}
	
	// 하단리뷰 자바빈 초기화
	@ModelAttribute
	public ReviewVO initCommain() {
		return new ReviewVO();
	}
	
	// 스토어 메인 호출
	@GetMapping("/store/storeMain.do")
	public String storeMain() {
		logger.debug("<<스토어 메인 호출>>");
		
		// 타일스 식별자
		return "storeMain";
	}
	
	// 상품 등록 폼 호출
	@GetMapping("/store/storeRegister.do")
	public String storeRegisterView() {
		logger.debug("<<상품 등록 호출>>");
		
		// 타일스 식별자
		return "storeRegister";
	}
	
	// 상품 등록 - 상품 등록 처리
	@PostMapping("/store/storeRegister.do")
	public String storeRegister(@Valid StoreVO storeVO, BindingResult result,
			                    HttpSession session) {
		logger.debug("<<상품 정보>>" + storeVO);
		
		// 유효성 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return storeRegisterView();
		}
		storeVO.setMem_num((Integer)session.getAttribute("user_num"));
		storeService.productRegister(storeVO);
		
		// 타일스 식별자
		return "redirect:/store/storeCategory.do";
	}
	
	// 카테고리 페이지 출력
	@RequestMapping("/store/storeCategory.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value="keyfield", defaultValue = "1") String keyfield,
			@RequestParam(value="prod_cate", defaultValue = "") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// 글의 총 갯수 또는 검색된 글의 갯수
		int count = storeService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		// 페이지 처리
		PagingUtil page = new PagingUtil(null, keyfield, currentPage, count, rowCount, pageCount, "storeCategory.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<StoreVO> list = null;
		if(count > 0) {
			list = storeService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("storeCategory");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	// 썸네일 이미지 표시
	@RequestMapping("/store/imageView.do")
	public ModelAndView viewImage(@RequestParam int prod_num) {
		
		StoreVO storeVO = storeService.selectProduct(prod_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
						// 속성명 		속성값(byte[]의 데이터)
		mav.addObject("imageFile", storeVO.getThumbnail_img());
		mav.addObject("filename", storeVO.getThumbnail_filename());
		
		return mav;
	}
	
	/*
	 * 작성자 : 신혜지
	 * 설명 : 글 상세 - 리뷰 부분 작성
	 */
	
	// 글 상세
	@RequestMapping("/store/storeDetail.do")
	public ModelAndView process(@RequestParam int prod_num) {
		
		StoreVO storeVO = storeService.selectProduct(prod_num);
		
		logger.debug("<<prod_num>>" + prod_num);
		logger.debug("<<storeVO>>" + storeVO);
		
		//하단리뷰용
		List<ReviewVO> reviewVO = reviewService.reviewListStore(prod_num);
		
		// HTML 태그 불허
		storeVO.setProd_name(StringUtil.useNoHtml(storeVO.getProd_name()));
		
		// HTML 태그 불허, 줄 바꿈 허용
		// CK에디터 사용으로 사용 x
		// storeVO.setProd_content(StringUtil.useBrNoHtml(storeVO.getProd_content()));
		
		// 썸네일 이미지 출력을 위해 뷰네임 세팅
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		// 리뷰 갯수 세기
		int rev_count = reviewService.reviewStoreCount(prod_num);
		
		// 리뷰가 1개 이상일 경우
		if(rev_count > 0) {
			// 리뷰 평균 값 구하기
			Integer rev_grade = reviewService.staravg(prod_num);
			// 평균 값 반올림
			Integer rev_grade_round = Math.round(rev_grade);
			
			// 				평균 값		평균 값
			mav.addObject("rev_grade", rev_grade);
			//				반올림한 값			반올림한 값
			mav.addObject("rev_grade_round", rev_grade_round);
			//				리뷰 갯수		리뷰 갯수		
			mav.addObject("rev_count", rev_count);
			
			Map<Object,String> ratingOptions = new HashMap<Object,String>();
			
			// 디비에서 리뷰 점수 호출 후 문자열로 변환
			ratingOptions.put(0, "☆☆☆☆☆");
			ratingOptions.put(1, "★☆☆☆☆");
			ratingOptions.put(2, "★★☆☆☆");
			ratingOptions.put(3, "★★★☆☆");
			ratingOptions.put(4, "★★★★☆");
			ratingOptions.put(5, "★★★★★");
			
			mav.addObject("ratingOptions",ratingOptions);
			mav.addObject("reviewVO", reviewVO);
		}
		
		mav.setViewName("storeProduct");
		mav.addObject("storeVO", storeVO);
		mav.addObject("rev_count", rev_count);
		
		return mav;
	}
	
	//리뷰부분 이미지 
	@GetMapping("/store/reviewImageView.do")
	public ModelAndView reviewImageContent(@RequestParam int rev_num) {
			
		ReviewVO reviewVO = reviewService.reviewImgStore(rev_num);
			
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
			
		mav.addObject("imageFile", reviewVO.getRev_img());
		mav.addObject("filename", reviewVO.getRev_filename());
			
		return mav;
	}
	
	// 상품 수정 - 폼 호출
	@GetMapping("/store/storeModify.do")
	public String productUpdate(@RequestParam int prod_num, Model model) {
		
		StoreVO storeVO = storeService.selectProduct(prod_num);
		
		model.addAttribute("storeVO", storeVO);
		
		return "storeModify";
	}
	
	// 상품 수정 - 전송된 데이터 처리
	@PostMapping("/store/storeModify.do")
	public String submitUpdate(@Valid StoreVO storeVO, BindingResult result, HttpServletRequest request, Model model) {
		
		logger.debug("<<상품 수정>> : " + storeVO);
		
		// 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "storeModify";
		}
		
		// 상품 수정
		storeService.updateProduct(storeVO);
		
		// View 에 표시할 메시지
		model.addAttribute("message", "상품 수정 완료");
		
		// 이동할 경로
		model.addAttribute("url", request.getContextPath() + "/store/storeCategory.do");
	
		return "common/resultView";		// JSP 경로 지정
	}
	
	// 상품 수정 - 섬네일 이미지 삭제
	@RequestMapping("/store/deleteFile.do")
	@ResponseBody
	public Map<String, String> processFile(int prod_num, HttpSession session) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		Integer mem_num = (Integer)session.getAttribute("user_num");
	
		if(mem_num == null) {
			map.put("result", "logout");
		} else {
			storeService.deleteThumbnail(prod_num);
			map.put("result", "success");
		}
		
		return map;
	}
	
	// 상품 삭제
	@RequestMapping("/store/productDelete.do")
	public String productDelete(@RequestParam int prod_num) {
		
		logger.debug("<<상품 삭제>> : " + prod_num);
		
		// 상품 삭제
		storeService.deleteProduct(prod_num);
		
		return "redirect:/store/storeCategory.do";
	}
}
