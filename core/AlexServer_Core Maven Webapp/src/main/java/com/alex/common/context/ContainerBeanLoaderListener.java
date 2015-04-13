package com.alex.common.context;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

public class ContainerBeanLoaderListener implements ServletContextListener{

	ContextLoader contextLoader;
    protected final Logger logger = Logger.getLogger(ContainerBeanLoaderListener.class); 
    
   
    
	@Override
	public void contextDestroyed(ServletContextEvent event){
		 logger.warn("contextDestroyed...");
		 if(contextLoader != null){
	            contextLoader.closeWebApplicationContext(event.getServletContext());
		 }
		ContextHolder.setContext( null);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.warn("contextInitialized...");
		if(contextLoader == null){
			contextLoader = new ContextLoader();
		}
        
		logger.debug("******************* container start begin ******************************");
		ContextHolder.setContext(contextLoader.initWebApplicationContext(event.getServletContext()));
		logger.debug("******************* container start successfull ************************");
		
		//启动定时获取微信支付Token的任务
//		GetTokenTimer getTokenTimer = new GetTokenTimer();
//		getTokenTimer.startScheduled();
		
	}
	   
		
}
