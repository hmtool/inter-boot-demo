package tech.mhuang.interchan.protocol.sso;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * 用户
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {

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
