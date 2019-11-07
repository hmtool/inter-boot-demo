package tech.mhuang.interchan.protocol.sso.sysuser.pwd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * 修改个人中心密码
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateCenterPwdDTO {

	@Getter(onMethod_={@ApiModelProperty(value = "旧密码",required = true)})
	private String oldPassword;
	
	/**
	 * 密码
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "新密码", required = true)})
	private String password;
	
	/**
	 * 用户id
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "用户id", required = true)})
	private String userid;
	
	/**
	 * 操作的用户id
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "操作用户id", hidden = true)})
	private String operateUser;
}
