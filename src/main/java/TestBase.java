
import org.junit.jupiter.api.*;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class TestBase {
	  // Shared between all tests in this class and subclasses.
	  protected static Playwright playwright;
	  protected static Browser browser;

	  // New instance for each test method.
	  protected BrowserContext context;
	  protected Page page;

	  @BeforeAll
	  static void launchBrowser() {
	    playwright = Playwright.create();
	    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
	  }

	  @AfterAll
	  static void closeBrowser() {
	    playwright.close();
	  }

	  @BeforeEach
	  void createContextAndPage() {
	    context = browser.newContext();
	    page = context.newPage();
	    String loginUrl = "https://www.hemnet.se/mitt_hemnet/session/logga_in?auth_origin=navigation_menu";
		  page.navigate(loginUrl);
		  Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
	                new Page.GetByRoleOptions().setName("Acceptera alla"));

	        if (acceptCookies.isVisible()) {
	            acceptCookies.click();}
	  }

	  @AfterEach
	  void closeContext() {
	    context.close();
	  }
}
