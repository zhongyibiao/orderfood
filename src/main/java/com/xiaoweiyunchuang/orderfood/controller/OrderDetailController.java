package com.xiaoweiyunchuang.orderfood.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.orderfood.domain.OrderDetail;
import com.xiaoweiyunchuang.orderfood.service.OrderDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "订单明细服务", description = "提供RESTful风格API的订单明细的删改查服务")
@RestController
public class OrderDetailController extends AbstractController {

	@Autowired
	OrderDetailService service;

	// -------------------Retrieve by OrderId------------------- //
	@ApiOperation("通过订单Id查询订单明细")
	@RequestMapping(value = "/orderDetail/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDetail>> getOrderInfo(@ApiParam("订单Id") @PathVariable("orderId") String orderId) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(orderId);
		List<OrderDetail> orderDetails = service.selectOrderDetailByOrderKey(orderId);
		return new ResponseEntity<List<OrderDetail>>(orderDetails, HttpStatus.OK);
	}

	// ------------------- Update a order------------------- //
	@ApiOperation("更新订单明细")
	@RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateOrder(@ApiParam("订单明细id") @PathVariable("id") String id,
			@ApiParam("订单信息") @RequestBody OrderDetail orderDetail) {
		logger.info("update Order by " + id);

		if (service.selectByPrimaryKey(id) == null) {
			logger.info("update OrderDetail by " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		orderDetail.setOrderId(id);
		orderDetail.setUpdateBy(subject.getPrincipal().toString());
		orderDetail.setUpdateDate(DateUtil.now());

		service.updateByPrimaryKeySelective(orderDetail);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}

	// ------------------- Delete a OrderDetail------------------- //
	@ApiOperation("通过ID删除订单明细")
	@RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMenuInfo(@ApiParam("订单明细id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting OrderDetail with id " + id);

		if (service.selectByPrimaryKey(id) == null) {
			logger.info("OrderDetail with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		service.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
