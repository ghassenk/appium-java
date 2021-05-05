import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumHelper {

    public static class TestFrameworkError extends Throwable {
        public TestFrameworkError(String msg) {
            super(msg);
        }
    }

    private static AndroidDriver<MobileElement> driver;

    public static void setUpAppium() throws Throwable {
        try {
            final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
            URL url = new URL(URL_STRING);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            driver = new AndroidDriver<>(url, capabilities);
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
}
