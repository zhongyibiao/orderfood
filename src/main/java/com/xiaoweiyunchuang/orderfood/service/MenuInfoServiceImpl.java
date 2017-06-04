package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.MenuConf;
import com.xiaoweiyunchuang.orderfood.domain.MenuInfo;
import com.xiaoweiyunchuang.orderfood.mapper.MenuConfMapper;
import com.xiaoweiyunchuang.orderfood.mapper.MenuInfoMapper;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {

	@Autowired
	private MenuInfoMapper menuInfoMapper;
	@Autowired
	private MenuConfMapper menuConfMapper;

	@Override
	@Transactional
	public void deleteByPrimaryKey(String menuId) {
		menuInfoMapper.deleteByPrimaryKey(menuId);
		menuConfMapper.deleteByMenuKey(menuId);
	}

	@Override
	@Transactional
	public void deleteByMenuTypeKey(String menuTypeId) {
		menuInfoMapper.deleteByMenuTypeKey(menuTypeId);
	}

	@Override
	@Transactional
	public void insert(MenuInfo menuInfo) {
		menuInfoMapper.insert(menuInfo);
		List<MenuConf> menuConfs = menuInfo.getMenuConfs();
		if (!ListUtils.isEmpty(menuConfs)) {
			for (MenuConf menuConf : menuConfs) {
				menuConf.setMenuConfId(IDGenerate.getId());
				menuConf.setMenuId(menuInfo.getMenuId());
				menuConf.setCreateBy(menuInfo.getCreateBy());
				menuConf.setCreateDate(DateUtil.now());
				menuConf.setUpdateBy(menuInfo.getUpdateBy());
				menuConf.setUpdateDate(DateUtil.now());
				menuConf.setDelFlag("N");
				menuConfMapper.insert(menuConf);
			}
		}
	}

	@Override
	@Transactional
	public void insertSelective(MenuInfo menuInfo) {
		menuInfoMapper.insertSelective(menuInfo);
	}

	@Override
	public MenuInfo selectByPrimaryKey(String menuId) {
		MenuInfo menu = menuInfoMapper.selectByPrimaryKey(menuId);
		List<MenuConf> menuConfs = menuConfMapper.selectByMenuKey(menu.getMenuId());
		menu.setMenuConfs(menuConfs);
		return menu;
	}

	@Override
	public List<MenuInfo> selectMenuInfos(MenuInfo menuInfo) {
		List<MenuInfo> menuInfos = menuInfoMapper.selectMenuInfos(menuInfo);
		for (MenuInfo menu : menuInfos) {
			List<MenuConf> menuConfs = menuConfMapper.selectByMenuKey(menu.getMenuId());
			menu.setMenuConfs(menuConfs);
		}
		return menuInfos;
	}

	@Override
	@Transactional
	public void updateByPrimaryKeySelective(MenuInfo menuInfo) {
		menuInfoMapper.updateByPrimaryKeySelective(menuInfo);
		List<MenuConf> menuConfs = menuInfo.getMenuConfs();
		if (!ListUtils.isEmpty(menuConfs)) {
			for (MenuConf menuConf : menuConfs) {
				if (StringUtils.isNotEmpty(menuConf.getMenuConfId())) {
					menuConf.setUpdateBy(menuInfo.getUpdateBy());
					menuConf.setUpdateDate(DateUtil.now());
					menuConfMapper.updateByPrimaryKeySelective(menuConf);
				} else {
					menuConf.setMenuConfId(IDGenerate.getId());
					menuConf.setMenuId(menuInfo.getMenuId());
					menuConf.setCreateBy(menuInfo.getCreateBy());
					menuConf.setCreateDate(DateUtil.now());
					menuConf.setUpdateBy(menuInfo.getUpdateBy());
					menuConf.setUpdateDate(DateUtil.now());
					menuConf.setDelFlag("N");
					menuConfMapper.insert(menuConf);
				}

			}
		}
	}

	@Override
	@Transactional
	public void updateByPrimaryKey(MenuInfo menuInfo) {
		menuInfoMapper.updateByPrimaryKey(menuInfo);
	}

	@Override
	public boolean isMenuInfoExist(MenuInfo menuInfo) {
		List<MenuInfo> menuInfos = menuInfoMapper.selectMenuInfos(menuInfo);
		return ListUtils.isEmpty(menuInfos) ? false : true;
	}

}
