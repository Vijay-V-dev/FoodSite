package com.FoodSite.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSite.FoodProjectApplication;
import com.FoodSite.bo.CustomerBO;
import com.FoodSite.bo.ItemMasterBO;
import com.FoodSite.bo.OrderBO;
import com.FoodSite.dto.CustomerDTO;
import com.FoodSite.dto.ItemMasterDTO;
import com.FoodSite.dto.OrderDTO;
import com.FoodSite.entity.CategoryEntity;
import com.FoodSite.entity.CityEntity;
import com.FoodSite.entity.CountryEntity;
import com.FoodSite.entity.CustomerCustomized;
import com.FoodSite.entity.CustomerEntity;
import com.FoodSite.entity.ItemMasterEntity;
import com.FoodSite.entity.OrderCustomerCustomized;
import com.FoodSite.entity.OrderEntity;
import com.FoodSite.entity.StateEntity;
import com.FoodSite.exception.NoDataFoundException;

/**
 * @author Vijay
 * Controller FoodService
 */
@RestController
@CrossOrigin
public class FoodService extends BaseService {

	public static Logger logger=Logger.getLogger(FoodProjectApplication.class);
	
	@Autowired
	OrderBO orderBO;
	@Autowired
	CustomerBO customerBO;
	@Autowired
	ItemMasterBO itemMasterBO;
	
	//-------------------------------------------------order-------------------------------------------------------//
	/**
	 * @param orderEntityObj
	 * @return OrderDTO
	 * @throws NoDataFoundException
	 * Invokes addOrder method of orderBo to save the orders
	 */
	@RequestMapping(value="/saveOrder",method=RequestMethod.POST)
	public OrderDTO addOrder(@RequestBody OrderEntity orderEntityObj) throws NoDataFoundException
	{
		logger.info("Entering... CustomerID--> "+orderEntityObj.getCustomerId());
		CustomerEntity customerEntityObj=customerBO.getCustomerByID(orderEntityObj.getCustomerId());		
		orderEntityObj.setOrderNo(orderEntityObj.getOrderNo());
		orderEntityObj.setOrderDate(orderEntityObj.getOrderDate());
		orderEntityObj.setPaymentStatus(orderEntityObj.isPaymentStatus());
		orderEntityObj.setCustomer(customerEntityObj);
		orderEntityObj.setItemMasterEntity(orderEntityObj.getItemMasterEntity());
		OrderEntity responseOrderEntityObj=orderBO.addOrder(orderEntityObj);
		OrderDTO orderDTOObj = orderDTOConvertor(responseOrderEntityObj);
		return orderDTOObj;
	}

	/**
	 * @param orderNO
	 * @return OrderDTO
	 * @throws NoDataFoundException
	 * Invokes getOrderBYId method of orderBo to fetch orders
	 */
	@RequestMapping(value="/getOrder",method=RequestMethod.POST)
	public OrderDTO getOrderByID(@RequestBody int orderNO) throws NoDataFoundException
	{
		OrderEntity responseOrderEntityObj=orderBO.getOrderByID(orderNO);
		OrderDTO orderDTOObj = orderDTOConvertor(responseOrderEntityObj);
		return orderDTOObj;
	}
	
	/**
	 * @return List of OrderDTO
	 * @throws NoDataFoundException
	 * This method Invokes getAllOrders of OrderBO
	 * to Fetch All the Orders from the Database
	 */
	@RequestMapping("/getAllOrder")
	public List<OrderDTO> getAllOrders() throws NoDataFoundException
	{
		List<OrderEntity> orderEntityObj=orderBO.getAllOrders();
		List<OrderDTO> orderList=orderListDTOConvertor(orderEntityObj);
		return orderList;
	}
	/**
	 * @param date
	 * @return List of OrderDTO
	 * @throws NoDataFoundException
	 * This method Invokes findByDate of OrderBO to 
	 * Fetch All the Orders from the Database based of Date Condition
	 */
	@RequestMapping(value="/getByDate",method=RequestMethod.POST)
	public List<OrderDTO> findByDate(@RequestBody Date date) throws NoDataFoundException
	{
		List<OrderEntity> orderEntityObj=orderBO.findByDate(date);
		List<OrderDTO> orderList=orderListDTOConvertor(orderEntityObj);
		return orderList;
	}
	/**
	 * @return List of Customized Customer Entity
	 * @throws NoDataFoundException
	 * This method Invokes getCustomerOrderCount of OrderBO
	 * To fetch Number Of Orders By Each Customer
	 */
	@RequestMapping("/numberOfOrdersByCustomer")
	public List<CustomerCustomized> getCustomerOrderCount() throws NoDataFoundException
	{
		List<CustomerCustomized> customerCustomizedObj=orderBO.getCustomerOrderCount();
		return customerCustomizedObj;
	}
	//----------------------------------customer----------------------------------------------//
	
	/**
	 * @param customerEntityObj
	 * @return CustomerDTO
	 * @throws NoDataFoundException
	 * Invokes addCustomer method of CustomerBo to save the Customers
	 */
	@RequestMapping(value="/saveCustomer",method=RequestMethod.POST )
	public CustomerDTO addCustomer(@RequestBody CustomerEntity customerEntityObj) throws NoDataFoundException {
		
		CityEntity cityEntity=new CityEntity();
		cityEntity.setCityId(customerEntityObj.getCityId());
		customerEntityObj.setCity(cityEntity);
		
		StateEntity stateEntity=new StateEntity();
		stateEntity.setStateId(customerEntityObj.getStateId());
		customerEntityObj.setState(stateEntity);
		
		CountryEntity countryEntity=new CountryEntity();
		countryEntity.setCountryId(customerEntityObj.getCountryId());
		customerEntityObj.setCountry(countryEntity);
		
		CustomerEntity responseCustomerObj=customerBO.addCustomer(customerEntityObj);
		CustomerDTO customerDTO=customerDTOConvertor(responseCustomerObj);
		return customerDTO;
	}
	
	/**
	 * @param customerId
	 * @return CustomerDTO
	 * @throws NoDataFoundException
	 * This method Invokes getCustomerByID of CustomerB
	 * to Fetches the Customer From the database
	 */
	@RequestMapping(value="/getCustomer")
	public CustomerDTO getCustomerByID(@RequestParam int customerId) throws NoDataFoundException
	{
		CustomerEntity responseCustomerObj=customerBO.getCustomerByID(customerId);
		CustomerDTO customerDTO=customerDTOConvertor(responseCustomerObj);
		return customerDTO;	
	}
	/**
	 * @param name
	 * @return List of customerDTO 
	 * @throws NoDataFoundException
	 * This method Invokes getCustomerByName of CustomerBO 
	 * to Fetch All the customer From Customer Table By Name Condition
	 */
	@RequestMapping(value="/getCustomerByName")
	public List<CustomerDTO> getCustomerByName(@RequestParam String name) throws NoDataFoundException
	{
		List<CustomerEntity> responseCustomerObj=customerBO.getCustomerByName(name);
		List<CustomerDTO> customerDTO=customerListDTOConvertor(responseCustomerObj);
		return customerDTO;	
	}
	/**
	 * @param name
	 * @return List of customerDTO 
	 * @throws NoDataFoundException
	 * This method Invokes getCustomer of CustomerBO 
	 * to Fetch All the customer From Customer Table
	 */
	@RequestMapping("/getAllCustomer")
	public List<CustomerDTO> getCustomer() throws NoDataFoundException
	{
		List<CustomerEntity> customerEntityObj=customerBO.getCustomer();
		List<CustomerDTO> customerList=customerListDTOConvertor(customerEntityObj);
		return customerList;
	}
	
	/**
	 * @param customerDTOObj
	 * @return CustomerDTO
	 * @throws NoDataFoundException
	 * This method Invokes updateCustomer of CustomerBO 
	 * to Update Customer Details by fetching Customer From Database
	 */
	@RequestMapping(value="/updateCustomer",method=RequestMethod.POST )
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTOObj) throws NoDataFoundException
	{
		CustomerEntity responseCustomerObj=customerBO.updateCustomer(customerDTOObj);
		CustomerDTO customerDTO=customerDTOConvertor(responseCustomerObj);
		return customerDTO;
	}
	
	/**
	 * @param customerId
	 * @return List of Customized OrderCustomer 
	 * @throws NoDataFoundException
	 * This method Invokes ordersOfCustomer of CustomerBO 
	 * to Fetches all the orders of required Customer
	 */
	@RequestMapping(value="/ordersOfACustomer",method=RequestMethod.POST)
	public  List<OrderCustomerCustomized> ordersOfCustomer(@RequestBody int customerId) throws NoDataFoundException
	{
		List<OrderCustomerCustomized> orderCustomerCustomized=customerBO.ordersOfCustomer(customerId);
		return orderCustomerCustomized;
	}
	
	/**
	 * @return List of CustomerDTO
	 * @throws NoDataFoundException
	 * Inner Join..This method Invokes findCustomerWithOrder of CustomerBO 
	 * to Fetching All Customer who are having Order History 
	 */
	@RequestMapping("/customerWithOrder")
	public  List<CustomerDTO> getCustomerWithOrders() throws NoDataFoundException
	{
		List<CustomerEntity> customerEntityObj=customerBO.findCustomerWithOrder();
		List<CustomerDTO> customerList=customerListDTOConvertor(customerEntityObj);
		return customerList;
	}
	/**
	 * @return
	 * @throws NoDataFoundException
	 * Left Outer Join..This method Invokes findCustomerDetailsLeft of CustomerBO 
	 * to Fetch Customer without order
	 */
	@RequestMapping("/customerWithOutOrder")
	public  List<OrderCustomerCustomized> getCustomerDetailsLeft() throws NoDataFoundException
	{
		List<OrderCustomerCustomized> customerCustomizedObj=customerBO.findCustomerDetailsLeft();
		return customerCustomizedObj;
	}
	
	//-----------------------------------------------------Item-----------------------------------------------------//
	
	/**
	 * @param itemMasterObj
	 * @return ItemMasterDTO
	 * @throws NoDataFoundException
	 * Invokes addItem of itemMasterBO to save items into the Database
	 */
	@RequestMapping(value="/saveItem",method = RequestMethod.POST)
	public ItemMasterDTO addItem(@RequestBody ItemMasterEntity itemMasterObj) throws NoDataFoundException
	{
		ItemMasterEntity itemMasterEntityObj=new ItemMasterEntity();
		itemMasterEntityObj.setItemName(itemMasterObj.getItemName());
		itemMasterEntityObj.setItemID(itemMasterObj.getItemID());
		
		CategoryEntity categoryEntityObj=new CategoryEntity();
		categoryEntityObj.setCategoryId(itemMasterObj.getCategoryID());
		itemMasterEntityObj.setCategory(categoryEntityObj);
		
		ItemMasterEntity responseItemMasterEntityObj=itemMasterBO.addItem(itemMasterEntityObj);
		ItemMasterDTO itemDTO=itemDTOConvertor(responseItemMasterEntityObj);
		return itemDTO;
	}
	
	/**
	 * @param itemId
	 * @return ItemMasterDTO
	 * @throws NoDataFoundException
	 * This method Invokes getItemById of itemMasterBO Fetch the Item Based On the ItemID
	 */
	@RequestMapping("/getItem")
	public ItemMasterDTO getItemByID(@RequestParam(name="itemId") int itemId) throws NoDataFoundException
	{
		ItemMasterEntity responseItemMasterEntityObj=itemMasterBO.getItemById(itemId);
		ItemMasterDTO itemDTO=itemDTOConvertor(responseItemMasterEntityObj);
		return itemDTO;	
	}
	
	/**
	 * @return List of ItemMasterDTO
	 * @throws NoDataFoundException
	 * This method Invokes getItem of itemMasterBO 
	 * to Fetch All the ITems From the Database
	 */
	@RequestMapping("/getAllItem")
	public List<ItemMasterDTO> getAllItem() throws NoDataFoundException
	{
		List<ItemMasterEntity> itemMasterEntityObj=itemMasterBO.getItem();
		List<ItemMasterDTO> itemList=itemListDTOConvertor(itemMasterEntityObj) ;
		return itemList;
	}
	/**
	 * @param itemDTOObj
	 * @return ItemMasterDTO
	 * @throws NoDataFoundException
	 * This method Invokes updateItem of itemMasterBO 
	 * to Update Item Details by fetching Item From Database
	 */
	@RequestMapping(value="/updateItem",method = RequestMethod.POST)
	public ItemMasterDTO updateItem(@RequestBody ItemMasterDTO itemDTOObj) throws NoDataFoundException
	{
		ItemMasterEntity responseItemMasterEntityObj=itemMasterBO.updateItem(itemDTOObj);
		ItemMasterDTO itemDTO=itemDTOConvertor(responseItemMasterEntityObj);
		return itemDTO;
		
	}
}

























