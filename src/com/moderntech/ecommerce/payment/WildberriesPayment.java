package com.moderntech.ecommerce.payment;

public class WildberriesPayment implements Payment {
    private final String marketplaceName = "WILDBERRIES";

    @Override
    public PaymentResult pay(double amount, PaymentMethod method) {
        System.out.println("\n--- ОПЛАТА ЧЕРЕЗ " + marketplaceName + " ---");
        System.out.println("Способ оплаты: " + method.getMethodName());
        System.out.println("Сумма: " + amount + " руб.");

        String result = method.processPayment(amount);
        System.out.println(result);

        if (method.isOnline()) {
            return new PaymentResult(true, "Оплата через " + marketplaceName + " успешно завершена",
                    PaymentStatus.SUCCESS);
        } else {
            return new PaymentResult(true, "Заказ оформлен с оплатой при получении через " + marketplaceName,
                    PaymentStatus.PROCESSING);
        }
    }
}