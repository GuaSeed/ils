package xyz.zzyitj.ils.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 19:07
 * @since 1.0
 */
class EncryptUtilsTest {

    @Test
    void hashPassword() {
        Assertions.assertEquals(
                "8cc153267a26cac94529e9957fcd7bba",
                EncryptUtils.hashPassword("123456@qq.com", "123456ab"));
    }
}