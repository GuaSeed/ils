package xyz.zzyitj.ils.service;

/**
 * 登录用户计次
 * 用户登录错误达到n次后封禁一段时间
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 19:40
 * @since 1.0
 */
public interface LockedAccountRedisService {
    /**
     * 用户登录错误后增加该ip的次数
     *
     * @param ip ip地址
     */
    void incrementLocked(String ip);

    /**
     * 检查该ip是否被封禁
     *
     * @param ip ip地址
     * @return true 被封禁
     */
    boolean checkLocked(String ip);


    /**
     * 登录成功删除该ip的locked
     *
     * @param ip ip地址
     */
    void deleteLocked(String ip);
}
