package com.unipi.ipap.paymentservice.service;

import com.unipi.ipap.paymentservice.entity.Payment;
import com.unipi.ipap.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment doPayment(Payment payment) {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    // Mock payment status
    public String paymentProcessing() {
        // Api should be 3rd party payment gateway (paypal, etc... )
        return new Random().nextBoolean() ? "success" : "failure";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) {
        return repository.findByOrderId(orderId);
    }
}