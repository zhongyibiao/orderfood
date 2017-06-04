package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.orderfood.domain.DinnerDesk;
import com.xiaoweiyunchuang.orderfood.mapper.DinnerDeskMapper;

@Service
public class DinnerDeskServiceImpl implements DinnerDeskService {

	@Autowired
	private DinnerDeskMapper dinnerDeskMapper;

	@Override
	public void deleteByPrimaryKey(String dinnerDeskId) {
		dinnerDeskMapper.deleteByPrimaryKey(dinnerDeskId);
	}

	@Override
	public void insert(DinnerDesk dinnerDesk) {
		dinnerDeskMapper.insert(dinnerDesk);
	}

	@Override
	public void insertSelective(DinnerDesk dinnerDesk) {
		dinnerDeskMapper.insertSelective(dinnerDesk);

	}

	@Override
	public DinnerDesk selectByPrimaryKey(String dinnerDeskId) {

		return dinnerDeskMapper.selectByPrimaryKey(dinnerDeskId);
	}

	@Override
	public List<DinnerDesk> selectByDinnerRoomKey(String dinnerRoomId) {
		return dinnerDeskMapper.selectByDinnerRoomKey(dinnerRoomId);
	}

	@Override
	public List<DinnerDesk> selectDinnerDesks(DinnerDesk dinnerDesk) {
		return dinnerDeskMapper.selectDinnerDesks(dinnerDesk);
	}

	@Override
	public void updateByPrimaryKeySelective(DinnerDesk dinnerDesk) {
		dinnerDeskMapper.updateByPrimaryKeySelective(dinnerDesk);
	}

	@Override
	public void updateByPrimaryKey(DinnerDesk dinnerDesk) {
		dinnerDeskMapper.updateByPrimaryKey(dinnerDesk);
	}

	@Override
	public boolean isDinnerDeskExsit(DinnerDesk dinnerDesk) {
		List<DinnerDesk> dinnerDesks = dinnerDeskMapper.selectDinnerDesks(dinnerDesk);
		return ListUtils.isEmpty(dinnerDesks) ? false : true;
	}

}
