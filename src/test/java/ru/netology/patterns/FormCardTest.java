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

    public void pasteDate(SelenideElement form, DataGenerator data, int days) {
        form.$("[data-test-id='date'] input")
                .press(chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(data.generateDate(days, "dd.MM.yyyy"));
    }

    @Test
    void replanDateTest() {
        DataGenerator data = new DataGenerator();
        DataGenerator.User user = data.new User();

        Selenide.open("http://0.0.0.0:9999");
        SelenideElement form = $("#root form");
        form.$("[data-test-id='city'] input").setValue(user.city);
        pasteDate(form, data, 3);
        form.$("[data-test-id='name'] input").setValue(user.name);
        form.$("[data-test-id='phone'] input").setValue(user.phone);
        form.$("[data-test-id='agreement']").click();
        form.$(withText("Запланировать")).click();
        $("#root [data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10));
        pasteDate(form, data, 5);
        form.$(withText("Запланировать")).click();
        $("#root [data-test-id='replan-notification'] button").shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        $("#root [data-test-id='success-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
