package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    String meetingDay(int day){
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test

    void testPositiveAllInput() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(meetingDay(5));
        form.$("[data-test-id='name'] input").setValue("Вероника Белова");
        form.$("[data-test-id='phone'] input").setValue("+79099678181");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='notification'] .notification__content").waitUntil(visible, 15000)
                .shouldHave(text(meetingDay(5)));


}

    @Test
    void testNegativeNameEmpty(){
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(meetingDay(3));
        form.$("[data-test-id='phone'] input").setValue("+79099678181");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        form.$("[data-test-id='name'] .input__sub").shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

 @Test
 void testNegativePhoneEmpty(){
     open("http://localhost:9999/");
     SelenideElement form = $("[action='/']");
     form.$("[data-test-id='city'] input").setValue("Москва");
     form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
     form.$("[data-test-id='date'] input").setValue(meetingDay(7));
     form.$("[data-test-id='name'] input").setValue("Вероника Белова");
     form.$("[data-test-id='agreement']").click();
     form.$(".button__content").click();
     form.$("[data-test-id='phone'] .input__sub").shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
 }
 }