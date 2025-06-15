package ru.netology.patterns;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.chord;

public class FormCardTest {

    @Test
    void replanDateTest() {
        DataGenerator data = new DataGenerator();

        Selenide.open("http://0.0.0.0:9999");
        SelenideElement form = $("#root form");

        form.$("[data-test-id='date'] input")
                .press(chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(data.generateDate(3, "dd.MM.yyyy"));

        DataGenerator.User user = data.new User();

        form.$("[data-test-id='name'] input").setValue(user.name);
        form.$("[data-test-id='city'] input").setValue(user.city);
        form.$("[data-test-id='phone'] input").setValue(user.phone);
        form.$("[data-test-id='agreement']").click();
        form.$(withText("Запланировать")).click();

        $("#root [data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10));

        form.$("[data-test-id='date'] input")
                .press(chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(data.generateDate(5, "dd.MM.yyyy"));
        form.$(withText("Запланировать")).click();

        $("#root [data-test-id='replan-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10));

        $("#root [data-test-id='replan-notification'] button").click();

        $("#root [data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
