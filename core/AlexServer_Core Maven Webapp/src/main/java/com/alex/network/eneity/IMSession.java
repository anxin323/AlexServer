package com.alex.network.eneity;

import java.io.Serializable;


public class IMSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	public IMSession(){
		
	}

	
	private String gid;//session全局ID
	
	private String nid;//session在本台服务器上的ID
	
	private String deviceid;//客户端设备ID
	
	private String host;//session绑定的服务器IP
	
	private String account;//session绑定的账号
	
	private String channel;//终端设备类型
	
	private String devicemodel;//终端设备型号
	
	private String bindtime;//登录时间

	private String heartbeat;//心跳时间

	 //0: 未发送 1：已发送 2：已接收 3：已查看
    private String status;
	
	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

//	public String getDeviceId() {
//		return deviceid;
//	}
//
//	public void setDeviceId(String deviceid) {
//		this.deviceid = deviceid;
//	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

/*	public String getDeviceModel() {
		return devicemodel;
	}

	public void setDeviceModel(String devicemodel) {
		this.devicemodel = devicemodel;
	}

	public String getBindTime() {
		return bindtime;
	}

	public void setBindTime(String bindtime) {
		this.bindtime = bindtime;
	}*/

	public String getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(String heartbeat) {
		this.heartbeat = heartbeat;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicemodel() {
		return devicemodel;
	}

	public void setDevicemodel(String devicemodel) {
		this.devicemodel = devicemodel;
	}

	public String getBindtime() {
		return bindtime;
	}

	public void setBindtime(String bindtime) {
		this.bindtime = bindtime;
	}

	
}
