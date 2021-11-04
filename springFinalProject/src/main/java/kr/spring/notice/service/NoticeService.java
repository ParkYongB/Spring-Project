package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import kr.spring.notice.vo.NoticeVO;

public interface NoticeService {
	public void noticeWrite(NoticeVO notice);
	public int noticeTotalCount();
	public int noticeHitCount(int notice_num);
	public NoticeVO noticeDetail(int notice_num);
	public void noticeUpdate(NoticeVO notice);
	public void noticeDelete(int notice_num);
	public List<NoticeVO> noticeGetList(Map<String,Object> map);
	public int noticeGetHits(int notice_num);
}
