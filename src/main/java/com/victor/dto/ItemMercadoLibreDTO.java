package com.victor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemMercadoLibreDTO {

    private String id;
    @JsonProperty("site_id")
    private String siteId;

    private String title;
    private Long sellerId;
    @JsonProperty("seller")
    private void unpackSellerId(Map<String, Object> seller ) {
            this.sellerId =  Long.parseLong(seller.get("id").toString());
    }

    private double price;

    @JsonProperty("available_quantity")
    private Double availableQuantity;

    private String permalink;

    private String sellerAddress;

    @JsonProperty("seller_address")
    private void unpackSellerAddress(Map<String, Object> sellerAddress ) {
        this.sellerAddress = sellerAddress.get("address_line").toString();
    }

    private boolean freeShipping;

    private String logisticType;

    @JsonProperty("shipping")
    private void unpackShipping(Map<String, Object> shipping ) {
        this.freeShipping = Boolean.parseBoolean(shipping.get("free_shipping").toString());
        this.logisticType = shipping.get("logistic_type").toString();
    }

    private List<AttributeItemMercadoLibre> attributes;


}
