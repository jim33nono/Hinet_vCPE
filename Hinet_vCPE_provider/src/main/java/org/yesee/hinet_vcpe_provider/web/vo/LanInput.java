package org.yesee.hinet_vcpe_provider.web.vo;


public class LanInput {
	
	private String lanIp;
	private String lanSubnetMask;
	private String lanPortMutiId;
	
	public String getLanIp() {
		return lanIp;
	}
	public void setLanIp(String lanIp) {
		this.lanIp = lanIp;
	}
	public String getLanSubnetMask() {
		return lanSubnetMask;
	}
	public void setLanSubnetMask(String lanSubnetMask) {
		this.lanSubnetMask = lanSubnetMask;
	}
	public String getLanPortMutiId() {
		return lanPortMutiId;
	}
	public void setLanPortMutiId(String lanPortMutiId) {
		this.lanPortMutiId = lanPortMutiId;
	}
	
	@Override
	public String toString() {
		return "LanInput [lanIp=" + lanIp + ", lanSubnetMask=" + lanSubnetMask + ", lanPortMutiId=" + lanPortMutiId
				+ "]";
	}
	
}
