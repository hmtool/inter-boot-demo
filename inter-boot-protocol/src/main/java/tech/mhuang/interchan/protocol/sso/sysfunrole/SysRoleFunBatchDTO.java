package tech.mhuang.interchan.protocol.sso.sysfunrole;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleFunBatchDTO implements Serializable {
    /**
     * 功能代号
     */
    private List<String> funids;

    /**
     * 角色代码
     */
    private String roleid;


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