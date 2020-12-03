package xyz.zzyitj.ils.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author intent zzy.main@gmail.com
 * @date 2020/12/1 23:03
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class UserDto {
    private Long userId;

    private Long create;

    private Long modify;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
}
