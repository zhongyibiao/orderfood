package com.xiaoweiyunchuang.orderfood.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.xiaoweiyunchuang.orderfood.domain.Recharge;
import com.xiaoweiyunchuang.orderfood.service.RechargeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "交易服务", description = "提供RESTful风格API的用户交易的增查服务")
@RestController
public class RechargeController extends AbstractController {
	@Autowired
	RechargeService rechargeService;
	
	// -------------------Retrieve All-----查詢制定客戶某個時間段的交易記錄-------------- //
		@ApiOperation("查詢制定客戶某個時間段的交易記錄")
		@RequestMapping(value = "/recharge/{startTime}/{endTime}/{customerId}", method = RequestMethod.GET)
		public ResponseEntity<List<Recharge>> listRechargeByTime(@ApiParam("startTime") @PathVariable String startTime,
				@ApiParam("endTime") @PathVariable String endTime,
				@ApiParam("customerId") @PathVariable String customerId){
			if(startTime != null && endTime != null && customerId != null){
				Recharge recharge = new Recharge();
				recharge.setStartTime(startTime);
				recharge.setEndTime(endTime);
				recharge.setCustomerId(customerId);
				List<Recharge> rechargeList = rechargeService.findRechargeInfoByTime(recharge);
				return new ResponseEntity<List<Recharge>>(rechargeList, HttpStatus.OK);
			}
			return new ResponseEntity<List<Recharge>>(HttpStatus.NO_CONTENT);
		}
		
		@ApiOperation("插入一條交易記錄")
		@RequestMapping(value = "/recharge/", method = RequestMethod.POST)
		public ResponseEntity<Recharge> saveRecharge(@RequestBody Recharge recharge, UriComponentsBuilder ucBuilder){
			Recharge re = new Recharge();
			if(recharge == null || (recharge != null && StringUtils.isEmpty(recharge.getCustomerId()))){
				logger.info("Post recharge is Null");
				re.setErrorCode(100);
				re.setErrorText("获取交易记录为空");
				return new ResponseEntity<Recharge>(re, HttpStatus.NO_CONTENT);
			}
			rechargeService.saveRecharge(recharge);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/recharge/{rechargeId}").buildAndExpand(recharge.getRechargeId()).toUri());
			return new ResponseEntity<Recharge>(headers, HttpStatus.CREATED);
		}
}
