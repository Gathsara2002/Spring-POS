package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Gathsara
 * created : 10/9/2023 -- 8:43 PM
 **/
public interface CustomerRepo extends JpaRepository<Customer,String> {
}
