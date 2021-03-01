package com.FoodSite.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vijay
 * CategoryEntity Mapped to master_categories Table
 */
@Entity
@Table(name="master_categories")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Category_Id")
	private int categoryId;
	
	@Column(name="Category")
	private String category;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CategoryEntity [categoryId=" + categoryId + ", category=" + category + "]";
	}

}
