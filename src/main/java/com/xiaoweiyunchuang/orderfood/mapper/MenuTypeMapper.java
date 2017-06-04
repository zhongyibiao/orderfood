package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.MenuType;

@Mapper
public interface MenuTypeMapper {

	int deleteByPrimaryKey(String menuTypeId);

	int insert(MenuType menuType);

	int insertSelective(MenuType menuType);

	MenuType selectByPrimaryKey(String menuTypeId);

	List<MenuType> selectMenuTypes(MenuType menuType);

	int updateByPrimaryKeySelective(MenuType menuType);

	int updateByPrimaryKey(MenuType menuType);
}