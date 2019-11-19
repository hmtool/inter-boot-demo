package tech.mhuang.interchan.sso.sysuser.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.mhuang.ext.interchan.core.mapper.BaseMapper;
import tech.mhuang.ext.interchan.protocol.data.Page;
import tech.mhuang.interchan.sso.sysuser.domain.SysUserDO;
import tech.mhuang.interchan.sso.sysuser.entity.SysUser;

import java.util.List;

/**
 * @ClassName: SysUserMapper
 * @Description:系统用户
 * @author: mhuang
 * @date: 2017年7月13日 下午3:42:41
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser, String> {

    /**
     * @param mobilephone
     * @return SysUser
     * @Title: getSysUserByMobilephone
     * @Description:通过手机号获取系统用户
     */
    SysUser getSysUserByMobilephone(String mobilephone);

    /**
     * @param sysUser
     * @return int
     * @Title: lock
     * @Description: 锁定/解锁用户
     */
    int lock(SysUser sysUser);

    /**
     * 排序分页查询
     *
     * @param pageUser
     * @return List<SysUser>
     * @Title: pageForOrder
     */
    List<SysUser> pageForOrder(Page<SysUserDO> pageUser);

    /**
     * 用户ID列表
     *
     * @param userIds
     * @return List<SysUser>
     * @Title: findByUserIds
     */
    List<SysUser> findByUserIds(List<String> userIds);
}
