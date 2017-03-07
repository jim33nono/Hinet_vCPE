package org.yesee.hinet_vcpe_provider.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_management")
public class AccountManagement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -175212689957146328L;
	
	public AccountManagement(){
		
	}
	
	public AccountManagement(Integer id){
		this.id = id;
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ACCOUNT")
	private String account;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "MAC_ADDRESS")
	private String macAddress;
	
	@Column(name ="USER_ID")
	private String userId;
	
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "AccountManagement [id=" + id + ", account=" + account + ", password=" + password + ", macAddress="
				+ macAddress + ", userId=" + userId + ", updateDate=" + updateDate + "]";
	}


}
