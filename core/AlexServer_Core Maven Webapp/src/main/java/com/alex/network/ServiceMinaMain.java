package com.alex.network;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alex.authenticator.service.UserInfoServiceI;
import com.alex.network.dao.SessionDaoI;
import com.alex.network.dao.UserDAOImpl;
import com.alex.network.filter.ServerMessageCodecFactory;
import com.alex.network.handler.MainIOHandler;
import com.alex.web.service.UserServiceI;

public class ServiceMinaMain {

	public static void main(String args[])
	{
		 //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
//        userService = (UserServiceI) ac.getBean("userService");
//        userInfoService = (UserInfoServiceI) ac.getBean("userInfoService");
//        userDAO = (UserDAOImpl) ac.getBean("userDAO");
//        sessionDao = (SessionDaoI) ac.getBean("sessionDao");
		
		NioSocketAcceptor acceptor=new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("code",new ProtocolCodecFilter(new ServerMessageCodecFactory()));
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
		acceptor.setHandler(new MainIOHandler());
		try {
			acceptor.bind(new InetSocketAddress(1235));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
