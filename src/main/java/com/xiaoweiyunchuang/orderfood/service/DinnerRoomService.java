package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.DinnerRoom;

public interface DinnerRoomService {

	boolean isDinnerRoomExist(DinnerRoom dinnerRoom);

	void deleteByPrimaryKey(String dinnerRoomId);

	void insert(DinnerRoom dinnerRoom);

	void insertSelective(DinnerRoom dinnerRoom);

	DinnerRoom selectByPrimaryKey(String dinnerRoomId);

	List<DinnerRoom> selectDinnerRooms(DinnerRoom dinnerRoom);

	void updateByPrimaryKeySelective(DinnerRoom dinnerRoom);

	void updateByPrimaryKey(DinnerRoom dinnerRoom);

}
