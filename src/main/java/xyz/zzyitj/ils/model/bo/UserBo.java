package xyz.zzyitj.ils.model.bo;

import xyz.zzyitj.ils.model.dataobject.UserDo;
import xyz.zzyitj.ils.model.dto.UserDto;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/1 23:08
 * @since 1.0
 */
public class UserBo {
    public static UserDto getUserDto(UserDo userDo) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userDo.getId());
        userDto.setEmail(userDo.getUkEmail());
        userDto.setCreate(userDo.getCreated().getTime());
        userDto.setModify(userDo.getModified().getTime());
        return userDto;
    }
}
