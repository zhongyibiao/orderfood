package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.PartnerInfo;

public interface PartnerInfoService {

	boolean isPartnerInfoExist(PartnerInfo partnerInfo);

	void deleteByPrimaryKey(String partnerId);

	void insert(PartnerInfo partnerInfo);

	void insertSelective(PartnerInfo partnerInfo);

	PartnerInfo selectByPrimaryKey(String partnerId);

	List<PartnerInfo> selectPartnerInfos(PartnerInfo partnerInfo);

	void updateByPrimaryKeySelective(PartnerInfo partnerInfo);

	void updateByPrimaryKey(PartnerInfo partnerInfo);

}
