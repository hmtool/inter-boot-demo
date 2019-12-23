package tech.mhuang.interchan.sso.sysuser.service;

import tech.mhuang.ext.interchan.core.service.BaseService;
import tech.mhuang.ext.interchan.protocol.data.PageVO;
import tech.mhuang.interchan.protocol.sso.UserDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.*;
import tech.mhuang.interchan.protocol.sso.sysuser.dto.SysUserPageDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.dto.SysUserPageQryDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.pwd.ResetPwdDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.pwd.UpdateCenterPwdDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.pwd.UpdatePwdDTO;
import tech.mhuang.interchan.sso.sysuser.entity.SysUser;

import java.util.List;

/**
 * @ClassName: ISysUserService
 * @Description:系统用户接口
 * @author: mhuang
 * @date: 2017年7月18日 下午7:13:17
 */
public interface ISysUserService extends BaseService<SysUser, String> {

    /**
     * @param sysUserDTO 分页条件DTO
     * @return PageVO<SysUserVO>
     * @Title: queryUserByPage
     * @Description: 分页查询用户
     */
    public PageVO<SysUserVO> queryUserByPage(SysUserDTO sysUserDTO);

    /**
     * @param sysUserDTO
     * @return int
     * @throws Exception
     * @Title: saveUser
     * @Description: 新增系统用户
     */
    public void saveUser(SysUserAddDTO sysUserAddDTO);

    /**
     * @param updatePwdDTO
     * @return void
     * @Title: updatePwd
     * @Description:修改系统用户 密码
     */
    public void updatePwd(UpdatePwdDTO updatePwdDTO);

    /**
     * @param updatePwdDTO
     * @return void
     * @Title: updatePwd
     * @Description:修改系统用户 密码
     */
    public void updateLoginPwd(UpdateCenterPwdDTO updatePwdDTO);

    /**
     * @param updatePwdDTO
     * @return void
     * @Title: resetPwd
     * @Description:重置系统用户 密码
     */
    public void resetPwd(ResetPwdDTO resetPwd);

    /**
     * @param lockUserDTO
     * @return void
     * @Title: lockUser
     * @Description: 锁定用户
     */
    public void lockUser(UserDTO lockUserDTO);

    /**
     * @param sysUserUpdateDTO
     * @return void
     * @Title: updateUser
     * @Description:修改系统用户
     */
    public void updateUser(SysUserUpdateDTO sysUserUpdateDTO);

    /**
     * @param mobilephone 手机号
     * @param password    密码
     * @return LoginSysUserDTO
     * @Title: loginUsePwd
     * @Description:使用密码登录
     */
    public LoginSysUserDTO loginUsePwd(String mobilephone, String password);

    /**
     * 排序分页查询
     *
     * @param sysUserDTO
     * @return List<SysUserPageDTO>
     * @Title: pageForOrder
     */
    public List<SysUserPageDTO> pageForOrder(SysUserPageQryDTO sysUserDTO);

    /**
     * 通过用户ID查询用户
     *
     * @param userIds
     * @return List<SysUser>
     * @Title: findByUserIds
     */
    public List<SysUser> findByUserIds(List<String> userIds);
}
