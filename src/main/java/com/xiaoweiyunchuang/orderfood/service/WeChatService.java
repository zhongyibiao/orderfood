package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.ReceiveMessage;
import com.xiaoweiyunchuang.orderfood.domain.ReplyMessage;

public interface WeChatService {

	void addReceiveMessage(ReceiveMessage receiveMessage);

	List<ReceiveMessage> selectReceiveMessage(ReceiveMessage receiveMessage);

	List<ReplyMessage> selectReplyMessage(ReplyMessage replyMessage);

	void addReplyMessage(ReplyMessage replyMessage);
}
