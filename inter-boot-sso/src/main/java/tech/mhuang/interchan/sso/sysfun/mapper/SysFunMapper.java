package tech.mhuang.interchan.sso.sysfun.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.mhuang.ext.interchan.core.mapper.BaseMapper;
import tech.mhuang.ext.interchan.protocol.InsertInto;
import tech.mhuang.interchan.protocol.sso.sysfun.SysFunDTO;
import tech.mhuang.interchan.sso.sysfun.entity.SysFun;

import java.util.List;

/**
 * @ClassName: SysFunMapper
 * @Description:系统功能权限mapper
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:11:32
 */
@Mapper
public interface SysFunMapper extends BaseMapper<SysFun, String> {

    /**
     * @param funm
     * @return List<SysFunDTO>
     * @Title: queryFunTree
     * @Description: 查询树形结构信息
     */
    List<SysFunDTO> queryFunTree(SysFun funm);

    /**
     * @param fun
     * @return void
     * @Title: updateById
     * @Description:更新功能权限
     */
    void updateById(SysFun fun);

    /**
     * @param funid
     * @return List<SyChanmgfunm>
     * @Title: queryFunByParent
     * @Description: 通过父查询系统功能权限
     */
    List<SysFun> queryFunByParent(String funid);

    /**
     * @param funids
     * @return List<SyChanmgfunm>
     * @Title: queryFunByParents
     * @Description:通过多个父查询系统功能权限
     */
    List<SysFun> queryFunByParents(List<String> funids);

    /**
     * @param into
     * @return void
     * @Title: insertIntos
     * @Description: 插入多个历史表信息
     */
    void insertIntos(InsertInto<List<String>> into);

    /**
     * @param funids
     * @return void
     * @Title: deleteFunByIds
     * @Description: 删除功能号
     */
    void deleteFunByIds(List<String> funids);

}