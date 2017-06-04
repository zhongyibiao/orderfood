package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.Recharge;

@Mapper
public interface RechargeMapper {
	/**按时间查询交易记录*/
	List<Recharge> findRechargeInfoByTime(Recharge recharge);
	void saveRecharge(Recharge recharge);
	Recharge findRechargeById(String rechargeId);
}
