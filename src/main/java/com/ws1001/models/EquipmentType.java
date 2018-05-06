package com.ws1001.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class EquipmentType extends BaseModel {
	private int label;
	private String name;

	public EquipmentType(){

	}

	public EquipmentType(int label, String name){
		this.label = label;
		this.name = name;
	}

	@Column(unique = true, nullable = false)
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
