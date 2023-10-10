package lk.ijse.spring.service.impl;

import lk.ijse.spring.config.WebRootConfig;
import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebRootConfig.class})
@ExtendWith(SpringExtension.class)
@Transactional
class PurchaseOrderServiceImplTest {

    @Autowired
    ModelMapper mapper;

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @org.junit.jupiter.api.Test
    void purchaseOrder(OrdersDTO dto) {

        //save order
        if (ordersRepo.existsById(dto.getOid())) {
            throw new RuntimeException(dto.getOid() + " Order is available, please check the Order ID.!");
        }
        Orders order = mapper.map(dto, Orders.class);
        ordersRepo.save(order);

        //save order details
        List<OrderDetailsDTO> orderDetails = dto.getOrderDetails();
        for (OrderDetailsDTO detail : orderDetails) {
            String oid = detail.getOid();
            String itemCode = detail.getItemCode();
            int qty = detail.getQty();
            BigDecimal unitPrice = detail.getUnitPrice();
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(oid, itemCode, qty, unitPrice);

            OrderDetails details = mapper.map(orderDetailsDTO, OrderDetails.class);
            orderDetailsRepo.save(details);
        }

    }
}