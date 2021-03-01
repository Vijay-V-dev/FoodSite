package com.FoodSite.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vijay
 * CountryEntity mapped to master_country table
 */
@Entity
@Table(name="master_country")
public class CountryEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Country_Id")
	private int countryId;
	
	@Column(name="Country_Name")
	private String countryName;

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "CountryEntity [countryId=" + countryId + ", countryName=" + countryName + "]";
	}

	
}
