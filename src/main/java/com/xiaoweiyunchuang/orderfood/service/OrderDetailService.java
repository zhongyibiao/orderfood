package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.OrderDetail;

public interface OrderDetailService {

	void deleteByPrimaryKey(String orderDetailId);

	void insert(OrderDetail orderDetail);

	void insertSelective(OrderDetail orderDetail);

	OrderDetail selectByPrimaryKey(String orderDetailId);

	List<OrderDetail> selectOrderDetailByOrderKey(String orderId);

	List<OrderDetail> selectOrderDetails(OrderDetail orderDetail);

	void updateByPrimaryKeySelective(OrderDetail orderDetail);

	void updateByPrimaryKey(OrderDetail orderDetail);

	public boolean isOrderDetailExsit(OrderDetail order);

}
