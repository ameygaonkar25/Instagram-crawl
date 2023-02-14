package provider;

import config.AccountCreds;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestProvider {
    private WebDriver driver;
    AccountCreds accountCreds = new AccountCreds();

    //#Contructor to define driver
    public RequestProvider(WebDriver driver) throws IOException, ParseException {
        this.driver = driver;
    }

    //#Signin using credentials
    public void signIn() throws IOException, ParseException {
//        AccountCreds accountCreds = new AccountCreds();
        driver.findElement(By.name("username")).sendKeys(accountCreds.getUsername());
        driver.findElement(By.name("password")).sendKeys(accountCreds.getPassword());
        driver.findElement(By.xpath("//div[text()='Log in']")).click();
    }

    //#Direct link to navigate into followers using URL builder property.
    public String urlDirectFollowersList(){
        return  "https://www.instagram.com/"+accountCreds.getUsername()+"/followers";
    }

    //#Check if dialog box appears or not
    public boolean hasDialogBox(){
        if(driver.findElement(By.xpath("//div[@role = 'dialog']")).isDisplayed())
            return true;
        else
            return false;
    }

    //#Go to Profile
    public void goToProfile(){
        driver.findElement(By.xpath("//div[text()='Profile']")).click();
    }

    //#Go to followers list
    public void goToFollowersList(){
        driver.findElement(By.xpath("//div[text()=' following']")).click();
    }

    //#Scroll till list is there
    public void scrollTillListEnd() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //turnaround code for scrolling
        boolean verificator=true;
        int i = 0;
        while(i<4) {
            WebElement element = driver.findElement(By.xpath("//div[@style='position: relative; display: flex; flex-direction: column; padding-bottom: 0px; padding-top: 0px;']/*[last()]"));
            js.executeScript("arguments[0].scrollIntoView(true);", element);
//            if(driver.findElement(By.xpath("//h4[text()='Suggestions for you']")).isDisplayed())
//                verificator=false;
            Thread.sleep(1000);
            i++;
        }

        WebElement followersDiv = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div/div[2]/div/div/div/div/div[2]/div/div/div[3]/div[1]/div"));
        List<WebElement> followers = followersDiv.findElements(By.tagName("a"));
        int followersCount = followers.size()/2;
        System.out.println(followersCount);

        //creating list to store usernames only
        List<String> followersList = new ArrayList<String>();

        //Storing usernames of the followers into list
        for(WebElement followList : followers) {
            String strLinkTest = followList.getText();;
            followersList.add(strLinkTest.split("\n")[0]);
        }
        System.out.println(Arrays.toString(followersList.toArray()));

    }

    //#Decline all Dialog notification
    public void declineDialogBox(){
        driver.findElement(By.xpath("//button[text()='Not Now']")).click();
    }

}
