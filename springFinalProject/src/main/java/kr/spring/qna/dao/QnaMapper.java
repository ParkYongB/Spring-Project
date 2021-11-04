package kr.spring.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.qna.vo.QnaVO;

public interface QnaMapper {

	@Insert("INSERT INTO qna_list (qna_num, qna_category, qna_content, qna_reply) VALUES (qna_list_seq.nextval, #{qna_category}, #{qna_content}, #{qna_reply})")
	public void qnaInsert(QnaVO qna);

	public int getQnaCount(Map<String, Object> map);
	
	@Select("SELECT COUNT(*) FROM qna_list")
	public int getQnaServiceCount();
	
	public List<QnaVO> getQnaList(Map<String, Object> map);
	
	@Select("SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM qna_list ORDER BY qna_num DESC)a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<QnaVO> getQnaServiceList(Map<String, Object> map);
	
	@Select("SELECT * FROM qna_list WHERE qna_num = #{qna_num}")
	public QnaVO getQna(int num);
	
	@Update("UPDATE qna_list SET qna_content=#{qna_content},qna_category=#{qna_category},qna_reply=#{qna_reply} WHERE qna_num=#{qna_num}")
	public void qnaUpdate(QnaVO qna);
	
	@Delete("DELETE FROM qna_list WHERE qna_num = #{qna_num}")
	public void qnaDelete(int num);

	
}
