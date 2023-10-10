package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    ItemRepo itemRepo;

    @Override
    public void purchaseOrder(OrdersDTO dto) {

        //save order
        if (ordersRepo.existsById(dto.getOid())) {
            throw new RuntimeException(dto.getOid() + " Order is available, please check the Order ID.!");
        }
        System.out.println(dto.toString());
        Orders order = mapper.map(dto, Orders.class);
        ordersRepo.save(order);

        //save order details
        List<OrderDetailsDTO> orderDetails = dto.getOrderDetails();
        for (OrderDetailsDTO detail : orderDetails) {
            String oid = dto.getOid();
            String itemCode = detail.getItemCode();
            int qty = detail.getQty();
            BigDecimal unitPrice = detail.getUnitPrice();
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(oid, itemCode, qty, unitPrice);

            OrderDetails details = mapper.map(orderDetailsDTO, OrderDetails.class);
            orderDetailsRepo.save(details);
        }

        //update item qty
        List<OrderDetailsDTO> orderDetails1 = dto.getOrderDetails();
        for (OrderDetailsDTO detailsDTO : orderDetails1) {
            Item item = itemRepo.findById(detailsDTO.getItemCode()).get();

            //get existing item details
            ItemDTO itemDTO = mapper.map(item, ItemDTO.class);
            String code = itemDTO.getCode();
            String description = itemDTO.getDescription();
            int oldQty = itemDTO.getQtyOnHand();
            double unitPrice = itemDTO.getUnitPrice();

            int boughtQty = detailsDTO.getQty();
            int newQty = oldQty - boughtQty;

            Item item1 = new Item(code, description, newQty, new BigDecimal(newQty));
            itemRepo.save(item1);
        }
    }
}
