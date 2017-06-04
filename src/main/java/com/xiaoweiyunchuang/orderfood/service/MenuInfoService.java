package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.MenuInfo;

public interface MenuInfoService {

	boolean isMenuInfoExist(MenuInfo menuInfo);

	void deleteByPrimaryKey(String menuId);

	void deleteByMenuTypeKey(String menuTypeId);

	void insert(MenuInfo menuInfo);

	void insertSelective(MenuInfo menuInfo);

	MenuInfo selectByPrimaryKey(String menuId);

	List<MenuInfo> selectMenuInfos(MenuInfo menuInfo);

	void updateByPrimaryKeySelective(MenuInfo menuInfo);

	void updateByPrimaryKey(MenuInfo menuInfo);

}
