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
@Table(name = "ipsec")
public class Ipsec implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4838004627783393836L;

	public Ipsec(){
		
	}
	
	public Ipsec(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "IPSEC_NAME")
	private String ipsecName;

	@Column(name = "REMOTE_LAN_IP")
	private String remoteLanIp;
	
	@Column(name = "REMOTE_LAN_SUBNET")
	private String remoteLanSubnet;
	
	@Column(name = "REMOTE_WAN_IP")
	private String remoteWanIp;
	
	@Column(name = "WAN_ID")
	private Integer wanId;
	
	@Column(name = "MAC_ADDRESS")
	private String macAddress;
	
	@Column(name = "SITE_TO_SITE_IP")
	private String siteToSiteIp;
	
	@Column(name = "PRE_SHARED_KEY")
	private String preSharedKey;
	
	@Column(name = "GATEWAY_INTERFACE")
	private String gatewayInterface;
	
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

	public String getIpsecName() {
		return ipsecName;
	}

	public void setIpsecName(String ipsecName) {
		this.ipsecName = ipsecName;
	}

	public String getRemoteLanIp() {
		return remoteLanIp;
	}

	public void setRemoteLanIp(String remoteLanIp) {
		this.remoteLanIp = remoteLanIp;
	}

	public String getRemoteLanSubnet() {
		return remoteLanSubnet;
	}

	public void setRemoteLanSubnet(String remoteLanSubnet) {
		this.remoteLanSubnet = remoteLanSubnet;
	}

	public String getRemoteWanIp() {
		return remoteWanIp;
	}

	public void setRemoteWanIp(String remoteWanIp) {
		this.remoteWanIp = remoteWanIp;
	}

	public Integer getWanId() {
		return wanId;
	}

	public void setWanId(Integer wanId) {
		this.wanId = wanId;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getSiteToSiteIp() {
		return siteToSiteIp;
	}

	public void setSiteToSiteIp(String siteToSiteIp) {
		this.siteToSiteIp = siteToSiteIp;
	}

	public String getPreSharedKey() {
		return preSharedKey;
	}

	public void setPreSharedKey(String preSharedKey) {
		this.preSharedKey = preSharedKey;
	}

	public String getGatewayInterface() {
		return gatewayInterface;
	}

	public void setGatewayInterface(String gatewayInterface) {
		this.gatewayInterface = gatewayInterface;
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
		return "Ipsec [id=" + id + ", ipsecName=" + ipsecName + ", remoteLanIp=" + remoteLanIp + ", remoteLanSubnet="
				+ remoteLanSubnet + ", remoteWanIp=" + remoteWanIp + ", wanId=" + wanId + ", macAddress=" + macAddress
				+ ", siteToSiteIp=" + siteToSiteIp + ", preSharedKey=" + preSharedKey + ", gatewayInterface="
				+ gatewayInterface + ", userId=" + userId + ", updateDate=" + updateDate + "]";
	}


	
	
	
}
