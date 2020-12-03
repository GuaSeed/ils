package xyz.zzyitj.ils.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.zzyitj.ils.model.dataobject.UserRoleDo;
import xyz.zzyitj.ils.service.UserRoleService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:30
 * @since 1.0
 */
@SpringBootTest
class UserRoleServiceImplTest {
    @Autowired
    private UserRoleService userRoleService;

    @Test
    void getDefaultUserRoles() {
        Set<UserRoleDo> defaultUserRoles = userRoleService.getDefaultUserRoles();
        Assertions.assertEquals(1, defaultUserRoles.size());
    }

    @Test
    void getUserRolesById() {
        Set<UserRoleDo> userRoles = userRoleService.getUserRolesById(2L);
        Assertions.assertEquals(1, userRoles.size());
    }
}