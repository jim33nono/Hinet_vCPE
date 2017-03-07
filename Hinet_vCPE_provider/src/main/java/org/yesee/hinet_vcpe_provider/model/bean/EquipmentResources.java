package org.yesee.hinet_vcpe_provider.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipment_resources")
public class EquipmentResources implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1074353954443701920L;
	
	public EquipmentResources() {
		
	}
	
	public EquipmentResources(Integer id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "CPU")
	private String cpu;
	
	@Column(name = "RAM")
	private String ram;
	
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "EquipmentResources [id=" + id + ", cpu=" + cpu + ", ram=" + ram + ", accountId=" + accountId + "]";
	}

	
	
	
}
