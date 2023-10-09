package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Gathsara
 * created : 10/9/2023 -- 8:44 PM
 **/
public interface ItemRepo extends JpaRepository<Item, String> {
}
