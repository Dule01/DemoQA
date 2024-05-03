package demoqapractice.abstractcomponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h5[text()=\"Elements\"]")
	WebElement elementsCart;
	
	@FindBy(id = "item-0")
	WebElement textBoxOption;
	
	public void navigateToPracticePage() {
		elementsCart.click();
	}
	
	public void selectTextBoxPractice() {
		textBoxOption.click();
	}

}
