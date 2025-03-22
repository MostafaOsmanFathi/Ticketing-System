package com.ticketing.model;

public class sudoPayment implements PaymentInterface {

    @Override
    public boolean pay(double amount) {
        return true;
    }

    @Override
    public boolean collect(double amount) {
        return true;
    }
}
