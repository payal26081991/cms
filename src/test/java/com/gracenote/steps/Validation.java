package com.gracenote.steps;

    import com.gracenote.pages.CMS;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Validation extends CMS {

    @Given("^User launches CMS Application$")
    public void user_launches_CMS_Application() throws Throwable {
        CMS.launch();
    }

    @Given("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_login_with_and(String username, String password) throws Throwable {
        CMS.login(username, password);

    }

    @When("^User clicks on program link$")
    public void user_clicks_on_program_link() throws Throwable {
        CMS.navigateToProgramPage();

    }

    @And("^User search for the \"([^\"]*)\"$")
    public void userSearchForThe(String Program) throws Throwable {
        CMS.searchProgram(Program);
    }

    @When("^User clicks program name$")
    public void user_clicks_program_name() throws Throwable {
        CMS.clickProgramLink();

    }

    @And("^User enters the provided words in Desc, Long Description, Short Description, STB Desc and clicks submit$")
    public void userEntersTheProvidedWordsInDescLongDescriptionShortDescriptionSTBDescAndClicksSubmit() throws Throwable {
        CMS.ValidateProgramDesc();
    }

    @Then("^Validation should be displayed to the user$")
    public void validation_should_be_displayed_to_the_user() throws Throwable {

    }

    @When("^User enters 'Series name', 'Series STB Desc' and 'Series Mobile Desc' and clicks Add button$")
    public void user_enters_Series_name_Series_STB_Desc_and_Series_Mobile_Desc_and_clicks_Add_button() throws Throwable {

    }

    @When("^User clicks browser back button$")
    public void user_clicks_browser_back_button() throws Throwable {

    }

    @When("^Click Add/Edit Episode button$")
    public void click_Add_Edit_Episode_button() throws Throwable {

    }

    @When("^User Clicks Edit button$")
    public void user_Clicks_Edit_button() throws Throwable {

    }

    @When("^User Enters 'Ep Long Desc', 'STB Episode Description' and 'Short Ep\\. Desc$")
    public void user_Enters_Ep_Long_Desc_STB_Episode_Description_and_Short_Ep_Desc() throws Throwable {

    }

        @After
        public void closeBrowser() {
            CMS.after();
        }

}
