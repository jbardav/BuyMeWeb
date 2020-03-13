import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class Procedures {

        private static WebDriver driver;           // create a driver with kind of web driver
        private static ExtentReports extent ;     // create ExtentReports and attach reporter(s)
        private static ExtentTest test ;          // creates a toggle for the given test, adds all log events under it
        private String currentTime = String.valueOf(System.currentTimeMillis()); // ScreenShot Timestamp

        //Open BuyMe WEB site by Chrome Driver in MAX size window
        @BeforeClass
        public static void beforeClass() throws IOException, SAXException, ParserConfigurationException {

            ExtentHtmlReporter htmlReporter = null;
            htmlReporter = new ExtentHtmlReporter(readFromXMLFile("HTMLReportPath"));

            // attach reporter
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            // test name and  description
            test = extent.createTest("Open BuyMe WEB site by Chrome Driver", "Before All the Tests: open the website is needed");

            // add custom system info
            extent.setSystemInfo("Environment", "BuyMe WEB site");
            extent.setSystemInfo("Tester", "Yehudit Bar-David");

            // log results
            test.log(Status.INFO, "Web site should be opened in maximize windows");

            boolean driverEstablishAndOpen = false;
            try {
                System.setProperty(readFromXMLFile("BrowserType"), readFromXMLFile("BrowserPath"));
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(readFromXMLFile("URL"));
                driverEstablishAndOpen = true;
            } catch (Exception e) {
                e.printStackTrace();
                fail("Cant connect chromeDriver");
                test.log(Status.FAIL, "Driver Connection Failed! " + e.getMessage());
                driverEstablishAndOpen = false;
            } finally {
                if (driverEstablishAndOpen) {
                    test.log(Status.PASS, "Driver established successfully and Web site was opened in maximize windows");
                }
            }
        }

        // new user registration in BuyMe website
        @Test
        public void Proc1_useRegistration() throws IOException, ParserConfigurationException, SAXException {

            test = extent.createTest("New User Registration", "All the user's detail required filed should be filled accordingly");
            String registrationCompleteButtonByClass = "ui-btn";
            boolean registrationClicked = false;

            try {
                 User_Registration registrationScreen = new User_Registration();
                 registrationScreen.registration(driver);
                 registrationClicked = true;
             }catch (Exception e){
                 e.printStackTrace();
                 test.log(Status.FAIL, "User Registrations Process - FAIL" + e.getMessage());
                test.fail("ScreenShot of User Registrations Process - FAIL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());
                registrationClicked = false;
             }finally {
                    if(registrationClicked){
                        test.log(Status.PASS, "User Registrations Process is completed - PASS");
                        test.pass("ScreenShot of User Registrations Process - PASS", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());
                    }
                }
            //To click the registration complete button after all the user details were filled accordingly (done by JS workaround since it is not clickable).
            WebElement registrationSubmitButton = driver.findElement(By.className(registrationCompleteButtonByClass));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", registrationSubmitButton);

            }


        // apply the User filter for search Gift
        @Test
        public void Proc2_userFilterToGiftSearch () throws IOException, ParserConfigurationException, SAXException {

            test = extent.createTest("User Filter for search a Gift", "Search a Gift by a Count/ Area/ Category ");

            boolean filterSearchGiftFilling  = false;
            try {
                Gift_Filter_HomeWebsite userFilter = new Gift_Filter_HomeWebsite();
                userFilter.homeScreen(driver);
                filterSearchGiftFilling  = true;
            }catch (Exception e){
                e.printStackTrace();
                test.log(Status.FAIL, "User Filter for search a Gift Process - FAIL"  + e.getMessage());
                filterSearchGiftFilling = false;
                test.fail("ScreenShot of User Filter for search a Gift Process - FAIL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());

            }finally {
                if(filterSearchGiftFilling){
                    test.log(Status.PASS, "User Filter for search a Gift Process is completed - PASS" );
                    test.pass("ScreenShot of User Filter for search a Gift Process - PASS", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());
                }
            }
         }

        // user choose for specific business from the options
        @Test
        public void Proc3_userGiftChooseAfterFilterIsDone() throws IOException, ParserConfigurationException, SAXException {

            test = extent.createTest("User Gift Choose according to Filter results", "Choose a business you are looking for.");
            String escapeRoomsFor2PlayerElement = "/html/body/div[1]/div/div[2]/div[2]/div/div/div[1]/a/div/div/div[2]/div/div[2]/a";

            boolean clickedOnBusinessToGift  = false;
            try {
                Gift_Choose giftChoose = new Gift_Choose();
                giftChoose.userGiftChoose(driver);
                clickedOnBusinessToGift  = true;

            }catch (Exception e){
                e.printStackTrace();
                test.log(Status.FAIL, "user Gift Choose according to Filter results - FAIL " + e.getMessage());
                clickedOnBusinessToGift = false;
                test.fail("ScreenShot of user Gift Choose according to Filter results - FAIL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());
            }finally {
                    if(clickedOnBusinessToGift){
                    test.log(Status.PASS, "User Gift Choose according to Filter results is completed - PASS" );
                    test.pass("ScreenShot of user Gift Choose according to Filter results - PASS", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());
                     }
                }
            driver.findElement(By.xpath(escapeRoomsFor2PlayerElement)).click();
            }


        // Sender And Receiver Gift details
        @Test
        public void Proc4_senderAndReceiverGiftInfo() throws IOException, ParserConfigurationException, SAXException {

            test = extent.createTest("Sender And Receiver Gift Information", "Sender and receiver details");
            String submitButton = "//form/div[1]/div/div/div[1]/div/div[2]/div[5]/button";

            boolean senderReceiverGiftInfoComplete  = false;
            try {
                Sender_Receiver_InfoScreen senderAndReceiverInfo = new Sender_Receiver_InfoScreen();
                senderAndReceiverInfo.senderReceiverData(driver);
                senderReceiverGiftInfoComplete  = true;
            }catch (Exception e){
                e.printStackTrace();
                test.log(Status.FAIL, "Sender And Receiver Gift Information filling process - FAIL " + e.getMessage());
                senderReceiverGiftInfoComplete = false;
                test.fail("ScreenShot of Sender And Receiver Gift Information filling process - FAIL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());

            }finally {
                if(senderReceiverGiftInfoComplete){
                 test.log(Status.PASS, "Sender And Receiver Gift Information filling process is completed - PASS" );
                 test.pass("ScreenShot of Sender And Receiver Gift Information filling process - PASS", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(readFromXMLFile("ScreenShotPath") + currentTime)).build());
                  }
                }
            driver.findElement(By.xpath(submitButton)).click();
        }


        // Closing the chrome driver and creating a final HTML report
        @AfterClass
        public static void afterClass(){

            test = extent.createTest("Closing of BuyMe website and create a report", "Closing of BuyMe website and create a report");
            test.log(Status.INFO, "Closing of BuyMe website and create a HTML report");

             driver.quit();
             test.log(Status.PASS, "Closing of BuyMe website is completed - PASS");
             extent.flush();

         }


    // Function to read the URL/UserName/Password etc. details by XML file.
    public static String readFromXMLFile(String keyName) throws ParserConfigurationException, IOException, SAXException, IOException, SAXException, ParserConfigurationException {
       String xmlPathName = "C:\\Users\\jbardav\\IdeaProjects\\BuyMe_WebSite_Project\\Config.xml";
       File configXmlFile = new File(xmlPathName);
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(configXmlFile);

       if (doc != null) {
            doc.getDocumentElement().normalize();
          }
       assert doc != null;
       return doc.getElementsByTagName(keyName).item(0).getTextContent();
        }


    // Function to take a ScreenShot
    private static String takeScreenShot(String ImagesPath) {
       TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
       File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
       File destinationFile = new File(ImagesPath + ".png");
       try {
          FileUtils.copyFile(screenShotFile, destinationFile);
       } catch (IOException e) {
       System.out.println(e.getMessage());
       }
       return ImagesPath+".png";
       }

    }
