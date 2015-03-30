package com.alex.authenticator.model;

import java.util.HashSet;
import java.util.Set;

public class UserInfo {
	private String id;
	private String usercode;
	private String userName;
	private String password;
	private String salt;
	private int locked;
	private Set<Role> role;
	
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
}
