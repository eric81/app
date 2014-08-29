package com.eudemon.taurus.app.cache.memcached;

import net.rubyeye.xmemcached.XMemcachedClient;

import com.eudemon.taurus.app.common.Log;

public class MemcachedService {
	/**
	 * memcahe客户端接口实例
	 */
	private XMemcachedClient cache = null;

	/**
	 * 缓存失效时间，单位：分钟
	 */
	private String cacheExp = "10";

	public String getCacheExp() {
		return cacheExp;
	}

	public void setCacheExp(String cacheExp) {
		this.cacheExp = cacheExp;
	}

	public XMemcachedClient getCache() {
		return cache;
	}

	public void setCache(XMemcachedClient cache) {
		this.cache = cache;
	}

	public Object get(String key) {
		try {
			return cache.get(key);
		} catch (Exception e) {
			Log.getErrorLogger().warn("get " + key + " from cache failed", e);
			return null;
		}
	}

	public boolean add(String key, Object obj) {
		try {
			return cache.add(key, Integer.parseInt(cacheExp) * 60, obj);
		} catch (Exception e) {
			Log.getErrorLogger().warn("add " + key + " to cache failed", e);
			return false;
		}
	}
	
	public boolean replace(String key, Object obj){
		try {
			return cache.replace(key, Integer.parseInt(cacheExp) * 60, obj);
		} catch (Exception e) {
			Log.getErrorLogger().warn("add " + key + " to cache failed", e);
			return false;
		}
	}

	public boolean delete(String key) {
		try {
			cache.delete(key);
			return true;
		} catch (Exception e) {
			Log.getErrorLogger().warn("delete " + key + " from cache failed", e);
			return false;
		}
	}

	public boolean delay(String key, int seconds) {
		try {
			return cache.touch(key, seconds * 60);
		} catch (Exception e) {
			Log.getErrorLogger().warn("delay " + key + " in cache failed", e);
			return false;
		}
	}
	
	public boolean isBatchReady(String key, int batchSize) {
		try {
			long count = cache.incr(key, 1);
			if (count == batchSize - 1) {
				cache.delete(key);
				return true;
			}
			return false;
		} catch (Exception e) {
			Log.getErrorLogger().warn("increase " + key + " count in cache failed", e);
			return false;
		}
	}
}
