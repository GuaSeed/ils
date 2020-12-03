package xyz.zzyitj.ils.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import xyz.zzyitj.ils.model.dataobject.UserDo;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/1 22:43
 * @since 1.0
 */
public interface UserMapper extends BaseMapper<UserDo> {
    /**
     * 插入用户
     *
     * @param userDo 用户
     * @return 1 插入成功
     */
    @Insert(value = "INSERT INTO t_users (created, modified, uk_email, password_hash, nickname, sex, ip) VALUES (#{created}, #{modified}, #{ukEmail}, #{passwordHash}, #{nickname}, #{sex}, INET6_ATON(#{ip}))")
    int insertUser(UserDo userDo);

    /**
     * 根据用户邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户
     */
    @Select(value = "SELECT id, created, modified, uk_email, password_hash, nickname, sex, INET6_NTOA(ip) as ip FROM t_users WHERE uk_email = #{email} AND is_delete = false LIMIT 1")
    UserDo selectByEmail(String email);
}
