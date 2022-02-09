package com.selenium.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverSample {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.edge.driver", "/msedgedriver");
                EdgeOptions options=new EdgeOptions();
                options.addArguments("headless");
                options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("--remote-debugging-port=9222");
                EdgeDriver driver = new EdgeDriver(options);
        try {

            driver.navigate().to("https://bing.com");
            WebElement element = driver.findElement(By.id("sb_form_q"));
            element.sendKeys("WebDriver");
            element.submit();

            System.out.println("Search Done");
             Thread.sleep(5000);
        } finally {
            driver.quit();
        }
    }
}
