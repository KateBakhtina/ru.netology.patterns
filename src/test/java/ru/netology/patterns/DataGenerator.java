package ru.netology.patterns;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    String city;
    String date;
    String name;
    String phone;
    Faker faker;

    public DataGenerator() {
        this.city = generateCity();
        this.date = generateDate();
        this.name = generateName();
        this.phone = generatePhone();
        this.faker = new Faker(new Locale("ru"));

    }

    private String generateCity() {
        return "";
    }

    private String generateDate() {
        return "";
    }

    private String generateName() {
        return faker.name().fullName();
    }

    private String generatePhone() {
        return "";
    }
}
