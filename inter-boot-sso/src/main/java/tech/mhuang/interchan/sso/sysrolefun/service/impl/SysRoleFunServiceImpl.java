package tech.mhuang.interchan.sso.sysrolefun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.mhuang.core.id.BaseIdeable;
import tech.mhuang.core.util.StringUtil;
import tech.mhuang.ext.interchan.core.service.impl.BaseServiceImpl;
import tech.mhuang.ext.interchan.protocol.InsertInto;
import tech.mhuang.ext.spring.util.DataUtil;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunAddDTO;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunBatchDTO;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunDTO;
import tech.mhuang.interchan.protocol.sso.sysfunrole.SysRoleFunTreeDTO;
import tech.mhuang.interchan.sso.sysrolefun.entity.SysRoleFun;
import tech.mhuang.interchan.sso.sysrolefun.mapper.SysRoleFunMapper;
import tech.mhuang.interchan.sso.sysrolefun.service.ISysRoleFunService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: sysRoleFunService
 * @Description:系统角色功能权限服务
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:10:04
 */
@Service("sysRoleFunService")
@Transactional(readOnly = true)
public class SysRoleFunServiceImpl extends BaseServiceImpl<SysRoleFun, String> implements ISysRoleFunService {

    @Autowired
    private SysRoleFunMapper sysRoleFunMapper;

    @Autowired
    private BaseIdeable<String> baseIdeable;

    /**
     * <p>Title: saveRoleFun</p>
     * <p>Description: </p>
     *
     * @param sysRoleFunAddDTO
     * @param userId
     * @see ISysRoleFunService#saveRoleFun(SysRoleFunAddDTO, String)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveRoleFun(SysRoleFunAddDTO sysRoleFunAddDTO, String userId) {

        InsertInto<String> into = new InsertInto<>();
        into.setReqNo(baseIdeable.generateId());
        into.setId(sysRoleFunAddDTO.getRoleid());
        into.setStatus(InsertInto.DELETE);
        into.setUserId(userId);
        into.setOpDate(new Date());
        this.sysRoleFunMapper.insertInto(into);

        SysRoleFunBatchDTO batchDTO = DataUtil.copyTo(sysRoleFunAddDTO, SysRoleFunBatchDTO.class);
        batchDTO.setOperateUser(userId);
        batchDTO.setOperateTime(new Date());
        this.sysRoleFunMapper.deleteByRoleId(sysRoleFunAddDTO.getRoleid());
        if (StringUtil.isNotBlank(sysRoleFunAddDTO.getFunid())) {
            batchDTO.setFunids(Arrays.asList(StringUtil.split(sysRoleFunAddDTO.getFunid(), ",")));
            this.sysRoleFunMapper.addRoleFun(batchDTO);
            into.setReqNo(baseIdeable.generateId());
            into.setStatus(InsertInto.UPDATE);
            this.sysRoleFunMapper.insertInto(into);
        }


    }

    /**
     * <p>Title: queryRoleFunTree</p>
     * <p>Description: </p>
     *
     * @param roleid
     * @return
     * @see ISysRoleFunService#queryRoleFunTree(String)
     */
    @Override
    public List<SysRoleFunTreeDTO> queryRoleFunTree(String roleid) {
        return this.sysRoleFunMapper.queryRoleFunTree(roleid);
    }

    /**
     * <p>Title: queryRoleFun</p>
     * <p>Description: </p>
     *
     * @param roleid
     * @return
     * @see ISysRoleFunService#queryRoleFun(String)
     */
    @Override
    public List<SysRoleFunDTO> queryRoleFun(String roleid) {
        return this.sysRoleFunMapper.queryRoleFun(roleid);
    }

    /**
     * <p>Title: deleteRoleFunByRole</p>
     * <p>Description: </p>
     *
     * @param roleid
     * @param userId
     * @see ISysRoleFunService#deleteRoleFunByRole(String, String)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteRoleFunByRole(String roleid, String userId) {
        InsertInto<String> into = new InsertInto<>();
        into.setReqNo(baseIdeable.generateId());
        into.setId(roleid);
        into.setStatus(InsertInto.DELETE);
        into.setUserId(userId);
        into.setOpDate(new Date());
        this.sysRoleFunMapper.insertInto(into);
        this.sysRoleFunMapper.deleteByRoleId(roleid);
    }

    /**
     * <p>Title: deleteRoleFunByFuns</p>
     * <p>Description: </p>
     *
     * @param ids
     */
    @Override
    public void deleteRoleFunByFuns(List<String> ids, String userId) {
        this.sysRoleFunMapper.deleteByFuns(ids);
        InsertInto<List<String>> into = new InsertInto<>();
        into.setReqNo(baseIdeable.generateId());
        into.setId(ids);
        into.setStatus(InsertInto.UPDATE);
        into.setUserId(userId);
        into.setOpDate(new Date());
        this.sysRoleFunMapper.insertIntoByFuns(into);
    }


}
