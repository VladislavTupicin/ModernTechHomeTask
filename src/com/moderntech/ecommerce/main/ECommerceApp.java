package com.moderntech.ecommerce.main;

import com.moderntech.ecommerce.models.*;
import com.moderntech.ecommerce.payment.*;
import com.moderntech.ecommerce.enums.OrderStatus;
import com.moderntech.ecommerce.enums.ProductCategory;

public class ECommerceApp {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         ИНТЕРНЕТ-МАГАЗИН - КОНСОЛЬНОЕ ПРИЛОЖЕНИЕ            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        // ============================================================
        // 1. Создание каталога товаров (4-5 товаров)
        // ============================================================
        System.out.println("\n=== ШАГ 1: КАТАЛОГ ТОВАРОВ ===\n");

        Product product1 = new Product(1, "iPhone 15 Pro", 99900, 10, ProductCategory.SMARTPHONE);
        Product product2 = new Product(2, "MacBook Air M2", 119900, 5, ProductCategory.LAPTOP);
        Product product3 = new Product(3, "AirPods Pro", 24900, 15, ProductCategory.ACCESSORY);
        Product product4 = new Product(4, "iPad Air", 69900, 7, ProductCategory.TABLET);
        Product product5 = new Product(5, "Sony Camera", 54900, 3, ProductCategory.CAMERA);

        System.out.println("Доступные товары:");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        System.out.println(product4);
        System.out.println(product5);

        // ============================================================
        // 2. Создание покупателя
        // ============================================================
        System.out.println("\n=== ШАГ 2: ПОКУПАТЕЛЬ ===\n");
        Customer customer = new Customer(1001, "Алексей Иванов", "alexey@example.com");
        System.out.println(customer);

        // ============================================================
        // 3. Создание корзины, добавление товаров
        // ============================================================
        System.out.println("\n=== ШАГ 3: КОРЗИНА ===\n");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product1, 1); // iPhone
        cart.addItem(product3, 2); // AirPods Pro 2 шт.
        cart.addItem(product4, 1); // iPad
        cart.displayCart();

        // ============================================================
        // 4. Оформление заказа (checkout)
        // ============================================================
        System.out.println("\n=== ШАГ 4: ОФОРМЛЕНИЕ ЗАКАЗА ===\n");
        Order order = new Order(customer, cart);
        order.displayOrder();

        // Изменение статуса заказа
        System.out.println("--- ИЗМЕНЕНИЕ СТАТУСА ЗАКАЗА ---");
        order.setStatus(OrderStatus.CONFIRMED);
        order.setStatus(OrderStatus.PROCESSING);
        order.setStatus(OrderStatus.SHIPPED);

        // ============================================================
        // 5. Три платёжных сценария
        // ============================================================
        System.out.println("\n=== ШАГ 5: ПЛАТЁЖНЫЕ СЦЕНАРИИ ===\n");

        double orderAmount = order.getTotalAmount() + order.getVAT();

        // Сценарий 1: Ozon + банковская карта
        System.out.println("\n--- СЦЕНАРИЙ 1: OZON + БАНКОВСКАЯ КАРТА ---");
        Payment ozonPayment = new OzonPayment();
        PaymentMethod creditCard = new CreditCardPayment("1234567890123456", "ALEXEY IVANOV", "12/28");
        ozonPayment.pay(orderAmount, creditCard);

        // Сценарий 2: Wildberries + электронный кошелёк
        System.out.println("\n--- СЦЕНАРИЙ 2: WILDBERRIES + ЭЛЕКТРОННЫЙ КОШЕЛЁК ---");
        Payment wbPayment = new WildberriesPayment();
        PaymentMethod digitalWallet = new DigitalWalletPayment("WB_USER_12345", "Wildberries");
        wbPayment.pay(orderAmount, digitalWallet);

        // Сценарий 3: Ozon + наложенный платёж
        System.out.println("\n--- СЦЕНАРИЙ 3: OZON + НАЛОЖЕННЫЙ ПЛАТЁЖ ---");
        PaymentMethod cashOnDelivery = new CashOnDelivery();
        ozonPayment.pay(orderAmount, cashOnDelivery);

        // ============================================================
        // 6. Итоговая сводка по заказу
        // ============================================================
        System.out.println("\n=== ШАГ 6: ИТОГОВАЯ СВОДКА ПО ЗАКАЗУ ===\n");
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      СВОДКА ЗАКАЗА                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.printf("║ Номер заказа:          %-30s ║%n", order.getOrderId());
        System.out.printf("║ Покупатель:            %-30s ║%n", customer.getName());
        System.out.printf("║ Email:                 %-30s ║%n", customer.getEmail());
        System.out.printf("║ Статус заказа:         %-30s ║%n", order.getStatus().getDescription());
        System.out.printf("║ Дата заказа:           %-30s ║%n", order.getOrderDate().toLocalDate());
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ Состав заказа:                                                ║");

        for (OrderItem item : order.getItems()) {
            System.out.printf("║   %s x %d = %.2f руб. %-10s ║%n",
                    item.product().name(), item.quantity(), item.getTotalPrice(), "");
        }

        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.printf("║ Сумма:                 %-30.2f ║%n", order.getTotalAmount());
        System.out.printf("║ НДС (20%%):              %-30.2f ║%n", order.getVAT());
        System.out.printf("║ ИТОГО к оплате:         %-30.2f ║%n", order.getTotalAmount() + order.getVAT());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        System.out.println("\n✅ ПРОГРАММА ЗАВЕРШИЛА РАБОТУ");
        System.out.println("📦 Спасибо за покупку!\n");
    }
}