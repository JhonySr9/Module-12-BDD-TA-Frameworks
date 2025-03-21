package com.epam.tat.module6.model;

public class DefaultCustomer extends CustomerFactory {

    private Customer cachedCustomer;

    @Override
    public Customer createCustomer() {

        if (cachedCustomer == null) {
            CustomerData customer = new CustomerData(
                    "Pepito",
                    "Colombia",
                    "Bogota",
                    "4835337791631"
            );
            cachedCustomer = new LoggingCustomerDecorator(customer);
        }

        return cachedCustomer;
    }
}
