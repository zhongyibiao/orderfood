package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.SysConfig;

public interface SysConfigMapper {
	int deleteByPrimaryKey(String cfgId);

	int insert(SysConfig sysConfig);

	int insertSelective(SysConfig sysConfig);

	SysConfig selectByPrimaryKey(String cfgId);

	List<SysConfig> selectSysConfigs(SysConfig sysConfig);

	int updateByPrimaryKeySelective(SysConfig sysConfig);

	int updateByPrimaryKey(SysConfig sysConfig);
}