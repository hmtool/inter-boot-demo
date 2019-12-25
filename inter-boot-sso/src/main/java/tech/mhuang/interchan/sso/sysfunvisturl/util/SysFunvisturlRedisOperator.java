package tech.mhuang.interchan.sso.sysfunvisturl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.mhuang.ext.interchan.auth.constant.AuthConstant;
import tech.mhuang.ext.interchan.redis.commands.IRedisExtCommands;
import tech.mhuang.interchan.sso.sysfunvisturl.entity.SyChanmgfunVistUrlm;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysUserRoleRedisOperator
 * @Author: Ever
 * @Description: 用户角色缓存类
 * @Date: 2019/12/24 19:32
 * @Version: 1.0
 */
@Component
public class SysFunvisturlRedisOperator {

    private final IRedisExtCommands redisExtCommands;

    @Autowired
    public SysFunvisturlRedisOperator(IRedisExtCommands redisExtCommands) {
        this.redisExtCommands = redisExtCommands;
    }

    /**
     * 缓存权限
     *
     * @param key     key
     * @param dataMap value
     */
    public void cacheData(String key, Map<String,Object> dataMap) {
        redisExtCommands.hmset(key, dataMap);
    }

    /**
     * 根据用户ID获取缓存URL
     *
     * @param userId 用户ID
     * @return 缓存URL
     */
    public List<SyChanmgfunVistUrlm> getVistUrls(String userId) {
        //检查路径与权限问题
        String cacheKey = AuthConstant.USER_VIST_URL_CACHEKEY;
        return redisExtCommands.hgetList(cacheKey, userId, SyChanmgfunVistUrlm.class);
    }

}
