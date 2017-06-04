package com.xiaoweiyunchuang.orderfood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.ListUtils;

import com.github.pagehelper.PageHelper;
import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.Order;
import com.xiaoweiyunchuang.orderfood.domain.SysConfig;
import com.xiaoweiyunchuang.orderfood.service.OrderService;
import com.xiaoweiyunchuang.orderfood.service.SysConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "订单服务", description = "提供RESTful风格API的订单的增删改查服务")
@RestController
public class OrderController extends AbstractController {

	@Autowired
	OrderService service;
	@Autowired
	SysConfigService confService;

	// -------------------Retrieve All------------------- //
	@ApiOperation("查询订单")
	@RequestMapping(value = "/order/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrderInfo(@ApiParam("pageNum") @PathVariable Integer pageNum,
			@ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}

		List<Order> orderList = service.selectOrders(null);
		if (orderList.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
	}

	// -------------------Retrieve by DeskId------------------- //
	@ApiOperation("通过桌子Id查询订单")
	@RequestMapping(value = "/order/{dinnerDeskNo}/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrderInfo(@ApiParam("桌子id") @PathVariable("dinnerDeskNo") String dinnerDeskNo,
			@ApiParam("pageNum") @PathVariable Integer pageNum, @ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		Order order = new Order();
		order.setDinnerDeskNo(dinnerDeskNo);
		List<Order> orderList = service.selectOrders(order);
		return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
	}

	// -------------------Retrieve by DeskId------------------- //
	@ApiOperation("通过订单Id查询订单")
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> getOrderInfo(@ApiParam("订单id") @PathVariable("id") String id) {
		Order order = service.selectByPrimaryKey(id);
		if (order == null) {
			logger.info("Order with id " + id + " not found");
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	// -------------------Create a order------------------- //
	@ApiOperation("新增订单")
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> createOrder(@ApiParam("订单信息") @RequestBody Order order,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating OrderInfo " + order.getOrderId());
		if (service.isOrderExsit(order)) {
			logger.info("Create Order by " + order.getDinnerDeskNo() + "and" + order.getCustomerId() + "is exist");
			return new ResponseEntity<Map<String, String>>(HttpStatus.CONFLICT);
		}

		SysConfig sysConfig = new SysConfig();
		sysConfig.setCfgCode("placeOrderCode");
		List<SysConfig> sysConfigList = confService.selectSysConfigs(sysConfig);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("placeOrderCodePass", "N");
		if (!ListUtils.isEmpty(sysConfigList)) {
			SysConfig sysConfig1 = sysConfigList.get(0);
			if (sysConfig1.getCfgValue().equals(order.getPlaceOrderCode())) {
				resultMap.put("placeOrderCodePass", "Y");
			}
		}

		if (resultMap.get("placeOrderCodePass").equals("N")) {
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.NO_CONTENT);
		}

		Subject subject = SecurityUtils.getSubject();
		order.setOrderId(IDGenerate.getId());
		order.setCreateBy(subject.getPrincipal().toString());
		order.setCreateDate(DateUtil.now());
		order.setUpdateBy(subject.getPrincipal().toString());
		order.setUpdateDate(DateUtil.now());
		order.setDelFlag("N");
		service.insert(order);

		resultMap.put("orderId", order.getOrderId());
		resultMap.put("createTime", DateUtil.now());

		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}

	// ------------------- Update a order------------------- //
	@ApiOperation("更新订单")
	@RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateOrder(@ApiParam("订单id") @PathVariable("id") String id,
			@ApiParam("订单信息") @RequestBody Order order) {
		logger.info("update Order by " + id);

		if (service.selectByPrimaryKey(id) == null) {
			logger.info("update order by " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		order.setOrderId(id);
		order.setUpdateBy(subject.getPrincipal().toString());
		order.setUpdateDate(DateUtil.now());

		service.updateByPrimaryKeySelective(order);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a OrderDetail------------------- //
	@ApiOperation("通过ID删除订单")
	@RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOrder(@ApiParam("订单id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting Order with id " + id);

		if (service.selectByPrimaryKey(id) == null) {
			logger.info("Order with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		service.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
