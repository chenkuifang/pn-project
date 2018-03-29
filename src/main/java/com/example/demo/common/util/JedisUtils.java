package com.example.demo.common.util;

import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 利用Jedis操作redis工具类, 因为StringRedisTemplate不能用new 注入，必须要用Spring
 * bean方式注入，使用有些地方使用起来不方便
 *
 * @author QuiFar
 * @version V1.0
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JedisUtils {

    private static JedisPool jedisPool = SpringContextUtils.getBean(JedisPool.class);

    /**
     * 获取连接
     */
    public static synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            }
            log.info("jedisPool为null,连接池连接异常");
        } catch (Exception e) {
            log.info("连接池连接异常");
            return null;
        }
        return null;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                log.debug("get {} = {}", key, value);
            }
        } catch (Exception e) {
            log.warn("get {} = {}", key, value, e);
        } finally {
            jedisColse(jedis);
        }
        return value;
    }

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("set {} = {}", key, value);
        } catch (Exception e) {
            log.warn("set {} = {}", key, value, e);
        } finally {
            jedisColse(jedis);
        }
        return result;
    }

    /**
     * 设置List缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.rpush(key, (String[]) value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setList {} = {}", key, value);
        } catch (Exception e) {
            log.warn("setList {} = {}", key, value, e);
        } finally {
            jedisColse(jedis);
        }
        return result;
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public static long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, (String[]) value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            log.debug("setSet {} = {}", key, value);
        } catch (Exception e) {
            log.warn("setSet {} = {}", key, value, e);
        } finally {
            jedisColse(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static long listAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            result = jedis.rpush(key, value);
            log.debug("listAdd {} = {}", key, value);
        } catch (Exception e) {
            log.warn("listAdd {} = {}", key, value, e);
        } finally {
            jedisColse(jedis);
        }
        return result;
    }

    /**
     * 关闭连接
     *
     * @param jedis jedis
     * @return 返回类型
     */
    private static void jedisColse(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
