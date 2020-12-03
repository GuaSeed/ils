package xyz.zzyitj.ils.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zzyitj.ils.mapper.UserMapper;
import xyz.zzyitj.ils.model.dataobject.UserDo;
import xyz.zzyitj.ils.service.UserRoleService;
import xyz.zzyitj.ils.service.UserService;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/1 22:45
 * @since 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDo> implements UserService {
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDo getUserByEmail(String email) {
        UserDo userDo = baseMapper.selectByEmail(email);
        if (userDo != null) {
            userDo.setUserRoleSet(userRoleService.getUserRolesById(userDo.getId()));
        }
        return userDo;
    }
}
