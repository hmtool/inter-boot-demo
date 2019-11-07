package tech.mhuang.interchan.sso.sysrole.service;

import tech.mhuang.ext.interchan.core.service.BaseService;
import tech.mhuang.ext.interchan.protocol.data.PageVO;
import tech.mhuang.interchan.protocol.sso.sysrole.SysRoleAddDTO;
import tech.mhuang.interchan.protocol.sso.sysrole.SysRoleModDTO;
import tech.mhuang.interchan.protocol.sso.sysrole.SysRolePageQueryDTO;
import tech.mhuang.interchan.protocol.sso.sysrole.SysRoleQueryDTO;
import tech.mhuang.interchan.protocol.sso.sysrole.SysRoleVO;
import tech.mhuang.interchan.protocol.sso.sysrole.request.SysRolePageQueryQVO;
import tech.mhuang.interchan.sso.sysrole.entity.SysRole;

import java.util.List;

/**
 * @ClassName: ISysRoleService
 * @Description:系统角色服务
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:06:08
 */
public interface ISysRoleService extends BaseService<SysRole, String> {

    /**
     * @param sysRoleAddDTO 角色参数
     * @param userId        操作人
     * @return void
     * @Title: saveRole
     * @Description: 新增角色信息
     */
    void saveRole(SysRoleAddDTO sysRoleAddDTO, String userId);

    /**
     * @param sysRoleModDTO
     * @param userId
     * @return void
     * @Title: updateRole
     * @Description:修改角色
     */
    void updateRole(SysRoleModDTO sysRoleModDTO, String userId);

    /**
     * @param dto
     * @return PageVo<SysRoleVo>
     * @Title: queryRoleByPage
     * @Description: 分页查询角色
     */
    PageVO<SysRoleVO> queryRoleByPage(SysRolePageQueryDTO dto);

    /**
     * @param roleid 角色id
     * @param userid 用户ID
     * @return void
     * @Title: deleteRole
     * @Description: 删除角色
     */
    void deleteRole(String roleid, String userid);

    /**
     * @param roleid        角色id
     * @param nullException 为空是否异常
     * @return SysRoleQueryDTO
     * @Title: queryRole
     * @Description:查询角色
     */
    SysRoleQueryDTO queryRole(String roleid, boolean nullException);

    /**
     * 分页查询角色
     *
     * @param dto
     * @return List<SysRole>
     * @Title: pageOrderRole
     */
    List<SysRole> pageOrderRole(SysRolePageQueryQVO dto);

    /**
     * 角色ID列表
     *
     * @param roleIds
     * @return List<SysRole>
     * @Title: findByRoleIds
     */
    List<SysRole> findByRoleIds(List<String> roleIds);

}
