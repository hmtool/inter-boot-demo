package tech.mhuang.interchan.protocol.sso.sysuser.pwd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * 修改密码
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdatePwdDTO {

	
	/**
	 * 密码
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "密码", required = true)})
	private String password;
	
	/**
	 * 用户id
	 */
	@Getter(onMethod_={@ApiModelProperty(value = "用户id", hidden = true)})
	private String userid;
}
