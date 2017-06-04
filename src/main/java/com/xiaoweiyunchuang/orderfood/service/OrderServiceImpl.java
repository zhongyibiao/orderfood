package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.Order;
import com.xiaoweiyunchuang.orderfood.domain.OrderDetail;
import com.xiaoweiyunchuang.orderfood.mapper.OrderDetailMapper;
import com.xiaoweiyunchuang.orderfood.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	OrderDetailMapper orderDetailMapper;

	@Override
	@Transactional
	public void deleteByPrimaryKey(String orderId) {
		orderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	@Transactional
	public void insert(Order order) {
		orderMapper.insert(order);
		List<OrderDetail> orderDetails = order.getOrderDetails();
		if (!ListUtils.isEmpty(orderDetails)) {
			for (OrderDetail orderDetail : orderDetails) {
				orderDetail.setOrderDetailId(IDGenerate.getId());
				orderDetail.setOrderId(order.getOrderId());
				orderDetail.setCreateBy(order.getCreateBy());
				orderDetail.setCreateDate(DateUtil.now());
				orderDetail.setUpdateBy(order.getUpdateBy());
				orderDetail.setUpdateDate(DateUtil.now());
				orderDetail.setDelFlag("N");
				orderDetailMapper.insert(orderDetail);
			}
		}
	}

	@Override
	@Transactional
	public void insertSelective(Order order) {
		orderMapper.insertSelective(order);
	}

	@Override
	public Order selectByPrimaryKey(String orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public List<Order> selectOrders(Order order) {
		return orderMapper.selectOrders(order);
	}

	@Override
	@Transactional
	public void updateByPrimaryKeySelective(Order order) {
		orderMapper.updateByPrimaryKeySelective(order);
		List<OrderDetail> orderDetails = order.getOrderDetails();
		if (!ListUtils.isEmpty(orderDetails)) {
			for (OrderDetail orderDetail : orderDetails) {
				if (StringUtils.isNotEmpty(orderDetail.getOrderDetailId())) {
					orderDetail.setUpdateBy(order.getUpdateBy());
					orderDetail.setUpdateDate(DateUtil.now());
					orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
				} else {
					orderDetail.setOrderDetailId(IDGenerate.getId());
					orderDetail.setOrderId(order.getOrderId());
					orderDetail.setCreateBy(order.getCreateBy());
					orderDetail.setCreateDate(DateUtil.now());
					orderDetail.setUpdateBy(order.getUpdateBy());
					orderDetail.setUpdateDate(DateUtil.now());
					orderDetail.setDelFlag("N");
					orderDetailMapper.insert(orderDetail);
				}

			}
		}
	}

	@Override
	@Transactional
	public void updateByPrimaryKey(Order order) {
		orderMapper.updateByPrimaryKey(order);
	}

	@Override
	public boolean isOrderExsit(Order order) {
		List<Order> orderList = selectOrders(order);
		return ListUtils.isEmpty(orderList) ? false : true;
	}

}
