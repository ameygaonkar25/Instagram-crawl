package handler;

import config.AccountCreds;
import config.InstagramConfiguration;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import provider.RequestProvider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NavigateRequest{
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(InstagramConfiguration.LOGIN_URL);

        //Mazimize current window
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        RequestProvider requestProvider = new RequestProvider(driver);
        requestProvider.signIn();

        //#disabling notification
        if(requestProvider.hasDialogBox())
            requestProvider.declineDialogBox();

        //#navigate to your profile section
        requestProvider.goToProfile();

        //#Click on followers to navihate into followers list
        requestProvider.goToFollowersList();
        requestProvider.scrollTillListEnd();

//        driver.close();
    }
}
