package com.myWork.Assessment.tests.ui.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.myWork.Assessment.tests.context.ScenarioContext;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.testng.Assert.assertTrue;
/**
 * Page Object representing the Signup page used for new user registration.
 * <p>
 * Supports selecting personal data, filling address fields, and submitting the form.
 */
@Data
public class SignupPage {
    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(SignupPage.class);
    ScenarioContext scenarioContext =ScenarioContext.getInstance();


    public SignupPage(Page page) {
        this.page = page;
    }

    /**
     * Verifies whether the Signup page is currently visible in the browser.
     *
     * @return true if the main form is visible, false otherwise
     */
    public boolean isSignupPageVisible() {
        logger.info("Checking if Signup Page is loaded...");
        logger.info("Current URL: {}", page.url());
        try {
            return page.locator("[id='form']").isVisible();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Randomly selects one of the available gender options (Mr or Mrs).
     */
    public void selectRandomTitle() {
        List<String> genderIds = List.of("#id_gender1", "#id_gender2"); // Mr, Mrs
        int index = new Random().nextInt(genderIds.size());
        page.locator(genderIds.get(index)).check();
        logger.debug("Was selected '{}'", index);
    }
    /**
     * Fills the password field with the given value.
     *
     * @param password the password to be entered
     */
    public void fillPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("Password is missing in DataTable!");
        }
        page.locator("#password").fill(password);
        logger.debug("Password ********* was filled in in the field");
    }
    /**
     * Selects date of birth from dropdowns using values provided in the map.
     * <p>
     * Required keys: day, month, year
     *
     * @param data map containing birth date components
     */
    public void SelectDateOfBirth(Map<String, String> data) {

        String day = data.get("day");
        String month = data.get("month");
        String year = data.get("year");

        if (day == null || month == null || year == null || day.isEmpty() || month.isEmpty() || year.isEmpty()) {
            throw new RuntimeException("Date of birth values are missing in DataTable!");
        }

        page.selectOption("#days", day);
        page.selectOption("#months", new SelectOption().setLabel(month));
        page.selectOption("#years", year);
        logger.debug("Following information was provided: Day '{}',Month '{}', Year '{}'", day, month, year);
    }
    /**
     * Selects the "Sign up for our newsletter!" checkbox.
     */
    public void selectNewsletter() {
        logger.info("Checkbox 'Newsletter' was checked");
        page.locator("#newsletter").check();
    }
    /**
     * Selects the "Receive special offers" checkbox.
     */
    public void selectOffers() {
        logger.info("Checkbox 'Offers' was selected");
        page.locator("#optin").check();
    }
    /**
     * Fills in all required address fields during signup.
     *
     * @param firstName     user's first name
     * @param lastName      user's last name
     * @param company       company name
     * @param address1      address line 1
     * @param address2      address line 2
     * @param country       selected country
     * @param state         state or region
     * @param city          city
     * @param zipCode       postal code
     * @param mobileNumber  contact phone number
     */
    public void enterAddressInfo(String firstName, String lastName, String company,String address1,String address2,
                                 String country,String state,String city,String zipCode,String mobileNumber){


        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
            throw new RuntimeException("First name and last are missing in DataTable!");
        }
        if (company == null || company.isEmpty()) {
            throw new RuntimeException("Company missing in DataTable!");
        }
        if (address1 == null || address2 == null || country == null || state == null || city == null
                || address1.isEmpty() || address2.isEmpty() || country.isEmpty() || state.isEmpty() || city.isEmpty()) {
            throw new RuntimeException("Address info is missing in Data table");
        }
        page.locator("#first_name").fill(firstName);
        page.locator("#last_name").fill(lastName);
        ;
        page.locator("#company").fill(company);
        page.locator("#address1").fill(address1);
        page.locator("#address2").fill(address2);
        page.selectOption("#country", country);
        page.locator("#state").fill(state);
        page.locator("#city").fill(city);
        page.locator("#zipcode").fill(zipCode);
        page.locator("#mobile_number").fill(mobileNumber);
        logger.debug("The following info was provided: First Name: '{}', Last Name: '{}', Company: '{}', Address1: '{}'," +
                        "Address2: '{}', Country: '{}', State: '{}', City: '{}', Zipcode: '{}', Mobile Number: '{}' ", firstName, lastName,
                company, address1, address2, country, state, city, zipCode, mobileNumber);
    }
    /**
     * Clicks the "Create Account" button to submit the signup form.
     */
    public void createAccountButton() {
        logger.info("User clicks on [Create Account] button");
        page.locator("[data-qa ='create-account']").click();
    }
}

