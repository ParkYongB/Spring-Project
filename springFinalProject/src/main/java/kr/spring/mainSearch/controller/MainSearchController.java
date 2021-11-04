package kr.spring.mainSearch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.event.service.EventService;
import kr.spring.event.vo.EventVO;
import kr.spring.houseBoard.service.HouseBoardService;
import kr.spring.houseBoard.vo.HouseBoardVO;
import kr.spring.store.service.StoreService;
import kr.spring.store.vo.StoreVO;
import kr.spring.util.SearchPagingUtil;

@Controller
public class MainSearchController {
	
	@Autowired
	private HouseBoardService houseBoardService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private StoreService storeService;
	
	// HouseBoardVO 객체 초기화
	 @ModelAttribute
	 public HouseBoardVO initCommand1() {
		 
	    return new HouseBoardVO();
	 }

	 // EventVO 객체 초기화
	 public EventVO initCommand2() {
	 
	    return new EventVO();
	 }
	 
	// StoreVO 객체 초기화
	 public StoreVO initCommand3() {
	 
	    return new StoreVO();
	 }
	
	private static final Logger logger = LoggerFactory.getLogger(MainSearchController.class);

	// 통합 검색 화면 호출
	@GetMapping("/mainSearch/mainSearch.do")
	public ModelAndView mainAllSearchCall(
			@RequestParam(value="housePageNum", defaultValue="1") int houseCurrentPage,
			@RequestParam(value="eventPageNum", defaultValue="1") int eventcurrentPage,
			@RequestParam(value="storePageNum", defaultValue="1") int storecurrentPage,
			@RequestParam(value="keyfield", defaultValue="1") String keyfield,
			@RequestParam(value="keyword", defaultValue="") String keyword) {
				
		//------------------------ 사진게시판 부분 ------------------------//
		
		Map<String,Object> hmap = new HashMap<String,Object>();
		hmap.put("keyfield", keyfield);
		hmap.put("keyword", keyword);
		
		// 사진게시판 검색 글 총 개수
		int houseCount = houseBoardService.selectAllSearchRowCount(hmap);
		logger.debug("<<사진 맵>> : " + hmap.toString());
		logger.debug("<<houseCount>> : " + houseCount);
		
		// 사진게시판 페이지 처리
		SearchPagingUtil housePage = new SearchPagingUtil(keyfield, keyword,
				houseCurrentPage,houseCount,4,5,
				"mainSearch.do?eventPageNum=" + eventcurrentPage + "&storePageNum=" + storecurrentPage,
				"house");	// 나중에 URL 수정
		
		hmap.put("start", housePage.getStartCount());
		hmap.put("end", housePage.getEndCount());
		
		List<HouseBoardVO> houseList = null;
		if(houseCount > 0) {
			houseList = houseBoardService.selectAllSearchList(hmap);
		}
		
		//------------------------ 이벤트 부분 ------------------------//
		
		Map<String,Object> emap = new HashMap<String,Object>();
		emap.put("keyfield", keyfield);
		emap.put("keyword", keyword);
		
		int eventCount = eventService.selectEventAllSearchRowCount(emap);
		logger.debug("<<이벤트 맵>> : " + emap.toString());
		logger.debug("<<eventCount>> : " + eventCount);
		
		// 사진게시판 페이지 처리
		SearchPagingUtil eventPage = new SearchPagingUtil(keyfield, keyword,
				eventcurrentPage,eventCount,4,5,
				"mainSearch.do?housePageNum=" + houseCurrentPage + "&storePageNum" + storecurrentPage,
				"event");	// 나중에 URL 수정
		
		emap.put("start", eventPage.getStartCount());
		emap.put("end", eventPage.getEndCount());
		
		List<EventVO> eventList = null;
		if(eventCount > 0) {
			eventList = eventService.selectEventAllSearchList(emap);
		}
		
		//------------------------ 스토어 부분 ------------------------//
		
		Map<String, Object> smap = new HashMap<String, Object>();
		smap.put("keyfield", keyfield);
		smap.put("keyword", keyword);
		
		
		// 글의 총 갯수 또는 검색된 글의 갯수
		int productCount = storeService.selectStoreAllSearchRowCount(smap);
		logger.debug("<<이벤트 맵>> : " + smap.toString());
		logger.debug("<<productCount>> : " + productCount);
		
		// 상품 페이징 처리
		SearchPagingUtil productPage = new SearchPagingUtil(keyfield, keyword,
				storecurrentPage,productCount,4,5,
				"mainSearch.do?housePageNum=" + houseCurrentPage + "&eventPageNum=" + eventcurrentPage,
				"store");	// 나중에 URL 수정
		
		smap.put("start", productPage.getStartCount());
		smap.put("end", productPage.getEndCount());
		
		
		List<StoreVO> productList = null;
		if(productCount > 0) {
			productList = storeService.selectStoreAllSearchList(smap);
		}
		
		
		//------------------------ Model로 데이터 보내기 ------------------------//
		
		// 전달 객체
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mainSearch"); // 타일스 식별자
		
		// 사진게시판
		mav.addObject("houseCount", houseCount);
		mav.addObject("houseList", houseList);
		mav.addObject("housePagingHtml", housePage.getPagingHtml());
		
		// 이벤트
		mav.addObject("eventCount", eventCount);
		mav.addObject("eventList", eventList);
		mav.addObject("eventPagingHtml", eventPage.getPagingHtml());
		
		// 상품
		mav.addObject("productCount", productCount);
		mav.addObject("productList", productList);
		mav.addObject("productPagingHtml", productPage.getPagingHtml());
		
		// 현재 검색어 출력
		mav.addObject("keyword", keyword);
		
		
		return mav;
				
		
	}
	 
}
