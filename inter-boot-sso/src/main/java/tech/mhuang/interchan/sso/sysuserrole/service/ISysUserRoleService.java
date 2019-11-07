package tech.mhuang.interchan.sso.sysuserrole.service;

import tech.mhuang.ext.interchan.core.service.BaseService;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserFunDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleAddDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleCheckDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleDTO;
import tech.mhuang.interchan.sso.sysuserrole.entity.SysUserRole;

import java.util.List;


/**
 * @ClassName: ISysUserRoleService
 * @Description:系统功能权限服务
 * @author: admin
 * @date: 2017年7月21日 下午8:13:48
 */
public interface ISysUserRoleService extends BaseService<SysUserRole, String> {

    /**
     * @param sysUserRoleAddDTO
     * @param userId
     * @return void
     * @Title: saveUserRole
     * @Description: 保存用户角色
     */
    void saveUserRole(SysUserRoleAddDTO sysUserRoleAddDTO, String userId);

    /**
     * @param userid
     * @return List<SysUserRoleDTO>
     * @Title: queryUserRole
     * @Description: 查询用户角色
     */
    List<SysUserRoleDTO> queryUserRole(String userid);

    /**
     * @param userid
     * @return List<SysUserFunDTO>
     * @Title: queryUserFun
     * @Description: 查询用户权限
     */
    List<SysUserFunDTO> queryUserFun(String userid);

    /**
     * @param userid
     * @return List<SysUserRoleCheckDTO>
     * @Title: queryUserRoleCheck
     * @Description: 查询用户角色选择信息
     */
    List<SysUserRoleCheckDTO> queryUserRoleCheck(String userid);

    /**
     * @param roleid
     * @param userId
     * @return void
     * @Title: sysUserRoleService
     * @Description: 删除人员角色
     */
    void sysUserRoleService(String roleid, String userId);

    /**
     * 设置用户角色
     *
     * @param userId
     * @return void
     * @Title: setUserRole
     */
    void setUserRoleToCache(String userId);


}
