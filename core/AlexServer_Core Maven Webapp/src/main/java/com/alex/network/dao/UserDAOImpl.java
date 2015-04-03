package com.alex.network.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.alex.network.eneity.IMSession;
import com.alex.web.domain.User;

public class UserDAOImpl {
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	private Log log = LogFactory.getLog(getClass());

	public void saveUser(final User user) {
		log.warn("saveUser");
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize(
								"user.uid." + user.getUserId()),
						redisTemplate.getStringSerializer().serialize(
								user.getUserName()));
				return null;
			}
		});
	}

	public User getUser(final long id) {
		log.warn("getUser");
		return redisTemplate.execute(new RedisCallback<User>() {
			public User doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						"user.uid." + id);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String name = redisTemplate.getStringSerializer()
							.deserialize(value);
					User user = new User();
					user.setUserName(name);
					user.setUserId(id + "");
					return user;
				}
				return null;
			}
		});
	}

	public String getString(final String keys) {
		return redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer()
						.serialize(keys);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String name = redisTemplate.getStringSerializer()
							.deserialize(value);
					return name;
				}
				return null;
			}
		});
	}

	public String saveSession(final IMSession session) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						session.getAccount());
				Map<byte[], byte[]> sessionMap = new HashMap<byte[], byte[]>();
				sessionMap.put(
						redisTemplate.getStringSerializer()
								.serialize("account"),
						redisTemplate.getStringSerializer().serialize(
								session.getAccount()));
				sessionMap.put(
						redisTemplate.getStringSerializer()
								.serialize("gid"),
						redisTemplate.getStringSerializer().serialize(
								session.getGid()));
				sessionMap.put(
						redisTemplate.getStringSerializer()
								.serialize("nid"),
						redisTemplate.getStringSerializer().serialize(
								session.getNid()));
				sessionMap.put(
						redisTemplate.getStringSerializer()
								.serialize("host"),
						redisTemplate.getStringSerializer().serialize(
								session.getHost()));
				sessionMap.put(
						redisTemplate.getStringSerializer()
								.serialize("devicedid"),
						redisTemplate.getStringSerializer().serialize(
								session.getDeviceid()));
				connection.hMSet(key, sessionMap);
				return null;
			}
		});
	}

	public static void main(String[] args) {
		Log log = LogFactory.getLog(UserDAOImpl.class);
		log.warn("begin");
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:spring*.xml");
		log.warn("load XML");
		UserDAOImpl userDAO = (UserDAOImpl) ac.getBean("userDAO");
		// System.out.println(userDAO.getString("alex"));

		log.warn("end");
	}
}