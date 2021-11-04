package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.order.dao.OrderMapper;
import kr.spring.order.vo.OrderVO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void insertOrder(OrderVO orderVO) {
		orderMapper.insertOrder(orderVO);
	}

	@Override
	public List<OrderVO> selectNonList(Map<String, Object> map) {
		return orderMapper.selectNonList(map);
	}

	@Override
	public int selectNonRowCount(Map<String, Object> map) {
		return orderMapper.selectNonRowCount(map);
	}

	@Override
	public List<OrderVO> selectList(Map<String, Object> map) {
		return orderMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return orderMapper.selectRowCount(map);
	}

}
