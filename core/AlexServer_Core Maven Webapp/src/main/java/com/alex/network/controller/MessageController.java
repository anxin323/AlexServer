package com.alex.network.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alex.common.context.ContextHolder;
import com.alex.network.eneity.ImMessage;
import com.alex.network.pusher.CIMMessagePusher;
import com.alex.network.pusher.DefaultMessagePusher;
import com.google.gson.Gson;

/**
 * 发送消息的接口
 * 1、判断消息类型，转交给相应的消息推送器处理
 * 2、不同的消息推送器可采用线程池处理
 * @author hadoop
 *
 */
@Controller
@RequestMapping(value = "message")
public class MessageController {
	private final Log log = LogFactory.getLog(MessageController.class);
	
	private static HashMap<String,String> messagePushMap;

    static {
    	
    	messagePushMap = new HashMap<String,String>();
    	messagePushMap.put(ImMessage.TYPE_0, "messagePusher"); // type=0:好友 ; type=0&fileType=8 :商情消息
    	messagePushMap.put(ImMessage.TYPE_1,"groupMessagePusher"); // type=1 :群消息(手机) 
    	messagePushMap.put(ImMessage.TYPE_5,"pcGroupMessagePusher"); // type=5 :群消息(PC)
    	messagePushMap.put(ImMessage.TYPE_2, "systemMessagePusher"); // type=2 :系统消息
    	messagePushMap.put(ImMessage.TYPE_4, "attentionGroupMessagePusher"); //type=4 营销消息
    	messagePushMap.put(ImMessage.TYPE_6, "weixinAttentionGroupMessagePusher"); //type=6 合并之前营销消息
    	messagePushMap.put(ImMessage.TYPE_100, "friendMessagePusher"); //type=100 :加好友消息
    	messagePushMap.put(ImMessage.TYPE_400, "leaguerMessagePusher"); // type=400 :客服消息
    	
    	messagePushMap.put(ImMessage.TYPE_1000, "controlMessagePusher"); // type=1000: 控制 3,4,6 跳转
    	messagePushMap.put(ImMessage.TYPE_999, "borcedorcedOffLineMessagePusher"); //被迫下线消息
    	
    	//2015-01-12 add by jjl 新增系统推送新闻的功能
    	messagePushMap.put(ImMessage.TYPE_7, "systemNewsPusher");
    }
    @RequestMapping(params = "send")
	public String send(ImMessage message,HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ImMessage message = new ImMessage();
    	log.warn("MessageController:send param =="+message.requestObjPrintXMl()); 
    	HashMap<String,Object> datamap = new HashMap<String,Object>();
    	HashMap<String,String> data = new HashMap<String,String>();
		response.setContentType("text/json;charset=UTF-8");
		datamap.put("code", 200);
		try{
//			messagePusher = (DefaultMessagePusher) ContextHolder.getBean(messagePushMap.get(message.getType()));
			CIMMessagePusher messagePusher = (CIMMessagePusher) ContextHolder.getBean("defaultMessagePusher");
			messagePusher.pushMessageToUser(message);
			
			log.warn("============= "+request.getRemoteAddr()+" request from server "+request.getLocalAddr()+" of msg transaction to xml is : "+message.requestObjPrintXMl());
			data.put("id", message.getGid());
	        data.put("createTime", message.getCreateTime());
	        datamap.put("data", data);
		}catch(Exception e){
			
			datamap.put("code", 500);
			e.printStackTrace();
		}
        response.getWriter().print(new Gson().toJson(datamap));
        return null;
    }
}
