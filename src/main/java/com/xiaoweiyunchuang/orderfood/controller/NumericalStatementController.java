package com.xiaoweiyunchuang.orderfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.orderfood.domain.FinanceReport;
import com.xiaoweiyunchuang.orderfood.service.NumericalStatementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "统计报表服务", description = "提供RESTful风格API的财务、菜品查服务")
@RestController
public class NumericalStatementController extends AbstractController {

	@Autowired
	NumericalStatementService service;

	// -------------------Retrieve by OrderId------------------- //
	@ApiOperation("通过订单Id查询订单明细")
	@RequestMapping(value = "/finance/{startTime}/{endTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FinanceReport>> getOrderInfo(
			@ApiParam("开始日期") @PathVariable("startTime") String startTime,
			@ApiParam("结束日期") @PathVariable("endTime") String endTime) {
		FinanceReport financeReport = new FinanceReport();
		financeReport.setStartTime(startTime);
		financeReport.setEndTime(endTime);
		List<FinanceReport> finances = service.selectFinanceReports(financeReport);
		if(!ListUtils.isEmpty(finances)){
			finances.get(0).setStartTime(startTime);
			finances.get(0).setEndTime(endTime);
		}

		return new ResponseEntity<List<FinanceReport>>(finances, HttpStatus.OK);
	}

}
