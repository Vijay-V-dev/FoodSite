package com.FoodSite.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Vijay
 * CustomerEntity Mapped to customer Table
 */
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Customer_ID")
	private int customerId;

	@Column(name = "Customer_Name")
	private String customerName;

	@Column(name = "Customer_Mobile_No")
	private long customerMobileNo;

	@Column(name = "Address_Line")
	private String addressLine;

	@Column(name = "City_ID", updatable = false, insertable = false)
	private int cityId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "City_Id",nullable = false,referencedColumnName ="City_Id" )
	private CityEntity city;
	
	@Column(name = "State_Id", updatable = false, insertable = false)
	private int stateId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "State_Id",nullable = false,referencedColumnName ="State_Id" )
	private StateEntity state;
	
	@Column(name = "Country_Id", updatable = false, insertable = false)
	private int countryId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Country_Id",nullable = false,referencedColumnName ="Country_Id" )
	private CountryEntity country;

	@Column(name = "Postal_Code")
	private int postalCode;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer")
	private List<OrderEntity> order;

	public List<OrderEntity> getOrder() {
		return order;
	}

	public void setOrder(List<OrderEntity> order) {
		this.order = order;
	}

	
	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = state;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public CustomerEntity() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(long customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "CustomerEntity [customerId=" + customerId + ", customerName=" + customerName + ", customerMobileNo="
				+ customerMobileNo + ", addressLine=" + addressLine + ", cityId=" + cityId + ", stateId=" + stateId
				+ ", countryId=" + countryId + ", postalCode=" + postalCode + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}


}
