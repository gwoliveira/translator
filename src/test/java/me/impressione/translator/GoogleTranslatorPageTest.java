package me.impressione.translator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static me.impressione.translator.GoogleTranslatorPage.Language.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class GoogleTranslatorPageTest {
	protected static WebDriver driver;
	private  GoogleTranslatorPage translatorPage;

	@BeforeClass
	public static void onlyOnce() {
		driver = new HtmlUnitDriver();
	}

	@Before
	public void setupSelenium() {
		translatorPage = GoogleTranslatorPage.navigateTo(driver);
		translatorPage.setLanguage(pt,en);
	}

	@After
	public void closeSelenium() {
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

	@Test
	public void testTranslate() {
		assertThat(translatorPage.translate("casa"), equalTo("home"));
		assertThat(translatorPage.translate("escola"), equalTo("school"));
		assertThat(translatorPage.translate("puteiro"), equalTo("whorehouse"));
		assertThat(translatorPage.translate("trapaça"), equalTo("cheating"));
	}
}
