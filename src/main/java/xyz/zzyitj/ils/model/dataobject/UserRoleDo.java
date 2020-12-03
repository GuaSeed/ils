package xyz.zzyitj.ils.model.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 21:48
 * @since 1.0
 */
@Data
@TableName(value = "t_user_role")
public class UserRoleDo implements Serializable {
    private static final long serialVersionUID = -3919007026026991199L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date created;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modified;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 是否删除
     */
    private Boolean isDelete;

    @TableField(exist = false)
    private Set<UserPermissionDo> permissionSet;
}
