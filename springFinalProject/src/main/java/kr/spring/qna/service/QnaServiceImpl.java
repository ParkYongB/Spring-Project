package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.qna.dao.QnaMapper;
import kr.spring.qna.vo.QnaVO;

@Service
@Transactional
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public void qnaInsert(QnaVO qna) {
		qnaMapper.qnaInsert(qna);
	}

	@Override
	public int getQnaCount(Map<String, Object> map) {
		return qnaMapper.getQnaCount(map);
	}

	@Override
	public List<QnaVO> getQnaList(Map<String, Object> map) {
		return qnaMapper.getQnaList(map);
	}

	@Override
	public QnaVO getQna(int num) {
		return qnaMapper.getQna(num);
	}

	@Override
	public void qnaUpdate(QnaVO qna) {
		qnaMapper.qnaUpdate(qna);
	}

	@Override
	public void qnaDelete(int num) {
		qnaMapper.qnaDelete(num);
	}

	@Override
	public int getQnaServiceCount() {
		return qnaMapper.getQnaServiceCount();
	}

	@Override
	public List<QnaVO> getQnaServiceList(Map<String, Object> map) {
		return qnaMapper.getQnaServiceList(map);
	}

	
	

}
