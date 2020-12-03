package xyz.zzyitj.ils.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import xyz.zzyitj.ils.model.dataobject.UserPermissionDo;

import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:00
 * @since 1.0
 */
public interface UserPermissionMapper extends BaseMapper<UserPermissionDo> {
    /**
     * 根据roleId搜索用户的所有权限
     *
     * @param roleId 角色id
     * @return 权限集合
     */
    @Select(value = "SELECT id, permission_name FROM t_user_permission WHERE fk_role_id = #{roleId} AND is_delete = false")
    Set<UserPermissionDo> selectUserPermissionsById(long roleId);
}
