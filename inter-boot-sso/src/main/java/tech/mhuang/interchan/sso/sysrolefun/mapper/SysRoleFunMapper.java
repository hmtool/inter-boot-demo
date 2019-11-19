package tech.mhuang.interchan.sso.sysrolefun.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.mhuang.ext.interchan.core.mapper.BaseMapper;
import tech.mhuang.ext.interchan.protocol.InsertInto;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunBatchDTO;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunDTO;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunTreeDTO;
import tech.mhuang.interchan.sso.sysrolefun.entity.SysRoleFun;

import java.util.List;

/**
 * @ClassName: SysRoleFunMapper
 * @Description:系统角色功能权限mapper
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:11:32
 */
@Mapper
public interface SysRoleFunMapper extends BaseMapper<SysRoleFun, String> {

    /**
     * @param roleId
     * @return void
     * @Title: deleteByRoleId
     * @Description: 删除角色功能权限
     */
    public void deleteByRoleId(String roleId);

    /**
     * @param roleId
     * @return List<SysRoleFunDTO>
     * @Title: queryRoleFun
     * @Description: 查询角色功能
     */
    List<SysRoleFunDTO> queryRoleFun(String roleId);

    /**
     * @param roleId
     * @return List<SysRoleFunTreeDTO>
     * @Title: queryRoleFunTree
     * @Description: 查询角色功能树
     */
    List<SysRoleFunTreeDTO> queryRoleFunTree(String roleId);

    /**
     * @param batchDTO
     * @return void
     * @Title: addRoleFun
     * @Description: 批量新增
     */
    public void addRoleFun(SysRoleFunBatchDTO batchDTO);

    /**
     * @param into
     * @return void
     * @Title: insertIntoByFuns
     * @Description:插入历史，通过权限
     */
    public void insertIntoByFuns(InsertInto<List<String>> into);

    /**
     * @param ids
     * @return void
     * @Title: deleteByFuns
     * @Description:删除通过权限id
     */
    public void deleteByFuns(List<String> ids);

}