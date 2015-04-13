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
 * ��Ϣ����ʵ���� 1����type:0����fileType=8ʱ�������ͻ��˷���������,�������������ͻ��˷��͵ĺ�����Ϣ)��
 * 2����type:1�����������ͻ��˵ķ���Ⱥ��Ϣ����
 * ,��type:5������PC��.net��̨���ķ���Ⱥ��Ϣ�������͸�������ʱtype������Ϊ��type:3����
 * 3����type:2��ϵͳ��Ϣ��ichat��̨���͸����ѵ���Ϣ��
 * 4����type:4���ɰ汾��Ӫ����Ϣ����type:6���°��Ӫ����Ϣ�������ͻ��˵�Ⱥ�����Ź��ܣ������Ѻ͹�ע���˿ɼ�����
 * 5����type:100���Ӻ��������Ϣ�������Ӻ������󡢼Ӻ��ѽ��֪ͨ��Ϣ�� 6����type:400���ͷ���ص���Ϣ�� 7����type:999���������ߣ�
 * 8����type:1000����ʾ����ת�����յ���Ϣ��ʽ�����Ӫ����Ϣ��Ⱥ��Ϣ�� 7����type:7��ϵͳ��������������Ϣ��
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
	 * ���û�������Ϣ
	 * 1���ж���Ϣ�������Ƿ����ߣ�����������򱣴�������Ϣ��
	 * 2��������ߣ��жϽ������Ƿ��ڱ�̨�������ϣ���������������Ӧ����������Ϣ���ͽӿڣ�
	 * 3�������ͬһ̨��������������Ϣ���ͽӿڣ�
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
				log.warn("DefaultMessagePusher: ��Ϣ�������� = "
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
