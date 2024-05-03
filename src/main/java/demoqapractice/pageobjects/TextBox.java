package demoqapractice.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demoqapractice.abstractcomponent.AbstractComponent;

public class TextBox extends AbstractComponent {

	WebDriver driver;

	public TextBox(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToTextBoxPage() {
		navigateToPracticePage();
		selectTextBoxPractice();
	}

	public void goTo() {
		driver.get("https://demoqa.com/");
	}

	@FindBy(id = "userName")
	WebElement fullNameInput;

	@FindBy(id = "userEmail")
	WebElement userEmailInput;

	@FindBy(id = "currentAddress")
	WebElement currentAddressInput;

	@FindBy(id = "permanentAddress")
	WebElement permanentAddressInput;

	@FindBy(id = "name")
	public WebElement fullNameOutput;

	@FindBy(id = "email")
	public WebElement userEmailOutput;

	@FindBy(css = "#output #currentAddress")
	public WebElement currentAddressOutput;

	@FindBy(css = "#output #permanentAddress")
	public WebElement permanentAddressOutput;

    public Map<String, String> getOutputs() {
        Map<String, String> outputs = new HashMap<>();
        outputs.put("Name", fullNameOutput.getText());
        outputs.put("Email", userEmailOutput.getText());
        outputs.put("Current Address", currentAddressOutput.getText());
        outputs.put("Permanent Address", permanentAddressOutput.getText());
        return outputs;
    }

	@FindBy(id = "submit")
	WebElement submitBtn;

	public void submitForm(String fullName, String email, String currentAddress, String permanentAddress) {
		fullNameInput.sendKeys(fullName);
		userEmailInput.sendKeys(email);
		currentAddressInput.sendKeys(currentAddress);
		permanentAddressInput.sendKeys(permanentAddress);
		submitBtn.click();
	}

}
