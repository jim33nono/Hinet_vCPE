package org.yesee.hinet_vcpe_for_client.web.vo;


public class GatewaySwitchVo {

	private Integer id;
	private String destinationIp;
	private String subnet;
	private String primaryCircuit;
	private String secondaryCircuit;
	private Integer msDelay;
	
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
	public String getPrimaryCircuit() {
		return primaryCircuit;
	}
	public void setPrimaryCircuit(String primaryCircuit) {
		this.primaryCircuit = primaryCircuit;
	}
	public String getSecondaryCircuit() {
		return secondaryCircuit;
	}
	public void setSecondaryCircuit(String secondaryCircuit) {
		this.secondaryCircuit = secondaryCircuit;
	}
	public Integer getMsDelay() {
		return msDelay;
	}
	public void setMsDelay(Integer msDelay) {
		this.msDelay = msDelay;
	}
	
	
	
}
