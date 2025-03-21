package com.ticketing.model;

public class sudoPayment implements PaymentInterface {

    @Override
    public boolean pay(int amount) {
        return true;
    }

    @Override
    public boolean collect(int amount) {
        return true;
    }
}
