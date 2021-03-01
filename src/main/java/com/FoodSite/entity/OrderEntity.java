package com.FoodSite.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Vijay
 * OrderEntity mapped to orders table
 */
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Order_No")
	private int orderNo;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@Column(name = "payment_Status")
	private boolean paymentStatus;

	@Column(name = "Order_Date")
	private Date orderDate;

	@Column(name = "Customer_ID", updatable = false, insertable = false)
	private int customerId;

	@OneToOne(fetch = FetchType.EAGER)
	/* f.k table reference and P.k entity reference of column */
//	@JsonManagedReference
	@JoinColumn(name = "Customer_ID", nullable = false, referencedColumnName = "Customer_ID")
	CustomerEntity customer;


	@ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
	@JoinTable(name = "Order_item", joinColumns = { @JoinColumn(name = "Order_No") }, inverseJoinColumns = {
			@JoinColumn(name = "Item_ID") })
	private List<ItemMasterEntity> item = new ArrayList<>();

	public List<ItemMasterEntity> getItemMasterEntity() {
		return item;
	}

	public void setItemMasterEntity(List<ItemMasterEntity> item) {
		this.item = item;
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

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public OrderEntity() {
		super();
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderNo=" + orderNo + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", paymentStatus=" + paymentStatus + ", orderDate=" + orderDate + ", customerId=" + customerId
				+ ", customer=" + customer + ", item=" + item + "]";
	}
	

//	@Override
//	public String toString() {
//		return "OrderEntity [orderNo=" + orderNo + ", orderDate=" + orderDate + ", customerId=" + customerId + "]";
//	}

}
