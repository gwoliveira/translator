package me.impressione.translator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GoogleTranslatorPage {
	public enum Language{
		pt,en,es;
	}
	
	@FindBy(id = "source")
	private WebElement sourceField;
	@FindBy(id = "result_box")
	private WebElement resultBox;
	@FindBy(id = "gt-submit")
	private WebElement entrarButton;
	
	@FindBy(id = "gt-sl")
	private WebElement origemCombo;
	@FindBy(id = "gt-tl")
	private WebElement destinoCombo;
	
	public void setLanguage(Language from, Language to){
		Select idiomaOrigem = new Select(origemCombo);
		idiomaOrigem.selectByValue(from.toString());
		Select idiomaDestino = new Select(destinoCombo);
		idiomaDestino.selectByValue(to.toString());
	}
	
	public String translate(String texto) {
		sourceField.clear();
		sourceField.sendKeys(texto);
		entrarButton.submit();
		WebElement result = resultBox.findElement(By.tagName("span"));
		return result.getText();
	}

	public static GoogleTranslatorPage navigateTo(WebDriver driver) {
		driver.get("https://translate.google.com");
		return PageFactory.initElements(driver, GoogleTranslatorPage.class);
	}
}
