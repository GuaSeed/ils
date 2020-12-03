package xyz.zzyitj.ils.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.zzyitj.ils.model.dataobject.UserPermissionDo;
import xyz.zzyitj.ils.service.UserPermissionService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:40
 * @since 1.0
 */
@SpringBootTest
class UserPermissionServiceImplTest {
    @Autowired
    private UserPermissionService userPermissionService;

    @Test
    void getDefaultUserPermissions() {
        Set<UserPermissionDo> defaultUserPermissions = userPermissionService.getDefaultUserPermissions();
        Assertions.assertEquals(1, defaultUserPermissions.size());
    }

    @Test
    void getUserPermissionsById() {
        Set<UserPermissionDo> userPermissions = userPermissionService.getUserPermissionsById(2L);
        Assertions.assertEquals(4, userPermissions.size());
    }
}