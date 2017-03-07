package org.yesee.hinet_vcpe_for_client.web.vo;


public class DhcpInput {
	
	private String dhcpDns1;
	private String dhcpDns2;
	private String subnet;
	private String netmask;
	private String startIP;
	private String endIP;
	private String defaultGatewayForDhcp;
	
	public String getDhcpDns1() {
		return dhcpDns1;
	}
	public void setDhcpDns1(String dhcpDns1) {
		this.dhcpDns1 = dhcpDns1;
	}
	public String getDhcpDns2() {
		return dhcpDns2;
	}
	public void setDhcpDns2(String dhcpDns2) {
		this.dhcpDns2 = dhcpDns2;
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
	public String getStartIP() {
		return startIP;
	}
	public void setStartIP(String startIP) {
		this.startIP = startIP;
	}
	public String getEndIP() {
		return endIP;
	}
	public void setEndIP(String endIP) {
		this.endIP = endIP;
	}
	public String getDefaultGatewayForDhcp() {
		return defaultGatewayForDhcp;
	}
	public void setDefaultGatewayForDhcp(String defaultGatewayForDhcp) {
		this.defaultGatewayForDhcp = defaultGatewayForDhcp;
	}
	
	@Override
	public String toString() {
		return "DhcpInput [dhcpDns1=" + dhcpDns1 + ", dhcpDns2=" + dhcpDns2 + ", subnet=" + subnet + ", netmask="
				+ netmask + ", startIP=" + startIP + ", endIP=" + endIP + ", defaultGatewayForDhcp="
				+ defaultGatewayForDhcp + "]";
	}
	
}
