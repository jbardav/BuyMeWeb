import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Gift_Filter_HomeWebsite {

    private int countIndex= 1; // to select MAX count is 99 shekels
    private int areaIndex= 6; // to select Haifa City
    private int categoryIndex= 15; // to select Escape rooms
    private String countsWindowsElement = "//form/div[1]/a[@class='chosen-single']";
    private String areaWindowsElement = "//form/div[2]/a[@class='chosen-single']";
    private String categoryWindowsElement = "//form/div[3]/a[@class='chosen-single']";
    private String classOfElementsList = "active-result"; // this class is relevant in order to find all the count/area/category options
    private String findGiftButtonElement = "ui-btn";


    // The main function of home screen to call all the private functions according to filter actions required (count/area/category)
    public void homeScreen(WebDriver driver){
        userCountFilter(driver);
        userAreaFilter(driver);
        userCategoryFilter(driver);
        findGiftClick(driver);
    }

    // func to select the gift count options
    private void userCountFilter(WebDriver driver) {

        driver.findElement(By.xpath(countsWindowsElement)).click();

        List<WebElement> countsDropDownListElement = driver.findElements(By.className(classOfElementsList));
        countsDropDownListElement.get(countIndex).click();

        //  List<WebElement> countsDropDownListElement = driver.findElements(By.className(countsListElements));
        //  Random rand = new Random();
        //  int index = rand.nextInt(countsDropDownListElement.size());
        //  countsDropDownListElement.get(index).click();

    }

    // func to select the gift area options
    private void userAreaFilter (WebDriver driver){

        driver.findElement(By.xpath(areaWindowsElement)).click();

        List<WebElement> areaDropDownListElement = driver.findElements(By.className(classOfElementsList));
        areaDropDownListElement.get(areaIndex).click();
    }

    // func to select the gift category options
    private void userCategoryFilter (WebDriver driver){

        driver.findElement(By.xpath(categoryWindowsElement)).click();

        List<WebElement> categoryDropDownListElement = driver.findElements(By.className(classOfElementsList));
        categoryDropDownListElement.get(categoryIndex).click();
    }

    // func to submit all the filters above by click on find gift button
    private void findGiftClick(WebDriver driver){ driver.findElement(By.className(findGiftButtonElement)).click(); }


}



