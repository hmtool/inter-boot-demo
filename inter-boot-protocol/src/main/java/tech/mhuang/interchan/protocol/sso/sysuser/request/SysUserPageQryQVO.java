
package tech.mhuang.interchan.protocol.sso.sysuser.request;

import tech.mhuang.ext.interchan.protocol.data.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * TODO(这里用一句话描述这个类的作用)})
 * @ClassName:  SysUserPageQryQVO   
 * @author: admin
 * @date:   2018年3月23日 上午9:55:35   
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserPageQryQVO extends PageDTO {

	private String username;
	
	private String mobilephone;
	
	private String orderUserIds;

	private String available;
}
