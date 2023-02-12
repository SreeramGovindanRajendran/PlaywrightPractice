package com.example;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class LaunchBrowser {
    @Test
    public void launchBrowser(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                            .setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.google.com");
        System.out.println(page.title());

        page.close();
        browser.close();
        playwright.close();

    }
}
