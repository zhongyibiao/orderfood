package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.orderfood.domain.MenuConf;
import com.xiaoweiyunchuang.orderfood.mapper.MenuConfMapper;

@Service
public class MenuConfServiceImpl implements MenuConfService {

	@Autowired
	private MenuConfMapper menuConfMapper;

	@Override
	public boolean isMenuConfExist(MenuConf menuConf) {
		List<MenuConf> menuConfs = menuConfMapper.selectMenuConfs(menuConf);
		return ListUtils.isEmpty(menuConfs) ? false : true;
	}

	@Override
	@Transactional
	public void deleteByPrimaryKey(String menuConfId) {
		menuConfMapper.deleteByPrimaryKey(menuConfId);
	}

	@Override
	@Transactional
	public void deleteByMenuKey(String menuId) {
		menuConfMapper.deleteByMenuKey(menuId);
	}

	@Override
	@Transactional
	public void insert(MenuConf menuConf) {
		menuConfMapper.insert(menuConf);
	}

	@Override
	@Transactional
	public void insertSelective(MenuConf menuConf) {
		menuConfMapper.insertSelective(menuConf);
	}

	@Override
	public MenuConf selectByPrimaryKey(String menuConfId) {
		return menuConfMapper.selectByPrimaryKey(menuConfId);
	}

	@Override
	public List<MenuConf> selectByMenuKey(String menuId) {
		return menuConfMapper.selectByMenuKey(menuId);
	}

	@Override
	@Transactional
	public void updateByPrimaryKeySelective(MenuConf menuConf) {
		menuConfMapper.updateByPrimaryKeySelective(menuConf);
	}

	@Override
	@Transactional
	public void updateByPrimaryKey(MenuConf menuConf) {
		menuConfMapper.updateByPrimaryKey(menuConf);
	}

	@Override
	public List<MenuConf> selectMenuConfs(MenuConf menuConf) {
		return menuConfMapper.selectMenuConfs(menuConf);
	}

}
