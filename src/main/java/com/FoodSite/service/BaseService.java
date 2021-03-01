package com.FoodSite.service;

import java.util.ArrayList;
import java.util.List;

import com.FoodSite.dto.CustomerDTO;
import com.FoodSite.dto.ItemMasterDTO;
import com.FoodSite.dto.OrderDTO;
import com.FoodSite.entity.CustomerEntity;
import com.FoodSite.entity.ItemMasterEntity;
import com.FoodSite.entity.OrderEntity;

/**
 * @author Vijay
 * Base Service class
 */
public class BaseService {

	public BaseService()
	{
		super();
	}

	/**
	 * @param responseOrderEntity
	 * @return OrderDTO
	 * this method converts OrderEntity to OrderDTO object
	 */
	protected OrderDTO orderDTOConvertor(OrderEntity responseOrderEntity) 
	{
		OrderDTO orderDTOObj=new OrderDTO();
		orderDTOObj.setOrderNo(responseOrderEntity.getOrderNo());
		orderDTOObj.setCustomerId(responseOrderEntity.getCustomer().getCustomerId());
		orderDTOObj.setCustomerName(responseOrderEntity.getCustomer().getCustomerName());
		orderDTOObj.setOrderDate(responseOrderEntity.getOrderDate());
		orderDTOObj.setCreatedAt(responseOrderEntity.getCreatedAt());
		orderDTOObj.setUpdatedAt(responseOrderEntity.getUpdatedAt());
		orderDTOObj.setPaymentStatus(responseOrderEntity.isPaymentStatus());
		return orderDTOObj;
	}
	/**
	 * @param List<OrderEntity>
	 * @return List<OrderDTO>
	 * this method converts List of OrderEntity to List of OrderDTO object
	 */
	protected List<OrderDTO> orderListDTOConvertor(List<OrderEntity> responseOrderEntity) 
	{
		List<OrderDTO> orderList=new ArrayList<OrderDTO>();
		OrderDTO orderDTOObj;
		for(OrderEntity orderObj:responseOrderEntity)
		{
			orderDTOObj=orderDTOConvertor(orderObj);
			orderList.add(orderDTOObj);
		}
		return orderList;
	}

	/**
	 * @param responseCustomerEntity
	 * @return CustomerDTO
	 * this method converts CustomerEntity to CustomerDTO object
	 */
	protected CustomerDTO customerDTOConvertor(CustomerEntity responseCustomerEntity) 
	{
		CustomerDTO customerDTOObj=new CustomerDTO();
		customerDTOObj.setCustomerId(responseCustomerEntity.getCustomerId());
		customerDTOObj.setCustomerName(responseCustomerEntity.getCustomerName());
		customerDTOObj.setCreatedAt(responseCustomerEntity.getCreatedAt());
		customerDTOObj.setCustomerMobileNo(responseCustomerEntity.getCustomerMobileNo());
		customerDTOObj.setAddressLine(responseCustomerEntity.getAddressLine());
		customerDTOObj.setCityId(responseCustomerEntity.getCity().getCityId());
		customerDTOObj.setStateId(responseCustomerEntity.getState().getStateId());
		customerDTOObj.setCountryId(responseCustomerEntity.getCountry().getCountryId());
		customerDTOObj.setPostalCode(responseCustomerEntity.getPostalCode());
		customerDTOObj.setUpdatedAt(responseCustomerEntity.getUpdatedAt());
		customerDTOObj.setCityName(responseCustomerEntity.getCity().getCityName());
		customerDTOObj.setStateName(responseCustomerEntity.getState().getStateName());
		customerDTOObj.setCountryName(responseCustomerEntity.getCountry().getCountryName());
		return customerDTOObj;
	}
	/**
	 * @param responseCustomerEntity
	 * @return List<CustomerDTO>
	 * this method converts List<CustomerEntity> to List<CustomerDTO> object
	 */
	protected List<CustomerDTO> customerListDTOConvertor(List<CustomerEntity> responseCustomerEntity) 
	{
		List<CustomerDTO> customerList=new ArrayList<CustomerDTO>();
		CustomerDTO customerDTO=null;
		for(CustomerEntity customerObj:responseCustomerEntity)
		{
			customerDTO=customerDTOConvertor(customerObj);
			customerList.add(customerDTO);
		}
		return customerList;
	}
	/**
	 * @param responseItemEntity
	 * @return ItemMasterDTO
	 * this method converts ItemMasterEntity to ItemMasterDTO object
	 */
	protected ItemMasterDTO itemDTOConvertor(ItemMasterEntity responseItemEntity) 
	{
		ItemMasterDTO itemDTOObj=new ItemMasterDTO();
		itemDTOObj.setItemName(responseItemEntity.getItemName());
		itemDTOObj.setItemID(responseItemEntity.getItemID());
		itemDTOObj.setCategoryID(responseItemEntity.getCategory().getCategoryId());
		itemDTOObj.setCategoryName(responseItemEntity.getCategory().getCategory());
		return itemDTOObj;
	}
	/**
	 * @param responseItemEntity
	 * @return List of ItemMasterDTO
	 *  this method converts List<ItemMasterEntity> to List<ItemMasterDTO> object
	 */
	protected List<ItemMasterDTO> itemListDTOConvertor(List<ItemMasterEntity> responseItemEntity) 
	{
		List<ItemMasterDTO> itemList=new ArrayList<ItemMasterDTO>();
		ItemMasterDTO itemDTO;
		for(ItemMasterEntity itemObj:responseItemEntity)
		{
			itemDTO=itemDTOConvertor(itemObj);
			itemList.add(itemDTO);
		}
		return itemList;
	}

}