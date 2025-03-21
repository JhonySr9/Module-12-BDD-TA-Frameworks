package com.epam.tat.module6.model;
import com.github.javafaker.Faker;

public class RandomCustomer extends CustomerFactory {

    private Customer cachedCustomer;

    @Override
    public Customer createCustomer() {

        if (cachedCustomer == null) {
            CustomerData customer = new CustomerData(
                    Faker.instance().name().name(),
                    Faker.instance().address().country(),
                    Faker.instance().address().city(),
                    Faker.instance().finance().creditCard()
            );
            cachedCustomer = new LoggingCustomerDecorator(customer);
        }

        return cachedCustomer;
    }
}
