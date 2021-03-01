package com.FoodSite.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vijay
 * StateEntity mapped to master_state table
 */
@Entity
@Table(name="master_state")
public class StateEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="State_Id")
	private int stateId;
	
	@Column(name="State_Name")
	private String stateName;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "StateEntity [stateId=" + stateId + ", stateName=" + stateName + "]";
	}
	
	
}
