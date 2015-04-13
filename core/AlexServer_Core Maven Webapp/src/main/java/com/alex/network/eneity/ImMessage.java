package com.alex.network.eneity;

import java.util.UUID;

public class ImMessage {

	private static final long serialVersionUID = 1845362556725768545L;

	public static final String STATUS_NOT_RECEIVED = "0";// 未接受
	public static final String STATUS_RECEIVED = "1";// 已接收
	public static final String STATUS_READ = "2";// 已读取

	public static final String TYPE_0 = "0";
	public static final String TYPE_1 = "1";
	public static final String TYPE_2 = "2";
	public static final String TYPE_3 = "3";
	public static final String TYPE_4 = "4";// 营销
	public static final String TYPE_6 = "6"; // 合并之前营销
	public static final String TYPE_5 = "5";// PC过来的动态消息
	public static final String TYPE_100 = "100";
	public static final String TYPE_400 = "400";
	public static final String TYPE_999 = "999";
	public static final String TYPE_1000 = "1000";

	public static final String TYPE_7 = "7";// .Net后台推送给搜脉的新闻

	public ImMessage() {
		gid = String.valueOf(UUID.randomUUID());
		createTime = String.valueOf(System.currentTimeMillis());
		status = STATUS_NOT_RECEIVED;
	}

	private String gid;

	private String sender;

	private String receiver;

	private String file;

	private String fileType;

	private String title;

	private String content;

	// 0: 普通消息 1：用户向群发消息 2：系统消息 3：群向用户的消息
	private String type;

	// 0: 未发送 1：已发送 2：已接收 3：已查看
	private String status;

	private String createTime;

	private String updateTime;

	// 发送者真实姓名 add by yyc
	public String niname;

	// 发送者头像 add by yyc
	public String headimg;

	// 发送者usercustomerid add by yyc
	public String usercustomerid;

	public String receiver_headimg;

	// 接收者真实姓名 add by yyc
	public String receiver_niname;

	public String receivercustomerid;

	public String s_user;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	// 是否为动作消息，无需记录，无需显示
	public boolean isActionMessage() {

		return type.startsWith("9");
	}

	public String getNiname() {
		return niname;
	}

	public void setNiname(String niname) {
		this.niname = niname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getUsercustomerid() {
		return usercustomerid;
	}

	public void setUsercustomerid(String usercustomerid) {
		this.usercustomerid = usercustomerid;
	}

	public String getReceiver_headimg() {
		return receiver_headimg;
	}

	public void setReceiver_headimg(String receiver_headimg) {
		this.receiver_headimg = receiver_headimg;
	}

	public String getReceiver_niname() {
		return receiver_niname;
	}

	public void setReceiver_niname(String receiver_niname) {
		this.receiver_niname = receiver_niname;
	}

	public String requestObjPrintXMl() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<message>");
		buffer.append("<content><![CDATA[")
				.append(this.getContent() == null ? "" : this.getContent())
				.append("]]></content>");
		buffer.append("<timestamp>").append(System.currentTimeMillis())
				.append("</timestamp>");
		buffer.append("<file>")
				.append(this.getFile() == null ? "" : this.getFile())
				.append("</file>");
		buffer.append("<fileType>")
				.append(this.getFileType() == null ? "" : this.getFileType())
				.append("</fileType>");
		buffer.append("<gid>")
				.append(this.getGid() == null ? "" : this.getGid())
				.append("</gid>");
		buffer.append("<headimg>")
				.append(this.getHeadimg() == null ? "" : this.getHeadimg())
				.append("</headimg>");
		buffer.append("<receiver_headimg>")
				.append(this.getReceiver_headimg() == null ? "" : this
						.getReceiver_headimg()).append("</receiver_headimg>");
		buffer.append("<niname>")
				.append(this.getNiname() == null ? "" : this.getNiname())
				.append("</niname>");
		buffer.append("<receiver>")
				.append(this.getReceiver() == null ? "" : this.getReceiver())
				.append("</receiver>");
		buffer.append("<sender>")
				.append(this.getSender() == null ? "" : this.getSender())
				.append("</sender>");
		buffer.append("<status>")
				.append(this.getStatus() == null ? "" : this.getStatus())
				.append("</status>");
		buffer.append("<title>")
				.append(this.getTitle() == null ? "" : this.getTitle())
				.append("</title>");
		buffer.append("<type>")
				.append(this.getType() == null ? "" : this.getType())
				.append("</type>");
		buffer.append("<updateTime>")
				.append(this.getUpdateTime() == null ? "" : this
						.getUpdateTime()).append("</updateTime>");
		buffer.append("<receiver_niname>")
				.append(this.getReceiver_niname() == null ? "" : this
						.getReceiver_niname()).append("</receiver_niname>");
		buffer.append("<usercustomerid>")
				.append(this.getUsercustomerid() == null ? "" : this
						.getUsercustomerid()).append("</usercustomerid>");
		buffer.append("<receivercustomerid>")
				.append(this.getReceivercustomerid() == null ? "" : this
						.getReceivercustomerid())
				.append("</receivercustomerid>");
		buffer.append("<s_user>")
				.append(this.getS_user() == null ? "" : this.getS_user())
				.append("</s_user>");

		buffer.append("</message>");
		return buffer.toString();
	}

	public String getReceivercustomerid() {
		return receivercustomerid;
	}

	public void setReceivercustomerid(String receivercustomerid) {
		this.receivercustomerid = receivercustomerid;
	}

	public String getS_user() {
		return s_user;
	}

	public void setS_user(String s_user) {
		this.s_user = s_user;
	}

	// public String getUser() {
	// return user;
	// }
	//
	// public void setUser(String user) {
	// this.user = user;
	// }

}
