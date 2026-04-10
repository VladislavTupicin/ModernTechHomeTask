package com.moderntech.ecommerce.payment;

public final class CashOnDelivery implements PaymentMethod {

    @Override
    public String processPayment(double amount) {
        return String.format("Наложенный платёж на сумму %.2f руб. будет оплачен при получении", amount);
    }

    @Override
    public String getMethodName() {
        return "Наложенный платёж";
    }

    @Override
    public boolean isOnline() {
        return false;
    }
}