package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.MenuInfo;
import com.xiaoweiyunchuang.orderfood.domain.MenuType;

@Mapper
public interface MenuInfoMapper {

	int deleteByPrimaryKey(String menuId);

	int deleteByMenuTypeKey(String menuTypeId);

	int insert(MenuInfo menuInfo);

	int insertSelective(MenuInfo menuInfo);

	MenuInfo selectByPrimaryKey(String menuId);

	List<MenuInfo> selectMenuInfos(MenuInfo menuInfo);
	
	List<MenuInfo> selectByMenuTypeKey(MenuType menuType);

	int updateByPrimaryKeySelective(MenuInfo menuInfo);

	int updateByPrimaryKey(MenuInfo menuInfo);

}