package xyz.zzyitj.ils.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import xyz.zzyitj.ils.model.vo.UserVo;
import xyz.zzyitj.ils.util.IPUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 10:11
 * @since 1.0
 */
public class UserToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 1941130889738711486L;

    public UserToken(UserVo userVo, HttpServletRequest request) {
        setUsername(userVo.getEmail());
        setPassword(userVo.getPassword() == null ? null : userVo.getPassword().toCharArray());
        setHost(IPUtils.getIpAddr(request));
        setRememberMe(userVo.isRememberMe());
    }
}
