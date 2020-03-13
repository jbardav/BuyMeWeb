import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Gift_Choose {

    private String escapeRoomsBusinessElement = "thumbnail";
    private String expectedURLOfEscapeRoomsFilter = "https://buyme.co.il/search?budget=1&category=63&region=16";


    // user gift choose (specific escape Room Business for 2 player)
    public void userGiftChoose(WebDriver driver){

        try {
            Thread.sleep(3000); // sleep is added in order the assert will work fine else the program recognize the earlier URL and not the current one
            assertOfWebPageURL(driver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.className(escapeRoomsBusinessElement)).click();

    }

    //assert the URL in order to make sure we got the correct URL according to user filter
    private void assertOfWebPageURL(WebDriver driver)  {
        Assert.assertEquals(expectedURLOfEscapeRoomsFilter, driver.getCurrentUrl());

    }
}
