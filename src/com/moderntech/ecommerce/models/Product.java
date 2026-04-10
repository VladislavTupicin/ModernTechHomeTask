package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.ProductCategory;

public record Product(int id, String name, double price, int stock, ProductCategory category) {

    public Product {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Количество на складе не может быть отрицательным");
        }
    }

    public boolean isInStock() {
        return stock > 0;
    }

    public void decreaseStock(int quantity) {
        // В record нельзя изменять поля, поэтому для изменения stock
        // нужно создавать новый объект. В реальном приложении лучше использовать класс.
    }

    @Override
    public String toString() {
        return String.format("%d. %s | %s | %.2f руб. | В наличии: %d",
                id, name, category.getDescription(), price, stock);
    }
}