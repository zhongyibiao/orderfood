package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.MenuConf;

@Mapper
public interface MenuConfMapper {

	int deleteByPrimaryKey(String menuConfId);

	int deleteByMenuKey(String menuId);

	int insert(MenuConf menuConf);

	int insertSelective(MenuConf menuConf);

	MenuConf selectByPrimaryKey(String menuConfId);

	List<MenuConf> selectByMenuKey(String menuId);

	List<MenuConf> selectMenuConfs(MenuConf menuConf);

	int updateByPrimaryKeySelective(MenuConf menuConf);

	int updateByPrimaryKey(MenuConf menuConf);

}