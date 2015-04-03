 
package com.alex.network.session;

import java.util.Collection;

import com.alex.network.eneity.CIMSession;


/**
 *  客户端的 session管理接口
 *  可自行实现此接口管理session
 */
 
public interface  SessionManager  {


	
	public void addSession(String account,CIMSession session);
	
	
	
	 public Collection<CIMSession> getSessions(String account) ;
	
	
	
	/**
	 * 
	 * @param account 客户端session的 key 一般可用 用户账号来对应session
	 * @return
	 */
	CIMSession getSession(String account);
	
	/**
	 * 获取所有session
	 * @return
	 */
	public Collection<CIMSession> getSessions();
	
	/**
	 * 删除session
	 * @param session
	 */
    public void  removeSession(CIMSession session) ;
    
    
    /**
	 * 删除session
	 * @param session
	 */
    public void  removeSession(String account);
    
    /**
	 * session是否存在
	 * @param session
	 */
    public boolean containsCIMSession(CIMSession ios);
    
    /**
	 * session获取对应的 用户 key  
	 * @param session
	 */
    public String getAccount(CIMSession ios);
    
  }