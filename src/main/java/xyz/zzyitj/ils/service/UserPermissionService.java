package xyz.zzyitj.ils.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.zzyitj.ils.model.dataobject.UserPermissionDo;

import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:11
 * @since 1.0
 */
public interface UserPermissionService extends IService<UserPermissionDo> {
    /**
     * 获取默认用户的所有权限
     *
     * @return 权限集合
     */
    Set<UserPermissionDo> getDefaultUserPermissions();

    /**
     * 根据id获取某个用户的权限
     *
     * @param roleId 角色id
     * @return 权限集合
     */
    Set<UserPermissionDo> getUserPermissionsById(Long roleId);
}
