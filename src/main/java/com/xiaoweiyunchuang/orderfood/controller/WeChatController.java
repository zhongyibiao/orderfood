package com.xiaoweiyunchuang.orderfood.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xiaoweiyunchuang.orderfood.common.WeChatUtil;
import com.xiaoweiyunchuang.orderfood.domain.ReceiveMessage;
import com.xiaoweiyunchuang.orderfood.domain.ReplyMessage;
import com.xiaoweiyunchuang.orderfood.service.WeChatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "微信核心 web入口 ", description = "提供RESTful风格API的收到消息、回复消息的服务")
@RestController
public class WeChatController extends AbstractController {

	private static final String TOKEN = "xiaoweiyunchuang";

	@Autowired
	private WeChatService weChatService;

	// 接收微信公众号接收的消息，处理后再做相应的回复
	@RequestMapping(value = "/weChat", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public String replyMessage(HttpServletRequest request) {
		// 仅处理微信服务端发的请求
		if (checkWeChatRequest(request)) {
			Map<String, String> requestMap = WeChatUtil.parseXml(request);
			logger.info("requestMap:" + requestMap);
			ReceiveMessage message = WeChatUtil.mapToMessage(requestMap);
			//weChatService.addReceiveMessage(message);// 保存接受消息到数据库
			String replyContent = ReplyMessage.WELCOME_CONTENT;
			String type = message.getMsgType();
			if (type.equals(ReceiveMessage.TEXT)) {// 仅处理文本回复内容
				String content = message.getContent();// 消息内容
				String[] cs = content.split("_");// 消息内容都以下划线_分隔
				try {
					replyContent="你是最好的消费者。";
					logger.info("content:" + content);

				} catch (NumberFormatException e) {
					replyContent = ReplyMessage.ERROR_CONTENT;
				}

			}
			// 拼装回复消息
			ReplyMessage reply = new ReplyMessage();
			reply.setToUserName(message.getFromUserName());
			reply.setFromUserName(message.getToUserName());
			reply.setCreateTime(new Date());
			reply.setMsgType(ReplyMessage.TEXT);
			reply.setContent(replyContent);
			//weChatService.addReplyMessage(reply);// 保存回复消息到数据库
			// 将回复消息序列化为xml形式
			String back = WeChatUtil.replyToXml(reply);
			logger.info("back:"+back);
			return back;
		} else {
			return "error";
		}
	}

	// 微信公众平台验证url是否有效使用的接口
	@RequestMapping(value = "/weChat", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String initWeChatURL(HttpServletRequest request) {
		String echostr = request.getParameter("echostr");
		if (checkWeChatRequest(request) && echostr != null) {
			logger.info("initWeChatURL:" + "echostr");
			return echostr;
		} else {
			logger.info("initWeChatURL:" + "error");
			return "error";
		}
	}

	/**
	 * 根据token计算signature验证是否为weChat服务端发送的消息
	 */
	private static boolean checkWeChatRequest(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (signature != null && timestamp != null && nonce != null) {
			String[] strSet = new String[] { TOKEN, timestamp, nonce };
			java.util.Arrays.sort(strSet);
			String key = "";
			for (String string : strSet) {
				key = key + string;
			}
			String pwd = WeChatUtil.sha1(key);
			return pwd.equals(signature);
		} else {
			return false;
		}
	}

	@ApiOperation("查询收到消息")
	@RequestMapping(value = "/messages/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReceiveMessage>> getReceiveMessage(@ApiParam("pageNum") @PathVariable Integer pageNum,
			@ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}

		ReceiveMessage message = new ReceiveMessage();
		List<ReceiveMessage> messages = weChatService.selectReceiveMessage(message);
		return new ResponseEntity<List<ReceiveMessage>>(messages, HttpStatus.OK);
	}

	@ApiOperation("查询 回复消息")
	@RequestMapping(value = "/replys/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyMessage>> getReplyMessage(@ApiParam("pageNum") @PathVariable Integer pageNum,
			@ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		ReplyMessage reply = new ReplyMessage();
		List<ReplyMessage> replys = weChatService.selectReplyMessage(reply);
		return new ResponseEntity<List<ReplyMessage>>(replys, HttpStatus.OK);
	}

}
