package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.DinnerDesk;
import com.xiaoweiyunchuang.orderfood.domain.DinnerRoom;
import com.xiaoweiyunchuang.orderfood.mapper.DinnerDeskMapper;
import com.xiaoweiyunchuang.orderfood.mapper.DinnerRoomMapper;

@Service
public class DinnerRoomServiceImpl implements DinnerRoomService {

	@Autowired
	private DinnerRoomMapper dinnerRoomMapper;
	@Autowired
	private DinnerDeskMapper dinnerDeskMapper;

	public void deleteByPrimaryKey(String dinnerRoomId) {
		dinnerRoomMapper.deleteByPrimaryKey(dinnerRoomId);
	}

	@Transactional
	public void insert(DinnerRoom dinnerRoom) {
		dinnerRoomMapper.insert(dinnerRoom);
		List<DinnerDesk> dinnerDesks = dinnerRoom.getDinnerDesks();
		if (!ListUtils.isEmpty(dinnerDesks)) {
			for (DinnerDesk dinnerDesk : dinnerDesks) {
				dinnerDesk.setDinnerDeskId(IDGenerate.getId());
				dinnerDesk.setDinnerRoomId(dinnerRoom.getDinnerRoomId());
				dinnerDesk.setCreateBy(dinnerRoom.getCreateBy());
				dinnerDesk.setCreateDate(DateUtil.now());
				dinnerDesk.setUpdateBy(dinnerRoom.getUpdateBy());
				dinnerDesk.setUpdateDate(DateUtil.now());
				dinnerDesk.setDelFlag("N");
				dinnerDeskMapper.insert(dinnerDesk);
			}
		}
	}

	@Transactional
	public void insertSelective(DinnerRoom dinnerRoom) {
		dinnerRoomMapper.insertSelective(dinnerRoom);
	}

	public DinnerRoom selectByPrimaryKey(String dinnerRoomId) {
		return dinnerRoomMapper.selectByPrimaryKey(dinnerRoomId);

	}

	public List<DinnerRoom> selectDinnerRooms(DinnerRoom dinnerRoom) {
		return dinnerRoomMapper.selectDinnerRooms(dinnerRoom);
	}

	@Transactional
	public void updateByPrimaryKeySelective(DinnerRoom dinnerRoom) {
		dinnerRoomMapper.updateByPrimaryKeySelective(dinnerRoom);

		List<DinnerDesk> dinnerDesks = dinnerRoom.getDinnerDesks();
		if (!ListUtils.isEmpty(dinnerDesks)) {
			for (DinnerDesk dinnerDesk : dinnerDesks) {
				if (StringUtils.isNotEmpty(dinnerDesk.getDinnerDeskId())) {
					dinnerDesk.setUpdateBy(dinnerRoom.getUpdateBy());
					dinnerDesk.setUpdateDate(DateUtil.now());
					dinnerDeskMapper.updateByPrimaryKeySelective(dinnerDesk);
				} else {
					dinnerDesk.setDinnerDeskId(IDGenerate.getId());
					dinnerDesk.setDinnerRoomId(dinnerRoom.getDinnerRoomId());
					dinnerDesk.setCreateBy(dinnerRoom.getCreateBy());
					dinnerDesk.setCreateDate(DateUtil.now());
					dinnerDesk.setUpdateBy(dinnerRoom.getUpdateBy());
					dinnerDesk.setUpdateDate(DateUtil.now());
					dinnerDesk.setDelFlag("N");
					dinnerDeskMapper.insert(dinnerDesk);
				}

			}
		}
	}

	@Transactional
	public void updateByPrimaryKey(DinnerRoom dinnerRoom) {
		dinnerRoomMapper.updateByPrimaryKey(dinnerRoom);
	}

	@Override
	public boolean isDinnerRoomExist(DinnerRoom dinnerRoom) {
		List<DinnerRoom> dinnerRooms = dinnerRoomMapper.selectDinnerRooms(dinnerRoom);
		return ListUtils.isEmpty(dinnerRooms) ? false : true;
	}

}
