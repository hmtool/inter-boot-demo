package tech.mhuang.interchan.sso.sysfun.service;

import tech.mhuang.ext.interchan.core.service.BaseService;
import tech.mhuang.ext.interchan.protocol.data.PageVO;
import tech.mhuang.interchan.protocol.sso.sysfun.SysFunAddDTO;
import tech.mhuang.interchan.protocol.sso.sysfun.SysFunModDTO;
import tech.mhuang.interchan.protocol.sso.sysfun.SysFunPageQueryDTO;
import tech.mhuang.interchan.protocol.sso.sysfun.SysFunQueryDTO;
import tech.mhuang.interchan.protocol.sso.sysfun.SysFunTreeQueryDTO;
import tech.mhuang.interchan.protocol.sso.sysfun.SysFunVO;
import tech.mhuang.interchan.sso.sysfun.entity.SysFun;

import java.util.List;

/**
 * @ClassName: ISysFunService
 * @Description:系统功能权限服务
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:06:08
 */
public interface ISysFunService extends BaseService<SysFun, String> {

    /**
     * @param sysFunAddDTO 功能权限参数
     * @param userId       操作人
     * @return void
     * @Title: saveFun
     * @Description: 新增功能权限信息
     */
    void saveFun(SysFunAddDTO sysFunAddDTO, String userId);

    /**
     * @param sysFunModDTO
     * @param userId
     * @return void
     * @Title: updateFun
     * @Description:修改功能权限
     */
    void updateFun(SysFunModDTO sysFunModDTO, String userId);

    /**
     * @param dto
     * @return PageVo<SysFunVo>
     * @Title: queryFunByPage
     * @Description: 分页查询功能权限
     */
    PageVO<SysFunVO> queryFunByPage(SysFunPageQueryDTO dto);

    /**
     * @param fun    功能权限id
     * @param userid 用户ID
     * @return void
     * @Title: deleteFun
     * @Description: 删除功能权限
     */
    void deleteFun(String fun, String userid);

    /**
     * @param fun           功能权限id
     * @param nullException 为空是否异常
     * @return SysFunQueryDTO
     * @Title: queryFun
     * @Description:查询功能权限
     */
    SysFunQueryDTO queryFun(String fun, boolean nullException);

    /**
     * @param dto
     * @return List<SysFunVo>
     * @Title: queryFunTree
     * @Description: 查询功能权限树形结构
     */
    List<SysFunVO> queryFunTree(SysFunTreeQueryDTO dto);

}
