package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final List<OrderItem> items;
    private final LocalDateTime orderDate;
    private OrderStatus status;
    private double totalAmount;

    public Order(Customer customer, ShoppingCart cart) {
        this.orderId = generateOrderId();
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;

        for (CartItem cartItem : cart.getItems()) {
            this.items.add(new OrderItem(cartItem));
            this.totalAmount += cartItem.getTotalPrice();
        }
    }

    private String generateOrderId() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        OrderStatus oldStatus = this.status;
        this.status = status;
        System.out.printf("  Статус заказа изменён: %s -> %s%n",
                oldStatus.getDescription(), status.getDescription());
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getVAT() {
        return totalAmount * 0.20;
    }

    public void displayOrder() {
        System.out.println("\n========================================");
        System.out.println("          ЗАКАЗ ОФОРМЛЕН");
        System.out.println("========================================");
        System.out.println("Номер заказа: " + orderId);
        System.out.println("Дата: " + orderDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        System.out.println("Покупатель: " + customer.getName());
        System.out.println("Статус: " + status.getDescription());
        System.out.println("\nСостав заказа:");
        for (OrderItem item : items) {
            System.out.println(item);
        }
        System.out.println("----------------------------------------");
        System.out.printf("Сумма: %.2f руб.%n", totalAmount);
        System.out.printf("НДС (20%%): %.2f руб.%n", getVAT());
        System.out.printf("ИТОГО к оплате: %.2f руб.%n", totalAmount + getVAT());
        System.out.println("========================================\n");
    }
}