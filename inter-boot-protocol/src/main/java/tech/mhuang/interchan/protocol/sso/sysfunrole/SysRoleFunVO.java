package tech.mhuang.interchan.protocol.sso.sysfunrole;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleFunVO implements Serializable {
    /**
     * 功能代号
     */
    private String funid;

    /**
     * 功能名称
     */
    private String fundesc;

    /**
     * 父代码
     */
    private String parentid;

    /**
     * 父功能名称
     */
    private String parentName;

    private int orderval;

    private static final long serialVersionUID = 1L;
}