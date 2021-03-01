package com.FoodSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodSite.entity.ItemMasterEntity;

/**
 * @author Vijay
 * ItemMaster Repository for ItemMasterEntity
 */
@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMasterEntity, Integer> {

}
