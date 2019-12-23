package tech.mhuang.interchan.protocol.sso.sysfunvisturl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SyChanmgfunExcludeUrlVO implements Serializable {
    /**
     * 主键
     */
    private String type;

    /**
     * 可访问地址
     */
    private String url;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作者
     */
    private String operateUser;

    private static final long serialVersionUID = 1L;

}