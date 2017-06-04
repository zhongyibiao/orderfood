package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.Article;
import com.xiaoweiyunchuang.orderfood.domain.ReplyMessage;

@Mapper
public interface ReplyMessageMapper {

	void addReplyMessage(ReplyMessage reply);

	void addArticle(Article article);

	List<ReplyMessage> selectReplyMessages(ReplyMessage reply);

}
