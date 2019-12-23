package tech.mhuang.interchan.protocol.sso.sysrole.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.mhuang.ext.interchan.protocol.data.PageDTO;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysRolePageQueryQVO extends PageDTO implements Serializable {

    /**
     * 角色id
     */
    private String roleid;


    /**
     * 角色名称
     */
    private String roledesc;

    /**
     * 审核标示
     */
    private String markflag;


    private String orderRoleIds;

    /**
     * sy_chanmgrolem
     */
    private static final long serialVersionUID = 1L;
}