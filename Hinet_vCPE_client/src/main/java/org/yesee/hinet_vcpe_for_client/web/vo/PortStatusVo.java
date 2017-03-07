package org.yesee.hinet_vcpe_for_client.web.vo;

import org.yesee.hinet_vcpe_for_client.model.bean.PortStatus.OnlineStatus;

public class PortStatusVo {

	private Integer id;
	private String portName;
	private OnlineStatus onlineStatus;
	private String uploadRate;
	private String downloadRate;
	private Integer portId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(OnlineStatus onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	public String getUploadRate() {
		return uploadRate;
	}
	public void setUploadRate(String uploadRate) {
		this.uploadRate = uploadRate;
	}
	public String getDownloadRate() {
		return downloadRate;
	}
	public void setDownloadRate(String downloadRate) {
		this.downloadRate = downloadRate;
	}
	public Integer getPortId() {
		return portId;
	}
	public void setPortId(Integer portId) {
		this.portId = portId;
	}
	
	
	
}
