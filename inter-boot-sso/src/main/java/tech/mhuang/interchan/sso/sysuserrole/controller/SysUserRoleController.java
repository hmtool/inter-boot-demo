package tech.mhuang.interchan.sso.sysuserrole.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.mhuang.ext.interchan.core.controller.BaseController;
import tech.mhuang.ext.interchan.core.local.GlobalHeaderThreadLocal;
import tech.mhuang.ext.interchan.protocol.GlobalHeader;
import tech.mhuang.ext.interchan.protocol.Result;
import tech.mhuang.ext.interchan.protocol.data.PageVO;
import tech.mhuang.ext.spring.util.DataUtil;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserFunDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserFunVO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleAddDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleCheckDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleCheckVO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleDTO;
import tech.mhuang.interchan.protocol.sso.sysuserrole.SysUserRoleVO;
import tech.mhuang.interchan.sso.sysfunvisturl.service.ISyChanmgfunVistUrlService;
import tech.mhuang.interchan.sso.sysuserrole.service.ISysUserRoleService;

import java.util.List;

/**
 * @ClassName: SysUserRoleController
 * @Description:系统用户角色
 * @author: admin
 * @date: 2017年7月20日 下午6:48:48
 */
@RestController
@Api(value = "系统用户角色管理", tags = "系统用户角色")
@RequestMapping("/sy/sysuserrole")
public class SysUserRoleController extends BaseController {


    @Autowired
    private ISyChanmgfunVistUrlService syChanmgfunVistUrlService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @ApiOperation(value = "保存用户角色信息", notes = "保存")
    @PostMapping(value = "/setRoleUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUserRoleAddDTO", value = "用户角色对象,多个roleid用逗号隔开", required = true, paramType = "body", dataType = "SysUserRoleAddDTO"),
    })
    public Result<?> setRoleUser(@RequestBody SysUserRoleAddDTO sysUserRoleAddDTO) {
        GlobalHeader globalHeader = GlobalHeaderThreadLocal.getOrException();
        sysUserRoleService.saveUserRole(sysUserRoleAddDTO, globalHeader.getUserId());
        return Result.ok();
    }


    @ApiOperation(value = "查询用户角色信息", notes = "查询")
    @GetMapping(value = "/queryUserRole")
    public Result<List<SysUserRoleVO>> queryUserRole(@RequestParam(required = false) String userid) {
        GlobalHeaderThreadLocal.getOrException();
        List<SysUserRoleDTO> userRoles = this.sysUserRoleService.queryUserRole(userid);
        List<SysUserRoleVO> roleVos = DataUtil.copyTo(userRoles, SysUserRoleVO.class);
        return (Result<List<SysUserRoleVO>>) Result.ok(roleVos);
    }


    @ApiOperation(value = "查询用户角色选择信息", notes = "查询")
    @GetMapping(value = "/queryUserRoleCheck")
    public Result<PageVO<List<SysUserRoleCheckVO>>> queryUserRoleAll(@RequestParam(required = false) String userid) {
        GlobalHeaderThreadLocal.getOrException();
        List<SysUserRoleCheckDTO> userRoles = this.sysUserRoleService.queryUserRoleCheck(userid);
        List<SysUserRoleCheckVO> roleVos = DataUtil.copyTo(userRoles, SysUserRoleCheckVO.class);
        PageVO pageVO = new PageVO();
        pageVO.setResult(roleVos);
        pageVO.setTotalSize(roleVos.size());
        return (Result<PageVO<List<SysUserRoleCheckVO>>>) Result.ok(pageVO);
    }


    @ApiOperation(value = "查询用户权限", notes = "查询")
    @GetMapping(value = "/queryUserFun")
    public Result<List<SysUserFunVO>> queryUserFun(@RequestParam String userid) {
        GlobalHeaderThreadLocal.getOrException();
        List<SysUserFunDTO> dtos = this.sysUserRoleService.queryUserFun(userid);
        List<SysUserFunVO> roleFunVos = DataUtil.copyTo(dtos, SysUserFunVO.class);
        return (Result<List<SysUserFunVO>>) Result.ok(roleFunVos);
    }


    @ApiOperation(value = "查询用户权限", notes = "查询")
    @GetMapping(value = "/queryUserFunForLogin")
    public Result<List<SysUserFunVO>> queryUserFunForLogin() {
        GlobalHeader header = GlobalHeaderThreadLocal.getOrException();
        List<SysUserFunDTO> dtos = this.sysUserRoleService.queryUserFun(header.getUserId());
        List<SysUserFunVO> roleFunVos = DataUtil.copyTo(dtos, SysUserFunVO.class);
        this.syChanmgfunVistUrlService.setVistUrlPowerNow(header.getUserId());
        this.sysUserRoleService.setUserRoleToCache(header.getUserId());
        return (Result<List<SysUserFunVO>>) Result.ok(roleFunVos);
    }
}
