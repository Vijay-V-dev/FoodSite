package com.FoodSite.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.FoodSite.entity.CustomOrderItem;
import com.FoodSite.entity.CustomerCustomized;
import com.FoodSite.entity.OrderEntity;

/**
 * @author Vijay
 * Order Repository for OrderEntity
 */
@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {

	@Query("Select o.customerId as customerId,count(o.customerId) as numberOfOrders from OrderEntity o group by customerId")
	List<CustomerCustomized> getCustomerOrderCount();

	@Query("Select o from OrderEntity o where o.orderDate >:date")
	List<OrderEntity> findByDate(@Param("date")Date date);
	
	@Query("Select  o from OrderEntity o  Left outer join CustomerEntity c on c.customerId=o.customer")
	List<OrderEntity> findOrderDetailsRight();

	@Query("Select o.orderNo as orderNo,i.itemID as itemID,i.itemName as itemName from OrderEntity o join ItemMasterEntity i on o.item=i.orderEntity")
	List<CustomOrderItem> getitemsOfAnOrder(@Param("orderNo")int orderNo);
}
