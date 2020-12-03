package xyz.zzyitj.ils.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;
import xyz.zzyitj.ils.mapper.UserRoleMapper;
import xyz.zzyitj.ils.model.dataobject.UserRoleDo;
import xyz.zzyitj.ils.service.UserPermissionService;
import xyz.zzyitj.ils.service.UserRoleService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:04
 * @since 1.0
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDo> implements UserRoleService {
    @Autowired
    private UserPermissionService permissionService;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Value(value = "${redis.account.role.prefix}")
    private String redisAccountRolePrefix;

    public static final long DEFAULT_USER_ID = 1L;

    @Override
    public Set<UserRoleDo> getDefaultUserRoles() {
        return getUserRolesById(DEFAULT_USER_ID, false);
    }

    @Override
    public Set<UserRoleDo> getUserRolesById(Long userId, boolean isSaveToRedis) {
        List<Object> userRoleList = listOperations.range(
                redisAccountRolePrefix + userId, 0, -1);
        Set<UserRoleDo> userRoles = new HashSet<>();
        if (userRoleList == null || userRoleList.isEmpty()) {
            userRoles = baseMapper.selectUserRolesById(userId);
            userRoles.forEach(userRole -> {
                userRole.setPermissionSet(permissionService.getUserPermissionsById(userRole.getId()));
            });
            if (userRoles.isEmpty()) {
                isSaveToRedis = false;
                userRoles = getDefaultUserRoles();
            }
            if (isSaveToRedis) {
                listOperations.rightPushAll(redisAccountRolePrefix + userId, new ArrayList<>(userRoles));
            }
        } else {
            for (Object o : userRoleList) {
                userRoles.add((UserRoleDo) o);
            }
        }
        return userRoles;
    }

    @Override
    public Set<UserRoleDo> getUserRolesById(Long userId) {
        return getUserRolesById(userId, true);
    }
}
