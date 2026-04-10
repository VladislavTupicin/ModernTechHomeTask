package com.moderntech.ecommerce.payment;

public interface Payment {
    PaymentResult pay(double amount, PaymentMethod method);

    record PaymentResult(boolean success, String message, PaymentStatus status) {}
}