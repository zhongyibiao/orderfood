package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.SysConfig;

public interface SysConfigService {

	boolean isSysConfigExsit(SysConfig sysConfig);

	void deleteByPrimaryKey(String cfgId);

	void insert(SysConfig sysConfig);

	void insertSelective(SysConfig sysConfig);

	SysConfig selectByPrimaryKey(String cfgId);

	List<SysConfig> selectSysConfigs(SysConfig sysConfig);

	void updateByPrimaryKeySelective(SysConfig sysConfig);

	void updateByPrimaryKey(SysConfig sysConfig);

}
