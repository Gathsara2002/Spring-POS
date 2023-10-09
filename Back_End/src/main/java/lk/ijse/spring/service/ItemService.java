package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.ItemDTO;

import java.util.List;

/**
 * @author : Gathsara
 * created : 10/9/2023 -- 8:50 PM
 **/

public interface ItemService {

    void addItem(ItemDTO dto);

    void deleteItem(String code);

    List<ItemDTO> getAllItem();

    ItemDTO findItem(String id);

    void updateItem(ItemDTO dto);

}
