package com.moderntech.ecommerce.enums;

public enum OrderStatus {
    PENDING("Ожидает подтверждения"),
    CONFIRMED("Подтверждён"),
    PROCESSING("В обработке"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменён");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}