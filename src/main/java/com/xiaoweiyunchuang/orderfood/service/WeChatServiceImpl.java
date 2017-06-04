package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoweiyunchuang.orderfood.domain.Article;
import com.xiaoweiyunchuang.orderfood.domain.ReceiveMessage;
import com.xiaoweiyunchuang.orderfood.domain.ReplyMessage;
import com.xiaoweiyunchuang.orderfood.mapper.ReceiveMessageMapper;
import com.xiaoweiyunchuang.orderfood.mapper.ReplyMessageMapper;

@Service
public class WeChatServiceImpl implements WeChatService {

	@Autowired
	ReceiveMessageMapper receiveMapper;

	@Autowired
	ReplyMessageMapper replyMapper;

	@Transactional
	public void addReceiveMessage(ReceiveMessage message) {
		receiveMapper.addReceiveMessage(message);
	}

	public List<ReceiveMessage> selectReceiveMessage(ReceiveMessage message) {
		return receiveMapper.selectReceiveMessages(message);
	}

	public List<ReplyMessage> selectReplyMessage(ReplyMessage reply) {
		return replyMapper.selectReplyMessages(reply);
	}

	@Transactional
	public void addReplyMessage(ReplyMessage reply) {
		replyMapper.addReplyMessage(reply);
		if (ReplyMessage.NEWS.equals(reply.getMsgType()) && null != reply.getArticles()) {
			List<Article> articles = reply.getArticles();
			for (Article a : articles) {
				a.setReplyId(reply.getReplyid());
				replyMapper.addArticle(a);
			}
		}
	}
}
