
package tech.mhuang.interchan.protocol.sso.sysrole;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.mhuang.ext.interchan.protocol.data.PageDTO;

/**
 * @ClassName: SysRolePageOrderQueryDTO
 * @author: admin
 * @date: 2018年3月26日 下午1:46:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRolePageOrderQueryDTO extends PageDTO {


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
}
