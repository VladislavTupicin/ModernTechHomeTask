package com.moderntech.ecommerce.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private final List<CartItem> items;
    private static final double VAT_RATE = 0.20; // 20% НДС

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
        if (product.stock() < quantity) {
            throw new IllegalArgumentException("Недостаточно товара на складе. Доступно: " + product.stock());
        }

        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.product().id() == product.id())
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.quantity() + quantity;
            items.remove(item);
            items.add(new CartItem(product, newQuantity));
        } else {
            items.add(new CartItem(product, quantity));
        }
    }

    public void removeItem(int productId) {
        items.removeIf(item -> item.product().id() == productId);
    }

    public void updateQuantity(int productId, int quantity) {
        if (quantity <= 0) {
            removeItem(productId);
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            if (item.product().id() == productId) {
                items.set(i, new CartItem(item.product(), quantity));
                break;
            }
        }
    }

    public double getSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public double getVAT() {
        return getSubtotal() * VAT_RATE;
    }

    public double getTotalWithVAT() {
        return getSubtotal() + getVAT();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("  Корзина пуста");
            return;
        }

        System.out.println("\n--- СОДЕРЖИМОЕ КОРЗИНЫ ---");
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Товаров на сумму: %.2f руб.%n", getSubtotal());
        System.out.printf("НДС (20%%): %.2f руб.%n", getVAT());
        System.out.printf("ИТОГО к оплате: %.2f руб.%n", getTotalWithVAT());
    }
}