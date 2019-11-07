package tech.mhuang.interchan.sso.sysrole.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tech.mhuang.ext.interchan.core.mapper.BaseMapper;
import tech.mhuang.ext.interchan.protocol.data.Page;
import tech.mhuang.interchan.sso.sysrole.domain.SysRolePageQueryDO;
import tech.mhuang.interchan.sso.sysrole.entity.SysRole;

import java.util.List;

/**
 * @ClassName: SysRoleMapper
 * @Description:系统角色mapper
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:11:32
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole, String> {

    /**
     * @param page
     * @return List<SyChanmgrolem>
     * @Title: page
     * @Description: 分页查询角色信息
     */
    @Override
    List<SysRole> page(Page<SysRole> page);

    /**
     * @param page
     * @return int
     * @Title: pageCount
     * @Description:分页查询数量
     */
    @Override
    int pageCount(Page<SysRole> page);

    /**
     * 分页查角色
     *
     * @param page
     * @return List<SysRole>
     * @Title: pageOrderRole
     */
    List<SysRole> pageOrderRole(Page<SysRolePageQueryDO> page);

    /**
     * 角色ID列表
     *
     * @param roleIds
     * @return List<SysRole>
     * @Title: findByRoleIds
     */
    List<SysRole> findByRoleIds(List<String> roleIds);

}