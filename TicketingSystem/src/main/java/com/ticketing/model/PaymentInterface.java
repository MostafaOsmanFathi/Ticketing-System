package com.ticketing.model;

public interface PaymentInterface {

    boolean pay(double amount);

    boolean collect(double amount);

}
