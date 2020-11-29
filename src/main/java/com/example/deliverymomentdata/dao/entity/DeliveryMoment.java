package com.example.deliverymomentdata.dao.entity;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class DeliveryMoment {
	
	@BsonProperty("_id")
	@BsonId
	private ObjectId _id;
	
	@BsonProperty("id")
	private String id;
	
	private int storenumber;

	private int streamnumber;
	
	private String deliveryDateTime;
	
	private String orderDateTime;
	
	private String totalOrderQuantity;
	
	private int delivererNumber;
	
	private List<StoreOrder> storeOrder;
	
	private String createdBy;
	
	private String creationDateTime;
	
	private String updatedBy;
	
	private String updateDateTime;
	
	public ObjectId get_id() {
		return _id;
	}
	
	public String getId() {
		return id;
	}
	
	@BsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}
	
	@BsonProperty("_id")
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	public List<StoreOrder> getStoreOrder() {
		return storeOrder;
	}

	public void setStoreOrder(List<StoreOrder> storeOrder) {
		this.storeOrder = storeOrder;
	}

	public int getStorenumber() {
		return storenumber;
	}

	public void setStorenumber(int storenumber) {
		this.storenumber = storenumber;
	}
	
	public int getStreamnumber() {
		return streamnumber;
	}

	public String getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public String getTotalOrderQuantity() {
		return totalOrderQuantity;
	}

	public int getDelivererNumber() {
		return delivererNumber;
	}

	

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreationDateTime() {
		return creationDateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setStreamnumber(int streamnumber) {
		this.streamnumber = streamnumber;
	}

	public void setDeliveryDateTime(String deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public void setTotalOrderQuantity(String totalOrderQuantity) {
		this.totalOrderQuantity = totalOrderQuantity;
	}

	public void setDelivererNumber(int delivererNumber) {
		this.delivererNumber = delivererNumber;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	@Override
	public String toString() {
		return "DeliveryMoment [_id=" + _id + ", id=" + id + ", storenumber=" + storenumber + ", streamnumber="
				+ streamnumber + ", deliveryDateTime=" + deliveryDateTime + ", orderDateTime=" + orderDateTime
				+ ", totalOrderQuantity=" + totalOrderQuantity + ", delivererNumber=" + delivererNumber
				+ ", storeOrder=" + storeOrder + ", createdBy=" + createdBy + ", creationDateTime=" + creationDateTime
				+ ", updatedBy=" + updatedBy + ", updateDateTime=" + updateDateTime + "]";
	}
}
