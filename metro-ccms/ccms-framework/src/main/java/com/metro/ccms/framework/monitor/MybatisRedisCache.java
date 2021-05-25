package com.metro.ccms.framework.monitor;

import java.util.Stack;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import com.metro.ccms.common.utils.spring.SpringUtils;

/**
 * @author lw
 * mybatis缓存处理
 */
public class MybatisRedisCache implements Cache {
    private String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public MybatisRedisCache(String id) {
        this.id = id;
    }
    private RedisTemplate<Object, Object> getRedisTemplate(){
        return SpringUtils.getBean("redisTemplate");
       
    }
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		try {
	        RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
	        redisTemplate.boundHashOps(getId()).put(key, value);
	        System.out.print("ok");
		}catch(Exception ex) {
			
		}

	}

	@Override
	public Object getObject(Object key) {
		try {
	        RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
	        Object value = redisTemplate.boundHashOps(getId()).get(key);
	        JwMonitorContext jwMonitorContext =null;
	        Stack<JwMonitor> cacheStack=null;
	        JwMonitor jwMonitor =null;
	        if(null!=value) {	
	        	//使用了缓存
	        	jwMonitorContext = JwMonitorContextHolder.get();
	        	cacheStack=jwMonitorContext.getCacheStack();
	        	jwMonitor = cacheStack.peek();
	        	jwMonitor.setCacheUsed(JwMonitor.CACHEEUSER);
	        	jwMonitorContext.setCacheStack(cacheStack);
	        	JwMonitorContextHolder.put(jwMonitorContext);
	        }
	        return value;
		}catch(Exception ex) {
			
		}
		return null;

	}

	@Override
	public Object removeObject(Object key) {
		try {
	        RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
	        Object value = redisTemplate.boundHashOps(getId()).delete(key);
	        return value;
		}catch(Exception ex) {
			
		}
		return null;

	}

	@Override
	public void clear() {
		try {
	        RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
	        redisTemplate.delete(getId());
		}catch(Exception ex) {
			
		}


	}

	@Override
	public int getSize() {
		try {
	        RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
	        Long size = redisTemplate.boundHashOps(getId()).size();
	        return size == null ? 0 : size.intValue();
		}catch(Exception ex) {
			
		}
		return 0;

	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
