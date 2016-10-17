package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Created by kevinmonster on 16/10/17.
 */
public class RedisWithPool {


    public static void main(String[] args) {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 7002);

        try (Jedis jedis = pool.getResource()) {
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        }
        /// ... when closing your application:
        pool.destroy();
    }
}
