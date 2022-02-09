package com.selenium.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverSample {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.edge.driver", "path_to_driver");
		EdgeOptions options=new EdgeOptions();
		options.addArguments("headless");
		EdgeDriver driver = new EdgeDriver(options);
        try {

            driver.navigate().to("https://bing.com");
            WebElement element = driver.findElement(By.id("sb_form_q"));
            element.sendKeys("WebDriver");
            element.submit();

            Thread.sleep(5000);
        } finally {
            driver.quit();
        }
    }
}
