package com.xiaoweiyunchuang.orderfood.controller;

import org.apache.commons.lang3.StringUtils;
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

import com.xiaoweiyunchuang.orderfood.domain.Customer;
import com.xiaoweiyunchuang.orderfood.service.CustomerService;

@RestController
public class CustomerRestController extends AbstractController {
	@Autowired
	CustomerService customerService;

	// -------------------Retrieve Single-----根据用户ID-------------- //
	@RequestMapping(value = "/customer/{customerId}/{random}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getUser(@PathVariable("customerId") String customerId) {
		logger.info("Fetching Customer with customerId " + customerId);
		Customer customer = customerService.findCustomerById(customerId);
		if (customer == null) {
			logger.info("Customer with customerId " + customerId + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	//-----------------获取验证码------------------//
	@RequestMapping(value = "/customer/{phoneNum}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getPhoneNumber(@PathVariable("phoneNum") String phoneNum){
		logger.info("Fetching PhoneNum:"+phoneNum);
		if(phoneNum == null || phoneNum == ""){
			logger.info("Customer with phoneNum "+ phoneNum +" not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		//发送短信到短信平台
		if(!customerService.isSendSMSSuccess(phoneNum)){
			return new ResponseEntity<Customer>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	// -------------------Create a-----注册及验证码登录-------------- //
	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<Customer> registerOrLogin(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		Customer  cust  = new Customer();
		if(customer == null || (customer != null && StringUtils.isEmpty(customer.getPhoneNum()))){
			logger.info("Creating Customer not found");
			cust.setErrorCode(100);
			cust.setErrorText("获取用户信息失败");
			return new ResponseEntity<Customer>(cust,HttpStatus.NO_CONTENT);
		}
		//缺少一个验证码参数
		if(customerService.checkValidateCode(customer.getPhoneNum(), customer.getValidateCode(), customer.getWaitTime())){
			if(customerService.isPhoneNumExist(customer)){
				return new ResponseEntity<Customer>(customer, HttpStatus.OK);
			}else if(customer != null){
				customerService.saveCustomer(customer);
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/customer/{customerId}").buildAndExpand(customer.getCustomerId()).toUri());
			return new ResponseEntity<Customer>(headers, HttpStatus.CREATED);
		}else{
			logger.info("Fetching customer phone"+customer.getPhoneNum()+" login or register is failed");
			cust.setErrorCode(101);
			cust.setErrorText("获取验证码失败");
			return new ResponseEntity<Customer>(cust, HttpStatus.REQUEST_TIMEOUT);
		}
	}
	
	//--------------------完善个人资料----------------//
	@RequestMapping(value = "/customer/", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomerInfo(@RequestBody Customer customer, UriComponentsBuilder ucBuilder){
		if(customer == null){
			logger.info("Creating Customer is Null");
			Customer cust = new Customer();
			cust.setErrorCode(100);
			cust.setErrorText("传入信息有误");
			return new ResponseEntity<Customer>(cust, HttpStatus.NO_CONTENT);
		}
		if(customerService.isCustomerExist(customer.getCustomerId())){
			customerService.updataCustomerInfo(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}else{
			logger.info("Customer with customerId "+ customer.getCustomerId() +" not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}
//	
//	@RequestMapping(value = "/customer/{balance}/{cusotmerId}", method = RequestMethod.PUT)
//	public ResponseEntity<Customer> recharge(@PathVariable("balance") String balance, @PathVariable("customerId") String customerId){
//		logger.info("update Customer info with customerId " + customerId);
//		if(!customerService.isCustomerExist(customerId)){
//			logger.info("Customer with customerId "+ customerId +" not found");
//			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
//		}else{
//			if(!CheckUtil.isNumber(balance)){
//				logger.info("banlace is not numbel with customerId"+ customerId);
//				//返回参数不规范
//			}else{
//				Customer customer = new Customer();
//				customer.setCustomerId(customerId);
//				customer.setBalance(new BigDecimal(balance));
//				customerService.updataCustomerInfo(customer);
//			}
//			return new ResponseEntity<Customer>(HttpStatus.OK);
//		}
//	}
	
	
}
