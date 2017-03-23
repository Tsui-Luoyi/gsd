/**
 * 
 */
package com.jsd.web.po;

import java.io.Serializable;

/**
 * @author Jnhuy
 * 
 */
public class TuserOther implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String userCode;
	private String userName;
	private String userGender;
	private Integer userType;
	private String password;
	private String note;
	private Integer tuserId;
	private String uuid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getTuserId() {
		return tuserId;
	}

	public void setTuserId(Integer tuserId) {
		this.tuserId = tuserId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
