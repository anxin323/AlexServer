package com.alex.network.handler;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.alex.network.eneity.CIMConstant;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.Message;
import com.alex.network.eneity.ReplyBody;
import com.alex.network.eneity.SentBody;

/**
 * 客户端请求的入口，所有请求都首先经过它分发处理
 */
public class MainIOHandler extends IoHandlerAdapter{
	
	private Message exMessage = new Message();

	protected final Log logger = LogFactory.getLog(MainIOHandler.class);

	private HashMap<String, CIMRequestHandler> handlers = new HashMap<String, CIMRequestHandler>();

	public void sessionCreated(IoSession session) throws Exception {
		//logger.warn("sessionCreated()... from "+ session.getRemoteAddress().toString());
	
	}

	public void sessionOpened(IoSession session) throws Exception {
		String xml = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
		session.write(xml);
		logger.warn("sessionOpened()... from "+ session.getRemoteAddress().toString());
	}

	
	
	/**
	 * 接收信息
	 */
	public void messageReceived(IoSession ios, Object message) throws Exception {
		
		logger.warn("============="+ios.getRemoteAddress()+" request from server "+ios.getLocalAddress()+" of msg is : "+ message.toString());
		try {
			CIMSession cimSession = new CIMSession(ios);
			ReplyBody reply = new ReplyBody();// 应答对象
			SentBody body = (SentBody) message;
			String key = body.getKey();
			// spring aop inject
			CIMRequestHandler handler = handlers.get(key);
			//CIMRequestHandler handler = new AuthHandler();
			if (handler == null) {
				reply.setCode(CIMConstant.ReturnCode.CODE_405);
				reply.setCode("KEY [" + key + "] 服务端未定义");
			} else {
				reply = handler.process(cimSession, body);
			}

			if (reply != null) {
				reply.setKey(key);
				cimSession.write(reply);
				logger.warn("============="+ios.getLocalAddress()+" response to client "+ios.getRemoteAddress()+" of msg is : "+ reply.toString());
			}

			// 设置心跳时间
			cimSession.setHeartbeat(System.currentTimeMillis());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * session close time,execute this function
	 */
	public void sessionClosed(IoSession ios) throws Exception {
		logger.warn("MainIOHandler:sessionClosed.RemoteAddress is "+ios.getRemoteAddress());
		
		try {
			CIMSession cimSession = new CIMSession(ios);
//			logger.warn("sessionClosed()... from "
//					+ cimSession.getRemoteAddress());
			CIMRequestHandler handler = handlers.get("sessionClosedHander");
			if (handler != null
					&& cimSession.containsAttribute(CIMConstant.SESSION_KEY)) {
				handler.process(cimSession, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 */
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
//		logger.warn("sessionIdle()... from "
//				+ session.getRemoteAddress().toString());
		CIMSession cimSession = new CIMSession(session);
		/*if (!session.containsAttribute(CIMConstant.SESSION_KEY)) {
			cimSession.close(true);
		} else {
			// 如果5分钟之内客户端没有发送心态，则可能客户端断网，关闭连接 
			Long heartbeat = cimSession.getHeartbeat();
			if (heartbeat == null) {
				Object objheart = cimSession.getIoSession().getAttribute(
						CIMConstant.HEARTBEAT_KEY);
				if (objheart != null) {
					heartbeat = Long.valueOf(objheart.toString());
				}
			}
			 code start 
			if (heartbeat != null && System.currentTimeMillis() - heartbeat >= 300000)
			{
				logger.fatal("MainIOHandler Client:"+session.getAttribute(CIMConstant.SESSION_KEY).toString()+" "+session.getLocalAddress()+"空闲5分钟，没发心跳包，被迫关闭连接!!!");
				logger.warn("kongxian:"+ (System.currentTimeMillis() - Long.valueOf(heartbeat)));
				//关闭Session连接
				cimSession.close(true);
				//删除数据库表中记录
				CIMRequestHandler handler = handlers.get("sessionClosedHander");
				if (handler != null && cimSession.containsAttribute(CIMConstant.SESSION_KEY)) {
					handler.process(cimSession, null);
				}
			}
			 code end 
		}*/
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.fatal(cause);
		logger.error("exceptionCaught()... from " + session.getRemoteAddress());
		logger.error(cause);
		cause.printStackTrace();
	}

	/**
	 */
	public void messageSent(IoSession session, Object message) throws Exception {
		// 设置心跳时间
		CIMSession cimSession = new CIMSession(session);
		cimSession.setHeartbeat(System.currentTimeMillis());
		//logger.warn("MainIOHandler:messageSent loginTime is "+cimSession.getAttribute("loginTime"));
		//logger.warn("MainIOHandler:messageSent account is "+cimSession.getAttribute(CIMConstant.SESSION_KEY));

		
	}

	public HashMap<String, CIMRequestHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(HashMap<String, CIMRequestHandler> handlers) {
		this.handlers = handlers;
	}

	public Message getExMessage() {
		return exMessage;
	}

	public void setExMessage(Message exMessage) {
		this.exMessage = exMessage;
	}

	 
}