package xyz.zzyitj.ils.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.zzyitj.ils.model.dataobject.UserDo;
import xyz.zzyitj.ils.service.UserService;
import xyz.zzyitj.ils.util.EncryptUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 08:32
 * @since 1.0
 */
public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return UserRealm.class.getSimpleName();
    }

    /**
     * 权限认证，即登录过后，每个身份不一定，对应的所能看的页面也不一样。
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDo userDo = principalCollection.oneByType(UserDo.class);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        // 添加权限
        userDo.getUserRoleSet().forEach(userRole -> {
            userRole.getPermissionSet().forEach(userPermission -> {
                stringSet.add(userPermission.getPermissionName());
            });
        });
        info.setStringPermissions(stringSet);
        return info;
    }

    /**
     * 身份认证。即登录通过账号和密码验证登陆人的身份信息。
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String email = (String) authenticationToken.getPrincipal();
        if (StringUtils.isBlank(email)) {
            return null;
        }
        UserDo userDo = userService.getUserByEmail(email);
        if (userDo == null) {
            return null;
        }
//        String password = new String((char[]) authenticationToken.getCredentials());
        return new SimpleAuthenticationInfo(userDo, userDo.getPasswordHash(),
                EncryptUtils.getSalt(userDo.getUkEmail()), getName());
    }

}
