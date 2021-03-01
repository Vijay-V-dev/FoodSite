package com.FoodSite.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vijay
 * CityEntity mapped to master_city table
 */
@Entity
@Table(name="master_city")
public class CityEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="City_Id")
	private int cityId;
	
	@Column(name="City_Name")
	private String cityName;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CityEntity [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	
}
