package tech.mhuang.interchan.protocol.sso.sysfun;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysFunDTO implements Serializable {
    /**
     * 功能代号
     */
    private String funid;

    /**
     * 排序
     */
    private Integer orderval;

    /**
     * 当前层
     */
    private Integer layid;

    /**
     * 功能名称
     */
    private String fundesc;

    /**
     * 对应页面路径
     */
    private String funpath;

    /**
     * 父功能代号
     */
    private String parentid;

    /**
     * 是否是底层权限[0=否/1=是(default)})]
     */
    private String botflag;

    /**
     * 是否在使用[0=否/1=是(default)})]
     */
    private String useflag;

    /**
     * 是否展示【0：否/1：是】
     */
    private String displayfun;


    /**
     * 父功能代号
     */
    private String parentName;

    private boolean hasChild;

    private boolean hasMenuChild;

    private String attachid;

    private int childSize;

    private int childMenuSize;

    /**
     * 是否是管理员 0 是 1不是
     */
    private String manager;
    /**
     * sy_chanmgfunm
     */
    private static final long serialVersionUID = 1L;
}