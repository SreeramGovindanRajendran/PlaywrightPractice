package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.*;

public class LambdaTest {
    Playwright playwright;
    Browser browser;
    Page page;
    String url = "https://www.lambdatest.com/selenium-playground/simple-form-demo";

    @BeforeTest
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false));
        page = browser.newPage();
        page.navigate(url);
        System.out.println("PageTitle : " + page.title());
    }

    @Test(priority = 1)
    public void passSingleInput() {
        page.locator("//div/input[@id='user-message']").fill("Hi Sreeram");
        page.locator("//button[@id='showInput']").click();
        String singleInput = page.locator("//*[@id='message']").textContent();
        System.out.println("Text Typed : " + singleInput);
    }

    @Test(priority = 2)
    public void passDoubleInput() {
        int a = 10;
        int b = 11;
        page.locator("//*[@id='sum1']").type(String.valueOf(a));
        page.locator("//*[@id='sum2']").type(String.valueOf(b));
        page.locator("//*[@id='gettotal']/button").click();
        String dualInput = page.locator("//*[@id='addmessage']").textContent();
        System.out.println("Added value : " + dualInput);
        Assert.assertEquals(Integer.parseInt(dualInput), a + b);
    }

    @AfterTest
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }
}
