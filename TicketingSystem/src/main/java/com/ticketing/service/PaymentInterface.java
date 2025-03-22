package com.ticketing.service;

public interface PaymentInterface {

    boolean pay(double amount);

    boolean collect(double amount);

}
