package tech.mhuang.interchan.sso.sysrolefun.service;

import tech.mhuang.ext.interchan.core.service.BaseService;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunAddDTO;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunDTO;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunTreeDTO;
import tech.mhuang.interchan.sso.sysrolefun.entity.SysRoleFun;

import java.util.List;


/**
 * @ClassName: ISysRoleFunService
 * @Description:系统功能权限服务
 * @author: admin
 * @date: 2017年7月21日 下午8:13:48
 */
public interface ISysRoleFunService extends BaseService<SysRoleFun, String> {

    /**
     * @param sysRoleFunAddDTO
     * @param userId
     * @return void
     * @Title: saveRoleFun
     * @Description: 新增角色权限
     */
    void saveRoleFun(SysRoleFunAddDTO sysRoleFunAddDTO, String userId);

    /**
     * @param roleid
     * @return List<SysRoleFunTreeDTO>
     * @Title: queryRoleFunTree
     * @Description: 查询角色树
     */
    List<SysRoleFunTreeDTO> queryRoleFunTree(String roleid);

    /**
     * @param roleid
     * @return List<SysRoleFunDTO>
     * @Title: queryRoleFun
     * @Description: 查询角色功能权限
     */
    List<SysRoleFunDTO> queryRoleFun(String roleid);

    /**
     * @param roleid
     * @param userId
     * @return void
     * @Title: deleteRoleFunByRole
     * @Description: 删除角色权限
     */
    void deleteRoleFunByRole(String roleid, String userId);

    /**
     * @param ids
     * @return void
     * @Title: deleteRoleFunByFuns
     * @Description: 删除角色权限，通过权限ID
     */
    void deleteRoleFunByFuns(List<String> ids, String userId);

}
