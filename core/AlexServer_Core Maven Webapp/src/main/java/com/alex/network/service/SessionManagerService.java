package com.alex.network.service;

import java.util.Collection;
import java.util.List;

import com.alex.common.utils.Pages;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.IMSession;

public interface SessionManagerService {
	/**
	 * 保存客户端访问Session信息
	 * @param session
	 */
	public void save(CIMSession session);
	
	
	
	public void update(CIMSession session);
	
	/**
	 * 获取用户Session信息
	 * @param account
	 * @return
	 */
	public CIMSession getSession(String account);
	
	/**
	 * 获取所有Session信息
	 * @return
	 */
	public Collection<CIMSession> getSessions();
	
	
	
	/**
	 * 获取所有Session信息
	 * @return
	 */
	public Collection<CIMSession> getSessions(String account);
	
	
	/**
	 * 删除Session
	 * @param gid
	 */
	public void removeSession(String gid);
	
	 
	/**
	 * 删除Session
	 * @param account
	 */
	public void removeAccountSession(String account);
	
	/**
	 * 判断用户Session是否存在
	 * @param account
	 * @return
	 */
	public boolean containsCIMSession(String account);
	
	/**
	 * 获取在线用户所属服务器的数量，用户分配Session
	 * @return
	 */
	public List getHostList();
	
	
	public CIMSession getClusterSession(String account,String nid,String ip) ;
	
	
	public void deleteClusterSession(String account,String nid,String ip) ;
	
	
	/* code start */
	public Pages getAllSessionList(IMSession imSession,Pages pages) ;
	/* code end */
	
	
	public List<IMSession> getCIMSessionList(String deviceId);
	
	
	
	
}
