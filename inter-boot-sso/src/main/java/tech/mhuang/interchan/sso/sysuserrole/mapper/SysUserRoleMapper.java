package tech.mhuang.interchan.sso.sysuserrole.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.mhuang.ext.interchan.core.mapper.BaseMapper;
import tech.mhuang.ext.interchan.protocol.InsertInto;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserFunDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleBatchDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleCheckDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleDTO;
import tech.mhuang.interchan.sso.sysuserrole.entity.SysUserRole;

import java.util.List;

/**
 * @ClassName: SysRoleFunMapper
 * @Description:系统角色功能权限mapper
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:11:32
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole, String> {

    /**
     * @param userid
     * @return void
     * @Title: deleteByUserId
     * @Description: 删除用户角色
     */
    void deleteByUserId(String userid);

    /**
     * @param batchDTO
     * @return void
     * @Title: addUserRole
     * @Description: 新增用户角色
     */
    void addUserRole(SysUserRoleBatchDTO batchDTO);

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
     * @Description:查询用户权限
     */
    List<SysUserFunDTO> queryUserFun(String userid);

    /**
     * @param userid
     * @return List<SysUserRoleCheckDTO>
     * @Title: queryUserRoleCheck
     * @Description: 查询用户角色选中信息
     */
    List<SysUserRoleCheckDTO> queryUserRoleCheck(String userid);

    /**
     * @param into
     * @return void
     * @Title: insertIntoByRoleId
     * @Description: 参数历史，通过角色ID
     */
    void insertIntoByRoleId(InsertInto<String> into);

    /**
     * @param roleid
     * @return void
     * @Title: deleteByRoleId
     * @Description: 删除人员通过角色ID
     */
    void deleteByRoleId(String roleid);


}