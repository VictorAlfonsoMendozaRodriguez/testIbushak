package com.victor.services;

import com.victor.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.util.stream.Collectors.joining;

@Service
public class ItemService implements IItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    static final String END_POINT_ITEMS = "https://api.mercadolibre.com/sites/MLA/search?";
    static final String END_POINT_USER = "https://api.mercadolibre.com/users/?ids=";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<ItemDTO> getItemSortPrices() {

        List<ItemDTO> listItem = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int offset = 0;
            Map<String, String> params = new HashMap<>();
            params.put("category", "MLA1051");
            params.put("sort", "price_asc");
            params.put("offset", offset+"");


            String encodedURL = params.keySet().stream()
                    .map(key -> key + "=" + encodeParam(params.get(key)))
                    .collect(joining("&", END_POINT_ITEMS, ""));

            ResponseItemsMercadoLibreDTO responseTemp = restTemplate.getForObject(encodedURL, ResponseItemsMercadoLibreDTO.class);

            Set<UsersResponseMercadoLibre> userMercadoLibre = new HashSet<>();

            Set<String> sellersId = new HashSet<String>();

            responseTemp.getResults().forEach(itemMercadoLibreDTO -> {
                sellersId.add(itemMercadoLibreDTO.getSellerId().toString());
                if(sellersId.size()==20)
                {
                    userMercadoLibre.addAll(Arrays.asList(restTemplate.getForEntity(END_POINT_USER + String.join(",", sellersId), UsersResponseMercadoLibre[].class).getBody()));
                    sellersId.clear();
                }


            });

            if(sellersId.size()>0)
            {
                userMercadoLibre.addAll(Arrays.asList(restTemplate.getForEntity(END_POINT_USER + String.join(",", sellersId), UsersResponseMercadoLibre[].class).getBody()));
                sellersId.clear();
            }

            responseTemp.getResults().forEach(itemMercadoLibreDTO -> {
                ItemDTO itemTemp = new ItemDTO(itemMercadoLibreDTO,
                        userMercadoLibre.stream()
                                .filter(usersResponseMercadoLibre ->
                                        usersResponseMercadoLibre.getBody().getId().equals(itemMercadoLibreDTO.getSellerId())).findFirst().orElse(new UsersResponseMercadoLibre()).getBody()
                );
                listItem.add(itemTemp);
            });
            offset = offset + 50;

        }
        return listItem;
    }

    private String encodeParam(String value) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error {}", e.getMessage(), e);
        }
        return encoded;
    }

}