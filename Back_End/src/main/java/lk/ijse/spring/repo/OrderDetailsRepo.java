package lk.ijse.spring.repo;

import lk.ijse.spring.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Gathsara
 * created : 10/9/2023 -- 8:46 PM
 **/
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, String> {
}
