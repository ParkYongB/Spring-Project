package kr.spring.serviceBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.serviceBoard.vo.ServiceBoardVO;

public interface ServiceBoardMapper {  

	@Insert("INSERT INTO service_board (service_num, service_title, service_nickname, service_content, service_email, service_keyword, service_filename, service_file)"
			+ "VALUES (service_board_seq.nextval, #{service_title}, #{service_nickname}, #{service_content}, #{service_email},"
			+ "#{service_keyword}, #{service_filename},#{service_file})")
	public void serviceBoardInsert(ServiceBoardVO serviceboard);
	//@Select("SELECT COUNT(*) FROM service_board")
	//public int getServiceBoardCount();
	public int selectRowCount(Map<String,Object> map);
	//@Select("SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM service_board ORDER BY service_num DESC)a) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<ServiceBoardVO> getServiceBoardList(Map<String, Object> map);
	@Select("SELECT * FROM service_board WHERE service_num = #{service_num}")
	public ServiceBoardVO getServiceBoard(int service_num);
	//@Update("UPDATE service_board SET service_title=#{service_title},service_content=#{service_content},service_keyword=#{service_keyword},service_file=#{service_file} WHERE service_num=#{service_num}")
	//public void serviceBoardUpdate(ServiceBoardVO serviceboard);
	//@Delete("DELETE FROM service_board WHERE service_num = #{service_num}")
	//public void serviceBoardDelete(int service_num);
	
	
	
	
    
}
