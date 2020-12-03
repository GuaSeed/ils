package xyz.zzyitj.ils.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.zzyitj.ils.model.dataobject.UserRoleDo;

import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:03
 * @since 1.0
 */
public interface UserRoleService extends IService<UserRoleDo> {
    /**
     * 获取默认用户的角色
     *
     * @return 角色集合
     */
    Set<UserRoleDo> getDefaultUserRoles();

    /**
     * 根据userId获取用户的角色
     *
     * @param userId        用户id
     * @param isSaveToRedis 是否存储到redis
     * @return 角色集合
     */
    Set<UserRoleDo> getUserRolesById(Long userId, boolean isSaveToRedis);

    /**
     * 根据userId获取用户的角色
     * @param userId 用户id
     * @return 角色集合
     */
    Set<UserRoleDo> getUserRolesById(Long userId);

}
