package com.unipi.ipap.orderservice.service;

import com.unipi.ipap.orderservice.common.Payment;
import com.unipi.ipap.orderservice.common.TransactionRequest;
import com.unipi.ipap.orderservice.common.TransactionResponse;
import com.unipi.ipap.orderservice.entity.Order;
import com.unipi.ipap.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class OrderService {

    private final String PAYMENT_SERVICE_URL = "lb://payment-service/payment/doPayment";

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest) {
        String response = "";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        // Rest API call
        Payment paymentResponse = restTemplate.postForObject(PAYMENT_SERVICE_URL, payment, Payment.class);

        response = paymentResponse.getPaymentStatus().equals("success") ?
                "payment processing successful and order placed" :
                "there is a failure in payment api, order added to card";
        repository.save(order);
        return TransactionResponse.builder()
                .order(order)
                .amount(paymentResponse.getAmount())
                .transactionId(paymentResponse.getTransactionId())
                .message(response)
                .build();

    }


}