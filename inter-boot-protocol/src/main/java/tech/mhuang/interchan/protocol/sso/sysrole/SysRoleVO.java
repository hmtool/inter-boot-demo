package tech.mhuang.interchan.protocol.sso.sysrole;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleVO implements Serializable {
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
    /**
     * 是否是管理员 0是 1不是
     */
    private String manager;
    /*
     * 公司id
     */
    private String companyId;
}