package com.example.deliverymomentdata.dao;

import javax.inject.Inject;

import com.example.deliverymomentdata.dao.entity.OrderNumberSequence;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.micronaut.context.annotation.Value;
import io.reactivex.Flowable;

public class OrderNumberSeqRepository {

	@Inject
	MongoClient mongoClient;

	@Value("${app.mongodb.db.name}")
	private String mangodb;

	@Value("${app.mongodb.db.collection.sequences}")
	private String collectionName;

	protected String getCollectionName() {
		return collectionName;
	}

	private MongoCollection<OrderNumberSequence> collection;

	public MongoCollection<OrderNumberSequence> getCollection() {
		if (collection == null) {
			collection = mongoClient.getDatabase(mangodb).getCollection(collectionName, OrderNumberSequence.class);
		}
		return collection;
	}

	public int getNextSequence() {
		
		OrderNumberSequence orderNumberSequence = Flowable
				.fromPublisher(getCollection().find(Filters.eq("_id", "orderNumber")).limit(1)).firstElement()
				.blockingGet();
		int currentSeq = orderNumberSequence.getSeq();
		int nextSeq = currentSeq + 1;
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("seq", nextSeq);
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);
		UpdateResult result = Flowable
				.fromPublisher(getCollection().updateOne(Filters.eq("_id", "orderNumber"), updateObject))
				.blockingFirst();
		return nextSeq;
	}

}
