package ru.netology.patterns;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.chord;

public class FormCardTest {

    private String getDate(int days, String formatOfDate) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(formatOfDate));
    }

    @Test
    void changeDateTest() {
        Selenide.open("http://0.0.0.0:9999");
        SelenideElement form = $("#root form");
        form.$("[data-test-id='city'] input").setValue("Орёл");
        form.$("[data-test-id='date'] input")
                .press(chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(getDate(3, "dd.MM.yyyy"));
        form.$("[data-test-id='name'] input").setValue("Катя Бахтина");
        form.$("[data-test-id='phone'] input").setValue("+79208123162");
        form.$("[data-test-id='agreement']").click();
        form.$(withText("Запланировать")).click();
    }
}
