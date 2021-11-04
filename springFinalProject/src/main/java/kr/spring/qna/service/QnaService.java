package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import kr.spring.qna.vo.QnaVO;

public interface QnaService {
	public void qnaInsert(QnaVO qna);
	public int getQnaCount(Map<String, Object> map);
	public int getQnaServiceCount();
	public List<QnaVO> getQnaList(Map<String,Object> map);
	public List<QnaVO> getQnaServiceList(Map<String,Object> map);
	public QnaVO getQna(int num);
	public void qnaUpdate(QnaVO qna);
	public void qnaDelete(int num);
}
