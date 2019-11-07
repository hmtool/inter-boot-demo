
package tech.mhuang.interchan.sso.sysrole.domain;

import lombok.Data;

/**
 * @ClassName: SysRolePageQueryQVO
 * @author: admin
 * @date: 2018年3月26日 下午1:56:42
 */
@Data
public class SysRolePageQueryDO {

    /**
     * 角色id
     */
    private String roleid;

    /**
     * 角色名称
     */
    private String roledesc;

    /**
     * 审核标记[0 有效 1 无效]
     */
    private String markflag;

    private String orderRoleIds;
}
