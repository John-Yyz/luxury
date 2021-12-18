package com.luxury.utils.redis;
/**
 * ---------------------------------------------------------------------------   
 * 类名称   ： RedisClient
 * 类描述   ：   TODO
 * 创建人   ： yuyz
 * 创建时间：2021年12月15日
 * ---------------------------------------------------------------------------
 */

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisException;

import java.io.*;
import java.util.Set;

/**
 * 工具类 RedisClient
 * 因为本类中获取JedisPool调用的是JedisConnectionFactory中protected修饰的方法fetchJedisConnector()
 * 所以该类需要与JedisConnectionFactory在同一个package中
 */
public class RedisClient {

    private static Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private JedisConnectionFactory factory;

    public RedisClient(JedisConnectionFactory factory) {
        super();
        this.factory = factory;
    }

    /**
     * put操作（存储序列化对象）+ 生效时间
     * 
     * @param key
     * @param value
     * @return
     */
    public void putObject(final String key, final Object value, final int cacheSeconds) {
        if (StringUtils.isNotBlank(key)) {
            redisTemplete(key, new RedisExecute<Object>() {
                @Override
                public Object doInvoker(Jedis jedis) {
                    try {
                        jedis.setex(key.getBytes(Protocol.CHARSET), cacheSeconds, serialize(value));
                    } catch (UnsupportedEncodingException e) {
                    }

                    return null;
                }
            });
        }
    }

    /**
     * get操作（获取序列化对象）
     * 
     * @param key
     * @return
     */
    public Object getObject(final String key) {
        return redisTemplete(key, new RedisExecute<Object>() {
            @Override
            public Object doInvoker(Jedis jedis) {
                try {
                    byte[] byteKey = key.getBytes(Protocol.CHARSET);
                    byte[] byteValue = jedis.get(byteKey);
                    if (byteValue != null) {
                        return deserialize(byteValue);
                    }
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
                return null;
            }
        });
    }

    /**
     * setex操作
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public String set(final String key, final String value, final int cacheSeconds) {
        return redisTemplete(key, new RedisExecute<String>() {
            @Override
            public String doInvoker(Jedis jedis) {
                if (cacheSeconds == 0) {
                    return jedis.set(key, value);
                }
                return jedis.setex(key, cacheSeconds, value);
            }
        });
    }
    
    /**
     * @Description: hset操作
     * @param @param key
     * @param @param field
     * @param @param value
     * @param @return   
     * @return Long  
     * @throws
     * @author 龚恒
     * @date 2017年11月9日
     */
    public Long hset(final String key, final String field, final String value) {
        return redisTemplete(key, new RedisExecute<Long>() {
            @Override
            public Long doInvoker(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }
    
    /**
     * @Description: hdel操作
     * @param @param key
     * @param @param field
     * @param @param value
     * @param @return   
     * @return Long  
     * @throws
     * @author 龚恒
     * @date 2018年1月18日
     */
    public Long hdel(final String key, final String field) {
        return redisTemplete(key, new RedisExecute<Long>() {
            @Override
            public Long doInvoker(Jedis jedis) {
                return jedis.hdel(key, field);
            }
        });
    }
    
    /**
     * @Description: hexists操作
     * @param @param key
     * @param @param field
     * @param @return   
     * @return Boolean  
     * @throws
     * @author 龚恒
     * @date 2018年1月18日
     */
    public Boolean hexists(final String key, final String field) {
        return redisTemplete(key, new RedisExecute<Boolean>() {
            @Override
            public Boolean doInvoker(Jedis jedis) {
                return jedis.hexists(key, field);
            }
        });
    }
    
    public Boolean exists(final String key) {
        return redisTemplete(key, new RedisExecute<Boolean>() {
            @Override
            public Boolean doInvoker(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }
    
    
    public Set<String> keys(final String key) {
        return redisTemplete(key, new RedisExecute<Set<String>>() {
            @Override
            public Set<String> doInvoker(Jedis jedis) {
                return jedis.keys(key);
            }
        });
    }
    
    public String hget(final String key, final String field) {
        return redisTemplete(key, new RedisExecute<String>() {
            @Override
            public String doInvoker(Jedis jedis) {
                String value = jedis.hget(key, field);
                return StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        });
    }

    /**
     * get操作
     * 
     * @param key
     *            键
     * @return 值
     */
    public String get(final String key) {
        return redisTemplete(key, new RedisExecute<String>() {
            @Override
            public String doInvoker(Jedis jedis) {
                String value = jedis.get(key);
                return StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        });
    }

    
    
    public Long lpush(final String key, final String message) {
        return redisTemplete(key, new RedisExecute<Long>() {
            @Override
            public Long doInvoker(Jedis jedis) {
                return jedis.lpush(key, message);
            }
        });
    }

    public String lpop(final String key) {
        return redisTemplete(key, new RedisExecute<String>() {
            @Override
            public String doInvoker(Jedis jedis) {
            	String value = jedis.lpop(key);
            	return StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        });
    }
    
    
    /**
     * del操作
     * 
     * @param key
     *            键
     * @return
     */
    public long del(final String key) {
        return redisTemplete(key, new RedisExecute<Long>() {
            @Override
            public Long doInvoker(Jedis jedis) {
                return jedis.del(key);
            }
        });
    }

    /**
     * 获取资源
     * 
     * @return
     * @throws JedisException
     */
    public Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = (Jedis) factory.getConnection();
        } catch (JedisException e) {
            logger.error("getResource.", e);
            returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }

    /**
     * 获取资源
     * 
     * @return
     * @throws JedisException
     */
    public Jedis getJedis() throws JedisException {
        return getResource();
    }

    /**
     * 归还资源
     * 
     * @param jedis
     */
    public void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 释放资源
     * 
     * @param jedis
     */
    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 操作jedis客户端模板
     * 
     * @param key
     * @param execute
     * @return
     */
    public <R> R redisTemplete(String key, RedisExecute<R> execute) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis == null) {
                return null;
            }

            return execute.doInvoker(jedis);
        } catch (Exception e) {
            logger.error("operator redis api fail,{}", key, e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    /**
     * 功能简述: 对实体Bean进行序列化操作.
     * 
     * @param source
     *            待转换的实体
     * @return 转换之后的字节数组
     * @throws Exception
     */
    public static byte[] serialize(Object source) {
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream ObjOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            ObjOut = new ObjectOutputStream(byteOut);
            ObjOut.writeObject(source);
            ObjOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ObjOut) {
                    ObjOut.close();
                }
            } catch (IOException e) {
                ObjOut = null;
            }
        }
        return byteOut.toByteArray();
    }

    /**
     * 功能简述: 将字节数组反序列化为实体Bean.
     * 
     * @param source
     *            需要进行反序列化的字节数组
     * @return 反序列化后的实体Bean
     * @throws Exception
     */
    public static Object deserialize(byte[] source) {
        ObjectInputStream ObjIn = null;
        Object retVal = null;
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(source);
            ObjIn = new ObjectInputStream(byteIn);
            retVal = ObjIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ObjIn) {
                    ObjIn.close();
                }
            } catch (IOException e) {
                ObjIn = null;
            }
        }
        return retVal;
    }

    interface RedisExecute<T> {
        T doInvoker(Jedis jedis);
    }
}
