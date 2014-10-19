package me.impressione.translator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTranslatorJavaScriptPage {
	private WebDriver driver;
	@FindBy(id = "source")
	private WebElement sourceField;
	@FindBy(xpath = "id('result_box')/span")
	private WebElement hpsField;
	@FindBy(id = "gt-submit")
	private WebElement entrarButton;

	public GoogleTranslatorJavaScriptPage(WebDriver driver) {
		this.driver = driver;
	}

	public String translate(String texto) {
		String text = hpsField.getText();
		sourceField.clear();
		sourceField.sendKeys(texto);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(hpsField, text)));
		return hpsField.getText();
	}

	public static GoogleTranslatorJavaScriptPage navigateTo(WebDriver driver) {
		driver.get("https://translate.google.com.br/#pt/en/início");
		return PageFactory.initElements(driver, GoogleTranslatorJavaScriptPage.class);
	}
}
