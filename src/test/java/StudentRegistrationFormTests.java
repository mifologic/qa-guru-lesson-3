import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class StudentRegistrationFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillRegistrationForm() {
        String studentName = "Beth";
        String studentLastName = "Gibbons";
        String email = "bgibbons@gmail.com";
        String gender = "Female";
        String phone = "3980456871";
        String subject = "Maths";
        String hobby = "Music";
        String currentAddress = "London";

        open("/automation-practice-form");
        $("#firstName").setValue(studentName);
        $("#lastName").setValue(studentLastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("October");
        $(".react-datepicker__year-select").selectOptionContainingText("1978");
        $(".react-datepicker__day--013").click();
        $("#subjectsContainer").$("input").setValue(subject).sendKeys(Keys.ENTER);
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/bg.jpg"));
        $("#currentAddress").setValue(currentAddress);
        $("#stateCity-wrapper").$("input").setValue("NCR").sendKeys(Keys.TAB);
        $("#city").$("input").setValue("Delhi").sendKeys(Keys.TAB);
        $("#submit").click();

        // check form values
        $(".modal-title").shouldHave(Condition.text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                Condition.text(studentName),
                Condition.text(studentLastName),
                Condition.text(email),
                Condition.text(gender),
                Condition.text(phone),
                Condition.text(subject),
                Condition.text(hobby));
    }
}
