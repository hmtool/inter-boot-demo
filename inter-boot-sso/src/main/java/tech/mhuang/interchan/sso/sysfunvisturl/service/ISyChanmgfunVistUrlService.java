package tech.mhuang.interchan.sso.sysfunvisturl.service;

import org.springframework.scheduling.annotation.Async;
import tech.mhuang.ext.interchan.core.service.BaseService;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunExcludeUrlDTO;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunVistUrlmAddDTO;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunVistUrlmQryDTO;
import tech.mhuang.interchan.sso.sysfunvisturl.entity.SyChanmgfunVistUrlm;
import tech.mhuang.interchan.sso.sysrole.entity.SysRole;

import java.util.List;

/**
 * @ClassName: ISyChanmgfunVistUrlService
 * @Description:权限可访问服务
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:06:08
 */
public interface ISyChanmgfunVistUrlService extends BaseService<SyChanmgfunVistUrlm, String> {

    /**
     * @param funid
     * @return void
     * @Title: deleteByAuth
     * @Description: 删除通过历史
     */
    void deleteByAuth(String funid);

    /**
     * @param dtos
     * @return void
     * @Title: insertPowersUrl
     * @Description: 插入权限
     */
    void insertPowersUrl(List<SyChanmgfunVistUrlmAddDTO> dtos, String userId, String seqno);

    /**
     * @param funid
     * @param userId
     * @param status
     * @param seqno
     * @return void
     * @Title: insertHByAuth
     * @Description: 插入历史通过权限
     */
    void insertHByAuth(String funid, String userId, String status, String seqno);

    /**
     * @param funid
     * @return List<SyChanmgfunVistUrlmQryDTO>
     * @Title: queryFunVist
     * @Description:通过权限查询访问地址
     */
    List<SyChanmgfunVistUrlmQryDTO> queryFunVist(String funid);

    /**
     * @return void
     * @Title: setExcludeUrl
     * @Description: 设置排除url
     */
    List<SyChanmgfunExcludeUrlDTO> setExcludeUrl();

    /**
     * @param userid
     * @return void
     * @Title: setVistUrlPower
     * @Description: 设置用户访问权限
     */
    void setVistUrlPower(String userid);

    /**
     * @param userId
     * @return void
     * @Title: setVistUrlPowerNow
     * @Description: 设置用户访问权限
     */
    void setVistUrlPowerNow(String userId);

    /**
     * @param roleid
     * @return void
     * @Title: setUserVistPowerByRoleAsync
     * @Description: 设置人员角色权限信息
     */
    @Async
    void setUserVistPowerByRoleAsync(String roleid);

    /**
     * @param roles
     * @return void
     * @Title: setUserVistPowerByRolesAsync
     * @Description:设置用户访问权限
     */
    void setUserVistPowerByRolesAsync(List<SysRole> roles);

    /**
     * 批量删除
     *
     * @param ids
     * @param userId
     * @param delete
     * @param reqNo
     * @return void
     * @Title: deleteByFunsIds
     */
    void deleteByFunsIds(List<String> ids, String userId, String delete, String reqNo);

    /**
     * 重新加载可访问路径
     *
     * @param userId
     * @return void
     * @Title: reloadVistUrl
     */
    void reloadVistUrl(String userId);

}
