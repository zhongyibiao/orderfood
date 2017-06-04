package com.xiaoweiyunchuang.orderfood.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.ConsumeToIntegral;
import com.xiaoweiyunchuang.orderfood.domain.Customer;
import com.xiaoweiyunchuang.orderfood.domain.IntegralConvert;
import com.xiaoweiyunchuang.orderfood.domain.IntegralToIntegral;
import com.xiaoweiyunchuang.orderfood.domain.Recharge;
import com.xiaoweiyunchuang.orderfood.domain.RechargeToIntegral;
import com.xiaoweiyunchuang.orderfood.mapper.CustomerMapper;
import com.xiaoweiyunchuang.orderfood.mapper.RechargeMapper;

@Service
public class RechargeService {
	
	@Autowired
	private RechargeMapper mapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	@Transactional
	public List<Recharge> findRechargeInfoByTime(Recharge recharge){
		List<Recharge> rechargeList = mapper.findRechargeInfoByTime(recharge);
		return rechargeList;
	}
	
	@Transactional
	public void saveRecharge(Recharge recharge){
		//插入一条交易记录的同时更新会员的积分和余额。
		//如果会员的余额为0，则只更新该会员的积分。
		recharge.setRechargeId(""+""+IDGenerate.getId());
		recharge.setCreateBy(recharge.getCustomerId());
		recharge.setUpdateBy(recharge.getCustomerId());
		recharge.setCreateDate(DateUtil.now());
		recharge.setRechargeTime(DateUtil.now());
		recharge.setUpdateDate(DateUtil.now());
		recharge.setDelFlag("N");
		try{
			int rechargeType = Integer.valueOf(recharge.getRechargeType());
			IntegralConvert integralConvert = new IntegralConvert();
			Customer customer = customerMapper.findCustomerById(recharge.getCustomerId());
			customer.setCustomerId(recharge.getCustomerId());
			customer.setUpdateBy(recharge.getCustomerId());
			customer.setUpdateDate(DateUtil.now());
			if(customer.getBalance() == null){
				BigDecimal bd = new BigDecimal("0.00");
				customer.setBalance(bd);
			}
			int rechargeIntegral = 0;
			switch(rechargeType){
			case Recharge.RECHARGE:
				integralConvert.setIntegralCalculate(new RechargeToIntegral());
				rechargeIntegral = integralConvert.calculateIntegral(recharge.getRechargeAmount().intValue());
				customer.setBalance(customer.getBalance().add(recharge.getRechargeAmount()));
				customer.setIntegral(customer.getIntegral() + rechargeIntegral);
				break;
			case Recharge.CONSUME:
				integralConvert.setIntegralCalculate(new ConsumeToIntegral());
				rechargeIntegral = integralConvert.calculateIntegral(recharge.getRechargeAmount().intValue());
				if(recharge.getPayType() == Recharge.BALANCE)
					customer.setBalance(customer.getBalance().subtract(recharge.getRechargeAmount()));
				customer.setIntegral(customer.getIntegral() + rechargeIntegral);
				break;
			case Recharge.INTERGRAL:
				integralConvert.setIntegralCalculate(new IntegralToIntegral());
				rechargeIntegral = integralConvert.calculateIntegral(recharge.getRechargeAmount().intValue());
				customer.setIntegral(customer.getIntegral() - rechargeIntegral);
				break;
			}
			recharge.setRechargeIntegral(rechargeIntegral);
			mapper.saveRecharge(recharge);
			customerMapper.updateCustomer(customer);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private boolean ishasRecharge(String rechargeId){
		Recharge recharge = mapper.findRechargeById(rechargeId);
		if(recharge == null){
			return true;
		}
		return false;
	}
}
