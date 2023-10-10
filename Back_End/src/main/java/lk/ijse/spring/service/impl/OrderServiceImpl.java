package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author : Gathsara
 * created : 10/10/2023 -- 7:16 AM
 **/

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    OrdersRepo ordersRepo;

    @Override
    public void addOrder(OrdersDTO dto) {
        if (ordersRepo.existsById(dto.getOid())) {
            throw new RuntimeException(dto.getOid() + " is already available, please insert a new Order ID");
        }
        Orders map = mapper.map(dto, Orders.class);
        ordersRepo.save(map);
    }

    @Override
    public void deleteOrder(String oid) {
        if (!ordersRepo.existsById(oid)) {
            throw new RuntimeException(oid + " Order is not available, please check the Order ID before delete.!");
        }
        ordersRepo.deleteById(oid);
    }

    @Override
    public List<OrdersDTO> getAllOrder() {
        List<Orders> all = ordersRepo.findAll();
        return mapper.map(all, new TypeToken<List<OrdersDTO>>() {
        }.getType());
    }

    @Override
    public OrdersDTO findOrder(String oid) {
        if (!ordersRepo.existsById(oid)) {
            throw new RuntimeException(oid + " Order is not available, please check the Order ID.!");
        }
        Orders orders = ordersRepo.findById(oid).get();
        return mapper.map(orders, OrdersDTO.class);
    }

    @Override
    public void updateOrder(OrdersDTO dto) {
        if (!ordersRepo.existsById(dto.getOid())) {
            throw new RuntimeException(dto.getOid() + " Order is not available, please check the Order ID before update.!");
        }
        Orders map = mapper.map(dto, Orders.class);
        ordersRepo.save(map);
    }
}
