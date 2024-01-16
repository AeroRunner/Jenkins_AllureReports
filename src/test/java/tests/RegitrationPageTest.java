package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.UserData;
import utils.ResultModal;

import static io.qameta.allure.Allure.step;

@DisplayName("Registration Page Tests")
public class RegitrationPageTest extends BaseTest {
    RegistrationPage regPage = new RegistrationPage();
    ResultModal resModal = new ResultModal();
    UserData data = new UserData();

    @DisplayName("Full Registration Page Test: Filing and Check Result Form")
    @Test
    @Tag("simple")
    public void filingAndCheckResultRegistrationPageTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open Refistration Page", ()->{
            regPage.openFormPage();
        });
        regPage.removeBanner();
        regPage.setFirstName(data.name);
        regPage.setLastName(data.lastName);
        regPage.setEmail(data.email);
        regPage.choiceGender(data.gender);
        regPage.setUserNumber(data.number);
        regPage.choiceDateOfBirth(data.birthDay, data.birthMonth, data.birthYear);
        regPage.setSubj(data.arts);
        regPage.choiceHobby(data.sports);
        regPage.uploadImage(data.testImg);
        regPage.setCurrAddress(data.currAddress);
        regPage.choiceStateCity(data.state, data.city);
        regPage.clickSubmit();

        resModal.verifyModalAppeared();
        resModal.checkResult(resModal.graphName, resModal.fullName);
        resModal.checkResult(resModal.graphEmail, data.email);
        resModal.checkResult(resModal.graphGender, data.gender);
        resModal.checkResult(resModal.graphMobile, data.number);
        resModal.checkResult(resModal.graphBorn, resModal.bornDate);
        resModal.checkResult(resModal.graphSubj, data.arts);
        resModal.checkResult(resModal.graphHobby, data.sports);
        resModal.checkResult(resModal.graphPicture, data.testImg);
        resModal.checkResult(resModal.graphAddress, data.currAddress);
        resModal.checkResult(resModal.graphStateCity, resModal.stateAndCity);
    }

    @DisplayName("Registration Page required fields test")
    @Test
    @Tag("simple")
    public void filingRequiredFieldsRegistrPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        regPage.openFormPage();
        regPage.removeBanner();
        regPage.setFirstName(data.name);
        regPage.setLastName(data.lastName);
        regPage.choiceGender(data.gender);
        regPage.setUserNumber(data.number);
    }

    @DisplayName("Registration Page Negative Tests")
    @Test
    @Tag("simple")
    public void requiredFieldsNegativeTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        regPage.openFormPage();
        regPage.removeBanner();
        regPage.setFirstName(data.name);
        regPage.clickSubmit();
        regPage.checkTableHidden();


    }
}
