package com.epam.tat.module6.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingCustomerDecorator implements Customer {

    private static final Logger log = LogManager.getRootLogger();
    private final Customer customer;

    public LoggingCustomerDecorator(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getName() {
        log.info("Getting customer name: " + customer.getName());
        return customer.getName();
    }

    @Override
    public String getCountry() {
        log.info("Getting customer country: " + customer.getCountry());
        return customer.getCountry();
    }

    @Override
    public String getCity() {
        log.info("Getting customer city: " + customer.getCity());
        return customer.getCity();
    }

    @Override
    public String getCreditCard() {
        log.info("Getting customer credit card: " + customer.getCreditCard());
        return customer.getCreditCard();
    }
}
