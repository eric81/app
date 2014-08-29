package com.eudemon.taurus.app.cache.memcached;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.eudemon.taurus.app.cache.UserCache;
import com.eudemon.taurus.app.common.Log;
import com.eudemon.taurus.app.dao.UserDao;
import com.eudemon.taurus.app.entity.User;

@Component
public class UserCacheImpl implements UserCache {
	private String key = "user.id:";
	@Autowired
	private MemcachedService cache;
	@Autowired
	private UserDao dao;

	@Override
	public User get(long id) {
		User user = (User) cache.get(key + id);
		if (null == user) {
			try {
				user = dao.query(id);
			} catch (EmptyResultDataAccessException e) {
				Log.getErrorLogger().warn("User not find, id : " + id);
			}

			if (null != user) {
				add(user);
			}
		}

		return user;
	}

	@Override
	public boolean add(User user) {
		if (null == user) {
			return false;
		}
		return cache.add(key + user.getId(), user);
	}

	@Override
	public boolean replace(User user) {
		if (null == user) {
			return false;
		}
		return cache.replace(key + user.getId(), user);
	}

	@Override
	public boolean delete(long id) {
		return cache.delete(key + id);
	}

	@Override
	public boolean expire(long id, int seconds) {
		return cache.delay(key + id, seconds);
	}

}
