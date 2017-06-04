package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.Order;

public interface OrderService {

	boolean isOrderExsit(Order record);

	void deleteByPrimaryKey(String orderId);

	void insert(Order record);

	void insertSelective(Order record);

	Order selectByPrimaryKey(String orderId);

	List<Order> selectOrders(Order order);

	void updateByPrimaryKeySelective(Order record);

	void updateByPrimaryKey(Order record);

}
