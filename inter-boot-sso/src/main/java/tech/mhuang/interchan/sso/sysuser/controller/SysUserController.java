package tech.mhuang.interchan.sso.sysuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.mhuang.ext.interchan.core.controller.BaseController;
import tech.mhuang.ext.interchan.core.local.GlobalHeaderThreadLocal;
import tech.mhuang.ext.interchan.protocol.GlobalHeader;
import tech.mhuang.ext.interchan.protocol.Result;
import tech.mhuang.ext.interchan.protocol.data.PageVO;
import tech.mhuang.ext.jwt.admin.JwtFramework;
import tech.mhuang.ext.spring.util.DataUtil;
import tech.mhuang.interchan.protocol.sso.UserDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.*;
import tech.mhuang.interchan.protocol.sso.sysuser.dto.SysUserPageDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.dto.SysUserPageQryDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.pwd.ResetPwdDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.pwd.UpdateCenterPwdDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.pwd.UpdatePwdDTO;
import tech.mhuang.interchan.protocol.sso.sysuser.request.SysUserPageQryQVO;
import tech.mhuang.interchan.protocol.sso.sysuser.response.SysUserPageQryRVO;
import tech.mhuang.interchan.protocol.sso.sysuser.response.SysUserRVO;
import tech.mhuang.interchan.sso.sysuser.entity.SysUser;
import tech.mhuang.interchan.sso.sysuser.service.ISysUserService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysUserController
 * @Description:系统用户管理
 * @author: mhuang
 * @date: 2017年7月13日 下午3:39:48
 */
@RestController("sy/sysuser")
@RequestMapping("sy/sysuser")
@Api(value = "系统用户管理", tags = {"权限"})
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private JwtFramework jwtFramework;

    @GetMapping("/queryUserByPage")
    @ApiOperation(value = "系统用户分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "mobilephone", value = "移动电话", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "rows", value = "条数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "start", value = "页数", required = true, dataType = "Integer")
    })
    public Result<PageVO<SysUserVO>> queryUserByPage(@RequestParam(required = false) String username,
                                                     @RequestParam(required = false) String mobilephone, @RequestParam("start") Integer start,
                                                     @RequestParam("rows") Integer rows) {
        SysUserDTO sysUserDTO = new SysUserDTO();
        //组装对象
        packPageSysUserDTO(username, mobilephone, start, rows, sysUserDTO);

        return (Result<PageVO<SysUserVO>>) Result.ok(sysUserService.queryUserByPage(sysUserDTO));
    }


    @SuppressWarnings("unchecked")
    @GetMapping("/pageForOrder")
    @ApiOperation(value = "系统用户分页查询", notes = "分页查询")
    public Result<PageVO<SysUserPageQryRVO>> pageForOrder(@ModelAttribute SysUserPageQryQVO qryVo) {
        SysUserPageQryDTO sysUserDTO = DataUtil.copyTo(qryVo, SysUserPageQryDTO.class);
        SysUser sysUser = DataUtil.copyTo(qryVo, SysUser.class);
        int count = this.sysUserService.count(sysUser);
        PageVO<SysUserPageQryRVO> pageVo = new PageVO<>();
        pageVo.setTotalSize(count);
        if (count != 0) {
            List<SysUserPageDTO> pageDtos = sysUserService.pageForOrder(sysUserDTO);
            pageVo.setResult(DataUtil.copyTo(pageDtos, SysUserPageQryRVO.class));
        }
        return (Result<PageVO<SysUserPageQryRVO>>) Result.ok(pageVo);
    }


    @ApiOperation(value = "新增系统用户", notes = "新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUserAddDto", value = "修改的对象", required = true, paramType = "body", dataType = "SysUserAddDTO"),
    })
    @PostMapping("/saveUser")
    public Result<?> saveUser(@RequestBody SysUserAddDTO sysUserAddDto) {
        GlobalHeader globalHeader = GlobalHeaderThreadLocal.getOrException();
        sysUserAddDto.setOperateUser(globalHeader.getUserId());
        sysUserService.saveUser(sysUserAddDto);
        return Result.ok();
    }

    @ApiOperation(value = "修改系统用户", notes = "修改")
    @PutMapping("/updateUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUserUpdateDTO", value = "修改的对象", required = true, paramType = "body", dataType = "SysUserUpdateDTO"),
    })
    public Result<?> updateUser(@RequestBody SysUserUpdateDTO sysUserUpdateDTO) {
        if (true) {
            throw new NullPointerException();
        }
        GlobalHeader globalHeader = GlobalHeaderThreadLocal.getOrException();
        sysUserUpdateDTO.setOperateUser(globalHeader.getUserId());
        sysUserService.updateUser(sysUserUpdateDTO);
        return Result.ok();
    }

    @ApiOperation(value = "修改密码", notes = "修改")
    @PutMapping("/updatePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "updatePwd", value = "修改的对象", required = true, paramType = "body", dataType = "UpdatePwd"),
    })
    public Result<?> updatePassword(@RequestBody UpdatePwdDTO updatePwd) {
        GlobalHeader globalHeader = GlobalHeaderThreadLocal.getOrException();
        updatePwd.setUserid(globalHeader.getUserId());
        sysUserService.updatePwd(updatePwd);
        return Result.ok();
    }

    @ApiOperation(value = "系统登录密码修改", notes = "修改")
    @PutMapping("/updateLoginPwd")
    public Result<?> updateLoginPwd(@RequestBody UpdateCenterPwdDTO updatePwd) {

        GlobalHeader globalHeader = GlobalHeaderThreadLocal.getOrException();
        updatePwd.setUserid(globalHeader.getUserId());
        sysUserService.updateLoginPwd(updatePwd);
        return Result.ok();
    }

    @ApiOperation(value = "重置密码", notes = "重置")
    @PutMapping("/resetPassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resetPwd", value = "修改的对象", required = true, paramType = "body", dataType = "ResetPwdDTO"),
    })
    public Result<?> resetPassword(@RequestBody ResetPwdDTO resetPwd) {
        GlobalHeader globalHeader = GlobalHeaderThreadLocal.getOrException();
        resetPwd.setOperateUser(globalHeader.getUserId());
        sysUserService.resetPwd(resetPwd);
        return Result.ok();
    }

    @ApiOperation(value = "用户锁定/解锁", notes = "锁定/解锁")
    @PutMapping("/lockUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lockUserDTO", value = "锁定的对象", required = true, paramType = "body", dataType = "UserDTO"),
    })
    public Result<?> lockUser(@RequestBody UserDTO lockUserDTO) {
        lockUserDTO.setOperateUser(GlobalHeaderThreadLocal.getOrException().getUserId());
        sysUserService.lockUser(lockUserDTO);
        return Result.ok();
    }

    /**
     * @return void
     * @Title: packPageSysUserDTO
     * @Description: 组装user对象
     */
    private void packPageSysUserDTO(String username, String mobilePhone, Integer start, Integer rows, SysUserDTO sysUserDTO) {
        sysUserDTO.setMobilephone(mobilePhone);
        sysUserDTO.setStart(start);
        sysUserDTO.setUsername(username);
        sysUserDTO.setRows(rows);
    }

    @ApiOperation(value = "系统用户使用密码登录", notes = "登录")
    @GetMapping("/loginUsePwd")
    public Result<?> loginUsePwd(@RequestParam String mobilephone, @RequestParam String password) {

        LoginSysUserDTO userDto = this.sysUserService.loginUsePwd(mobilephone, password);
        LoginSysUserVO userVo = DataUtil.copyTo(userDto, LoginSysUserVO.class);
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("userId", userDto.getUserid());
        claimMap.put("type", "webuser");
        String token = jwtFramework.getProducer("zhangsan").create(claimMap);
        userVo.setToken(token);
        userVo.setAuthType("zhangsan");
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + token);
        headerMap.put("authType", "zhangsan");
        return Result.ok(userVo);
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/findByUserIds")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    public Result<List<SysUserRVO>> findByUserIds(@RequestParam String userIds) {
        GlobalHeaderThreadLocal.getOrException();
        List<SysUser> users = sysUserService.findByUserIds(Arrays.asList(userIds.split(",")));
        return (Result<List<SysUserRVO>>) Result.ok(DataUtil.copyTo(users, SysUserRVO.class));
    }
}