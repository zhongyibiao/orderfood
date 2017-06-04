package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.ReceiveMessage;

@Mapper
public interface ReceiveMessageMapper {

	public int addReceiveMessage(ReceiveMessage receiveMessage);

	public List<ReceiveMessage> selectReceiveMessages(ReceiveMessage receiveMessage);

}
