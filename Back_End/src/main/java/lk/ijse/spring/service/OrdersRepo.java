package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.OrdersDTO;

import java.util.List;

/**
 * @author : Gathsara
 * created : 10/10/2023 -- 7:11 AM
 **/

public interface OrdersRepo {

    void addOrder(OrdersDTO dto);

    void deleteOrder(String oid);

    List<OrdersDTO> getAllOrder();

    OrdersDTO findOrder(String oid);

    void updateOrder(OrdersDTO dto);
}
