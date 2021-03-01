package com.FoodSite.bo;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodSite.FoodProjectApplication;
import com.FoodSite.dto.CustomerDTO;
import com.FoodSite.entity.CustomerEntity;
import com.FoodSite.entity.OrderCustomerCustomized;
import com.FoodSite.exception.NoDataFoundException;
import com.FoodSite.repository.ICustomerRepository;

@Service
public class CustomerBO {

	public static Logger logger=Logger.getLogger(FoodProjectApplication.class);
	@Autowired
	ICustomerRepository customerRepo;

	/**
	 * @param customerEntityObj
	 * @return CustomerEntity
	 * @throws NoDataFoundException
	 * Invokes save method,Which saves the given entity and 
	 * returns the saved entity for further operation
	 * 
	 */
	public CustomerEntity addCustomer(CustomerEntity customerEntityObj) throws NoDataFoundException {
		if(customerEntityObj==null)
		{
			logger.error("Exception--Null Insertion");
			throw new NoDataFoundException("Customer Should Not Be Null....");
		}
		logger.debug("CustomerId-->"+customerEntityObj.getCustomerId());
		CustomerEntity customerEntity=customerRepo.save(customerEntityObj);
		logger.info("Successfully Inserted");
		return customerEntity;
	}

	/**
	 * @param customerID
	 * @return CustomerEntity
	 * @throws NoDataFoundException
	 * Invokes findById method,which retrieves the entity by ID.
	 */
	public CustomerEntity getCustomerByID(int customerID) throws NoDataFoundException {
		if(customerID==0)
		{
			
			logger.error("Exception--Null Insertion..CustomerID is Not Valid...");
			throw new NoDataFoundException("CustomerID is Not Valid....");
		
		
		}
		logger.debug("Entered CustomerID-->"+customerID);
		Optional<CustomerEntity> customerEntityObj = customerRepo.findById(customerID);
		if(!customerEntityObj.isPresent())
		{
			logger.error("Exception--No Customer Present for the given ID...."+customerID);
			throw new NoDataFoundException("No Customer Present for the given  ID... "+customerID);
		}
		logger.info("Successfully Fetched");
		return customerEntityObj.get();
	}

	/**
	 * @return List of CustomerEntity
	 * @throws NoDataFoundException
	 * Invokes FindAll method, Which returns all the Entity of the given type
	 */
	public List<CustomerEntity> getCustomer() throws NoDataFoundException {
		List<CustomerEntity> customerList=customerRepo.findAll();
		if(customerList.isEmpty())
		{
			logger.error("Exception--No Customers Present....");
			throw new NoDataFoundException("No Customer Present....");
		}
		logger.info("Successfully Fetched-->"+customerList.size()+" Records");
		return customerList;
	}

	/**
	 * @param name
	 * @return List of CustomerEntity
	 * @throws NoDataFoundException
	 * Invokes findByName method,which retrieves the entity by Name.
	 */
	public List<CustomerEntity> getCustomerByName(String name) throws NoDataFoundException {
		List<CustomerEntity> customerList=customerRepo.findByName(name);
		logger.debug("Entered Name.."+name);
		if(customerList.isEmpty())
		{
			logger.error("Exception--No Customers Present");
			throw new NoDataFoundException("No Customers Present in the given name...."+name);
		}
		logger.info("Successfully Fetched-->"+customerList.size()+" Records");
		return customerList;
	}
	/**
	 * @param customerID
	 * @return List Of Customized OrderCustomer Interface
	 * @throws NoDataFoundException
	 * Invokes ordersOfCustomer method,which retrieves Orders Of Customer Based on ID.
	 */
	public List<OrderCustomerCustomized> ordersOfCustomer(int customerID) throws NoDataFoundException {
		if(customerID==0)
		{
			logger.error("Exception--Null Insertion..CustomerID is Not Valid...");
			throw new NoDataFoundException("CustomerID is Not Valid....");
		}
		List<OrderCustomerCustomized> orderCustomerCustomized = customerRepo.ordersOfCustomer(customerID);
		logger.debug("CustomerId-->"+customerID);
		if(orderCustomerCustomized.isEmpty())
		{
			logger.error("Exception--No Customers Present");
			throw new NoDataFoundException("No Order Present for the given ID..."+customerID);
		}
		logger.info("Successfully Fetched-->"+orderCustomerCustomized.size()+" Records");
		return orderCustomerCustomized;
	}
	/**
	 * @return List OF CustomerEntity
	 * @throws NoDataFoundException
	 * Invokes FindCustomerWithOrder which Returns all the Customer Who Are Placed Orders
	 * by using InnerJoin Named Queries
	 */
	public List<CustomerEntity> findCustomerWithOrder() throws NoDataFoundException {
		 List<CustomerEntity> responseCustomerEntity=customerRepo.findCustomerWithOrder();
		 if(responseCustomerEntity.isEmpty())
		 {
			 logger.error("Exception--No Customers Present");
			 throw new NoDataFoundException("No Customer Present.....");
		 }
		 logger.info("Successfully Fetched-->"+responseCustomerEntity.size()+" Records");
		 return responseCustomerEntity;
	}

	/**
	 * @return List Of Customized OrderCustomer Interface
	 * @throws NoDataFoundException
	 * Invokes FindCustomerDetailsLeft which Returns all the Customer Who Are Placed Orders
	 * and Customer with No Orders by using Left Outer Join Named Queries
	 */
	public List<OrderCustomerCustomized> findCustomerDetailsLeft() throws NoDataFoundException {
		 List<OrderCustomerCustomized> responseCustomerCustomized=customerRepo.findCustomerDetailsLeft();
		 if(responseCustomerCustomized.isEmpty())
		 {
			 logger.error("Exception--No Customers Present");
			 throw new NoDataFoundException("No Customer Present.....");
		 }
		 logger.info("Successfully Fetched-->"+responseCustomerCustomized.size()+" Records");
		 return responseCustomerCustomized;
	}
	/**
	 * @param customerDTOObj
	 * @return CustomerEntity
	 * @throws NoDataFoundException
	 * Invokes findById method,which retrieves the entity by ID once Updates the Entity
	 * then Invokes save method,Which saves the given entity and 
	 * returns the saved entity for further operation.
	 */
	public CustomerEntity updateCustomer(CustomerDTO customerDTOObj) throws NoDataFoundException
	{
		CustomerEntity customerEntityObj=null;
		if(customerDTOObj.getCustomerId()!=0)
		{
			customerEntityObj=getCustomerByID(customerDTOObj.getCustomerId());
			if(customerDTOObj.getCustomerName()!=null)
			{
				customerEntityObj.setCustomerName(customerDTOObj.getCustomerName());
			}
			if(customerDTOObj.getCustomerMobileNo()!=0)
			{
				customerEntityObj.setCustomerMobileNo(customerDTOObj.getCustomerMobileNo());
			}
			if(customerDTOObj.getCityId()!=0)
			{
				customerEntityObj.setCityId(customerDTOObj.getCityId());
			}
			if(customerDTOObj.getStateId()!=0)
			{
				customerEntityObj.setStateId(customerDTOObj.getStateId());
			}
			if(customerDTOObj.getCountryId()!=0)
			{
				customerEntityObj.setCountryId(customerDTOObj.getCountryId());
			}
			if(customerDTOObj.getAddressLine()!=null)
			{
				customerEntityObj.setAddressLine(customerDTOObj.getAddressLine());
			}
			if(customerDTOObj.getPostalCode()!=0)
			{
				customerEntityObj.setPostalCode(customerDTOObj.getPostalCode());
			}
			customerEntityObj=customerRepo.save(customerEntityObj);
			logger.info("Successfully Updated");
		}
		else
		{
			logger.debug("CustomerID-->"+customerDTOObj.getCustomerId());
			logger.error("Exception--Null Insertion..CustomerID is Not Valid...");
			throw new NoDataFoundException("CustomerID is not Valid.....");
		}
		return customerEntityObj;
	}
	
}
