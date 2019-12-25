package tech.mhuang.interchan.sso.sysuserrole.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.mhuang.ext.interchan.redis.commands.IRedisExtCommands;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleDTO;

import java.util.List;

/**
 * @ClassName: SysUserRoleRedisOperator
 * @Author: Ever
 * @Description: 用户角色缓存类
 * @Date: 2019/12/24 19:32
 * @Version: 1.0
 */
@Component
public class SysUserRoleRedisOperator {
    private final IRedisExtCommands redisExtCommands;

    @Autowired
    public SysUserRoleRedisOperator(IRedisExtCommands redisExtCommands) {
        this.redisExtCommands = redisExtCommands;
    }

    public static final String SYSTEM_USER_ROLE_CACHE_KEY = "SYSTEM_USER_ROLE";

    /**
     * 缓存用户角色
     *
     * @param userId    用户ID key
     * @param userRoles 角色与用户
     */
    public void cacheUserRole(String userId, List<SysUserRoleDTO> userRoles) {
        redisExtCommands.hset(SYSTEM_USER_ROLE_CACHE_KEY, userId, userRoles);
    }


}
