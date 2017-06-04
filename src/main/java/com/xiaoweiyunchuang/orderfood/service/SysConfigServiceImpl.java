package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.orderfood.domain.SysConfig;
import com.xiaoweiyunchuang.orderfood.mapper.SysConfigMapper;

@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	private SysConfigMapper sysConfigMapper;

	@Override
	public void deleteByPrimaryKey(String cfgId) {
		sysConfigMapper.deleteByPrimaryKey(cfgId);
	}

	@Override
	public void insert(SysConfig sysConfig) {
		sysConfigMapper.insert(sysConfig);
	}

	@Override
	public void insertSelective(SysConfig sysConfig) {
		sysConfigMapper.insertSelective(sysConfig);
	}

	@Override
	public SysConfig selectByPrimaryKey(String cfgId) {
		return sysConfigMapper.selectByPrimaryKey(cfgId);
	}

	@Override
	public List<SysConfig> selectSysConfigs(SysConfig sysConfig) {
		return sysConfigMapper.selectSysConfigs(sysConfig);
	}

	@Override
	public void updateByPrimaryKeySelective(SysConfig sysConfig) {
		sysConfigMapper.updateByPrimaryKey(sysConfig);

	}

	@Override
	public void updateByPrimaryKey(SysConfig sysConfig) {
		sysConfigMapper.updateByPrimaryKey(sysConfig);

	}

	@Override
	public boolean isSysConfigExsit(SysConfig sysConfig) {
		List<SysConfig> sysConfigList = selectSysConfigs(sysConfig);
		return ListUtils.isEmpty(sysConfigList) ? false : true;
	}

}
