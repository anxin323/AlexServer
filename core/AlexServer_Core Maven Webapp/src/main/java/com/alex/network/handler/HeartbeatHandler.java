 
package com.alex.network.handler;

import org.apache.log4j.Logger;

import com.alex.network.eneity.CIMConstant;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.ReplyBody;
import com.alex.network.eneity.SentBody;
/**
 *客户端心跳实现
 * 
 * @author
 */
public class HeartbeatHandler implements CIMRequestHandler {

	protected final Logger logger = Logger.getLogger(HeartbeatHandler.class);

	public ReplyBody process(CIMSession session, SentBody message) {

		logger.warn(session.getAccount()+" heartbeat... from "+session.getRemoteAddress().toString());
		ReplyBody reply = new ReplyBody();
		reply.setKey(CIMConstant.RequestKey.CLIENT_HEARTBEAT);
		reply.setCode(CIMConstant.ReturnCode.CODE_200);
		session.setHeartbeat(System.currentTimeMillis());
//		session.setAttribute("heartbeat", System.currentTimeMillis());
		 
		return reply;
	}
	
 
	
}