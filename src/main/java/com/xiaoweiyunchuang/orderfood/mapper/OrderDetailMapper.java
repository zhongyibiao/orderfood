package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.OrderDetail;

@Mapper
public interface OrderDetailMapper {
	int deleteByPrimaryKey(String orderDetailId);

	int insert(OrderDetail orderDetail);

	int insertSelective(OrderDetail orderDetail);

	OrderDetail selectByPrimaryKey(String orderDetailId);

	List<OrderDetail> selectOrderDetailByOrderKey(String orderId);

	List<OrderDetail> selectOrderDetails(OrderDetail orderDetail);

	int updateByPrimaryKeySelective(OrderDetail orderDetail);

	int updateByPrimaryKey(OrderDetail orderDetail);
}