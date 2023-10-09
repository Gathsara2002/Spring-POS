package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Gathsara
 * created : 10/9/2023 -- 8:45 PM
 **/
public interface OrdersRepo extends JpaRepository<Orders, String> {
}
