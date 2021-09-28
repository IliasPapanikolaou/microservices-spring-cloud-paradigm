package com.unipi.ipap.orderservice.controller;

import com.unipi.ipap.orderservice.common.Payment;
import com.unipi.ipap.orderservice.common.TransactionRequest;
import com.unipi.ipap.orderservice.common.TransactionResponse;
import com.unipi.ipap.orderservice.entity.Order;
import com.unipi.ipap.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest){
        return service.saveOrder(transactionRequest);
    }
}
