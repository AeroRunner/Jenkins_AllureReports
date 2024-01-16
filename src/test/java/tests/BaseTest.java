package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import static io.qameta.allure.Allure.step;


public class BaseTest {
    @BeforeAll
    @DisplayName("Set settings before start tests ")
    public static void setUP() {
        step(" Set remote browser", () -> {
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
                });
        step("Set timeout(10000)", () -> {

                    Configuration.timeout = 10000;
                });
        step("Set baseUrl - https://demoqa.com", () -> {
                    Configuration.baseUrl = "https://demoqa.com";
                });
        step("Set browsersize(1920x1080)", () -> {
                    Configuration.browserSize = "1920x1080";
                });

    }
    @AfterEach
    @DisplayName("After every test we need reboot browser")
    void afterEveryTest(){
        step("Close Browser", ()->{
            Selenide.closeWebDriver();
        });
    }
}
