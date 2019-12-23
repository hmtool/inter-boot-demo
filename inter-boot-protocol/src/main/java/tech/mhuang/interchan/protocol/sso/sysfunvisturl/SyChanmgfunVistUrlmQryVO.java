package tech.mhuang.interchan.protocol.sso.sysfunvisturl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SyChanmgfunVistUrlmQryVO implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 可访问地址
     */
    private String url;

    /**
     * 功能代码
     */
    private String funid;

    private static final long serialVersionUID = 1L;

}