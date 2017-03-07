package org.yesee.hinet_vcpe_for_client.web.vo;

import org.yesee.hinet_vcpe_for_client.model.bean.Wan.DefaultSetting;

public class WanVo {
	
	private Integer id;
	private DefaultSetting defaultSetting;
	private String wanName;
	private String portName;
	private String wanIp;
	private String subnet;
	private String defaultGateway;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public DefaultSetting getDefaultSetting() {
		return defaultSetting;
	}
	public void setDefaultSetting(DefaultSetting defaultSetting) {
		this.defaultSetting = defaultSetting;
	}
	public String getWanName() {
		return wanName;
	}
	public void setWanName(String wanName) {
		this.wanName = wanName;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
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
	
	
	
}
