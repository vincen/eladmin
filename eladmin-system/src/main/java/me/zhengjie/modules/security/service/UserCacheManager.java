package me.zhengjie.modules.security.service;

import cn.hutool.core.util.RandomUtil;
import me.zhengjie.modules.security.config.bean.LoginProperties;
import me.zhengjie.modules.security.service.dto.JwtUserDto;
import me.zhengjie.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 用户缓存管理
 **/
@Component
public class UserCacheManager {

    @Resource
    private RedisUtils redisUtils;
    @Value("${login.user-cache.idle-time}")
    private long idleTime;

    /**
     * 返回用户缓存
     * @param userName 用户名
     * @return JwtUserDto
     */
    public JwtUserDto getUserCache(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            // 获取数据
            Object obj = redisUtils.get(LoginProperties.cacheKey + userName);
            if(obj != null){
                return (JwtUserDto)obj;
            }
        }
        return null;
    }

    /**
     *  添加缓存到Redis
     * @param userName 用户名
     */
    @Async
    public void addUserCache(String userName, JwtUserDto user) {
        if (StringUtils.isNotEmpty(userName)) {
            // 添加数据, 避免数据同时过期
            long time = idleTime + RandomUtil.randomInt(900, 1800);
            redisUtils.set(LoginProperties.cacheKey + userName, user, time);
        }
    }

    /**
     * 清理用户缓存信息
     * 用户信息变更时
     * @param userName 用户名
     */
    @Async
    public void cleanUserCache(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            // 清除数据
            redisUtils.del(LoginProperties.cacheKey + userName);
        }
    }
}
