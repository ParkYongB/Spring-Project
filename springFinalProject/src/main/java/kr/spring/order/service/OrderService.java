package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import kr.spring.order.vo.OrderVO;

public interface OrderService {
	
	public void insertOrder(OrderVO orderVO);
	public List<OrderVO> selectNonList(Map<String, Object> map);
	public int selectNonRowCount(Map<String, Object> map);
	public List<OrderVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
}
