package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 微信消息（服务端接收到的）
 * 
 */
public class ReceiveMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151845539697213121L;

	public static final String TEXT = "text";
	public static final String IMAGE = "image";
	public static final String LOCATION = "location";
	public static final String LINK = "link";
	public static final String EVENT = "event";

	@XStreamOmitField
	private String receiveId;// 数据库存储的id

	// 开发者微信号
	private String toUserName;
	// 发送方帐号（一个OpenID）
	private String fromUserName;
	// 消息创建时间
	private Date createTime;
	// 消息类型（text/image/location/link）
	private String msgType;
	// 消息id，64位整型
	private String msgId;

	// 消息内容 (文本消息专有)
	private String content;

	// 图片链接 (图片消息专有)
	private String picUrl;

	// 消息标题 (链接消息专有)
	private String title;
	// 消息描述 (链接消息专有)
	private String description;
	// 消息链接 (链接消息专有)
	private String url;

	// 语音格式，如amr，speex等(语音消息专有)
	private String format;
	// 语音识别结果，UTF8编码(语音消息专有)
	private String recognition;

	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。(视频消息专有)
	private String thumbMediaId;

	// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据(语音消息、视频消息专有)
	private String mediaId;

	// 地理位置纬度 Location_X(地理位置专有)
	private String locationX;
	// 地理位置经度 Location_Y(地理位置专有)
	private String locationY;
	// 地图缩放大小 (地理位置专有)
	private String scale;
	// 地理位置信息 (地理位置专有)
	private String label;

	// 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件) （事件推送专有）
	private String event;
	// 事件KEY值，与自定义菜单接口中KEY值对应（事件推送专有）
	private String eventKey;

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
