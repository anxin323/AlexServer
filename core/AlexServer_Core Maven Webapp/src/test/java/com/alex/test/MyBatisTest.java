
package com.alex.test;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.alex.authenticator.model.Permission;
import com.alex.authenticator.model.Role;
import com.alex.authenticator.model.UserInfo;
import com.alex.authenticator.model.UserRoleLink;
import com.alex.authenticator.service.UserInfoServiceI;
import com.alex.common.utils.DateUtils;
import com.alex.network.dao.SessionDaoI;
import com.alex.network.dao.UserDAOImpl;
import com.alex.network.eneity.IMSession;
import com.alex.network.filter.ServerMessageCodecFactory;
import com.alex.network.handler.MainIOHandler;
import com.alex.web.domain.User;
import com.alex.web.service.UserServiceI;



public class MyBatisTest {

    private UserServiceI userService;
    private UserInfoServiceI userInfoService;
    private UserDAOImpl userDAO;
    private SessionDaoI sessionDao;
    
    /**
     * 这个before方法在所有的测试方法之前执行，并且只执行一次
     * 所有做Junit单元测试时一些初始化工作可以在这个方法里面进行
     * 比如在before方法里面初始化ApplicationContext和userService
     */
    @Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        userService = (UserServiceI) ac.getBean("userService");
        userInfoService = (UserInfoServiceI) ac.getBean("userInfoService");
        userDAO = (UserDAOImpl) ac.getBean("userDAO");
        sessionDao = (SessionDaoI) ac.getBean("sessionDao");
    }
    
//    @Test
//    public void testMina(){
//    	NioSocketAcceptor acceptor=new NioSocketAcceptor();
//		acceptor.getFilterChain().addLast("code",new ProtocolCodecFilter(new ServerMessageCodecFactory()));
//		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
//		acceptor.setHandler(new MainIOHandler());
//		try {
//			acceptor.bind(new InetSocketAddress(1235));
//			System.out.println("asdasdasd");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
    
    @Test
    public void testRedis(){
    	Log log = LogFactory.getLog(UserDAOImpl.class);
		log.warn("begin");
		IMSession session =sessionDao.getSessionByAccount("alex");
		System.out.println("session="+session.toString());
		//log.warn("get value from redis="+userDAO.getString("alex"));
		log.warn("end");
    }
    
    @Test
    public void saveSession(){
    	Log log = LogFactory.getLog(UserDAOImpl.class);
		log.warn("begin");
		IMSession session = new IMSession();
		session.setAccount("alex");
		session.setHost("113.77.88.99");
		session.setGid("d5f648sdf2d5f4sd5fgyuiouyvfb");
		session.setNid("ertfbb56xkooop1881fd238sdas6");
		session.setDeviceid("235441as1d651sad51asas2d1s");
		session.setBindtime(System.currentTimeMillis()+"");
		session.setHeartbeat(DateUtils.getCurrentDate("yy-MM-dd HH:mm:ss"));
		session.setChannel("IOS");
		session.setDevicemodel("IPhone6 plus");
		session.setStatus("1");
		sessionDao.save(session);
		//log.warn("get value from redis="+userDAO.getString("alex"));
		log.warn("end");
    }
    
//    @Test
//    public void testAddUser(){
//        //ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
//        //UserServiceI userService = (UserServiceI) ac.getBean("userService");
//        for (int i = 0; i < 100; i++) {
//        	User user = new User();
//            user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
//            user.setUserName("Sole"+i);
//            user.setUserBirthday(new Date());
//            user.setUserSalary(30000D+i*100);
//            userService.addUser(user);
//		}
//    	
//    }
    
    @Test
    public void testGetUserByPage(){
    	//User user = new User();
    	List<User> userList = userService.getAllUserByPage(1,15);
    	System.out.println("testGetUserByPage  page="+userList.size());
    	for (User user:userList) {
			System.out.println("testGetUserByPage  name=" + user.getUserName());
		}
    	System.out.println("****************************************************************");
    	List<User> userList1 = userService.getAllUserByPage(2,15);
    	System.out.println("testGetUserByPage  page="+userList.size());
    	for (User user:userList1) {
			System.out.println("testGetUserByPage  name=" + user.getUserName());
		}
    }
    
    @Test
    public void testGetUserByName(){
    	UserInfo user = userInfoService.getUserByName("zhang");
    	System.out.println("username=" + user.getUserName());
    	System.out.println("password=" + user.getPassword());
    	System.out.println("getRole().size()=" + user.getRole().size());
    	for(Role role: user.getRole()){
    		System.out.println("getRole().size()=" +role.getName());
    		for(Permission permission:role.getPermission()){
    			System.out.println("permissionName=" +permission.getName());
    			System.out.println("permissionDesc=" +permission.getDescription());
    		}
    	}
    }
    
/*    @Test
    public void testInsertUserRole(){
    	UserRoleLink userRoleLink = new UserRoleLink();
    	Role role= new Role();
    	role.setId(5);
		userRoleLink.setRole(role);
    	UserInfo userInfo = new UserInfo();
    	userInfo.setId("1");
		userRoleLink.setUserInfo(userInfo);
    	userInfoService.modifyUserRole(userRoleLink);
    }*/
    
}
