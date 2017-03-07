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
@Table(name = "lan")
public class Lan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4271339763713439188L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "IP")
	private String ip;

	@Column(name = "SUBNET_MASK")
	private String subnetMask;

	@Column(name = "PORT_MUTI_ID")
	private String portMutiId;
	
	@Column(name = "MAC_ADDRESS")
	private String macAddress;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSubnetMask() {
		return subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getPortMutiId() {
		return portMutiId;
	}

	public void setPortMutiId(String portMutiId) {
		this.portMutiId = portMutiId;
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
		return "Lan [id=" + id + ", ip=" + ip + ", subnetMask=" + subnetMask + ", portMutiId=" + portMutiId
				+ ", macAddress=" + macAddress + ", userId=" + userId + ", updateDate=" + updateDate + "]";
	}
	
	

}
