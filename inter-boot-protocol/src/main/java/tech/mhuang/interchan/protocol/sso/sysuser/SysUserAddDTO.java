package tech.mhuang.interchan.protocol.sso.sysuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * 添加系统用户实体
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserAddDTO {

	/**
	 * 用户名
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "用户名", required = true)})
	private String username;
	
	/**
	 * 密码
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "密码", required = true)})
	private String password;
	
	/**
	 * 邮件
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "邮箱")})
	private String email;
	
	/**
	 * 电话
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "电话")})
	private String tel;
	
	/**
	 * 移动电话
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "移动电话", required = true)})
	private String mobilephone;
	
	/**
	 * 地址
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "地址")})
	private String address;
	
	/**
     * 操作者
     */
	@Getter(onMethod_={@ApiModelProperty(hidden = true)})
    private String operateUser;

	/**
	 * 账号
	 */
	private String account;
	/**
	 * 是否是管理员 0 是 1 不是
	 */
	private String manager;
	/**
	 * 关联的公司id
	 */
	private String companyId;
}
