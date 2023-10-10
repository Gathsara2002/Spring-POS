package lk.ijse.spring.service;

import lk.ijse.spring.dto.OrdersDTO;

import java.util.List;

/**
 * @author : Gathsara
 * created : 10/10/2023 -- 7:15 AM
 **/

public interface OrderService {

    void addOrder(OrdersDTO dto);

    void deleteOrder(String oid);

    List<OrdersDTO> getAllOrder();

    OrdersDTO findOrder(String oid);

    void updateOrder(OrdersDTO dto);

}
