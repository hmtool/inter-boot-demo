package tech.mhuang.interchan.protocol.sso.sysuser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.mhuang.ext.interchan.protocol.data.PageDTO;

import java.io.Serializable;

/**
 * @ClassName: SysUserDTO
 * @Description:系统用户DTO
 * @author: mhuang
 * @date: 2017年7月17日 下午4:42:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;//用户名称

    private String mobilephone;//手机
}
