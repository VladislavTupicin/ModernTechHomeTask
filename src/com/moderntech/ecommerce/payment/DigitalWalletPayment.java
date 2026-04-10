package com.moderntech.ecommerce.payment;

public final class DigitalWalletPayment implements PaymentMethod {
    private final String walletId;
    private final String provider;

    public DigitalWalletPayment(String walletId, String provider) {
        this.walletId = maskWalletId(walletId);
        this.provider = provider;
    }

    private String maskWalletId(String id) {
        if (id == null || id.length() < 4) {
            return "****";
        }
        return "***" + id.substring(id.length() - 3);
    }

    @Override
    public String processPayment(double amount) {
        return String.format("Оплата через %s кошелёк %s на сумму %.2f руб. выполнена",
                provider, walletId, amount);
    }

    @Override
    public String getMethodName() {
        return "Электронный кошелёк (" + provider + ")";
    }

    @Override
    public boolean isOnline() {
        return true;
    }
}