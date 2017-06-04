package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.Order;

@Mapper
public interface OrderMapper {
	int deleteByPrimaryKey(String orderId);

	int insert(Order order);

	int insertSelective(Order order);

	Order selectByPrimaryKey(String orderId);

	List<Order> selectOrders(Order order);

	int updateByPrimaryKeySelective(Order order);

	int updateByPrimaryKey(Order order);

}