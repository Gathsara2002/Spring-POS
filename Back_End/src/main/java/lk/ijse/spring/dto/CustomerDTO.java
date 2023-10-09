package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author : Gathsara
 * created : 10/9/2023 -- 8:20 PM
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private BigDecimal salary;
}
