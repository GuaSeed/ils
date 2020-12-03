package xyz.zzyitj.ils.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import xyz.zzyitj.ils.model.dataobject.UserRoleDo;

import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 22:00
 * @since 1.0
 */
public interface UserRoleMapper extends BaseMapper<UserRoleDo> {
    /**
     * 根据userId搜索用户的所有角色
     *
     * @param userId 用户id
     * @return 角色集合
     */
    @Select(value = "SELECT id, role_name FROM t_user_role WHERE fk_user_id = #{userId} AND is_delete = false")
    Set<UserRoleDo> selectUserRolesById(long userId);
}
