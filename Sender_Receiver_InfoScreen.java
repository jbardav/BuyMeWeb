import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Sender_Receiver_InfoScreen {

    private int eventIndex = 7;

    private String forSomeoneRadioButtonElement = "label[data=forSomeone]";
    private String eventElementOptions = "active-result";
    private String sendingNowRadioButton = "label[class=send-now]";
    private String receiverNameElementFiled = "//form/div[1]/div/div/div[1]/div/div[2]/div[1]/div/div[2]/label[1]/input[@type='text']";
    private String senderNameElementFiled = "//form/div[1]/div/div/div[1]/div/div[2]/div[1]/div/div[2]/label[2]/input[@type='text']";
    private String blessingTextElementFiled = "//form/div[1]/div/div/div[1]/div/div[2]/div[1]/div/div[3]/label[2]/textarea";
    private String eventChooseElement = "//form/div[1]/div/div/div[1]/div/div[2]/div[1]/div/div[3]/label[1]/div/a";
    private String sendGiftByEmailOptionElement = "//form/div[1]/div/div/div[1]/div/div[2]/div[4]/div/div[1]/div[2]/div/button";
    private String receiverEmailFiledElement = "//form/div[1]/div/div/div[1]/div/div[2]/div[4]/div/div[4]/div/div[1]/div/div/input";
    private String receiverEmailSaveButton = "//form/div[1]/div/div/div[1]/div/div[2]/div[4]/div/div[4]/div/div[2]/button[2]";
    private String uploadPictureButton = "input[name=fileUpload]";


    // The main function to call all the private functions according to fields filling required
    public void senderReceiverData(WebDriver driver) throws ParserConfigurationException, SAXException, IOException, InterruptedException {

        ToSomeoneElseOrMyself(driver);
        receiver(driver);
        sender(driver);
        event(driver);
        blessing(driver);
        sendingTime(driver);
        sendingOptions(driver);
        pictureVideoUploading(driver);

    }

    //To choose the radiobutton of someone else and not myself
    private void ToSomeoneElseOrMyself(WebDriver driver){
        driver.findElement(By.cssSelector(forSomeoneRadioButtonElement)).click();
    }

    //receiver name filling
    private void receiver(WebDriver driver) throws IOException, SAXException, ParserConfigurationException {
        WebElement receiverNameFiled = driver.findElement(new By.ByXPath(receiverNameElementFiled));
        receiverNameFiled.click();
        receiverNameFiled.clear();
        receiverNameFiled.sendKeys(Procedures.readFromXMLFile("ReceiverName"));

    }

    //sender name filling
    private void sender(WebDriver driver) throws IOException, SAXException, ParserConfigurationException {
        WebElement senderNameFiled = driver.findElement(new By.ByXPath(senderNameElementFiled));
        senderNameFiled.click();
        senderNameFiled.clear();
        senderNameFiled.sendKeys(Procedures.readFromXMLFile("SenderName"));
    }

    //To add the blessing txt (clear the text filed before writing)
    private void blessing(WebDriver driver) throws IOException, SAXException, ParserConfigurationException {
    WebElement blessingTestFiled = driver.findElement(By.xpath(blessingTextElementFiled));
    blessingTestFiled.click();
    blessingTestFiled.clear();
    blessingTestFiled.sendKeys(Procedures.readFromXMLFile("BlessingText"));
    }

    //Choose the event name that you should a gift for.
    private void event(WebDriver driver){
        driver.findElement(By.xpath(eventChooseElement)).click();
        List<WebElement> eventGiftOptions = driver.findElements(By.className(eventElementOptions));
        eventGiftOptions.get(eventIndex).click();
    }

    //To send the gift after the payment
    private void sendingTime(WebDriver driver){
        driver.findElement(By.cssSelector(sendingNowRadioButton)).click();
    }

    //To choose os gift sending by email & filled the receiver email
    private void sendingOptions(WebDriver driver) throws IOException, SAXException, ParserConfigurationException {
        driver.findElement(By.xpath(sendGiftByEmailOptionElement)).click();
        driver.findElement(By.xpath(receiverEmailFiledElement)).click();
        driver.findElement(By.xpath(receiverEmailFiledElement)).sendKeys(Procedures.readFromXMLFile("ReceiverEmail"));
        driver.findElement(By.xpath(receiverEmailSaveButton)).click();
    }

    //the sender can upload a picture to the gift card
    private void pictureVideoUploading(WebDriver driver) throws InterruptedException, IOException, SAXException, ParserConfigurationException {
        driver.findElement(By.cssSelector(uploadPictureButton)).sendKeys(Procedures.readFromXMLFile("SenderPicturePathToUpload"));
        Thread.sleep(1000); // Sleep is added in order the screenshot will catch the uploaded image indication
    }


}
