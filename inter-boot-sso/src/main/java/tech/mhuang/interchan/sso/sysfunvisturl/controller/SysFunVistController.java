
package tech.mhuang.interchan.sso.sysfunvisturl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.mhuang.ext.interchan.core.controller.BaseController;
import tech.mhuang.ext.interchan.core.local.GlobalHeaderThreadLocal;
import tech.mhuang.ext.interchan.protocol.GlobalHeader;
import tech.mhuang.ext.interchan.protocol.Result;
import tech.mhuang.ext.spring.util.DataUtil;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunVistUrlmQryDTO;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunVistUrlmQryVO;
import tech.mhuang.interchan.sso.sysfunvisturl.service.ISyChanmgfunVistUrlService;

import java.util.List;

/**
 * @ClassName: SysFunController
 * @Description:系统功能权限
 * @author: admin
 * @date: 2017年7月20日 下午6:48:48
 */
@RestController
@Api(value = "系统功能权限访问地址管理", tags = "功能权限访问地址管理")
@RequestMapping("/sy/sysfunvist")
public class SysFunVistController extends BaseController {

    @Autowired
    private ISyChanmgfunVistUrlService syChanmgfunVistUrlService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "通过功能权限查询访问vo", notes = "查询")
    @GetMapping(value = "/getVistUrl")
    public Result<List<SyChanmgfunVistUrlmQryVO>> getVistUrl(@RequestParam String funid) {
        GlobalHeaderThreadLocal.getOrException();
        List<SyChanmgfunVistUrlmQryDTO> dtos = this.syChanmgfunVistUrlService.queryFunVist(funid);
        List<SyChanmgfunVistUrlmQryVO> vos = DataUtil.copyTo(dtos, SyChanmgfunVistUrlmQryVO.class);
        return (Result<List<SyChanmgfunVistUrlmQryVO>>) Result.ok(vos);
    }


    @ApiOperation(value = "重新加载可访问地址等", notes = "查询")
    @GetMapping(value = "/reloadVistUrl")
    public Result<?> reloadVistUrl() {
        GlobalHeader userHead = GlobalHeaderThreadLocal.get();
        if (userHead != null) {
            this.syChanmgfunVistUrlService.reloadVistUrl(userHead.getUserId());
        } else {
            this.syChanmgfunVistUrlService.reloadVistUrl(null);
        }

        return Result.ok();
    }
}
