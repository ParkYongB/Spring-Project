package kr.spring.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.notice.vo.NoticeVO;
public interface NoticeMapper {
	@Insert("INSERT INTO notice (notice_num,notice_title,notice_content,notice_reg_date,notice_hits,mem_num) VALUES(notice_seq.NEXTVAL,#{notice_title},#{notice_content},sysdate,1,#{mem_num})")
	public void noticeWrite(NoticeVO noticeVO);
	@Select("SELECT COUNT(*) FROM notice")
	public int noticeTotalCount();
	@Update("UPDATE notice SET notice_hits=notice_hits+1 WHERE notice_num=#{notice_num}")
	public int noticeHitCount(int notice_num);
	@Select("SELECT * FROM notice where notice_num=#{notice_num}")
	public NoticeVO noticeDetail(int notice_num);
	@Update("UPDATE notice SET notice_title=#{notice_title},notice_content=#{notice_content} WHERE notice_num=#{notice_num}")
	public void noticeUpdate(NoticeVO noticeVO);
	@Delete("DELETE FROM notice WHERE notice_num=#{notice_num}")
	public void noticeDelete(int notice_num);
	@Select("select * FROM (select a.*,rownum rnum FROM (SELECT * FROM notice ORDER BY notice_reg_date DESC)a)WHERE rnum>= #{start} AND rnum <= #{end}")
	public List<NoticeVO> noticeGetList(Map<String,Object> map);
	@Update("UPDATE notice SET notice_hits=notice_hits+1 WHERE notice_num=#{notice_num}")
	public int noticeGetHits(int notice_num);
}
