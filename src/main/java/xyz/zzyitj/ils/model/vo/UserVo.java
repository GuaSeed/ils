package xyz.zzyitj.ils.model.vo;

import lombok.Data;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 09:58
 * @since 1.0
 */
@Data
public class UserVo {
    private String email;
    private String password;
    private String ip;
    private boolean rememberMe;
}
