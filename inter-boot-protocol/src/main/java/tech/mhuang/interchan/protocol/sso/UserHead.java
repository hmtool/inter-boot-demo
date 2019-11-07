package tech.mhuang.interchan.protocol.sso;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * 用户头像
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserHead {

	@Getter(onMethod_={@ApiModelProperty(hidden = true)})
	private String userId;
	
	@Getter(onMethod_={@ApiModelProperty(value = "用户头像Id",required = true)})
	private String attachId;
}
