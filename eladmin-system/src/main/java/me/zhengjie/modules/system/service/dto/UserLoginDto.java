package me.zhengjie.modules.system.service.dto;

import java.io.Serializable;

/**
 * 用户缓存时使用
 **/
public class UserLoginDto extends UserDto {

    private String password;

    private Boolean isAdmin;
}
