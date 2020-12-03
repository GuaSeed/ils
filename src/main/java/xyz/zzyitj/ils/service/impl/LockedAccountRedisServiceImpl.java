package xyz.zzyitj.ils.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import xyz.zzyitj.ils.service.LockedAccountRedisService;

import java.util.concurrent.TimeUnit;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 19:43
 * @since 1.0
 */
@Service
public class LockedAccountRedisServiceImpl implements LockedAccountRedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Value(value = "${redis.account.lock.prefix}")
    private String redisAccountLockPrefix;

    @Value(value = "${redis.account.lock.count}")
    private Integer redisAccountLocCount;

    @Value(value = "${redis.account.lock.exp}")
    private Integer redisAccountLockExp;

    @Override
    public void incrementLocked(String ip) {
        Integer count = getLockCount(ip);
        if (count == null) {
            valueOperations.set(redisAccountLockPrefix + ip, 1, redisAccountLockExp, TimeUnit.DAYS);
        } else {
            valueOperations.increment(redisAccountLockPrefix + ip);
        }
    }

    @Override
    public boolean checkLocked(String ip) {
        Integer count = getLockCount(ip);
        return count != null && (count > redisAccountLocCount);
    }

    @Override
    public void deleteLocked(String ip) {
        redisTemplate.delete(redisAccountLockPrefix + ip);
    }

    private Integer getLockCount(String ip) {
        return (Integer) valueOperations.get(redisAccountLockPrefix + ip);
    }
}
