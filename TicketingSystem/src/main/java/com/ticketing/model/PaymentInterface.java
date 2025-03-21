package com.ticketing.model;

public interface PaymentInterface {

    boolean pay(int amount);
    boolean collect(int amount);

}
