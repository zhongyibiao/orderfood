package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.MenuType;

public interface MenuTypeService {

	boolean isMenuTypeExist(MenuType menuType);

	void deleteByPrimaryKey(String menuTypeId);

	void insert(MenuType record);

	void insertSelective(MenuType record);

	MenuType selectByPrimaryKey(String menuTypeId);

	List<MenuType> selectMenuTypes(MenuType menuType);

	void updateByPrimaryKeySelective(MenuType record);

	void updateByPrimaryKey(MenuType record);
}
