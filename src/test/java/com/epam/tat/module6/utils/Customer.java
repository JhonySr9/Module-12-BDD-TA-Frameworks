package com.epam.tat.module6.utils;

public class Customer {

    private String name;
    private String country;
    private String city;
    private String creditCard;

    public Customer (String name, String country, String city, String creditCard) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.creditCard = creditCard;
    }

    public static Customer createDefaultCustomer() {
        return new Customer("Diego", "Colombia", "Cali", "1234 5678 9012 3456");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", creditCard='" + creditCard + '\'' +
                '}';
    }

}
