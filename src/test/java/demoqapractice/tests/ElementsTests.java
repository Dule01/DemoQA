package demoqapractice.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demoqapractice.pageobjects.TextBox;
import demoqapractice.testcomponents.BaseTest;

public class ElementsTests extends BaseTest {

	@Test(dataProvider = "getData")
	public void submitTextForm(HashMap<String, String> input) {
		TextBox textBox = new TextBox(driver);
		textBox.navigateToTextBoxPage();
		textBox.submitForm(input.get("fullname"), input.get("email"), input.get("currentAddress"),
				input.get("permanentAddress"));

		Map<String, String> actualOutputs = textBox.getOutputs();
		Assert.assertEquals(actualOutputs.get("Name"), "Name:" + input.get("fullname"));
		Assert.assertEquals(actualOutputs.get("Email"), "Email:" + input.get("email"));
		Assert.assertEquals(actualOutputs.get("Current Address"),
				"Current Address :" + input.get("currentAddress"));
		Assert.assertEquals(actualOutputs.get("Permanent Address"),
				"Permananet Address :" + input.get("permanentAddress"));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\demoqapractice\\data\\data.json");

		return new Object[][] { { data.get(0) } };

	}
}
