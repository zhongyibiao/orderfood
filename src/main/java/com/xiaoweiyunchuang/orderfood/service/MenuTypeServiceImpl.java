package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.orderfood.domain.MenuConf;
import com.xiaoweiyunchuang.orderfood.domain.MenuInfo;
import com.xiaoweiyunchuang.orderfood.domain.MenuType;
import com.xiaoweiyunchuang.orderfood.mapper.MenuConfMapper;
import com.xiaoweiyunchuang.orderfood.mapper.MenuInfoMapper;
import com.xiaoweiyunchuang.orderfood.mapper.MenuTypeMapper;

@Service
public class MenuTypeServiceImpl implements MenuTypeService {

	@Autowired
	private MenuTypeMapper menuTypeMapper;
	@Autowired
	private MenuInfoMapper menuInfoMapper;
	@Autowired
	private MenuConfMapper menuConfMapper;

	@Override
	@Transactional
	public void deleteByPrimaryKey(String menuTypeId) {
		menuTypeMapper.deleteByPrimaryKey(menuTypeId);
		menuInfoMapper.deleteByMenuTypeKey(menuTypeId);
	}

	@Override
	@Transactional
	public void insert(MenuType record) {
		menuTypeMapper.insert(record);
	}

	@Override
	@Transactional
	public void insertSelective(MenuType record) {
		menuTypeMapper.insertSelective(record);
	}

	@Override
	@Transactional
	public MenuType selectByPrimaryKey(String menuTypeId) {
		return menuTypeMapper.selectByPrimaryKey(menuTypeId);
	}

	@Override
	public List<MenuType> selectMenuTypes(MenuType menuType) {
		List<MenuType> menuTypes = menuTypeMapper.selectMenuTypes(menuType);
		if (!ListUtils.isEmpty(menuTypes) && menuTypes.size() > 0) {
			MenuType menuType1 = menuTypes.get(0);
			List<MenuInfo> menuInfos = menuInfoMapper.selectByMenuTypeKey(menuType1);
			for (MenuInfo menuInfo : menuInfos) {
				List<MenuConf> menuConfs = menuConfMapper.selectByMenuKey(menuInfo.getMenuId());
				menuInfo.setMenuConfs(menuConfs);
			}
			menuType1.setMenuInfos(menuInfos);
		}
		return menuTypes;
	}

	@Override
	@Transactional
	public void updateByPrimaryKeySelective(MenuType record) {
		menuTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public void updateByPrimaryKey(MenuType record) {
		record.setUpdateDate(DateUtil.now());
		menuTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public boolean isMenuTypeExist(MenuType menuType) {
		List<MenuType> menuTypes = menuTypeMapper.selectMenuTypes(menuType);
		return ListUtils.isEmpty(menuTypes) ? false : true;

	}

}
