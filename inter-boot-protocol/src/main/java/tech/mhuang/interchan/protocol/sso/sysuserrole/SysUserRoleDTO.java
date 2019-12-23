package tech.mhuang.interchan.protocol.sso.sysuserrole;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRoleDTO implements Serializable {
    /**
     * 角色代号
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

    /**
     * sy_chanmgrolem
     */
    private static final long serialVersionUID = 1L;
}