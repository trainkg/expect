package com.barley.sercurity.modal;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 基本人员信息
 * 
 * @author peculiar.1@163.com
 * @version $ID: User.java, V1.0.0 2015年3月20日 下午8:37:28 $
 */
public class User implements Principal {

	/*
	 * 用户状态
	 */
	enum UserStatus {
		// 激活,删除,锁定,未激活
		ACTIVE, REMOVED, LOCKED, UNACTIVE
	}
	
	/**
	 * 用户性别
	 * @author zhuyy
	 *
	 */
	enum Sex{
		MAN,FEMALE,OTHER
	}

	public User() {
	}

	public User(String name, String password) {
		this.username = name;
		this.password = password;
	}

	@Getter @Setter
	private String id;

	/**
	 * 用户别名
	 */
	@Getter @Setter
	private String username;

	/**
	 * 用户登陆名使用 全局唯一
	 */
	@Getter @Setter
	private String loginName;

	/**
	 * 密码
	 */
	@Getter @Setter
	private String password;

	/**
	 * 性别
	 */
	@Getter @Setter
	private Sex sex;

	/**
	 * 邮箱
	 */
	@Getter @Setter
	private String email;

	/**
	 * 地址
	 */
	@Getter @Setter
	private String homeaddress;

	/**
	 * 电话
	 */
	@Getter @Setter
	private String phone;

	/**
	 * 用户状态
	 */
	@Getter @Setter
	private UserStatus status;

	/**
	 * 创建时间
	 */
	@Getter @Setter
	private Date createDate;

	/**
	 * 用户拥有的资源列表
	 */
	@Getter @Setter
	private List<Resource> userResource;

	/**
	 * 用户拥有的角色列表
	 */
	@Getter @Setter
	private List<Role> roles;

	@Override
	public String getName() {
		return getUsername();
	}

	
}