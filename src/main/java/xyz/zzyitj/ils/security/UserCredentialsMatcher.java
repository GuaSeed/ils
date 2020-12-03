package xyz.zzyitj.ils.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.zzyitj.ils.service.LockedAccountRedisService;


/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/2 19:29
 * @since 1.0
 */
public class UserCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private LockedAccountRedisService lockedAccountRedisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UserToken userToken = (UserToken) token;
        if (lockedAccountRedisService.checkLocked(userToken.getHost())) {
            throw new LockedAccountException();
        } else {
            lockedAccountRedisService.incrementLocked(userToken.getHost());
        }
        return super.doCredentialsMatch(token, info);
    }
}
