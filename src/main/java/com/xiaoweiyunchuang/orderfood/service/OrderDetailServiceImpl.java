package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.orderfood.domain.OrderDetail;
import com.xiaoweiyunchuang.orderfood.mapper.OrderDetailMapper;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailMapper orderDetailMapper;

	@Override
	public void deleteByPrimaryKey(String orderDetailId) {
		orderDetailMapper.deleteByPrimaryKey(orderDetailId);
	}

	@Override
	public void insert(OrderDetail orderDetail) {
		orderDetailMapper.insert(orderDetail);

	}

	@Override
	public void insertSelective(OrderDetail orderDetail) {
		orderDetailMapper.insertSelective(orderDetail);

	}

	@Override
	public OrderDetail selectByPrimaryKey(String orderDetailId) {
		return orderDetailMapper.selectByPrimaryKey(orderDetailId);
	}

	@Override
	public List<OrderDetail> selectOrderDetailByOrderKey(String orderId) {
		return orderDetailMapper.selectOrderDetailByOrderKey(orderId);
	}

	@Override
	public void updateByPrimaryKeySelective(OrderDetail orderDetail) {
		orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
	}

	@Override
	public void updateByPrimaryKey(OrderDetail orderDetail) {
		orderDetailMapper.updateByPrimaryKey(orderDetail);
	}

	@Override
	public boolean isOrderDetailExsit(OrderDetail order) {
		List<OrderDetail> orderList = selectOrderDetails(order);
		return ListUtils.isEmpty(orderList) ? false : true;
	}

	@Override
	public List<OrderDetail> selectOrderDetails(OrderDetail orderDetail) {
		return orderDetailMapper.selectOrderDetails(orderDetail);
	}

}
