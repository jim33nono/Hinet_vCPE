package org.yesee.hinet_vcpe_for_client.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gateway_switch")
public class GatewaySwitch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5393103346541929723L;

	/**
	 * 
	 */

	public GatewaySwitch() {

	}

	public GatewaySwitch(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DESTINATION_IP")
	private String destinationIp;
	
	@Column(name ="SUBNET")
	private String subnet;

	@Column(name = "PRIMARY_CIRCUIT_WAN")
	private Integer primaryCircuitWan;

	@Column(name = "SECONDARY_CIRCUIT_WAN")
	private Integer secondaryCircuitWan;

	@Column(name = "PRIMARY_CIRCUIT_IPSEC")
	private Integer primaryCircuitIpsec;

	@Column(name = "SECONDARY_CIRCUIT_IPSEC")
	private Integer secondaryCircuitIpsec;

	@Column(name = "MS_DELAY")
	private Integer msDelay;
	
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
	
	public String getDestinationIp() {
		return destinationIp;
	}

	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public Integer getPrimaryCircuitWan() {
		return primaryCircuitWan;
	}

	public void setPrimaryCircuitWan(Integer primaryCircuitWan) {
		this.primaryCircuitWan = primaryCircuitWan;
	}

	public Integer getSecondaryCircuitWan() {
		return secondaryCircuitWan;
	}

	public void setSecondaryCircuitWan(Integer secondaryCircuitWan) {
		this.secondaryCircuitWan = secondaryCircuitWan;
	}

	public Integer getPrimaryCircuitIpsec() {
		return primaryCircuitIpsec;
	}

	public void setPrimaryCircuitIpsec(Integer primaryCircuitIpsec) {
		this.primaryCircuitIpsec = primaryCircuitIpsec;
	}

	public Integer getSecondaryCircuitIpsec() {
		return secondaryCircuitIpsec;
	}

	public void setSecondaryCircuitIpsec(Integer secondaryCircuitIpsec) {
		this.secondaryCircuitIpsec = secondaryCircuitIpsec;
	}

	public Integer getMsDelay() {
		return msDelay;
	}

	public void setMsDelay(Integer msDelay) {
		this.msDelay = msDelay;
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
		return "GatewaySwitch [id=" + id + ", destinationIp=" + destinationIp + ", subnet=" + subnet
				+ ", primaryCircuitWan=" + primaryCircuitWan + ", secondaryCircuitWan=" + secondaryCircuitWan
				+ ", primaryCircuitIpsec=" + primaryCircuitIpsec + ", secondaryCircuitIpsec=" + secondaryCircuitIpsec
				+ ", msDelay=" + msDelay + ", userId=" + userId + ", updateDate=" + updateDate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GatewaySwitch other = (GatewaySwitch) obj;
		if (id != other.getId()) {
			return false;
		}
		return true;
	}


}
