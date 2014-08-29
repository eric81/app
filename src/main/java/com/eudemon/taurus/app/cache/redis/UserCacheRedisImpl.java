package com.eudemon.taurus.app.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.eudemon.taurus.app.cache.UserCache;
import com.eudemon.taurus.app.entity.User;

public class UserCacheRedisImpl implements UserCache {
	@Autowired
	@Qualifier("redisTemplateDefaultImpl")
	private RedisTemplate redisTemplate;
	private String key = "user.id:";

	@Override
	public User get(long id) {
		byte[] value = redisTemplate.get((key + id).getBytes());
		if (null == value) {
			return null;
		}

		Object obj = KryoUtil.toObject(value, User.class);
		if (null == obj) {
			return null;
		}

		return (User) obj;
	}

	@Override
	public boolean add(User t) {
		if (null == t) {
			return false;
		}
		boolean rs = redisTemplate.set((key + t.getId()).getBytes(), KryoUtil.toByteArray(t));
		return rs;
	}

	@Override
	public boolean replace(User t) {
		return add(t);
	}

	@Override
	public boolean delete(long id) {
		boolean rs = redisTemplate.del((key + id).getBytes());
		return rs;
	}

	@Override
	public boolean expire(long id, int seconds) {
		boolean rs = redisTemplate.expire((key + id).getBytes(), seconds);
		return rs;
	}

}
