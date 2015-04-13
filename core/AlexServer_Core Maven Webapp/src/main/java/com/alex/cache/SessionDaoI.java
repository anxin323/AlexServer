package com.alex.cache;

import com.alex.network.eneity.IMSession;

public interface SessionDaoI {

	String save(IMSession session);
	
	String update(IMSession session);
	
	Long delete(String account);
	
	IMSession getSessionByAccount(String account);
}
