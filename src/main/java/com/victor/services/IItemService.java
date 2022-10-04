package com.victor.services;

import com.victor.dto.ItemDTO;
import com.victor.dto.ResponseItemsMercadoLibreDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IItemService {
	List<ItemDTO> getItemSortPrices();

}
