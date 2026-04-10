package com.moderntech.ecommerce.payment;

public final class CreditCardPayment implements PaymentMethod {
    private final String cardNumber;
    private final String cardHolder;
    private final String expiryDate;

    public CreditCardPayment(String cardNumber, String cardHolder, String expiryDate) {
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
    }

    private String maskCardNumber(String number) {
        if (number == null || number.length() < 4) {
            return "****";
        }
        return "****" + number.substring(number.length() - 4);
    }

    @Override
    public String processPayment(double amount) {
        return String.format("Оплата картой %s (держатель: %s, срок: %s) на сумму %.2f руб. прошла успешно",
                cardNumber, cardHolder, expiryDate, amount);
    }

    @Override
    public String getMethodName() {
        return "Банковская карта";
    }

    @Override
    public boolean isOnline() {
        return true;
    }
}