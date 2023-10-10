package lk.ijse.spring.controller;

import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.service.OrderDetailsService;
import lk.ijse.spring.service.OrderService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Gathsara
 * created : 10/10/2023 -- 7:28 AM
 **/

@RestController
@RequestMapping("/purchase_order")
@CrossOrigin
public class PurchaseOrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @PostMapping
    public ResponseUtil placeOrder(@RequestBody OrdersDTO dto) {

        /*save order*/
        orderService.addOrder(dto);

        /*save order details*/

        /*update item count*/

        return new ResponseUtil("Ok", "Successfully Order Placed", dto);
    }
}
