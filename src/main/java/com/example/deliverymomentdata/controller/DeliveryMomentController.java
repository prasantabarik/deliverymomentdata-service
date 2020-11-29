package com.example.deliverymomentdata.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import com.example.deliverymomentdata.domain.DeliveryMoment;
import com.example.deliverymomentdata.service.DeliveryMomentDataService;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller("/deliveryMoment")
@Validated
public class DeliveryMomentController {

	@Inject
	DeliveryMomentDataService deliveryMomentDataService;
	
	@Post()
	@Operation(description = "Persist given DelivererNumber", summary = "Persist given DelivererNumber")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DeliveryMoment.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error : {\"errors\":[{\"code\":\"TECHNICAL_ERROR\",\"message\":\"Unable to process request.\"}]}", content = @Content(schema = @Schema(implementation = String.class)))
			 })
	public DeliveryMoment createDeliveryMoment(@Body @Valid DeliveryMoment deliveryMoment) throws Exception {
		return deliveryMomentDataService.createDeliveryMoment(deliveryMoment);
	}
	
	
	@Put(value="/{id}")
	@Operation(description = "Update given DelivererNumber", summary = "Update given DelivererNumber")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DeliveryMoment.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error : {\"errors\":[{\"code\":\"TECHNICAL_ERROR\",\"message\":\"Unable to process request.\"}]}", content = @Content(schema = @Schema(implementation = String.class)))
			 })
	public DeliveryMoment updateDeliveryMoment(@PathVariable(value = "id") String id,@Body @Valid DeliveryMoment deliveryMoment) {
		return deliveryMomentDataService.updateDeliveryMoment(id, deliveryMoment);
	}
	
	
	@Delete(value="/{id}")
	@Operation(description = "Delete given DelivererNumber", summary = "Delete given DelivererNumber")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DeliveryMoment.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error : {\"errors\":[{\"code\":\"TECHNICAL_ERROR\",\"message\":\"Unable to process request.\"}]}", content = @Content(schema = @Schema(implementation = String.class)))
			 })
	public String deleteDeliveryMoment(@PathVariable(value = "id") String id) {
		 if(deliveryMomentDataService.deleteDeliveryMoment(id))
			 return "Success";
		 else
			 return "Failed";
	}
}
