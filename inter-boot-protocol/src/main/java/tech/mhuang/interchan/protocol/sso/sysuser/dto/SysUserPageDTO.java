
package tech.mhuang.interchan.protocol.sso.sysuser.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询DTO
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserPageDTO {
    private String userid;
    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * Email
     */
    private String email;

    /**
     * 电话
     */
    private String tel;

    /**
     * 移动电话
     */
    private String mobilephone;

    /**
     * 用户账号是否有效[1=有效(default)})/0=无效]
     */
    private String available;
    /**
     * 地址
     */
    private String address;
}
