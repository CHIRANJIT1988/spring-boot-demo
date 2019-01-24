package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.config.ValidateModel;
import com.txtbravo.txtbravo.entity.Order;
import com.txtbravo.txtbravo.model.User;
import com.txtbravo.txtbravo.service.OrderService;
import com.txtbravo.txtbravo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/user/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Order> findAll(@PageableDefault(size = 20) Pageable pageable)
    {
        return orderService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id)
    {
        Order order = orderService.findById(id);

        if(order == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@Valid @RequestBody Order order, BindingResult bindingResults)
    {
        if(!bindingResults.hasErrors())
        {
            User user = userService.findById(order.getUser().getId());

            if(user != null)
            {
                return orderService.save(order);
            }

            return ResponseEntity.notFound().build();
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Order _order, BindingResult bindingResults)
    {

        if(!bindingResults.hasErrors())
        {
            Order order = orderService.findById(id);

            if(order == null)
            {
                return ResponseEntity.notFound().build();
            }

            //product.setProductName(_product.getProductName());
            //product.setProductDescription(_product.getProductDescription());

            return ResponseEntity.ok().body(orderService.save(order));
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }
}