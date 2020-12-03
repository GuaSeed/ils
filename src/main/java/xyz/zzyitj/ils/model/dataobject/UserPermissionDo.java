package xyz.zzyitj.ils.model.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 21:48
 * @since 1.0
 */
@Data
@TableName(value = "t_user_permission")
public class UserPermissionDo implements Serializable {

    private static final long serialVersionUID = 8329058164138816379L;
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
    private String permissionName;
    /**
     * 是否删除
     */
    private Boolean isDelete;
}
