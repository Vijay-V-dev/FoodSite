package com.FoodSite.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Vijay
 * ItemMasterEntity mapped to master_item table
 */
@Entity
@Table(name = "master_item")
public class ItemMasterEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Item_ID")
	private int itemID;

	@Column(name = "Item_Name")
	private String itemName;

	@Column(name = "Category_Id", updatable = false, insertable = false)
	private int categoryID;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Category_Id", nullable = false, referencedColumnName = "Category_Id")
	private CategoryEntity category;

	@ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL, mappedBy = "item")
	private List<OrderEntity> orderEntity = new ArrayList<>();

	public int getItemID() {
		return itemID;
	}

	public List<OrderEntity> getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(List<OrderEntity> orderEntity) {
		this.orderEntity = orderEntity;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ItemMasterEntity [itemID=" + itemID + ", itemName=" + itemName + ", categoryID=" + categoryID + "]";
	}

}
