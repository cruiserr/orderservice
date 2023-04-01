package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.OrderUpdate;
import edu.iu.c322.orderservice.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository repository;

    // this is bad it is binding this class the customerRepository so instead we use spring dependency injection
    /*
    public CustomerController() {
        this.repository = new CustomerRepository();
    }
     */

    //this is dpeendency injection
    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/{id}")
    public Order findByCustomerId(@PathVariable int id){

        return repository.findByCustomer(id);

    }

    //@valid tells spring to ensure validations are checked, our validation is currently in the customer class
    @PostMapping
    public int create(@Valid @RequestBody Order order){
        return repository.create(order);
    }

    // PUT lcoalhost:8080.customers/2
    @PutMapping("/return")
    public void update(@Valid @RequestBody OrderUpdate order){
        repository.update(order);
    }

    //path variables shown in url
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        repository.delete(id);
    }


}