package ru.netology.patterns;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    public DataGenerator() {
    }

    public class User {
        Faker faker;
        String name;
        String city;
        String phone;

        public User() {
            this.faker = new Faker(new Locale("ru"));

            this.name = generateName();
            this.city = generateCity();
            this.phone = generatePhone();
        }

        private String generateName() {
            return this.faker.name().firstName() + " " + this.faker.name().lastName();
        }

        private String generateCity() {
            return this.faker.address().city();
        }

        private String generatePhone() {
            return "+7" + this.faker.phoneNumber().subscriberNumber(10);
        }
    }

    public String generateDate(int days, String formatOfDate) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(formatOfDate));
    }
}


