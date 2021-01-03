package com.barley.system.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Group Wapper
 * 
 * @author peculiar.1@163.com
 * @version $ID: GroupWapper.java, V1.0.0 2021年1月3日 上午11:12:24 $
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupWapper {

	private Group group;
	private List<RoleWapper> roleList;

}
