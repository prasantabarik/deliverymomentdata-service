
package com.example.deliverymomentdata.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "orderNumber",
    "warehouseNumber"
})
public class StoreOrder {

    @JsonProperty("orderNumber")
    @JsonPropertyDescription("")
    private Integer orderNumber;
   
    @JsonProperty("warehouseNumber")
    @JsonPropertyDescription("")
    private Integer warehouseNumber;

    @JsonProperty("orderNumber")
    public Integer getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public StoreOrder withOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    @JsonProperty("warehouseNumber")
    public Integer getWarehouseNumber() {
        return warehouseNumber;
    }

    @JsonProperty("warehouseNumber")
    public void setWarehouseNumber(Integer warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public StoreOrder withWarehouseNumber(Integer warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderNumber).append(warehouseNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StoreOrder) == false) {
            return false;
        }
        StoreOrder rhs = ((StoreOrder) other);
        return new EqualsBuilder().append(orderNumber, rhs.orderNumber).append(warehouseNumber, rhs.warehouseNumber).isEquals();
    }

}
