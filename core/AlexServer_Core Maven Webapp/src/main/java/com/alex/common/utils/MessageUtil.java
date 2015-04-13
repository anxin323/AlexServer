package com.alex.common.utils;

import com.alex.network.eneity.ImMessage;
import com.alex.network.eneity.Message;




public class MessageUtil {
	 
		public static Message  transform(ImMessage msg)
		{
			Message m = new Message();
			m.setContent( msg.getContent());
			m.setFile( msg.getFile());
			m.setTitle( msg.getTitle());
			m.setFileType( msg.getFileType());
			m.setReceiver( msg.getReceiver());
			m.setSender( msg.getSender());
			m.setType(String.valueOf( msg.getType()));
			m.setMid(msg.getGid());
			m.setTimestamp(Long.valueOf(msg.getCreateTime()));
			m.setNiname(msg.getNiname());
			m.setHeadimg(msg.getHeadimg());
			m.setReceiver_headimg(msg.getReceiver_headimg());
			m.setUsercustomerid(msg.getUsercustomerid());
			m.setReceiver_niname(msg.getReceiver_niname());
			m.setS_user(msg.getS_user());
			return m;
		}
		
		
		public static ImMessage msgToModel(Message msg){
			ImMessage m =new ImMessage();
			m.setContent( msg.getContent());
			m.setFile( msg.getFile());
			m.setTitle( msg.getTitle());
			m.setFileType( msg.getFileType());
			m.setReceiver( msg.getReceiver());
			m.setSender( msg.getSender());
			m.setType(String.valueOf( msg.getType()));
			m.setGid(msg.getMid());
			m.setCreateTime(String.valueOf(System.currentTimeMillis()));
			m.setNiname(msg.getNiname());
			m.setHeadimg(msg.getHeadimg());
			m.setReceiver_headimg(msg.getReceiver_headimg());
			m.setUsercustomerid(msg.getUsercustomerid());
			m.setReceiver_niname(msg.getReceiver_niname());
			m.setS_user(msg.getS_user());
			return m;
		}
		 
}
