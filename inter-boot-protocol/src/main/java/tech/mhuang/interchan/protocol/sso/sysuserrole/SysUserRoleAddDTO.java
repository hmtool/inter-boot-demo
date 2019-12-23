package tech.mhuang.interchan.protocol.sso.sysuserrole;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRoleAddDTO implements Serializable {
    /**
     * 用户代码
     */
    private String userid;

    /**
     * 角色代码
     */
    private String roleid;


    private static final long serialVersionUID = 1L;
}