package com.moderntech.ecommerce.models;

public record CartItem(Product product, int quantity) {

    public CartItem {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
    }

    public double getTotalPrice() {
        return product.price() * quantity;
    }

    @Override
    public String toString() {
        return String.format("  %s x %d = %.2f руб.",
                product.name(), quantity, getTotalPrice());
    }
}