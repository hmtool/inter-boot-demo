package tech.mhuang.interchan.sso.sysfunvisturl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tech.mhuang.ext.interchan.core.mapper.BaseMapper;
import tech.mhuang.ext.interchan.protocol.InsertInto;
import tech.mhuang.interchan.sso.sysfunvisturl.entity.SyChanmgfunExcludeUrl;
import tech.mhuang.interchan.sso.sysfunvisturl.entity.SyChanmgfunVistUrlm;
import tech.mhuang.interchan.sso.sysuser.entity.SysUser;

import java.util.List;

@Mapper
public interface SyChanmgfunVistUrlmMapper extends BaseMapper<SyChanmgfunVistUrlm, String> {

    /**
     * @param into
     * @return void
     * @Title: insertIntoByAuth
     * @Description: 插入历史通过权限
     */
    void insertIntoByAuth(InsertInto<String> into);

    /**
     * @param funid
     * @return void
     * @Title: deleteByAuth
     * @Description:通过权限删除数据
     */
    void deleteByAuth(String funid);

    /**
     * @param funid
     * @return List<SyChanmgfunVistUrlm>
     * @Title: queryFunVist
     * @Description: 查询访问路径
     */
    List<SyChanmgfunVistUrlm> queryFunVist(String funid);

    /**
     * @return List<SyChanmgfunExcludeUrl>
     * @Title: getExcludeUrl
     * @Description: 查询排除路径
     */
    List<SyChanmgfunExcludeUrl> getExcludeUrl();

    /**
     * @param userId
     * @param checkUrl
     * @return boolean
     * @Title: checkUrlPower
     * @Description: 检查访问地址权限
     */
    boolean checkUrlPower(@Param("userId") String userId, @Param("checkUrl") String checkUrl);

    List<SyChanmgfunVistUrlm> getUserUrlPower(String userId);

    /**
     * @param roleIds
     * @return List<SysUser>
     * @Title: queryUserByRole
     * @Description: 查询具有角色的用户
     */
    List<SysUser> queryUserByRole(List<String> roleIds);

    /**
     * 批量新增
     *
     * @param into
     * @return void
     * @Title: insertIntoByAuths
     */
    void insertIntoByAuths(InsertInto<List<String>> into);

    /**
     * 批量删除
     *
     * @param ids
     * @return void
     * @Title: deleteByAuths
     */
    void deleteByAuths(List<String> ids);
}