package kr.spring.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.event.dao.EventMapper;
import kr.spring.event.vo.ECommentVO;
import kr.spring.event.vo.EventVO;
import kr.spring.houseBoard.vo.HouseBoardVO;
@Service
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventMapper eventMapper;
	
	@Override
	public void eventWrite(EventVO event) {
		eventMapper.eventWrite(event);
	}

	/*
	 * @Override public int eventTotalCount() { return
	 * eventMapper.eventTotalCount(); }
	 */
	@Override
	public EventVO eventDetail(int event_num) {
		return eventMapper.eventDetail(event_num);
	}
	@Override
	public void eventUpdate(EventVO event) {
		eventMapper.eventUpdate(event);
	}
	@Override
	public void eventDelete(int event_num) {
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		eventMapper.deleteECommentByEventNum(event_num);
		eventMapper.eventDelete(event_num);
	}
	@Override
	public List<EventVO> eventGetList(Map<String, Object> map) {
		return eventMapper.eventGetList(map);
	}
	@Override
	public int eventGetHits(int event_num) {
		return eventMapper.eventGetHits(event_num);
	}
	@Override
	public void deleteFile(Integer event_num) {
		eventMapper.deleteFile(event_num);
	}
	@Override
	public int selectRowCount(Map<String, Object> map) {
		return eventMapper.selectRowCount(map);
	}
	
	//------------------댓글-------------------

	@Override
	public List<ECommentVO> selectListEComment(Map<String, Object> map) {
		return eventMapper.selectListEComment(map);
	}
	@Override
	public int selectRowCountComment(Map<String, Object> map) {
		return eventMapper.selectRowCountComment(map);
	}
	@Override
	public void insertEComment(ECommentVO eComment) {
		eventMapper.insertEComment(eComment);
	}
	@Override
	public void updateEComment(ECommentVO eComment) {
		eventMapper.updateEComment(eComment);
	}
	@Override
	public void deleteEComment(Integer comm_num) {
		eventMapper.deleteEComment(comm_num);
	}

	@Override
	public int selectEventAllSearchRowCount(Map<String, Object> map) {
		return eventMapper.selectEventAllSearchRowCount(map);
	}

	@Override
	public List<EventVO> selectEventAllSearchList(Map<String, Object> map) {
		return eventMapper.selectEventAllSearchList(map);
	}



}
