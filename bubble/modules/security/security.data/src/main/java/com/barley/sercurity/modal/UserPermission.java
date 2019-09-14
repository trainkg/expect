package com.barley.sercurity.modal;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: UserPermission.java, V1.0.0 2019年5月11日 上午11:26:32 $
 */
public class UserPermission {
	@Getter @Setter
    private String id;

	@Getter @Setter
    private String roleId;
	
	@Getter @Setter
    private String permId;
    
    /**
     * 
     * <PRE>是否已经授权
     * 已经授权,状态为 'Y'
     * 没有授权,状态为 'N'
     * </PRE>
     */
	@Getter @Setter
    private String auth;

    
}