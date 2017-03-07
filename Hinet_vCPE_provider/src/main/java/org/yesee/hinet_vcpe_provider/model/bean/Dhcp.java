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
@Table(name = "dhcp")
public class Dhcp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -29939473770010737L;

	public Dhcp(){
		
	}
	
	public Dhcp(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DNS1")
	private String dns1;

	@Column(name = "DNS2")
	private String dns2;
	
	@Column(name = "SUBNET")
	private String subnet;

	@Column(name = "NETMASK")
	private String netmask;
	
	@Column(name = "START_IP")
	private String startIp;
	
	@Column(name = "END_IP")
	private String endIp;
	
	@Column(name = "DEFAULT_GATEWAY")
	private String defaultGateway;
	
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

	public String getDns1() {
		return dns1;
	}

	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}

	public String getDns2() {
		return dns2;
	}

	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getStartIp() {
		return startIp;
	}

	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}

	public String getEndIp() {
		return endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	public String getDefaultGateway() {
		return defaultGateway;
	}

	public void setDefaultGateway(String defaultGateway) {
		this.defaultGateway = defaultGateway;
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
		return "Dhcp [id=" + id + ", dns1=" + dns1 + ", dns2=" + dns2 + ", subnet=" + subnet + ", netmask=" + netmask
				+ ", startIp=" + startIp + ", endIp=" + endIp + ", defaultGateway=" + defaultGateway + ", macAddress="
				+ macAddress + ", userId=" + userId + ", updateDate=" + updateDate + "]";
	}


	
	
}
