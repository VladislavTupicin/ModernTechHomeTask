package com.moderntech.ecommerce.enums;

public enum ProductCategory {
    SMARTPHONE("Смартфоны"),
    LAPTOP("Ноутбуки"),
    TABLET("Планшеты"),
    ACCESSORY("Аксессуары"),
    CAMERA("Фотоаппараты");

    private final String description;

    ProductCategory(String description) {
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