package com.epam.tat.module6.model;
import com.epam.tat.module6.tests.BaseTests;
import com.github.javafaker.Faker;

public class CreateCustomer extends BaseTests {

    public static final String name = Faker.instance().name().name();
    public static final String country = Faker.instance().address().country();
    public static final String city = Faker.instance().address().city();
    public static final String creditCard = Faker.instance().finance().creditCard();


    public static Customer defaultCustomer() {
        return new Customer(
                name,
                country,
                city,
                creditCard
        );
    }
}
