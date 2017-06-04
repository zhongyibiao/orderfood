package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.PartnerInfo;

@Mapper
public interface PartnerInfoMapper {
	int deleteByPrimaryKey(String partnerId);

	int insert(PartnerInfo partnerInfo);

	int insertSelective(PartnerInfo partnerInfo);

	PartnerInfo selectByPrimaryKey(String partnerId);

	List<PartnerInfo> selectPartnerInfos(PartnerInfo partnerInfo);

	int updateByPrimaryKeySelective(PartnerInfo partnerInfo);

	int updateByPrimaryKey(PartnerInfo partnerInfo);
	
}