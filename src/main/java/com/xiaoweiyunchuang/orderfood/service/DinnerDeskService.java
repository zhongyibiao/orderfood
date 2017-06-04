package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.DinnerDesk;


public interface DinnerDeskService {
	
	void deleteByPrimaryKey(String dinnerDeskId);

	void insert(DinnerDesk dinnerDesk);

	void insertSelective(DinnerDesk dinnerDesk);

	DinnerDesk selectByPrimaryKey(String dinnerDeskId);

	List<DinnerDesk> selectByDinnerRoomKey(String dinnerRoomId);

	List<DinnerDesk> selectDinnerDesks(DinnerDesk dinnerDesk);

	void updateByPrimaryKeySelective(DinnerDesk dinnerDesk);

	void updateByPrimaryKey(DinnerDesk dinnerDesk);

	boolean isDinnerDeskExsit(DinnerDesk dinnerDesk);

}
