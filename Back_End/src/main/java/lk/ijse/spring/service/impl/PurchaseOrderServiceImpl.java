package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Gathsara
 * created : 10/10/2023 -- 9:37 AM
 **/

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Override
    public void purchaseOrder(OrdersDTO dto) {

        //save order
        if (!ordersRepo.existsById(dto.getOid())) {
            throw new RuntimeException(dto.getOid() + " Order is not available, please check the Order ID.!");
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
