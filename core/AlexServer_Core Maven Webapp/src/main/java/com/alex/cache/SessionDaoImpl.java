package com.alex.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alex.network.eneity.IMSession;

@Repository(value="sessionDao")
public class SessionDaoImpl implements SessionDaoI {
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	private Log log = LogFactory.getLog(getClass());

	private String bytesToString(byte[] bytes) {
		return redisTemplate.getStringSerializer().deserialize(bytes);
	}

	private byte[] stringToBytes(String str) {
		return redisTemplate.getStringSerializer().serialize(str);
	}

	@Override
	public String save(final IMSession session) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						session.getAccount());
				Map<byte[], byte[]> sessionMap = new HashMap<byte[], byte[]>();
				sessionMap.put(stringToBytes("account"),
						stringToBytes(session.getAccount()));
				sessionMap.put(stringToBytes("gid"),
						stringToBytes(session.getGid()));
				sessionMap.put(stringToBytes("nid"),
						stringToBytes(session.getNid()));
				sessionMap.put(stringToBytes("host"),
						stringToBytes(session.getHost()));
				sessionMap.put(stringToBytes("devicedid"),
						stringToBytes(session.getDeviceid()));
				sessionMap.put(stringToBytes("channel"),
						stringToBytes(session.getChannel()));
				sessionMap.put(stringToBytes("devicemodel"),
						stringToBytes(session.getDevicemodel()));
				sessionMap.put(stringToBytes("bindtime"),
						stringToBytes(session.getBindtime()));
				sessionMap.put(stringToBytes("heartbeat"),
						stringToBytes(session.getHeartbeat()));
				sessionMap.put(stringToBytes("status"),
						stringToBytes(session.getStatus()));
				connection.hMSet(key, sessionMap);
				return null;
			}
		});
	}

	@Override
	public String update(IMSession session) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public Long delete(final String account) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(account);
				return connection.del(key);
			}
		});

	}

	@Override
	public IMSession getSessionByAccount(final String account) {
		return redisTemplate.execute(new RedisCallback<IMSession>() {
			@Override
			public IMSession doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						account);
				List<byte[]> resultList = connection.hMGet(key,
						stringToBytes("account"), stringToBytes("gid"),
						stringToBytes("nid"), stringToBytes("host"),
						stringToBytes("devicedid"), stringToBytes("channel"),
						stringToBytes("devicemodel"),
						stringToBytes("bindtime"), stringToBytes("heartbeat"),
						stringToBytes("status"));

				IMSession session = new IMSession();
				if(resultList.get(0)==null){
					return null;
				}
				session.setAccount(bytesToString(resultList.get(0)));
				session.setGid(bytesToString(resultList.get(1)));
				session.setNid(bytesToString(resultList.get(2)));
				session.setHost(bytesToString(resultList.get(3)));
				session.setDeviceid(bytesToString(resultList.get(4)));
				session.setChannel(bytesToString(resultList.get(5)));
				session.setDevicemodel(bytesToString(resultList.get(6)));
				session.setBindtime(bytesToString(resultList.get(7)));
				session.setHeartbeat(bytesToString(resultList.get(8)));
				session.setStatus(bytesToString(resultList.get(9)));
//				for (byte[] bytes : resultList) {
//					// session.setAccount(bytesToString(bytes));
//					System.out.println("session=" + bytesToString(bytes));
//				}
				return session;
			}
		});
	}
}
