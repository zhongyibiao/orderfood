package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.DinnerDesk;

@Mapper
public interface DinnerDeskMapper {
	int deleteByPrimaryKey(String dinnerDeskId);

	int insert(DinnerDesk dinnerDesk);

	int insertSelective(DinnerDesk dinnerDesk);

	DinnerDesk selectByPrimaryKey(String dinnerDeskId);

	List<DinnerDesk> selectByDinnerRoomKey(String dinnerRoomId);

	List<DinnerDesk> selectDinnerDesks(DinnerDesk dinnerDesk);

	int updateByPrimaryKeySelective(DinnerDesk dinnerDesk);

	int updateByPrimaryKey(DinnerDesk dinnerDesk);
}