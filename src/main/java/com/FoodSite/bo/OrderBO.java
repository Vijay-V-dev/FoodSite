package com.FoodSite.bo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodSite.FoodProjectApplication;
import com.FoodSite.entity.CustomerCustomized;
import com.FoodSite.entity.OrderEntity;
import com.FoodSite.exception.NoDataFoundException;
import com.FoodSite.repository.IOrderRepository;

@Service
public class OrderBO {

	public static Logger logger=Logger.getLogger(FoodProjectApplication.class);
	@Autowired
	IOrderRepository orderRepo;

	/**
	 * @param orderEntityObj
	 * @return OrderEntity
	 * @throws NoDataFoundException
	 * Invokes save method,Which saves the given entity and 
	 * returns the saved entity for further operation
	 */
	public OrderEntity addOrder(OrderEntity orderEntityObj) throws NoDataFoundException {
		if(orderEntityObj==null)
		{
				logger.debug("Debug Message...."+orderEntityObj);
				logger.error("Exception--Order Should Not Be Null....");
				throw new NoDataFoundException("Order Should Not Be Null....");
		}
		logger.debug("OrderNo->"+orderEntityObj.getOrderNo()+"CustomerID->"+orderEntityObj.getCustomerId());
		OrderEntity orderEntity=orderRepo.save(orderEntityObj);
		logger.info("Successfully Inserted");
		return orderEntity;
	}

	/**
	 * @param orderID
	 * @return OrderEntity
	 * @throws NoDataFoundException
	 * Invokes findById method,which retrieves the entity by ID.
	 */
	public OrderEntity getOrderByID(int orderID) throws NoDataFoundException {
		if(orderID==0)
		{
			logger.debug("Entered OrderID-->"+orderID);
			logger.error("Exception--Order Should Not Be Null or Zero....");
			throw new NoDataFoundException("OrderID is Not Valid....");
		}
		logger.debug("Entered OrderID-->"+orderID);
		Optional<OrderEntity> orderEntityObj = orderRepo.findById(orderID);
		if(!orderEntityObj.isPresent())
		{
			logger.error("Exception--No Order Present for the given ID...."+orderID);
			throw new NoDataFoundException("No Order Present for the given ID... "+orderID);
		}
		logger.info("Successfully Fetched");
		return orderEntityObj.get();
	}

	/**
	 * @return List Of OrderEntity
	 * @throws NoDataFoundException
	 * Invokes FindAll method, Which returns all the Entity of the given type
	 */
	public List<OrderEntity> getAllOrders() throws NoDataFoundException {
		List<OrderEntity> orderList=orderRepo.findAll();
		if(orderList.isEmpty())
		{
			logger.error("Exception--No Orders Present....");
			throw new NoDataFoundException("No Orders Present....");
		}
		logger.info("Successfully Fetched-->"+orderList.size()+" Records");
		return orderList;
	}

	/**
	 * @param date
	 * @return List Of OrderEntity
	 * @throws NoDataFoundException
	 * Invokes findByDate method,which retrieves the entity by Date Condition.
	 */
	public List<OrderEntity> findByDate(Date date) throws NoDataFoundException {
		List<OrderEntity> orderList=orderRepo.findByDate(date);
		if(orderList.isEmpty())
		{
			logger.debug("Entered Date-->"+date);
			logger.error("Exception--No Orders Present after the given date");
			throw new NoDataFoundException("No Orders Present after the given date.... "+date);
		}
		logger.debug("Entered Date-->"+date);
		logger.info("Successfully Fetched-->"+orderList.size()+" Records");
		return orderList;
	}
	/**
	 * @return List OF Customized Customer Interface
	 * @throws NoDataFoundException
	 * Invokes getCustomerOrderCount which retrieves all the customer along with number of orders
	 */
	public 	List<CustomerCustomized> getCustomerOrderCount() throws NoDataFoundException {
		List<CustomerCustomized> orderList=orderRepo.getCustomerOrderCount();
		if(orderList.isEmpty())
		{
			logger.error("Exception--No Orders Present after the given date");
			throw new NoDataFoundException("No Orders Present....");
		}
		logger.info("Successfully Fetched-->"+orderList.size()+" Records");
		return orderList;
	}

}
