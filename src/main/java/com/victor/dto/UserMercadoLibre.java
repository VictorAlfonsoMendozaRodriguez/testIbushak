package com.victor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMercadoLibre {
    private Long id;
    @JsonProperty("nickname")
    private String nickName;
    @JsonProperty("site_id")
    private String siteId;
}
