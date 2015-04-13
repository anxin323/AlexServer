 
package com.alex.network.handler;


import java.util.Collection;

import org.apache.log4j.Logger;

import com.alex.common.context.ContextHolder;
import com.alex.network.eneity.CIMConstant;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.ReplyBody;
import com.alex.network.eneity.SentBody;
import com.alex.network.session.ClusterSessionManager;
import com.alex.network.session.SessionManager;
import com.alex.web.domain.User;
import com.alex.web.service.UserServiceImpl;
//import com.farsunset.cim.nio.session.DefaultSessionManager;

/**
 * 断开连接，清除session
 * 
 */
public class SessionClosedHandler implements CIMRequestHandler {


	protected final Logger logger = Logger.getLogger(SessionClosedHandler.class);

	public ReplyBody process(CIMSession ios, SentBody message) {
		try {
			logger.debug("SessionClosedHandler:session closed :account:["+  ios.getAttribute(CIMConstant.SESSION_KEY).toString()+"]-----------------------------" );
			SessionManager sessionManager=((SessionManager) ContextHolder.getBean("clusterSessionManager"));
			if(ios.getAttribute(CIMConstant.SESSION_KEY)==null)
			{
				return null;
			}
			String account = ios.getAttribute(CIMConstant.SESSION_KEY).toString();
			
			sessionManager.removeSession(account);
			logger.info("removeSession account is "+ account);
			
		} catch (Exception e) {
			logger.warn("database is close....");
			//e.printStackTrace();
		}
		
		return null;
	}
	
	
 
	
}