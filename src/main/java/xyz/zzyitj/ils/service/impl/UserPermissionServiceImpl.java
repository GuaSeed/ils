package xyz.zzyitj.ils.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;
import xyz.zzyitj.ils.mapper.UserPermissionMapper;
import xyz.zzyitj.ils.model.dataobject.UserPermissionDo;
import xyz.zzyitj.ils.service.UserPermissionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:12
 * @since 1.0
 */
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermissionDo>
        implements UserPermissionService {
    @Autowired
    private ListOperations<String, Object> listOperations;

    @Value(value = "${redis.account.permission.prefix}")
    private String redisAccountPermissionPrefix;

    private static final long DEFAULT_ROLE_ID = 1L;

    @Override
    public Set<UserPermissionDo> getDefaultUserPermissions() {
        return getUserPermissionsById(DEFAULT_ROLE_ID);
    }

    @Override
    public Set<UserPermissionDo> getUserPermissionsById(Long roleId) {
        List<Object> userPermissionList = listOperations.range(
                redisAccountPermissionPrefix + roleId, 0, -1);
        Set<UserPermissionDo> userPermissions = new HashSet<>();
        if (userPermissionList == null || userPermissionList.isEmpty()) {
            userPermissions = baseMapper.selectUserPermissionsById(roleId);
            listOperations.rightPushAll(redisAccountPermissionPrefix + roleId, new ArrayList<>(userPermissions));
        } else {
            // list to set
            for (Object o : userPermissionList) {
                userPermissions.add((UserPermissionDo) o);
            }
        }
        return userPermissions;
    }
}
