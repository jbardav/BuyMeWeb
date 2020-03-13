import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class User_Registration {

    private String registrationButton1ElementCSSelector = "span[class='seperator-link']";
    private String registrationButton2ElementCSSelector = "span[class='text-btn']";
    private String userNameFiledElementCSSelector = "input[type=text]";
    private String userEmailFiledElementCSSelector = "input[type=email]";
    private String userPasswordFiledElementID = "valPass";
    private String userPasswordConfirmFiledElementXpath = "//form/div[4]/label/input[@type='password']";
    private String ackToPolicyElementRadioButtonXpath = "//form/div[5]/label/input[@type='checkbox']";


    // The main function of registration that call to all the private functions according to registration actions required
     public void registration(WebDriver driver) throws IOException, SAXException, ParserConfigurationException {

       registrationsClick(driver);
       fillUserDetails(driver);
       ackToPolicy(driver);

    }


    // The 2 registration clicks (button & link)to the web site
    private void registrationsClick(WebDriver driver){
        driver.findElement(By.cssSelector(registrationButton1ElementCSSelector)).click();
        driver.findElement(By.cssSelector(registrationButton2ElementCSSelector)).click();
            }

    // To fill user details according to requirement by the web(name, email etc).
    private void fillUserDetails(WebDriver driver) throws IOException, SAXException, ParserConfigurationException {
        Procedures procedures = new Procedures();
        driver.findElement(By.cssSelector(userNameFiledElementCSSelector)).sendKeys(Procedures.readFromXMLFile("UserName"));
        driver.findElement(By.cssSelector(userEmailFiledElementCSSelector)).sendKeys(procedures.readFromXMLFile("UserEmail"));
        driver.findElement(By.id(userPasswordFiledElementID)).sendKeys(procedures.readFromXMLFile("UserPassword"));
        driver.findElement(new By.ByXPath(userPasswordConfirmFiledElementXpath)).sendKeys(procedures.readFromXMLFile("UserPassword"));
    }


    //To click the radiobutton's confirmation of the web site policy.
    private void ackToPolicy(WebDriver driver){
        WebElement ackToPolicyRadioButton = driver.findElement(By.xpath(ackToPolicyElementRadioButtonXpath));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", ackToPolicyRadioButton);
    }

}
