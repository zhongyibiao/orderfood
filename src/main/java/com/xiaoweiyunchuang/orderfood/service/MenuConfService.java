package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.MenuConf;

public interface MenuConfService {

	boolean isMenuConfExist(MenuConf menuConf);

	void deleteByPrimaryKey(String menuConfId);

	void deleteByMenuKey(String menuId);

	void insert(MenuConf menuConf);

	void insertSelective(MenuConf menuConf);

	MenuConf selectByPrimaryKey(String menuConfId);

	List<MenuConf> selectByMenuKey(String menuId);

	List<MenuConf> selectMenuConfs(MenuConf menuConf);

	void updateByPrimaryKeySelective(MenuConf menuConf);

	void updateByPrimaryKey(MenuConf menuConf);

}
