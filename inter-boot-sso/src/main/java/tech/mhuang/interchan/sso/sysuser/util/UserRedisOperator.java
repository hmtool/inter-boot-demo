package tech.mhuang.interchan.sso.sysuser.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.mhuang.ext.interchan.redis.commands.IRedisExtCommands;
import tech.mhuang.interchan.sso.sysuser.entity.SysUser;

/**
 * @ClassName: SysUserRoleRedisOperator
 * @Author: Ever
 * @Description: 用户缓存类
 * @Date: 2019/12/24 19:32
 * @Version: 1.0
 */
@Component
public class UserRedisOperator {


    private final IRedisExtCommands redisExtCommands;

    @Autowired
    public UserRedisOperator(IRedisExtCommands redisExtCommands) {
        this.redisExtCommands = redisExtCommands;
    }

    /**
     * 用户手机号RedisKey前缀
     */
    public static final String SYUSER_REDIS_MOBILE_TOUSERID_PREKEY = "syur_moblie_uid_";

    /**
     * 用户ID获取用户前缀
     */
    public static final String SYUSER_REDIS_USERID_TO_USER_PREKEY = "syur_user_uid_";

    /**
     * 根据手机号获取用户ID
     *
     * @param mobilePhone 手机号
     * @return 用户ID
     */
    public String getUserId(String mobilePhone) {
        return redisExtCommands.hget(SYUSER_REDIS_MOBILE_TOUSERID_PREKEY, mobilePhone);
    }

    /**
     * 根据用户ID获取用户
     *
     * @param userId 用户ID
     * @return 系统用户
     */
    public SysUser getSysUserByUserId(String userId) {
        return redisExtCommands.hget(SYUSER_REDIS_USERID_TO_USER_PREKEY, userId, SysUser.class);
    }

    /**
     * 缓存用户
     *
     * @param prefix  缓存前缀标识
     * @param key     KEY
     * @param value   用户ID VALUE
     * @param seconds 缓存时间（秒）
     * @return 是否成功
     */
    public boolean cacheUser(String prefix, String key, Object value, Long seconds) {
       return redisExtCommands.hset(prefix, key, value, seconds);
    }


}
