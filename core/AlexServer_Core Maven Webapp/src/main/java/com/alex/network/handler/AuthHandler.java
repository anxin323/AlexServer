package com.alex.network.handler;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alex.common.context.ContextHolder;
import com.alex.network.eneity.CIMConstant;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.ReplyBody;
import com.alex.network.eneity.SentBody;
import com.alex.network.service.SessionManagerService;
import com.alex.network.service.SessionManagerServiceImpl;
import com.alex.network.session.ClusterSessionManager;
import com.alex.network.session.SessionManager;

/**
 * 鉴权实现
 * 
 * @author
 */
public class AuthHandler implements CIMRequestHandler {
	protected final Log logger = LogFactory.getLog(AuthHandler.class);
//	@Autowired
//	@Qualifier("clusterSessionManager")
//	protected SessionManager sessionManager;
//	@Autowired
//	@Qualifier("sessionManagerServiceImpl")
//	private SessionManagerService database ;

	public ReplyBody process(CIMSession newSession, SentBody message) {

		ReplyBody reply = new ReplyBody();
		SessionManager sessionManager = ((SessionManager) ContextHolder
				.getBean("clusterSessionManager"));
		String host = "";

		String account = message.get("account");
		String password = message.get("password");
		logger.warn("account====>>>" + account);
		logger.warn("password====>>>" + password);
		if (account.equals("") || account == null) {
			reply.setCode(CIMConstant.ReturnCode.CODE_403);
			reply.setMessage("用户名或密码错误!");
		} else {
			Long sTime = System.currentTimeMillis();

			newSession.setAccount(account);
			newSession.setDeviceId(message.get("deviceId"));
			newSession.setChannel(message.get("channel"));
			newSession.setDeviceModel(message.get("device"));
			newSession.setGid(UUID.randomUUID().toString());
			// newSession.setAttribute("loginTime", System.currentTimeMillis());
			newSession.setAttribute("loginTime", sTime);
			host = "192.168.1.120";//com.alex.common.utils.IPUtil.getServerIp();
			newSession.setHost(host);

			logger.warn("AuthHandler:newSession.getIoSession().getId() is "
					+ newSession.getIoSession().getId());

			/**
			 * 由于客户端断线服务端可能会无法获知的情况，客户端重连时，需要关闭旧的连接
			 */

			newSession.setBindTime(sTime);
			newSession.setHeartbeat(sTime);
			sessionManager.addSession(account, newSession);
//			database.save(newSession);
			//reply.setCode(CIMConstant.ReturnCode.CODE_200);
			reply.setCode(account); 
		}

		logger.debug("auth :account:" + message.get("account")
				+ "-----------------------------" + reply.getCode());
		return reply;
	}

}