package com.barley.system.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * 
 * Role wapper
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: RoleWapper.java, V1.0.0 2021年1月3日 上午11:14:53 $
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleWapper {
	private Role role;
	private List<Permission> permissions;
}
