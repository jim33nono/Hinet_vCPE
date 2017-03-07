package org.yesee.hinet_vcpe_provider.web.vo;

public class IpsecVo {

	private Integer id;
	private String ipsecName;
	private String remoteLanIp;
	private String remoteLanSubnet;
	private String remoteWanIp;
	private String preSharedKey;
	private Integer wanId;
	private String wanName;
	
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
	public String getPreSharedKey() {
		return preSharedKey;
	}
	public void setPreSharedKey(String preSharedKey) {
		this.preSharedKey = preSharedKey;
	}
	public Integer getWanId() {
		return wanId;
	}
	public void setWanId(Integer wanId) {
		this.wanId = wanId;
	}
	public String getWanName() {
		return wanName;
	}
	public void setWanName(String wanName) {
		this.wanName = wanName;
	}
	
	
	
}
