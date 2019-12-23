
package tech.mhuang.interchan.protocol.sso.sysuser.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

/**
 * @ClassName: SysUserPageQryRVO
 * @author: admin
 * @date: 2018年3月23日 上午10:09:37
 */
@Data
public class SysUserRVO {
    @Getter(onMethod_ = {@ApiModelProperty(value = "userId")})
    private String userid;
    /**
     * 用户名称
     */
    @Getter(onMethod_ = {@ApiModelProperty(value = "用户名")})
    private String username;

    /**
     * 密码
     */
    @Getter(onMethod_ = {@ApiModelProperty(value = "密码")})
    private String password;

    /**
     * Email
     */
    @Getter(onMethod_ = {@ApiModelProperty(value = "邮箱")})
    private String email;

    /**
     * 电话
     */
    @Getter(onMethod_ = {@ApiModelProperty(value = "电话")})
    private String tel;

    /**
     * 移动电话
     */
    @Getter(onMethod_ = {@ApiModelProperty(value = "移动电话")})
    private String mobilephone;

    /**
     * 用户账号是否有效[1=有效(default)})/0=无效]
     */
    @Getter(onMethod_ = {@ApiModelProperty(value = "锁定")})
    private String available;
    /**
     * 地址
     */
    @Getter(onMethod_ = {@ApiModelProperty(value = "地址")})
    private String address;
}
