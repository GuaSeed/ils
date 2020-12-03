package xyz.zzyitj.ils.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.zzyitj.ils.model.dataobject.UserDo;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/1 22:44
 * @since 1.0
 */
public interface UserService extends IService<UserDo> {
    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱
     * @return {@link UserDo}
     */
    UserDo getUserByEmail(String email);
}
