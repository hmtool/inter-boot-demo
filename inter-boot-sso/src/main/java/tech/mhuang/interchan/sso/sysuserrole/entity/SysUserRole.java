package tech.mhuang.interchan.sso.sysuserrole.entity;

import lombok.Data;
import tech.mhuang.ext.interchan.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserRole extends BaseEntity implements Serializable {

    /**
     * 角色ID
     */
    private String roleid;

    /**
     * 用户ID
     */
    private String userid;
    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作者
     */
    private String operateUser;

    /**
     * sy_chanmgurrlm
     */
    private static final long serialVersionUID = 1L;
}