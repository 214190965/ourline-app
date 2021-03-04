package com.ourline.ourlinecommon.util;



import com.alibaba.fastjson.JSON;
import com.ourline.ourlinecommon.code.HttpContext;
import com.ourline.ourlinecommon.code.WebConstants;
import com.ourline.ourlinecommon.entiy.User;
import com.ourline.ourlinecommon.redis.service.IRedisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassName SessionUtil
 * @Description session模拟工具类，主要用户操作Redis、ehcache
 * @date 20210303 11:34:35
 * @Copyright
 */
@Component("sessionUtil")
public class SessionUtil {

	@Resource(name = "redisService")
	private IRedisService redisService;

	@Resource(name = "httpContext")
	private HttpContext httpContext;


	/**
	 * @Title put
	 * @Description 存储数据到Redis
	 * @param key
	 * @param value
	 * @param time  Redis过期时间(秒)
	 */
	public void put(String key, Object value, long time) {

		redisService.put(key, value, time);
	}

	/**
	 * @Title get
	 * @Description 获取Redis key指定值
	 * @param key
	 */
	public Object get(String key) {
//		RedisServiceImpl redisService = new RedisServiceImpl();
		return redisService.get(key);
	}

	/**
	 * @Title remove
	 * @Description 删除Redis指定key
	 * @param key
	 */
	public boolean removeRedis(String key) {

		return redisService.remove(key);
	}

	/**
	 * @Title delete
	 * @Description 批量删除Redis指定keys
	 * @param key
	 */
	public void delete(String... key) {

		redisService.delete(key);
	}

	/**
	 * @Title getCurrentUser
	 * @Description 获取当前用户,先从缓存中取，缓存没有去redis取
	 * @return
	 */
	public User getCurrentUser() {

		String sessionId = httpContext.getRequest().getHeader(WebConstants.SESSION_TOKEN);

		User user = null;

		// 取Redis
		Object userObj = redisService.getHashKey(sessionId, WebConstants.SESSION_USER);

		if (userObj != null) {

			if (userObj instanceof User) {

				user = (User) userObj;
			} else {

				// 类型不匹配，用json转
				String jsonString = JSON.toJSON(userObj).toString();

				user = JSON.parseObject(jsonString, User.class);

			}
			return user;
		}

		return null;
	}



	/**
	 * @Title putRedisUser
	 * @Description 更新Redis中user对象
	 * @param userObj
	 */
	public void putRedisUser(Object userObj) {

		String sessionId = httpContext.getRequest().getHeader(WebConstants.SESSION_TOKEN);

		HashMap<String, Object> map = redisService.getHash(sessionId);

		map.put(WebConstants.SESSION_USER, userObj);

		redisService.putHash(sessionId, map, 1800);
	}



	/**
	 * @Title putMap
	 * @Description redis 存储hash值
	 * @param sessionId
	 * @param sessionMap
	 * @param timeout
	 */
	public void putMap(String sessionId, HashMap<String, Object> sessionMap, long timeout) {

		redisService.putHash(sessionId, sessionMap, timeout);
	}


	/**
	 * @Title isDefaultUser
	 * @Description 当前登录用户是不是默认admin用户
	 * @return
	 */
	public boolean isDefaultUser() {
		User user = this.getCurrentUser();
		return WebConstants.DEFAULT_USERNAME.equals(user.getUserId());
	}

}
