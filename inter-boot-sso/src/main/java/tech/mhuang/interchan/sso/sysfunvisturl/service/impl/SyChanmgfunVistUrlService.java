package tech.mhuang.interchan.sso.sysfunvisturl.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.mhuang.core.id.BaseIdeable;
import tech.mhuang.core.util.CollectionUtil;
import tech.mhuang.core.util.StringUtil;
import tech.mhuang.ext.interchan.auth.constant.AuthConstant;
import tech.mhuang.ext.interchan.core.rest.SingleRestTemplate;
import tech.mhuang.ext.interchan.core.service.impl.BaseServiceImpl;
import tech.mhuang.ext.interchan.protocol.InsertInto;
import tech.mhuang.ext.interchan.redis.commands.IRedisExtCommands;
import tech.mhuang.ext.spring.util.DataUtil;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunExcludeUrlDTO;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunVistUrlmAddDTO;
import tech.mhuang.interchan.protocol.sso.sysfunvisturl.SyChanmgfunVistUrlmQryDTO;
import tech.mhuang.interchan.sso.sysfunvisturl.entity.SyChanmgfunExcludeUrl;
import tech.mhuang.interchan.sso.sysfunvisturl.entity.SyChanmgfunVistUrlm;
import tech.mhuang.interchan.sso.sysfunvisturl.mapper.SyChanmgfunVistUrlmMapper;
import tech.mhuang.interchan.sso.sysfunvisturl.service.ISyChanmgfunVistUrlService;
import tech.mhuang.interchan.sso.sysrole.entity.SysRole;
import tech.mhuang.interchan.sso.sysuser.entity.SysUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SyChanmgfunVistUrlService
 * @Description:权限可访问路径服务
 * @author: 张小虎
 * @date: 2017年7月19日 上午10:10:04
 */
@Service("syChanmgfunVistUrlService")
@Transactional(readOnly = true)
public class SyChanmgfunVistUrlService
        extends BaseServiceImpl<SyChanmgfunVistUrlm, String> implements ISyChanmgfunVistUrlService, InitializingBean {

    @Autowired
    private BaseIdeable<String> snowflake;

    @Autowired
    private IRedisExtCommands redisExtCommands;

    @Autowired
    private SyChanmgfunVistUrlmMapper syChanmgfunVistUrlmMapper;

    /**
     * @param funid
     * @return void
     * @Title: insertHByAuth
     * @Description: 插入历史通过权限
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertHByAuth(String funid, String userId, String status, String seqno) {
        InsertInto<String> into = new InsertInto<>();
        into.setId(funid);
        into.setOpDate(new Date());
        into.setUserId(userId);
        into.setReqNo(snowflake.generateId());
        into.setStatus(status);
        this.syChanmgfunVistUrlmMapper.insertIntoByAuth(into);
    }

    /**
     * @param funid
     * @return void
     * @Title: deleteByAuth
     * @Description:删除通过权限
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByAuth(String funid) {
        this.syChanmgfunVistUrlmMapper.deleteByAuth(funid);
    }

    /**
     * <p>Title: insertPowers</p>
     * <p>Description: </p>
     *
     * @param dtos
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertPowersUrl(List<SyChanmgfunVistUrlmAddDTO> dtos, String userId, String seqno) {
        dtos.forEach((value) -> {
            SyChanmgfunVistUrlm url = DataUtil.copyTo(value,SyChanmgfunVistUrlm.class);
            url.setOperateTime(new Date());
            url.setOperateUser(userId);
            url.setId(snowflake.generateId());
            this.syChanmgfunVistUrlmMapper.save(url);
        });
        if (CollectionUtil.isNotEmpty(dtos)) {
            InsertInto<String> into = new InsertInto<>();
            into.setReqNo(seqno);
            into.setId(dtos.get(0).getFunid());
            into.setStatus(InsertInto.ADD);
            into.setUserId(userId);
            into.setOpDate(new Date());
            this.syChanmgfunVistUrlmMapper.insertIntoByAuth(into);
        }
    }

    /**
     * <p>Title: queryFunVist</p>
     * <p>Description: </p>
     *
     * @param funid
     * @return
     */
    @Override
    public List<SyChanmgfunVistUrlmQryDTO> queryFunVist(String funid) {
        List<SyChanmgfunVistUrlm> urlm = this.syChanmgfunVistUrlmMapper.queryFunVist(funid);
        return DataUtil.copyTo(urlm, SyChanmgfunVistUrlmQryDTO.class);
    }

    /**
     * @return void
     * @Title: setExcludeUrl
     * @Description: 设置排除地址
     */
    @Override
    public List<SyChanmgfunExcludeUrlDTO> setExcludeUrl() {
        List<SyChanmgfunExcludeUrlDTO> vos = null;
        //查询数据库，并放入缓存中
        List<SyChanmgfunExcludeUrl> urls = this.syChanmgfunVistUrlmMapper.getExcludeUrl();
        if (CollectionUtil.isNotEmpty(urls)) {
            Map datas =
                    urls.parallelStream().collect(Collectors.groupingBy(SyChanmgfunExcludeUrl::getType));
            this.redisExtCommands.hmset(AuthConstant.AUTH_DICT_KEY, datas);
        }
        return vos;
    }

    /**
     * <p>Title: afterPropertiesSet</p>
     * <p>Description: </p>
     *
     * @throws Exception
     * @see InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        setExcludeUrl();
    }

    /**
     * <p>Title: setVistUrlPower</p>
     * <p>Description: </p>
     *
     * @param userid
     */
    @Override
    public void setVistUrlPower(String userid) {
        //检查路径与权限问题
        String cacheKey = AuthConstant.USER_VIST_URL_CACHEKEY;
        List<SyChanmgfunVistUrlm> vistUrls = this.redisExtCommands.hgetList(cacheKey, userid, SyChanmgfunVistUrlm.class);
        if (CollectionUtil.isEmpty(vistUrls)) {
            //不存在的时候设置权限
            setVistUrlPowerNow(userid);
        }

    }

    @Override
    public void setVistUrlPowerNow(String userId) {
        String cacheKey = AuthConstant.USER_VIST_URL_CACHEKEY;
        List<SyChanmgfunVistUrlm> urlms = this.syChanmgfunVistUrlmMapper.getUserUrlPower(userId);
        Map params = urlms.parallelStream().collect(Collectors.toMap((k) -> {
            return userId.concat("-").concat(k.getUrl());
        }, v -> v, (oldValue, newValue) -> oldValue));
        this.redisExtCommands.hmset(cacheKey, params);

    }

    /**
     * @param roleid
     * @return void
     * @Title: setUserVistPowerByRoleAsync
     * @Description:设置人员角色权限信息
     */
    @Override
    @Async
    public void setUserVistPowerByRoleAsync(String roleid) {
        //查询角色对应的用户信息
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleid);
        List<SysUser> users = this.syChanmgfunVistUrlmMapper.queryUserByRole(roleIds);
        users.forEach(user -> {
            setVistUrlPowerNow(user.getUserid());
        });
    }

    /**
     * <p>Title: setUserVistPowerByRolesAsync</p>
     * <p>Description: </p>
     *
     * @param roles
     */
    @Async
    @Override
    public void setUserVistPowerByRolesAsync(List<SysRole> roles) {
        if (CollectionUtil.isNotEmpty(roles)) {
            //查询角色对应的用户信息
            List<String> roleIds =
                    roles.parallelStream().map(value -> value.getRoleid()).collect(Collectors.toList());
            List<SysUser> users = this.syChanmgfunVistUrlmMapper.queryUserByRole(roleIds);
            users.forEach(user -> {
                setVistUrlPowerNow(user.getUserid());
            });
        }
    }

    /**
     * <p>Title: deleteByFunsIds</p>
     * <p>Description: </p>
     *
     * @param ids
     * @param userId
     * @param reqNo
     * @see ISyChanmgfunVistUrlService#deleteByFunsIds(List, String, String, String)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteByFunsIds(List<String> ids, String userId, String status, String reqNo) {
        InsertInto<List<String>> into = new InsertInto<>();
        into.setId(ids);
        into.setOpDate(new Date());
        into.setUserId(userId);
        into.setReqNo(reqNo);
        into.setStatus(status);
        this.syChanmgfunVistUrlmMapper.insertIntoByAuths(into);
        this.syChanmgfunVistUrlmMapper.deleteByAuths(ids);
    }

    /**
     * <p>Title: reloadVistUrl</p>
     * <p>Description: </p>
     *
     * @param userId
     * @see ISyChanmgfunVistUrlService#reloadVistUrl(String)
     */
    @Override
    public void reloadVistUrl(String userId) {
        if (StringUtil.isNotBlank(userId)) {
            this.setVistUrlPowerNow(userId);
        } else {
            this.setExcludeUrl();
        }

    }

}
