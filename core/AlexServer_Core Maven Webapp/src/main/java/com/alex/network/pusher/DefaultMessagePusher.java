package com.alex.network.pusher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alex.common.context.ContextHolder;
import com.alex.common.utils.IPUtil;
import com.alex.common.utils.MessageUtil;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.ImMessage;
import com.alex.network.session.ClusterSessionManager;
import com.alex.network.session.SessionManager;
import com.alibaba.fastjson.JSONObject;

/**
 * 消息发送实现类 1、【type:0】当fileType=8时是搜脉客户端发布的商情,其他的是搜脉客户端发送的好友消息)：
 * 2、【type:1】来自搜脉客户端的发送群消息请求
 * ,【type:5】来自PC（.net后台）的发送群消息请求，推送给接收者时type必须设为【type:3】；
 * 3、【type:2】系统消息，ichat后台推送给好友的消息：
 * 4、【type:4】旧版本的营销消息，【type:6】新版的营销消息（搜脉客户端的群发短信功能，仅好友和关注的人可见）：
 * 5、【type:100】加好友相关消息，包括加好友请求、加好友结果通知消息： 6、【type:400】客服相关的消息： 7、【type:999】被迫下线：
 * 8、【type:1000】表示处理（转换接收的消息格式）后的营销消息和群消息； 7、【type:7】系统推送搜脉新闻消息；
 */
@Component(value="defaultMessagePusher")
public class DefaultMessagePusher implements CIMMessagePusher {

	private final Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("clusterSessionManager")
	private SessionManager sessionManager;

//	public void setSessionManager(ClusterSessionManager sessionManager) {
//		this.sessionManager = sessionManager;
//	}

	/**
	 * 向用户发送消息
	 * 1、判断消息接收者是否在线，如果不在线则保存离线消息；
	 * 2、如果在线，判断接收者是否在本台服务器上，如果不在则调用相应服务器的消息发送接口；
	 * 3、如果在同一台服务器，调用消息推送接口；
	 * @param msg
	 * @throws Exception
	 */
	@Override
	public void pushMessageToUser(ImMessage msg) {
		// TODO Auto-generated method stub
		CIMSession sessionReciver = sessionManager.getSession(msg.getReceiver());
		if (sessionReciver == null) {
			log.warn(msg.getReceiver() + " is not online.");
			// ((MessageService)ContextHolder.getBean("messageServiceImpl")).save(msg);
			return;
		}
//		log.info("IPUtil.getServerIp()="+IPUtil.getServerIp());
//		log.info("sessionReciver.getHost()="+sessionReciver.getHost());
		if (IPUtil.getServerIp().equals(sessionReciver.getHost())) {
			String sender = null;
			if (sessionReciver.isConnected()) {
				try {
					JSONObject jsonObject = JSONObject.parseObject(msg
							.getS_user());
					sender = jsonObject.get("structureSender").toString();
				} catch (Exception e) {
					sender = msg.getSender();
				}
				msg.setSender(sender);
				log.warn("DefaultMessagePusher: 消息发送内容 = "
						+ MessageUtil.transform(msg).toString());
				sessionReciver.write(MessageUtil.transform(msg));
				return;
			} else {
				log.warn("DefaultMessagePusher:user is not online."
						+ msg.requestObjPrintXMl());
				try {
					JSONObject jsonObject = JSONObject.parseObject(msg
							.getS_user());
					sender = jsonObject.get("structureSender").toString();
				} catch (Exception e) {
					sender = msg.getSender();
				}
				msg.setSender(sender);
//				((MessageService) ContextHolder.getBean("messageServiceImpl"))
//						.save(msg);
				return;
			}
		}
	}
}
