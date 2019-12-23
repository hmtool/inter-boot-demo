
package tech.mhuang.interchan.sso.sysfun.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.mhuang.core.util.StringUtil;
import tech.mhuang.ext.interchan.core.controller.BaseController;
import tech.mhuang.ext.interchan.core.local.GlobalHeaderThreadLocal;
import tech.mhuang.ext.interchan.protocol.Result;
import tech.mhuang.ext.interchan.protocol.data.PageVO;
import tech.mhuang.ext.spring.util.DataUtil;
import tech.mhuang.interchan.protocol.sso.sysfun.*;
import tech.mhuang.interchan.sso.sysfun.service.ISysFunService;

import java.util.List;

/**
 * 权限控制器
 *
 * @author zhangxh
 * @since 1.0.0
 */
@RestController
@Api(value = "系统功能权限管理", tags = "功能权限")
@RequestMapping("/sy/sysfun")
public class SysFunController extends BaseController {

    @Autowired
    private ISysFunService sysFunService;

    @ApiOperation(value = "新增功能权限信息", notes = "新增")
    @PostMapping(value = "/saveFun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysFunAddDTO", value = "功能权限对象", required = true, paramType = "body", dataType = "SysFunAddDTO"),
    })
    public Result<?> saveFun(@RequestBody SysFunAddDTO sysFunAddDTO) {
        sysFunService.saveFun(sysFunAddDTO, GlobalHeaderThreadLocal.getOrException().getUserId());
        return Result.ok();
    }

    @ApiOperation(value = "修改功能权限信息", notes = "修改")
    @PutMapping(value = "/updateFun")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysFunModDTO", value = "功能权限对象", required = true, paramType = "body", dataType = "SysFunModDTO"),
    })
    public Result<?> updateFun(@RequestBody SysFunModDTO sysFunModDTO) {
        sysFunService.updateFun(sysFunModDTO, GlobalHeaderThreadLocal.getOrException().getUserId());
        return Result.ok();
    }


    @ApiOperation(value = "分页查询功能权限信息", notes = "分页查询")
    @GetMapping(value = "/queryFunByPage")
    public Result<PageVO<SysFunVO>> queryFunByPage(@ModelAttribute SysFunPageQueryDTO pageQry) {
        GlobalHeaderThreadLocal.getOrException();
        PageVO<SysFunVO> pageVo = this.sysFunService.queryFunByPage(pageQry);
        return (Result<PageVO<SysFunVO>>) Result.ok(pageVo);
    }

    @ApiOperation(value = "树形结构查询权限信息", notes = "查询")
    @GetMapping(value = "/queryFunTree")
    public Result<PageVO<SysFunVO>> queryFunTree(@ModelAttribute SysFunTreeQueryDTO queryTree) {
        GlobalHeaderThreadLocal.getOrException();
        if (StringUtil.isNotBlank(queryTree.getNodeid())) {
            queryTree.setParentid(queryTree.getNodeid());
        } else {
            queryTree.setParentid(queryTree.getParentid());
        }
        List<SysFunVO> funVos = this.sysFunService.queryFunTree(queryTree);
        funVos.parallelStream().forEach(t -> {
            if (t.isHasChild()) {
                t.setExpand(false);
                t.setLeaf(false);
            } else {
                t.setExpand(true);
                t.setLeaf(true);
            }
        });
        PageVO pageVo = new PageVO();
        pageVo.setResult(funVos);
        pageVo.setTotalSize(funVos.size());
        return (Result<PageVO<SysFunVO>>) Result.ok(pageVo);
    }

    @ApiOperation(value = "删除功能权限信息", notes = "删除")
    @DeleteMapping(value = "/deleteFun")
    public Result<?> remove(@RequestParam String funid) {
        this.sysFunService.deleteFun(funid, GlobalHeaderThreadLocal.getOrException().getUserId());
        return Result.ok();
    }

    @ApiOperation(value = "查询功能权限信息", notes = "查询")
    @GetMapping(value = "/queryFun")
    public Result<SysFunVO> queryFun(@RequestParam String funid) {
        SysFunQueryDTO dto = this.sysFunService.queryFun(funid, true);
        SysFunVO funVo = DataUtil.copyTo(dto, SysFunVO.class);
        return (Result<SysFunVO>) Result.ok(funVo);
    }

}
