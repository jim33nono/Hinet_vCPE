package org.yesee.hinet_vcpe_provider.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "wan")
public class Wan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6107365911611300619L;


	public Wan() {

	}

	public Wan(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "WAN_NAME")
	private String wanName;

	@Column(name = "PORT_ID")
	private Integer portId;

	@Column(name = "WAN_IP")
	private String wanIp;
	
	@Column(name = "SUBNET")
	private String subnet;

	@Column(name = "DEFAULT_GATEWAY")
	private String defaultGateway;

	@Column(name = "DEFAULT_SETTING")
	@Enumerated(EnumType.STRING)
	private DefaultSetting defaultSetting;

	public enum DefaultSetting {
		Yes, No;
	}
	
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

	public String getWanName() {
		return wanName;
	}

	public void setWanName(String wanName) {
		this.wanName = wanName;
	}

	public Integer getPortId() {
		return portId;
	}

	public void setPortId(Integer portId) {
		this.portId = portId;
	}

	public String getWanIp() {
		return wanIp;
	}

	public void setWanIp(String wanIp) {
		this.wanIp = wanIp;
	}
	
	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
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

	public DefaultSetting getDefaultSetting() {
		return defaultSetting;
	}

	public void setDefaultSetting(DefaultSetting defaultSetting) {
		this.defaultSetting = defaultSetting;
	}

	@Override
	public String toString() {
		return "Wan [id=" + id + ", wanName=" + wanName + ", portId=" + portId + ", wanIp=" + wanIp + ", subnet="
				+ subnet + ", defaultGateway=" + defaultGateway + ", defaultSetting=" + defaultSetting + ", macAddress="
				+ macAddress + ", userId=" + userId + ", updateDate=" + updateDate + "]";
	}



	
}
