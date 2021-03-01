package com.FoodSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.FoodSite.entity.CustomerEntity;
import com.FoodSite.entity.OrderCustomerCustomized;

/**
 * @author Vijay
 * Customer Repository for CustomerEntity
 */
@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {

	/**
	 * @param name
	 * @return List of CustomerEntity
	 * Selects all the records from table based on condition
	 */
	@Query("select c from CustomerEntity c where c.customerName like %:name%")
	List<CustomerEntity> findByName(@Param("name")String name);

	/**
	 * @param customerId
	 * @return List of OrderCustomerCustomized
	 * Select Specific records from tables order and customer by making use of joins
	 */
	@Query("Select  c.customerName as customerName,c.cityId as cityId,c.customerId as customerId,o.orderNo as orderNo"
			+ " from  CustomerEntity c  join OrderEntity o on c.customerId=o.customer where c.customerId=:customerId")
	List<OrderCustomerCustomized> ordersOfCustomer(@Param("customerId")int customerId);

	// Inner join to fetch from one table with join
	/**
	 * @return List of CustomerEntity
	 * Selects all the customers who are having orders
	 */
	@Query("Select c from CustomerEntity c inner join  OrderEntity o on c.customerId=o.customer")
	List<CustomerEntity> findCustomerWithOrder();

	// Left outer join
	/**
	 * @return List of OrderCustomerCustomized
	 * Selects all the customers who are having orders and Customers with no Orders
	 */
	@Query("Select  c.customerName as customerName,c.cityId as cityId,c.customerId as customerId,o.orderNo as orderNo"
			+ " from  CustomerEntity c Left outer join OrderEntity o on c.customerId=o.customer")
	List<OrderCustomerCustomized> findCustomerDetailsLeft();

}
