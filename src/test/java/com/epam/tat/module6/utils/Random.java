package com.epam.tat.module6.utils;

import com.github.javafaker.Faker;

public class Random {

    Faker faker = new Faker();

    public Random() {

    }

    public String getARandomUsername() {
        return faker.name().username();
    }

    public String getARandomPassword() {
        return faker.internet().password(true);
    }

}
