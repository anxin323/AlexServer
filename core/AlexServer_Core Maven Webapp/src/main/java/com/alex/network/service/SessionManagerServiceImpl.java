package com.alex.network.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alex.cache.SessionDaoI;
import com.alex.cache.SessionDaoImpl;
import com.alex.common.utils.Pages;
import com.alex.common.utils.SessionUtil;
import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.IMSession;

@Service(value="sessionManagerServiceImpl")
public class SessionManagerServiceImpl implements SessionManagerService {
	//@Resource
	@Autowired
	@Qualifier("sessionDao")  
	private SessionDaoI sessionDao;
	private Object save; 


//	public SessionDaoImpl getSessionDao() {
//		return sessionDao;
//	}
//
//	public void setSessionDao(SessionDaoI sessionDao) {
//		this.sessionDao = sessionDao;
//	}


	@Override
	public void save(CIMSession session) {
		IMSession sm=SessionUtil.SMtransform(session);
		//IP获取为空不需保存
		if(String.valueOf(sm.getHost()).equals("null")){
			return;
		}
		// set online 
		sm.setStatus("1"); 
		sessionDao.save(sm);
	}
	
	@Override
	public void update(CIMSession session) {
		/*IMSession sm = SessionUtil.SMtransform(session);
		sessionDao.update(sm);*/
	}
	
	
	
	@Override
	public CIMSession getSession(String account) {
		IMSession sm = sessionDao.getSessionByAccount(account);
		return SessionUtil.CIMTransform(sm);
	}

	@Override
	public Collection<CIMSession> getSessions() {
		Collection<CIMSession> coll=new HashSet<CIMSession>();
		/*//这里查询数据库 
    	List<IMSession> smlist=sessionDao.getSessions();
    	for(IMSession sm:smlist){
    		coll.add(SessionUtil.CIMTransform(sm));
    	}*/
		return coll;
	}

	@Override
	public void removeSession(String gid) {
		/*IMSession sm=new IMSession();
		sm.setGid(gid);
		System.err.println("delete gid is "+gid);
		sessionDao.delete(sm);*/
	}

	@Override
	public void removeAccountSession(String account) {
		sessionDao.delete(account);
	}

	@Override
	public boolean containsCIMSession(String account) {
/*//		IMSession sm=sessionDao.getSession(account);
//		if(sm==null)
//			return false;
//		else
*/			return true;
	}

	@Override
	public List getHostList() {
		return null;
//		return sessionDao.getHostList();
	}

	@Override
	public Collection<CIMSession> getSessions(String account) {
		Collection<CIMSession> coll=new HashSet<CIMSession>();
		//这里查询数据库 
    	/*List<IMSession> smlist=sessionDao.getSessions(account);
    	for(IMSession sm:smlist){
    		coll.add(SessionUtil.CIMTransform(sm));
    	}*/
		return coll;
	}

	@Override
	public CIMSession getClusterSession(String account, String nid, String ip) {
		return null;
		/*IMSession sm = sessionDao.getClusterSession(account,nid,ip);
		return SessionUtil.CIMTransform(sm);*/
	}

	@Override
	public Pages getAllSessionList(IMSession imSession,Pages pages) {
		return pages;
//		return sessionDao.getAllSessionList(imSession, pages);
	}

	@Override
	public void deleteClusterSession(String account, String nid, String ip) {
//		 sessionDao.deleteClusterSession(account, nid, ip);
	}

	@Override
	public List<IMSession> getCIMSessionList(String deviceId) {
		return null;
//		return sessionDao.getCIMSessionList(deviceId);
	}
 

	
}
