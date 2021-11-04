package kr.spring.serviceBoard.service;

import java.util.List;
import java.util.Map;

import kr.spring.serviceBoard.vo.ServiceBoardVO;

/**
 * @Package Name   : kr.spring.serviceBoard.service
 * @FileName  : ServiceBoardService.java
 * @작성일       : 2021. 10. 10. 
 * @작성자       : 나윤경
 * @프로그램 설명 : 고객센터 서비스
 */
public interface ServiceBoardService {
	public void serviceBoardInsert(ServiceBoardVO serviceboard);
	//public int getServiceBoardCount();
	public int selectRowCount(Map<String, Object> map);
	public List<ServiceBoardVO> getServiceBoardList(Map<String,Object> map);
	public ServiceBoardVO getServiceBoard(int service_num);
	//public void serviceBoardUpdate(ServiceBoardVO serviceboard);
	//public void serviceBoardDelete(int service_num);
}
