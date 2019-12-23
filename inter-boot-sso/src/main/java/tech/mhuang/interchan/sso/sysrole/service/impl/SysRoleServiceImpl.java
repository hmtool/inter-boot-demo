package tech.mhuang.interchan.sso.sysrole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.mhuang.core.id.BaseIdeable;
import tech.mhuang.ext.interchan.core.exception.BusinessException;
import tech.mhuang.ext.interchan.core.service.impl.BaseServiceImpl;
import tech.mhuang.ext.interchan.protocol.InsertInto;
import tech.mhuang.ext.interchan.protocol.Result;
import tech.mhuang.ext.interchan.protocol.data.Page;
import tech.mhuang.ext.interchan.protocol.data.PageVO;
import tech.mhuang.ext.spring.util.DataUtil;
import tech.mhuang.interchan.protocol.sso.sysrole.*;
import tech.mhuang.interchan.protocol.sso.sysrole.request.SysRolePageQueryQVO;
import tech.mhuang.interchan.sso.sysrole.domain.SysRolePageQueryDO;
import tech.mhuang.interchan.sso.sysrole.entity.SysRole;
import tech.mhuang.interchan.sso.sysrole.mapper.SysRoleMapper;
import tech.mhuang.interchan.sso.sysrole.service.ISysRoleService;
import tech.mhuang.interchan.sso.sysrolefun.service.ISysRoleFunService;
import tech.mhuang.interchan.sso.sysuserrole.service.ISysUserRoleService;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: SysRoleServiceImpl
 * @Description:系统角色服务
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:10:04
 */
@Service("sysRoleService")
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private ISysRoleFunService sysRoleFunService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private BaseIdeable<String> snowflake;

    @Autowired
    private Environment environment;

    /**
     * <p>Title: saveRole</p>
     * <p>Description: </p>
     *
     * @param sysRoleAddDTO
     * @param userId
     * @see ISysRoleService#saveRole(SysRoleAddDTO, String)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveRole(SysRoleAddDTO sysRoleAddDTO, String userId) {
        //检查代号是否存在
        SysRole role = this.sysRoleMapper.getById(sysRoleAddDTO.getRoleid());
        if (role != null) {//存在角色代码
            throw new BusinessException(Result.SYS_RESULT_FAILD, this.environment.getProperty("sysroleid_exists"));
        }
        role = DataUtil.copyTo(sysRoleAddDTO, SysRole.class);
        role.setMarkflag("0");
        role.setOperateTime(new Date());
        role.setOperateUser(userId);
        this.sysRoleMapper.save(role);
        InsertInto<String> into = new InsertInto<>();
        into.setReqNo(snowflake.generateId());
        into.setId(role.getRoleid());
        into.setStatus(InsertInto.ADD);
        into.setUserId(userId);
        this.sysRoleMapper.insertInto(into);
    }


    /**
     * <p>Title: saveRole</p>
     * <p>Description: </p>
     *
     * @param sysRoleModDTO
     * @param userId
     * @see ISysRoleService#saveRole(SysRoleAddDTO, String)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateRole(SysRoleModDTO sysRoleModDTO, String userId) {
        //检查代号是否存在
        SysRole role = this.sysRoleMapper.getById(sysRoleModDTO.getRoleid());
        if (role == null) {//存在不存在角色代码
            throw new BusinessException(Result.SYS_RESULT_FAILD, this.environment.getProperty("sysrole_not_exists"));
        }
        role = DataUtil.copyTo(sysRoleModDTO, SysRole.class);
        role.setMarkflag(sysRoleModDTO.getMarkflag());
        role.setOperateTime(new Date());
        role.setOperateUser(userId);
        this.sysRoleMapper.update(role);
        InsertInto<String> into = new InsertInto<>();
        into.setReqNo(snowflake.generateId());
        into.setId(role.getRoleid());
        into.setStatus(InsertInto.UPDATE);
        into.setUserId(userId);
        this.sysRoleMapper.insertInto(into);
    }

    /**
     * <p>Title: queryRoleByPage</p>
     * <p>Description: </p>
     *
     * @param dto
     * @return
     * @see ISysRoleService#queryRoleByPage(SysRolePageQueryDTO)
     */
    @Override
    public PageVO<SysRoleVO> queryRoleByPage(SysRolePageQueryDTO dto) {
        Page<SysRole> page = new Page<>();
        SysRole rolem = DataUtil.copyTo(dto, SysRole.class);
        page.setRecord(rolem);
        page.setRows(dto.getRows());
        page.setStart((dto.getStart() - 1) * dto.getRows());
        PageVO<SysRoleVO> pageVo = new PageVO<>();
        List<SysRole> sysRoles = sysRoleMapper.page(page);
        pageVo.setResult(DataUtil.copyTo(sysRoles, SysRoleVO.class));
        pageVo.setTotalSize(this.sysRoleMapper.pageCount(page));
        return pageVo;
    }


    /**
     * <p>Title: queryRole</p>
     * <p>Description: </p>
     *
     * @param roleid
     * @param nullException 是否抛出数据不存在异常
     * @return SysRoleQueryDTO
     */
    @Override
    public SysRoleQueryDTO queryRole(String roleid, boolean nullException) {
        SysRole role = sysRoleMapper.getById(roleid);
        SysRoleQueryDTO dto = null;
        if (role == null) {
            if (nullException) {
                throw new BusinessException(Result.SYS_RESULT_FAILD, this.environment.getProperty("sysrole_not_exists"));
            }
        } else {
            dto = DataUtil.copyTo(role, SysRoleQueryDTO.class);
        }
        return dto;
    }


    /**
     * <p>Title: deleteRole</p>
     * <p>Description: </p>
     *
     * @param roleid
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteRole(String roleid, String userId) {
        InsertInto<String> into = new InsertInto<>();
        into.setReqNo(snowflake.generateId());
        into.setId(roleid);
        into.setStatus(InsertInto.DELETE);
        into.setUserId(userId);
        into.setOpDate(new Date());
        this.sysRoleMapper.insertInto(into);
        this.sysRoleMapper.remove(roleid);
        this.sysRoleFunService.deleteRoleFunByRole(roleid, userId);
        this.sysUserRoleService.sysUserRoleService(roleid, userId);

    }

    /**
     * <p>Title: pageOrderRole</p>
     * <p>Description: </p>
     *
     * @param dto
     * @return
     * @see ISysRoleService#pageOrderRole(SysRolePageQueryQVO)
     */
    @Override
    public List<SysRole> pageOrderRole(SysRolePageQueryQVO dto) {
        Page<SysRolePageQueryDO> page = DataUtil.copyTo(dto, Page.class);
        page.setRecord(DataUtil.copyTo(dto, SysRolePageQueryDO.class));
        page.setStart(page.getStart() - 1);
        List<SysRole> sysRoles = sysRoleMapper.pageOrderRole(page);
        return sysRoles;
    }

    /**
     * <p>Title: findByRoleIds</p>
     * <p>Description: </p>
     *
     * @param roleIds
     * @return
     * @see ISysRoleService#findByRoleIds(List)
     */
    @Override
    public List<SysRole> findByRoleIds(List<String> roleIds) {
        return sysRoleMapper.findByRoleIds(roleIds);
    }

}
