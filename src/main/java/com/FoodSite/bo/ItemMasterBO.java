package com.FoodSite.bo;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodSite.FoodProjectApplication;
import com.FoodSite.dto.ItemMasterDTO;
import com.FoodSite.entity.ItemMasterEntity;
import com.FoodSite.exception.NoDataFoundException;
import com.FoodSite.repository.ItemMasterRepository;

@Service
public class ItemMasterBO {

	public static Logger logger=Logger.getLogger(FoodProjectApplication.class);
	@Autowired
	ItemMasterRepository itemMasterRepo;

	/**
	 * @param itemMasterObj
	 * @return ItemMasterEntity
	 * @throws NoDataFoundException
	 * Invokes save method,Which saves the given entity and 
	 * returns the saved entity for further operation
	 */
	public ItemMasterEntity addItem(ItemMasterEntity itemMasterObj) throws NoDataFoundException {
		if(itemMasterObj==null)
		{
			logger.debug("Debug Message...."+itemMasterObj);
			logger.error("Exception--Item Should Not Be Null....");
			throw new NoDataFoundException("Item Should Not Be Null....");
		}
		logger.debug("ItemId->"+itemMasterObj.getItemID());
		ItemMasterEntity itemMasterEntity=itemMasterRepo.save(itemMasterObj);
		logger.info("Successfully Inserted");
		return itemMasterEntity;
	}
	
	/**
	 * @param itemID
	 * @return ItemMasterEntity
	 * @throws NoDataFoundException
	 * Invokes findById method,which retrieves the entity by ID.
	 */
	public ItemMasterEntity getItemById(int itemID) throws NoDataFoundException
	{
		if(itemID==0)
		{
			logger.debug("Entered ItemID-->"+itemID);
			logger.error("Exception--Item Should Not Be Null or Zero....");
			throw new NoDataFoundException("ItemID is Not Valid....");
		}
		logger.debug("Entered ItemID-->"+itemID);
		Optional<ItemMasterEntity> itemMasterObj=itemMasterRepo.findById(itemID);
		if(!itemMasterObj.isPresent())
		{
			logger.error("Exception--No Item Present for the given ID...."+itemID);
			throw new NoDataFoundException("No Item Present for the given ID... "+itemID);
		}	
		logger.info("Successfully Fetched");
		return itemMasterObj.get();
	}
	/**
	 * @return List Of ItemMasterEntity
	 * @throws NoDataFoundException
	 * Invokes FindAll method, Which returns all the Entity of the given type 
	 */
	public List<ItemMasterEntity> getItem() throws NoDataFoundException
	{
		List<ItemMasterEntity> itemMasterObj=itemMasterRepo.findAll();
		if(itemMasterObj.isEmpty())
		{
			logger.error("Exception--No Item Present....");
			throw new NoDataFoundException("No Item Present....");
		}
		logger.info("Successfully Fetched-->"+itemMasterObj.size()+" Records");
		return itemMasterObj;
	}
	/**
	 * @param itemDTOObj
	 * @return ItemMasterEntity
	 * @throws NoDataFoundException
	 * Invokes findById method,which retrieves the entity by ID and once Updates the Entity
	 * then Invokes save method,Which saves the given entity and 
	 * returns the saved entity for further operation.
	 */
	public ItemMasterEntity updateItem(ItemMasterDTO itemDTOObj) throws NoDataFoundException
	{
		ItemMasterEntity responseItemMasterEntityObj=null;
		if(itemDTOObj.getItemID()!=0)
		{
			responseItemMasterEntityObj=getItemById(itemDTOObj.getItemID());
			if(itemDTOObj.getItemName()!=null)
			{
				responseItemMasterEntityObj.setItemName(itemDTOObj.getItemName());
			}
			if(itemDTOObj.getCategoryID()!=0)
			{
				responseItemMasterEntityObj.setCategoryID(itemDTOObj.getCategoryID());
			}
			responseItemMasterEntityObj=itemMasterRepo.save(responseItemMasterEntityObj);
			logger.info("Successfully Updated");
		}
		else
		{
			logger.debug("ItemID-->"+itemDTOObj.getItemID());
			logger.error("Exception--Null Insertion..ItemID is Not Valid...");
			throw new NoDataFoundException("ItemID is not Valid.....");
		}
		return responseItemMasterEntityObj;
	}
}
