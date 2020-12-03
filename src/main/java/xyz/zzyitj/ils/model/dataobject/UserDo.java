package xyz.zzyitj.ils.model.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/1 22:29
 * @since 1.0
 */
@Data
@TableName(value = "t_users")
public class UserDo implements Serializable {

    private static final long serialVersionUID = 906328274615222947L;

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
     * 邮箱
     */
    private String ukEmail;
    /**
     * 密码hash
     */
    private String passwordHash;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 性别
     */
    private Boolean sex;
    /**
     * 注册ip
     */
    private String ip;
    /**
     * 是否删除
     */
    private Boolean isDelete;

    @TableField(exist = false)
    private Set<UserRoleDo> userRoleSet;
}
