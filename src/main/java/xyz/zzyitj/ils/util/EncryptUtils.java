package xyz.zzyitj.ils.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 08:41
 * @since 1.0
 */
public class EncryptUtils {
    public static String hashPassword(String email, String pwd) {
        // 加密算法MD5
        // 迭代次数
        return new SimpleHash("MD5", pwd, getSalt(email), 2).toHex();
    }

    public static ByteSource getSalt(String email) {
        return ByteSource.Util.bytes(email + "salt");
    }
}
