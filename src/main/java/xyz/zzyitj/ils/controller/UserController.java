package xyz.zzyitj.ils.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.zzyitj.ils.enums.ResultEnum;
import xyz.zzyitj.ils.model.bo.UserBo;
import xyz.zzyitj.ils.model.dataobject.UserDo;
import xyz.zzyitj.ils.model.dto.UserDto;
import xyz.zzyitj.ils.security.UserToken;
import xyz.zzyitj.ils.model.vo.UserVo;
import xyz.zzyitj.ils.service.LockedAccountRedisService;
import xyz.zzyitj.ils.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/1 22:46
 * @since 1.0
 */
@RestController
@RequestMapping(value = "${mapping.user.base}")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LockedAccountRedisService lockedAccountRedisService;

    @PostMapping(value = "${mapping.user.login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody UserVo userVo) {
        Subject subject = SecurityUtils.getSubject();
        UserToken token = new UserToken(userVo, request);
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                lockedAccountRedisService.deleteLocked(token.getHost());
                return ResponseEntity.ok(UserBo.getUserDto(subject.getPrincipals().oneByType(UserDo.class)));
            } else {
                token.clear();
                return ResponseEntity.ok(ResultEnum.FAIL.toString());
            }
        } catch (UnknownAccountException e) {
            return ResponseEntity.badRequest().body(ResultEnum.UNKNOWN_ACCOUNT.toString());
        } catch (LockedAccountException e) {
            return ResponseEntity.badRequest().body(ResultEnum.LOCK_ACCOUNT.toString());
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(ResultEnum.AUTHENTICATION_ERROR.toString());
        }
    }

    @RequiresPermissions(value = {"user:show"})
    @GetMapping(value = "${mapping.user.getById}")
    public ResponseEntity<UserDto> getById(HttpServletRequest request,
                                           @PathVariable("userId") Long userId) {
        UserDo userDo = userService.getById(userId);
        return ResponseEntity.ok(UserBo.getUserDto(userDo));
    }

    /**
     * 测试权限
     *
     * @param request
     * @return
     */
    @RequiresPermissions(value = {"user:delete"})
    @GetMapping(value = "${mapping.user.test}")
    public ResponseEntity<?> testPermission(HttpServletRequest request) {
        return ResponseEntity.ok(ResultEnum.SUCCESS.toString());
    }
}
