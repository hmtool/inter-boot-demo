package tech.mhuang.interchan.protocol.sso.sysfunrole;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleFunTreeVO implements Serializable {
    /**
     * 功能代号
     */
    private String funid;

    /**
     * 功能描述
     */
    private String fundesc;

    /**
     * 父节点id
     */
    private String parentid;

    /**
     * 父节点名称
     */
    private String parentName;


    /**
     * 是否选择
     */
    private boolean checked;

    private int orderval;

    /**
     * 是否使用
     */
    private String useflag;

    /**
     * 是否底层权限
     */
    private String botflag;


    /**
     * 是否展示菜单权限
     */
    private String displayfun;


    /**
     * 子节点组合
     */
    private List<SysRoleFunTreeVO> children;


    private static final long serialVersionUID = 1L;
}