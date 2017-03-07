package org.yesee.hinet_vcpe_provider.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.yesee.hinet_vcpe_provider.util.PropertyValues;

@Entity
@Table(name = "port")
public class Port implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2197569676515392095L;

	public Port(){
		
	}
	
	public Port(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "PORT_NAME")
	private String portName;

	@Column(name = "PORT_NO")
	private Integer portNo;
	
	@Column(name = "MAC_ADDRESS")
	private String macAddress;

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

	public Integer getPortNo() {
		return portNo;
	}

	public void setPortNo(Integer portNo) {
		this.portNo = portNo;
	}
	
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Override
	public String toString() {
		return "Port [id=" + id + ", portName=" + portName + ", portNo=" + portNo + ", macAddress=" + macAddress + "]";
	}

	

}
