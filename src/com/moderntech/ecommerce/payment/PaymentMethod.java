package com.moderntech.ecommerce.payment;

public sealed interface PaymentMethod
        permits CreditCardPayment, DigitalWalletPayment, CashOnDelivery {

    String processPayment(double amount);  // Убрали Order
    String getMethodName();
    boolean isOnline();
}