package com.example.deliverymomentdata.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.deliverymomentdata.dao.entity.DeliveryMoment;
import com.example.utils.DeliveryMomentUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.micronaut.context.annotation.Value;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class DeliveryMomentRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryMomentRepository.class);

	@Inject
	MongoClient mongoClient;

	@Value("${app.mongodb.db.name}")
	private String mangodb;

	@Value("${app.mongodb.db.collection.deliveryMoment}")
	private String collectionName;

	protected String getCollectionName() {
		return collectionName;
	}

	private MongoCollection<DeliveryMoment> collection;

	public MongoCollection<DeliveryMoment> getCollection() {
		if (collection == null) {
			collection = mongoClient.getDatabase(mangodb).getCollection(collectionName, DeliveryMoment.class);
		}
		return collection;
	}

	public DeliveryMoment insert(DeliveryMoment deliveryMoment) {
		deliveryMoment.setCreatedBy("user1");
		deliveryMoment.setCreationDateTime(DeliveryMomentUtils.dateToString());
		long counter = Single
				.fromPublisher(mongoClient.getDatabase(mangodb).getCollection(collectionName).countDocuments()).blockingGet();

		LOGGER.info(" mongoClient.getDatabase(mangodb).getCollection(collectionName) Doc Count "+ counter);
		deliveryMoment.setId(""+(counter+1));
		LOGGER.info(" Document to be Inserted " + deliveryMoment);
		
		Document document = convertDeliveryMomentToDocument(deliveryMoment);
		
		InsertOneResult result = Single
				.fromPublisher(mongoClient.getDatabase(mangodb).getCollection(collectionName).insertOne(document))
				.blockingGet();

		LOGGER.info(" Inserted Result " + result.toString());
		return deliveryMoment;
	}

	private Document convertDeliveryMomentToDocument(DeliveryMoment deliveryMoment) {
		Document document = new Document();
		document.append("id", deliveryMoment.getId());
		document.append("storenumber", deliveryMoment.getStorenumber());
		document.append("streamnumber", deliveryMoment.getStreamnumber());
		document.append("deliveryDateTime", deliveryMoment.getDeliveryDateTime());
		document.append("orderDateTime", deliveryMoment.getOrderDateTime());
		document.append("totalOrderQuantity", deliveryMoment.getTotalOrderQuantity());
		document.append("delivererNumber", deliveryMoment.getDelivererNumber());
		document.append("storeOrder", deliveryMoment.getStoreOrder());
		document.append("createdBy", deliveryMoment.getCreatedBy());
		document.append("creationDateTime", deliveryMoment.getCreationDateTime());
		document.append("updatedBy", deliveryMoment.getUpdatedBy());
		document.append("updateDateTime", deliveryMoment.getUpdateDateTime());
		return document;
	}

	public DeliveryMoment update(DeliveryMoment deliveryMoment) {
		LOGGER.info(" Document to be Inserted " + deliveryMoment);
		deliveryMoment.setUpdatedBy("user2");
		deliveryMoment.setUpdateDateTime(DeliveryMomentUtils.dateToString());
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", deliveryMoment);
		UpdateResult result = Flowable.fromPublisher(getCollection().updateMany(Filters.eq("id",deliveryMoment.getId()), updateObject))
				.blockingFirst();
		LOGGER.info(" Matched Count " + result.getMatchedCount());
		LOGGER.info(" Matched Update " + result.getModifiedCount());
		return deliveryMoment;
	}

	public boolean delete(String id) {
		LOGGER.info(" Document to be Deleted " + id);
		DeleteResult result = Single.fromPublisher(getCollection().deleteOne(Filters.eq("id",id))).blockingGet();
		LOGGER.info(" Inserted Result " + result.getDeletedCount());
		return result.wasAcknowledged();
	}

	public Single<List<DeliveryMoment>> filterNameValueMap(final Map<String, Object> filterNameValueMap) {
		List<Bson> filterList = new ArrayList<Bson>();
		filterNameValueMap.forEach((k, v) -> filterList.add(Filters.eq(k, v)));
		return Flowable.fromPublisher(getCollection().find(Filters.and(filterList))).toList();
	}

}
