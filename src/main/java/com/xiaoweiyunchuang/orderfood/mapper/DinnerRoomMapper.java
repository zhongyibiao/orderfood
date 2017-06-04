package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.DinnerRoom;

@Mapper
public interface DinnerRoomMapper {
	int deleteByPrimaryKey(String dinnerRoomId);

	int insert(DinnerRoom dinnerRoom);

	int insertSelective(DinnerRoom dinnerRoom);

	DinnerRoom selectByPrimaryKey(String dinnerRoomId);

	List<DinnerRoom> selectDinnerRooms(DinnerRoom dinnerRoom);

	int updateByPrimaryKeySelective(DinnerRoom dinnerRoom);

	int updateByPrimaryKey(DinnerRoom dinnerRoom);
}