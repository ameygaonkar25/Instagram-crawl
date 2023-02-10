package handler;

import config.AccountCreds;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NavigateRequest{
    public static void main(String[] args) throws IOException, ParseException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.instagram.com/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        AccountCreds ac = new AccountCreds();

        //#Signin using credentials
        driver.findElement(By.name("username")).sendKeys(ac.getUsername());
        driver.findElement(By.name("password")).sendKeys(ac.getPassword());
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        driver.close();
    }
}
