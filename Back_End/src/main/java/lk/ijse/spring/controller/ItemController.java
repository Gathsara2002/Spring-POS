package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Gathsara
 * created : 10/9/2023 -- 9:26 PM
 **/

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService service;

    @PostMapping
    public ResponseUtil addItem(ItemDTO dto) {
        service.addItem(dto);
        return new ResponseUtil("Ok", "Successfully Added", dto);
    }

    @DeleteMapping(params = {"id"})
    public ResponseUtil deleteItem(String code) {
        service.deleteItem(code);
        return new ResponseUtil("Ok", "Successfully Deleted", code);
    }

    @GetMapping
    public ResponseUtil getAllItem() {
        return new ResponseUtil("Ok", "Successfully Loaded", service.getAllItem());
    }

    @GetMapping(params = {"id"})
    public ResponseUtil findItem(String code) {
        return new ResponseUtil("Ok", "Successful", service.findItem(code));
    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO dto) {
        service.updateItem(dto);
        return new ResponseUtil("Ok", "Successfully Updated", dto);
    }
}
