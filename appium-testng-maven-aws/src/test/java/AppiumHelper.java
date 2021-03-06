import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppiumHelper {

    private static final boolean enableLogs = true;
    private static String targetOs;

    public static class TestFrameworkError extends Throwable {
        public TestFrameworkError(String msg) {
            super(msg);
        }
    }

    private static AppiumDriver<MobileElement> driver;

    public static void setUpAppium() throws Throwable {
        try {
            final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
            URL url = new URL(URL_STRING);
            DesiredCapabilities desiredcapabilities = new DesiredCapabilities();

            targetOs = getTargetOsFromPropertiesFile();
            logMsg("Found targetOs=" + targetOs);

            if ("android".equals(targetOs)) {
                driver = new AndroidDriver<>(url, desiredcapabilities);
            } else if ("ios".equals(targetOs)) {
                driver = new IOSDriver<>(url, desiredcapabilities);
            } else {
                throw new IllegalArgumentException("Target OS env var not found!");
            }

            //Use a higher value if your mobile elements take time to show up
            driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

        } catch (Throwable throwable) {
            String msg = throwable.getMessage();
            if (msg != null && msg.contains("Connection refused")) {
                throw new TestFrameworkError("Failed to connect to Appium server, please" +
                        " check that server is launched and accessible from this client.");
            }
            throw throwable;
        }
    }

    public static void tearDownAppium() {
        driver.quit();
    }

    public static void setUpTest(Object testObject) {
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), testObject);
    }

    public static void tearDownTest() {

    }

    public static MobileElement findElementById(String id) {
        if ("android".equals(targetOs)) {
            return driver.findElementById(id);
        } else if ("ios".equals(targetOs)) {
            return driver.findElementByAccessibilityId(id);
        } else {
            throw new IllegalStateException("No target os defined!");
        }
    }

    public static String getElementText(MobileElement element) {
        return element.getText();
    }

    public static void clickButton(MobileElement button) {
        logMsg("Clicking on button:" + button + "...");
        button.click();
        logMsg("Button click OK.");
    }

    public static void waitUntilTextPresent(MobileElement element, String text) {
        logMsg("Waiting for text:" + text + " in element:" + element + "...");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    private static void logMsg(String msg) {
        if (enableLogs) {
            System.out.println(msg);
        }
    }

    private static String getTargetOsFromPropertiesFile() {
        try (InputStream input = AppiumHelper.class.getClassLoader().getResourceAsStream(
                "appium.properties")) {

            if (input == null) {
                logMsg("Sorry, unable to find appium.properties");
                return null;
            } else {
                Properties properties = new Properties();
                properties.load(input);
                return properties.getProperty("appium.target.os");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
