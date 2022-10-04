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
public class ItemDTO {

    private Long sellerId;

    private String sellerName;

    private String brand;

    private boolean freeShipping;

    private String logisticsType;

    private String placeOperationSelle;

    private String itemCondition;

    private String priceRange;

    public ItemDTO(ItemMercadoLibreDTO itemMercadoLibreDTO, UserMercadoLibre userMercadoLibre) {
        this.sellerId = itemMercadoLibreDTO.getSellerId();
        this.sellerName = userMercadoLibre.getNickName();
        this.brand = itemMercadoLibreDTO
                .getAttributes()
                .stream()
                .filter(attributeItemMercadoLibre ->
                        attributeItemMercadoLibre.getName().equals("Marca")).findFirst().orElse(new AttributeItemMercadoLibre()).getValue_name();
        this.freeShipping = itemMercadoLibreDTO.isFreeShipping();
        this.logisticsType = itemMercadoLibreDTO.getLogisticType();
        this.itemCondition = itemMercadoLibreDTO
                .getAttributes()
                .stream()
                .filter(attributeItemMercadoLibre ->
                        attributeItemMercadoLibre.getName().equals("Condición del ítem")).findFirst().orElse(new AttributeItemMercadoLibre()).getValue_name();
        this.priceRange = itemMercadoLibreDTO.getPrice()+" ";


    }
}
