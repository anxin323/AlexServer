 
package com.alex.network.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.alex.network.eneity.CIMConstant;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.Message;
import com.alex.network.eneity.ReplyBody;
import com.alex.network.eneity.SentBody;
import com.alex.web.domain.User;
import com.alex.web.service.UserServiceImpl;
import com.alibaba.druid.Constants;
//import com.farsunset.cim.nio.session.DefaultSessionManager;

/**
 * 退出连接实现
 * 
 */
public class LogoutHandler implements CIMRequestHandler {

	public ReplyBody process(CIMSession ios, SentBody message) {
		System.err.println("LogoutHandler :session closed :account:["+  ios.getAttribute(CIMConstant.SESSION_KEY).toString()+"]-----------------------------" );
		/*ClusterSessionManager sessionManager=((ClusterSessionManager) ContextHolder.getBean("clusterSessionManager"));
		if(ios.getAttribute(CIMConstant.SESSION_KEY)==null)
		{
			return null;
		}
		
		String account =ios.getAttribute(CIMConstant.SESSION_KEY).toString();
//		ios.removeAttribute(CIMConstant.SESSION_KEY);
//		ios.close(true);
		
		 code start
		Collection<CIMSession> offlineSessionList = sessionManager.getSessions(account);
		if(offlineSessionList.size()>1){
			for(CIMSession session:offlineSessionList){ 
				 boolean sessionIsConnected = false;
				 try {
					sessionIsConnected =  GetSessionUtil.isConnected(session.getHost(),String.valueOf(session.getNid()));
					if(!sessionIsConnected){
						 ios.close(true);
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			
			
			ios.removeAttribute(CIMConstant.SESSION_KEY);
			ios.close(true);
			
			 code start 
			SessionManagerService sessionServiceImpl=((SessionManagerService) ContextHolder.getBean("sessionServiceImpl"));
			CIMSession iosC = sessionServiceImpl.getSession(account);
			if(iosC.getDeviceId().length()==64 && iosC.getChannel().equals("ios")){
				iosC.setBindTime(Long.parseLong("0"));
				sessionServiceImpl.update(iosC);
			 code end 
			}else{
//				((ClusterSessionManager) ContextHolder.getBean("clusterSessionManager")).removeSession(account);
//				((ClusterSessionManager) ContextHolder.getBean("clusterSessionManager")).deleteClusterSession(ios.getAccount(), ios.getNid().toString(), ios.getHost());
				if(ios.getAccount()==null && ios.getHost()==null){
					((ClusterSessionManager) ContextHolder.getBean("clusterSessionManager")).removeSession(account);
				}else{
					((ClusterSessionManager) ContextHolder.getBean("clusterSessionManager")).deleteClusterSession(ios.getAccount(), ios.getNid().toString(), ios.getHost());
				}
				
			}
		}
		 code end 
		
		UserService uservice = ContextHolder.getBean(UserServiceImpl.class);
		uservice.updateOnline(account, User.OFF_LINE);
		broadcastOfflineMessage( account);*/
		return null;
	}
	
	
	public static  void  broadcastOfflineMessage(String account)
	{
		try {
			//通知在线好友，该用户已下线
			Message offlineMessage = new Message();
//			offlineMessage.setType(Constants.MessageType.TYPE_900);
//			offlineMessage.setSender("admin");
//			HashMap<String,String> map = new HashMap<String,String> ();
//			map.put("sourceAccount", account);
//			offlineMessage.setContent("{\"sourceAccount\":\""+account+"\"}");
//			
//			FriendService  friendService = ContextHolder.getBean(FriendServiceImpl.class);
//			List<String> friends =friendService.getFriendAccounts(account);
//	//		DefaultSessionManager sessionManager = ((DefaultSessionManager) ContextHolder.getBean("defaultSessionManager"));
//			ClusterSessionManager sessionManager=((ClusterSessionManager) ContextHolder.getBean("clusterSessionManager"));
//			for(String friendAccount : friends )
//			{
//				if(sessionManager.getSession(friendAccount)!=null)
//				{
//					offlineMessage.setReceiver(friendAccount);
//					offlineMessage.setMid(String.valueOf(System.currentTimeMillis()));
//					offlineMessage.setTimestamp(System.currentTimeMillis());
//					sessionManager.getSession(friendAccount).write(offlineMessage);
//				}
//				
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}