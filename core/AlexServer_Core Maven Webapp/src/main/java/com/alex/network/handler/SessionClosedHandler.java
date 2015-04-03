 
package com.alex.network.handler;


import java.util.Collection;

import org.apache.log4j.Logger;

import com.alex.network.eneity.CIMConstant;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.ReplyBody;
import com.alex.network.eneity.SentBody;
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
			/*ClusterSessionManager sessionManager=((ClusterSessionManager) ContextHolder.getBean("clusterSessionManager"));
			if(ios.getAttribute(CIMConstant.SESSION_KEY)==null)
			{
				return null;
			}
			String account = ios.getAttribute(CIMConstant.SESSION_KEY).toString();
			
			 code start 
			Collection<CIMSession> offlineSessionList = sessionManager.getSessions(account);
			for(CIMSession session:offlineSessionList){ 
				 
				
//				 if(!sessionIsConnected){
				 if(session.getDeviceId().length()==64 && session.getChannel().equals("ios")){
					     code start 
					 	SessionManagerService sessionServiceImpl=((SessionManagerService) ContextHolder.getBean("sessionServiceImpl"));
					    CIMSession iosC = sessionServiceImpl.getSession(account);
					    
						iosC.setBindTime(Long.parseLong("0"));
						logger.warn("***************************** SessionClosedHandler:"+iosC.getBindTime());
						sessionServiceImpl.update(iosC);
						
						 code end 
						logger.warn("SessionClosedHandler:ios 关闭判断....");
				 }else{
					 System.err.println("session.getbindTime is "+ session.getBindTime());
					 
//					 sessionManager.removeSession(session);
					 boolean sessionIsConnected = GetSessionUtil.isConnected(session.getHost(),String.valueOf(session.getNid()));
					 if(!sessionIsConnected){ 
						 sessionManager.deleteClusterSession(session.getAccount(), session.getNid().toString(), session.getHost());
					 }
					 
				 }
//				 }
			}
			
			 code end 
			logger.debug("session closed :account:["+ account+"]-----------------------------" );
			UserService uservice = ContextHolder.getBean(UserServiceImpl.class);
			if(offlineSessionList.size()==1){
				uservice.updateOnline(account,  User.OFF_LINE);
			}
//			sessionManager.removeSession(account);
			LogoutHandler.broadcastOfflineMessage(account);*/
			
		} catch (Exception e) {
			logger.warn("database is close....");
			//e.printStackTrace();
		}
		
		return null;
	}
	
	
 
	
}