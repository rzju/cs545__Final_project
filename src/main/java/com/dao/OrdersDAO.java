package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Orders;

@Repository("ordersDAO")
@Mapper
public interface OrdersDAO {

	public int insertOrders(Orders orders);

	public int updateOrders(Orders orders);

	public int deleteOrders(String ordersid);

	public int deleteOrdersByIds(String[] ids);

	public List<Orders> getAllOrders();

	public List<Orders> getOrdersByCond(Orders orders);

	public List<Orders> getOrdersByLike(Orders orders);

	public Orders getOrdersById(String ordersid);

}


