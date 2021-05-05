import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumHelper {

    private static AndroidDriver<MobileElement> driver;

    public static void setUpAppium() throws IOException {

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(URL_STRING);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // TODO appium must be running here (launched manually for now)
        //runCmd("appium");
        //runCmd("appium --app appium-testng/app-debug.apk");

        //startServer(capabilities);
        // setApk(capabilities);

        driver = new AndroidDriver<>(url, capabilities);
        //Use a higher value if your mobile elements take time to show up
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);


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
