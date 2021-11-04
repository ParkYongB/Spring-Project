package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.notice.dao.NoticeMapper;
import kr.spring.notice.vo.NoticeVO;
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	@Override
	public void noticeWrite(NoticeVO notice) {
		noticeMapper.noticeWrite(notice);
	}

	@Override
	public int noticeTotalCount() {
		return noticeMapper.noticeTotalCount();
	}

	@Override
	public int noticeHitCount(int notice_num) {
		return noticeMapper.noticeHitCount(notice_num);
	}

	@Override
	public NoticeVO noticeDetail(int notice_num) {
		return noticeMapper.noticeDetail(notice_num);
	}

	@Override
	public void noticeUpdate(NoticeVO notice) {
		noticeMapper.noticeUpdate(notice);
	}

	@Override
	public void noticeDelete(int notice_num) {
		noticeMapper.noticeDelete(notice_num);
	}

	@Override
	public List<NoticeVO> noticeGetList(Map<String, Object> map) {
		return noticeMapper.noticeGetList(map);
	}

	@Override
	public int noticeGetHits(int notice_num) {
		return noticeMapper.noticeGetHits(notice_num);
	}

}
