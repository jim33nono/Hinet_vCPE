package org.yesee.hinet_vcpe_for_client.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "port_status")
public class PortStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7798758898711499287L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ONLINE_STATUS")
	@Enumerated(EnumType.STRING)
	private OnlineStatus onlineStatus;
	
	public enum OnlineStatus{
		Yes, No;
	}
	
	@Column(name = "UPLOAD_RATE")
	private String uploadRate;
	
	@Column(name ="DOWNLOAD_RATE")
	private String downloadRate;
	
	@Column(name = "PORT_ID")
	private Integer portId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "PortStatus [id=" + id + ", onlineStatus=" + onlineStatus + ", uploadRate=" + uploadRate
				+ ", downloadRate=" + downloadRate + ", portId=" + portId + "]";
	}

	
	
	
}
