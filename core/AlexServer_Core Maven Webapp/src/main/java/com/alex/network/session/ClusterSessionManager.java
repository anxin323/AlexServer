package com.alex.network.session;

import java.util.Collection;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alex.common.context.ContextHolder;
import com.alex.network.eneity.CIMSession;
import com.alex.network.service.SessionManagerService;


/** 
 * 集群 session管理实现示例， 各位可以自行实现 AbstractSessionManager接口来实现自己的 session管理
 * 服务器集群时 须要将CIMSession 信息存入数据库或者nosql 等 第三方存储空间中，便于所有服务器都可以访问
 */ 
@Component(value="clusterSessionManager")
public class ClusterSessionManager  implements SessionManager{
	@Autowired
	@Qualifier("sessionManagerServiceImpl")
	private SessionManagerService database ;

//	public void setDatabase(SessionManagerService database) {
//		this.database = database;
//	}
	
	// session save db
	@Override
    public void addSession(String account,CIMSession session) {
		if(session!=null)
        {
			try{
      		    database.save(session);
	      	}
	      	catch(Exception ex){
	      		System.out.println("setIoSession Err"+ex.getMessage());
	      	}
        }
    }
	 
	// get single of session by account
	@Override
    public CIMSession getSession(String account) {
		//这里查询数据库 
        CIMSession session = database.getSession(account);
        if(session!=null){
        	try{
        		session.setIoSession(((NioSocketAcceptor)
        				ContextHolder.getBean("ioAcceptor")).
        				getManagedSessions().get(session.getNid()));  
        	}
        	catch(Exception ex){
        		System.out.println("setIoSession Err is "+ex.getMessage());
        	}
	        return session;
        }
        else{
        	return null;
        }
    }
	
	
	// get single of session by account,cluster 
//	@Override
//    public CIMSession getClusterSession(String account,String nid,String ip) {
//		//这里查询数据库 
//        CIMSession session = database.getSession(account,nid,ip);
//        if(session!=null){
//        	try{
//        		session.setIoSession(((NioSocketAcceptor)
//        				ContextHolder.getBean("serverAcceptor")).
//        				getManagedSessions().get(session.getNid()));  
//        	}
//        	catch(Exception ex){
//        		System.out.println("setIoSession Err is "+ex.getMessage());
//        	}
//	        return session;
//        }
//        else{
//        	return null;
//        }
//    }
	
   
	// get all of session
    @Override 
    public Collection<CIMSession> getSessions() {
    	Collection<CIMSession> collSession = database.getSessions();
    	for (CIMSession session : collSession) {
			if(session!=null){
				try{
	        		/*session.setIoSession(((NioSocketAcceptor)
	        				ContextHolder.getBean("serverAcceptor")).
	        				getManagedSessions().get(session.getNid())); */ 
	        		collSession.add(session);
	        	}
	        	catch(Exception ex){
	        		System.out.println("setIoSession Err is "+ex.getMessage());
	        	}
			}
		}
    	return collSession;
    }
 
    
    @Override
    public void removeSession(CIMSession session) {
    	database.removeSession(session.getGid());
    }

    @Override 
    public void  removeSession(String account) {
    	database.removeAccountSession(account);
    }
    
    @Override
    public boolean containsCIMSession(CIMSession ios)
    {
    	return database.containsCIMSession(ios.getAccount());
    }

    @Override
    public String getAccount(CIMSession ios)
    {
       return  ios.getAccount();
    }
	@Override
	public Collection<CIMSession> getSessions(String account) {
		return database.getSessions(account);
	}
	
	public void deleteClusterSession(String account,String nid,String ip) {
		database.deleteClusterSession(account, nid, ip);
	}
	
	

}
