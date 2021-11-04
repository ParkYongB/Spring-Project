package kr.spring.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import kr.spring.order.vo.OrderVO;

public interface OrderMapper {
	
	// 상품 주문
	@Insert("INSERT INTO orders(order_num, order_date, order_zipcode, order_address1, order_address2, "
			+ "receiver_name, receiver_phone, mem_num, prod_num, pay_quan, pay_price, "
			+ "buis_name, receiver_email)"
			+ "VALUES(orders_seq.nextval, SYSDATE, #{order_zipcode}, #{order_address1}, #{order_address2}, "
			+ "#{receiver_name}, #{receiver_phone}, #{mem_num}, #{prod_num}, #{pay_quan}, #{pay_price}, "
			+ "#{buis_name}, #{receiver_email})")
	public void insertOrder(OrderVO orderVO);
	
	// 비회원 주문 조회
	public List<OrderVO> selectNonList(Map<String, Object> map);
	public int selectNonRowCount(Map<String, Object> map);

	// 회원 주문 조회
	public List<OrderVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);

}
