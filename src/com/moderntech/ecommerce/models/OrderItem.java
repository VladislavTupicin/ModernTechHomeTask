package com.moderntech.ecommerce.models;

public record OrderItem(Product product, int quantity, double priceAtOrder) {

    public OrderItem {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
        if (priceAtOrder < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }

    public OrderItem(CartItem cartItem) {
        this(cartItem.product(), cartItem.quantity(), cartItem.product().price());
    }

    public double getTotalPrice() {
        return priceAtOrder * quantity;
    }

    @Override
    public String toString() {
        return String.format("  %s x %d = %.2f руб.",
                product.name(), quantity, getTotalPrice());
    }
}