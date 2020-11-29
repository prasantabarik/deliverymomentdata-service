package com.example.deliverymomentdata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.deliverymomentdata.dao.DeliveryMomentRepository;
import com.example.deliverymomentdata.dao.OrderNumberSeqRepository;
import com.example.deliverymomentdata.domain.DeliveryMoment;
import com.example.deliverymomentdata.domain.StoreOrder;
import com.example.deliverymomentdata.exception.DuplicateKeyException;
import com.example.deliverymomentdata.mapper.DeliveryMomentDataServiceMapper;

public class DeliveryMomentDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryMomentDataService.class);

	@Inject
	DeliveryMomentDataServiceMapper mapper;

	@Inject
	DeliveryMomentRepository deliveryMomentRepository;

	@Inject
	OrderNumberSeqRepository orderNumberSeqRepository;

	public DeliveryMoment createDeliveryMoment(DeliveryMoment deliveryMoment) throws DuplicateKeyException {

		Map<String, Object> filterNameValueMap = new HashMap<String, Object>();
		filterNameValueMap.put("storenumber", new Integer(deliveryMoment.getStorenumber()));
		filterNameValueMap.put("streamnumber", new Integer(deliveryMoment.getStreamnumber()));
		filterNameValueMap.put("delivererNumber", new Integer(deliveryMoment.getDelivererNumber()));
		filterNameValueMap.put("deliveryDateTime", deliveryMoment.getDeliveryDateTime());
		filterNameValueMap.put("orderDateTime", deliveryMoment.getOrderDateTime());
		LOGGER.info(" Filter Value Map " + filterNameValueMap);

		List<com.example.deliverymomentdata.dao.entity.DeliveryMoment> deliveryMomentList = deliveryMomentRepository
				.filterNameValueMap(filterNameValueMap).blockingGet();
		LOGGER.info(" deliveryMomentList " + deliveryMomentList);
		if (!deliveryMomentList.isEmpty() && deliveryMomentList.size() > 0) {
			LOGGER.info(" Duplicate Exception ");
			throw new DuplicateKeyException("Duplicate Delivery Moment");
		}

		deliveryMoment.getStoreOrder().forEach(s -> s.setOrderNumber(orderNumberSeqRepository.getNextSequence()));

		com.example.deliverymomentdata.dao.entity.DeliveryMoment deliveryMomentEntity = mapper
				.mapperDeliveryMomentDomanToEntity(deliveryMoment);

		deliveryMomentEntity = deliveryMomentRepository.insert(deliveryMomentEntity);

		DeliveryMoment insertedDeliveryMoment = mapper.mapperDeliveryMomentEntityToDomain(deliveryMomentEntity);

		return insertedDeliveryMoment;
	}

	public DeliveryMoment updateDeliveryMoment(String id, DeliveryMoment deliveryMoment) {
		deliveryMoment.setId(id);
		deliveryMoment.getStoreOrder().stream().forEach(s -> {
			if(s.getOrderNumber() == null)
				s.setOrderNumber(orderNumberSeqRepository.getNextSequence());
		});
		
		com.example.deliverymomentdata.dao.entity.DeliveryMoment deliveryMomentEntity = mapper
				.mapperDeliveryMomentDomanToEntity(deliveryMoment);
		deliveryMomentEntity = deliveryMomentRepository.update(deliveryMomentEntity);
		return mapper.mapperDeliveryMomentEntityToDomain(deliveryMomentEntity);
	}

	public boolean deleteDeliveryMoment(String id) {
		return deliveryMomentRepository.delete(id);
	}

}
