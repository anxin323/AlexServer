package com.alex.common.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpEntity;

import com.alex.network.eneity.CIMSession;
import com.alex.network.eneity.IMSession;

public class SessionUtil {

	private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	private static ThreadPoolExecutor executor =  new ThreadPoolExecutor(3, 5, 20,	TimeUnit.SECONDS,queue);;
	/*final static String sendUrl = "http://%1$s:"+GlobelConstant.port+"/ichat/cgi/session_gethost.action";

    public static void  execute(final String ip)
	{
	    executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String url=String.format(sendUrl,ip);
					
					CloseableHttpClient httpclient = HttpClients.createDefault();
					HttpPost httpPost = new HttpPost(url);
			        CloseableHttpResponse response2 = httpclient.execute(httpPost);
			        String data = null;
			        try {
			            System.out.println(response2.getStatusLine());
			            HttpEntity entity2 = response2.getEntity();
			            data = EntityUtils.toString(entity2);
			        } finally {
			            response2.close();
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 });
	}
	*/
	public static CIMSession  CIMTransform(IMSession sm){
		if(sm!=null){
			CIMSession c=new CIMSession();
			c.setAccount(sm.getAccount());
			c.setGid(sm.getGid());
			c.setNid(Long.valueOf(sm.getNid()));
			c.setDeviceId(sm.getDeviceid());
			c.setHost(sm.getHost());
			c.setChannel(sm.getChannel());
			c.setDeviceModel(sm.getDevicemodel());
			c.setBindTime(Long.valueOf(sm.getBindtime()));
			c.setHeartbeat(Long.valueOf(sm.getHeartbeat()));
			return c;
		}
		else 
			return null;
	}
	
	public static IMSession SMtransform(CIMSession c){
		IMSession sm=new IMSession();
		sm.setAccount(c.getAccount());
		sm.setGid(c.getGid());
		sm.setNid(String.valueOf(c.getNid()));            
		sm.setDeviceid(c.getDeviceId());                
		sm.setHost(c.getHost());                        
		sm.setChannel(c.getChannel());                  
		sm.setDevicemodel(c.getDeviceModel());          
		sm.setBindtime(String.valueOf(c.getBindTime()));  
		sm.setHeartbeat(String.valueOf(c.getHeartbeat()));
		return sm;
	}
    
	
//	public static void main(String[] args) throws Exception {
//		execute("192.168.10.59");
//	}
}
