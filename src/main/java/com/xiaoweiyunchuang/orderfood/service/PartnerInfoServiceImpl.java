package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.DinnerRoom;
import com.xiaoweiyunchuang.orderfood.domain.PartnerInfo;
import com.xiaoweiyunchuang.orderfood.mapper.DinnerRoomMapper;
import com.xiaoweiyunchuang.orderfood.mapper.PartnerInfoMapper;

@Service
public class PartnerInfoServiceImpl implements PartnerInfoService {

	@Autowired
	PartnerInfoMapper partnerInfoMapper;
	@Autowired
	private DinnerRoomMapper dinnerRoomMapper;

	public boolean isPartnerInfoExist(PartnerInfo partnerInfo) {
		List<PartnerInfo> partnerInfos = selectPartnerInfos(partnerInfo);
		return ListUtils.isEmpty(partnerInfos) ? false : true;
	}

	@Transactional
	public void deleteByPrimaryKey(String partnerId) {
		partnerInfoMapper.deleteByPrimaryKey(partnerId);
	}

	@Transactional
	public void insert(PartnerInfo partnerInfo) {
		partnerInfoMapper.insert(partnerInfo);
		List<DinnerRoom> dinnerRooms = partnerInfo.getDinnerRooms();
		if (!ListUtils.isEmpty(dinnerRooms)) {
			for (DinnerRoom dinnerRoom : dinnerRooms) {
				dinnerRoom.setDinnerRoomId(IDGenerate.getId());
				dinnerRoom.setPartnerId(partnerInfo.getPartnerId());
				dinnerRoom.setCreateBy(partnerInfo.getCreateBy());
				dinnerRoom.setCreateDate(DateUtil.now());
				dinnerRoom.setUpdateBy(partnerInfo.getUpdateBy());
				dinnerRoom.setUpdateDate(DateUtil.now());
				dinnerRoom.setDelFlag("N");
				dinnerRoomMapper.insert(dinnerRoom);
			}
		}

	}

	@Transactional
	public void insertSelective(PartnerInfo partnerInfo) {
		partnerInfoMapper.insertSelective(partnerInfo);
	}

	public PartnerInfo selectByPrimaryKey(String partnerId) {
		return partnerInfoMapper.selectByPrimaryKey(partnerId);
	}

	public List<PartnerInfo> selectPartnerInfos(PartnerInfo partnerInfo) {
		return partnerInfoMapper.selectPartnerInfos(partnerInfo);
	}

	@Transactional
	public void updateByPrimaryKeySelective(PartnerInfo partnerInfo) {
		partnerInfoMapper.updateByPrimaryKeySelective(partnerInfo);
		List<DinnerRoom> dinnerRooms = partnerInfo.getDinnerRooms();
		if (!ListUtils.isEmpty(dinnerRooms)) {
			for (DinnerRoom dinnerRoom : dinnerRooms) {
				if (StringUtils.isNotEmpty(dinnerRoom.getDinnerRoomId())) {
					dinnerRoom.setUpdateBy(dinnerRoom.getUpdateBy());
					dinnerRoom.setUpdateDate(DateUtil.now());
					dinnerRoomMapper.updateByPrimaryKeySelective(dinnerRoom);
				} else {
					dinnerRoom.setDinnerRoomId(IDGenerate.getId());
					dinnerRoom.setPartnerId(partnerInfo.getPartnerId());
					dinnerRoom.setCreateBy(dinnerRoom.getCreateBy());
					dinnerRoom.setCreateDate(DateUtil.now());
					dinnerRoom.setUpdateBy(dinnerRoom.getUpdateBy());
					dinnerRoom.setUpdateDate(DateUtil.now());
					dinnerRoom.setDelFlag("N");
					dinnerRoomMapper.insert(dinnerRoom);
				}

			}
		}

	}

	@Transactional
	public void updateByPrimaryKey(PartnerInfo partnerInfo) {
		partnerInfoMapper.updateByPrimaryKey(partnerInfo);

	}
}
