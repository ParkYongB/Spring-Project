package kr.spring.main.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.event.service.EventService;
import kr.spring.event.vo.EventVO;
import kr.spring.houseBoard.service.HouseBoardService;
import kr.spring.houseBoard.vo.HouseBoardVO;
import kr.spring.store.service.StoreService;
import kr.spring.store.vo.StoreVO;
import kr.spring.util.PagingUtil;


@Controller
public class MainController {
	
	@Autowired
	private HouseBoardService houseService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private StoreService storeService;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/main/main.do")
	public String main(@RequestParam(value="keyword", defaultValue = "") String keyword,
			Model model) {
		
		Map<String, Object>map = new HashMap<String, Object>();
		
		//집들이 사진 게시판
		int HCount = houseService.selectRowCount(map);
		
		logger.debug("<<HCount>>: " + HCount);
		
		PagingUtil HPage = new PagingUtil(1, HCount, 4, 1, null);
		
		map.put("start", HPage.getStartCount());
		map.put("end", HPage.getEndCount());
		
		List<HouseBoardVO> HList = null;
		if(HCount>0) {
			HList = houseService.selectHBoardList(map);
			logger.debug("<<house list>>" + HList);
		}
		
		//스토어
		int SCount = storeService.selectRowCount(map);
		
		logger.debug("<<SCount>>: " + SCount);
		
		PagingUtil SPage = new PagingUtil(1, SCount, 4, 1, null);
		
		map.put("start", SPage.getStartCount());
		map.put("end", SPage.getEndCount());
		
		List<StoreVO> SList = null;
		if(SCount>0) {
			SList =storeService.selectList(map);
			logger.debug("<<store list>>" + SList);
		}
		map.put("keyword", keyword);
		
		
		//이벤트
		int ECount = eventService.selectRowCount(map);
		
		logger.debug("<<ECount>>: " + ECount);
		
		PagingUtil page = new PagingUtil(1, ECount, 4, 1, null);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		
		List<EventVO> EList = null;
		if(ECount>0) {
			EList = eventService.eventGetList(map);
			logger.debug("<<event list>>" + EList);
		}
		
		//request에 저장
		model.addAttribute("HCount", HCount);
		model.addAttribute("HList", HList);
		model.addAttribute("SCount", SCount);
		model.addAttribute("SList", SList);
		model.addAttribute("ECount", ECount);
		model.addAttribute("EList", EList);
			
		return "main"; //타일스 식별자
	}
	 
}
