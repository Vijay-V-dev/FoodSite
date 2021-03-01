package com.FoodSite.entity;
//this is projection interface
/**
 * @author Vijay
 * Interface For Customized Queries of order and customer
 */
public interface OrderCustomerCustomized {

	public Integer getOrderNo();
	public String getCustomerName();
	public Integer getCustomerId();
	public Integer getCityId();

}
