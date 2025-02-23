package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    static final String BaseUrl = "http://demowebshop.tricentis.com/";

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod
    public void setUpMethod() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate(BaseUrl);
    }

    @AfterMethod
    public void tearDownMethod() {
        page.close();
        context.close();
        browser.close();
    }
}
