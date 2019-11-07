
package tech.mhuang.interchan.protocol.sso.sysuser.dto;

import tech.mhuang.ext.interchan.protocol.data.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 用户分页查询DTO
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserPageQryDTO extends PageDTO{

	private String username;
	
	private String mobilephone;
	
	private String orderUserIds;
	
	private String available;
}
