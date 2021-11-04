package kr.spring.event.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.event.vo.ECommentVO;
import kr.spring.event.vo.EventVO;
import kr.spring.houseBoard.vo.HouseBoardVO;

public interface EventMapper {
	//부모글
	@Insert("INSERT INTO event (event_num,event_title,event_content,event_type,event_day,event_hits,event_reg_date,event_modi,mem_num,event_filename,event_photo) VALUES(event_seq.nextval,#{event_title},#{event_content},#{event_type},#{event_day},1,SYSDATE,SYSDATE,#{mem_num},#{event_filename},#{event_photo})")
	public void eventWrite(EventVO eventVO);
	//@Select("select count(*) from event")
	//public int eventTotalCount();
	public int selectRowCount(Map<String,Object> map);
	@Select("SELECT * FROM event b JOIN mem_detail m ON b.mem_num=m.mem_num WHERE b.event_num=#{event_num}")
	public EventVO eventDetail(int event_num);
	//@Update("UPDATE event SET event_title=#{event_title},event_content=#{event_content},event_day=#{event_day},event_type=#{event_type} WHERE event_num=#{event_num}")
	public void eventUpdate(EventVO eventVO);
	@Delete("DELETE FROM event WHERE event_num=#{event_num}")
	public void eventDelete(int event_num);
	//@Select("select * FROM (select a.*,rownum rnum FROM (SELECT * FROM event ORDER BY event_reg_date DESC)a)WHERE rnum>= #{start} AND rnum <= #{end}")
	public List<EventVO> eventGetList(Map<String,Object> map);
	@Update("UPDATE event SET event_hits=event_hits+1 WHERE event_num=#{event_num}")
	public int eventGetHits(int event_num);
	// 썸네일 삭제(업데이트)
	@Update("UPDATE event SET event_filename = '', event_photo = '' WHERE event_num = #{event_num}")
	public void deleteFile(Integer event_num);
	
	//댓글
	public List<ECommentVO> selectListEComment(Map<String, Object>map);
	public int selectRowCountComment(Map<String, Object>map);
	@Insert("INSERT INTO comments (comm_num, comm_content, event_num, mem_num) VALUES (comments_seq.nextval,#{comm_content},#{event_num},#{mem_num})")
	public void insertEComment(ECommentVO eComment);
	@Update("UPDATE comments SET comm_content=#{comm_content},comm_mod_date=SYSDATE WHERE comm_num=#{comm_num}")
	public void updateEComment(ECommentVO eComment);
	@Delete("DELETE FROM comments WHERE comm_num=#{comm_num}")
	public void deleteEComment(Integer comm_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제 전 댓글 삭제
	@Delete("DELETE FROM comments WHERE event_num=#{event_num}")
	public void deleteECommentByEventNum(Integer event_num);

	// =============== 통합검색 =============== //
	public int selectEventAllSearchRowCount(Map<String,Object> map);		// 통합 검색 결과 게시글 수
	public List<EventVO> selectEventAllSearchList(Map<String,Object> map);		// 통합 검색 결과 게시글 리스트
}
