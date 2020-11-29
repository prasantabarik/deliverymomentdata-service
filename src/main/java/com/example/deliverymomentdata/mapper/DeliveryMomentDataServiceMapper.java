package com.example.deliverymomentdata.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "jsr330", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DeliveryMomentDataServiceMapper {

	List<com.example.deliverymomentdata.domain.DeliveryMoment> mapperDeliveryMomentEntityToDomain(
			List<com.example.deliverymomentdata.dao.entity.DeliveryMoment> deliveryMomentEntity);

	List<com.example.deliverymomentdata.dao.entity.DeliveryMoment> mapperDeliveryMomentDomanToEntity(
			List<com.example.deliverymomentdata.domain.DeliveryMoment> deliveryMomentDomain);

	@Mappings({ @Mapping(source = "storenumber", target = "storenumber"),
			@Mapping(source = "streamnumber", target = "streamnumber"),
			@Mapping(source = "deliveryDateTime", target = "deliveryDateTime"),
			@Mapping(source = "orderDateTime", target = "orderDateTime"),
			@Mapping(source = "totalOrderQuantity", target = "totalOrderQuantity"),
			@Mapping(source = "delivererNumber", target = "delivererNumber"),
			@Mapping(source = "storeOrder", target = "storeOrder")
	})
	com.example.deliverymomentdata.domain.DeliveryMoment mapperDeliveryMomentEntityToDomain(
			com.example.deliverymomentdata.dao.entity.DeliveryMoment deliveryMomentEntity);

	@Mappings({ @Mapping(source = "storenumber", target = "storenumber"),
			@Mapping(source = "streamnumber", target = "streamnumber"),
			@Mapping(source = "deliveryDateTime", target = "deliveryDateTime"),
			@Mapping(source = "orderDateTime", target = "orderDateTime"),
			@Mapping(source = "totalOrderQuantity", target = "totalOrderQuantity"),
			@Mapping(source = "delivererNumber", target = "delivererNumber"),
			@Mapping(source = "storeOrder", target = "storeOrder"),
			@Mapping(target = "createdBy", ignore = true),
		    @Mapping(target = "creationDateTime", ignore = true),
		    @Mapping(target = "updatedBy", ignore = true),
		    @Mapping(target = "updateDateTime", ignore = true)
	
			
	})
	com.example.deliverymomentdata.dao.entity.DeliveryMoment mapperDeliveryMomentDomanToEntity(
			com.example.deliverymomentdata.domain.DeliveryMoment deliveryMomentDomain);

	List<com.example.deliverymomentdata.domain.StoreOrder> mapperStoreOrderEntityToDomain(
			List<com.example.deliverymomentdata.dao.entity.StoreOrder> storeOrderEntity);

	List<com.example.deliverymomentdata.dao.entity.StoreOrder> mapperStoreOrderDomanToEntity(
			List<com.example.deliverymomentdata.domain.StoreOrder> storeOrderDomain);

	com.example.deliverymomentdata.domain.StoreOrder mapperStoreOrderEntityToDomain(
			com.example.deliverymomentdata.dao.entity.StoreOrder storeOrderEntity);

	com.example.deliverymomentdata.dao.entity.StoreOrder mapperStoreOrderDomanToEntity(
			com.example.deliverymomentdata.domain.StoreOrder storeOrderDomain);
}
