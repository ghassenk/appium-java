import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumHelper {

    private static AndroidDriver<MobileElement> driver;

    public static void setUpAppium() throws IOException, InterruptedException {

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

    private static void runCmd(String cmd) throws IOException, InterruptedException {
        //String cmd = "ls -al";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line=buf.readLine())!=null) {
            System.out.println(line);
        }
    }

    //region Not Working

    private static void startServer(DesiredCapabilities capabilities) {
        final AppiumServiceBuilder builder;
        builder = new AppiumServiceBuilder();
        builder.withCapabilities(capabilities);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();
        String appiumServiceUrl = service.getUrl().toString();
        System.out.print("Appium Service started at " + appiumServiceUrl);
    }

    private static void setApk(DesiredCapabilities capabilities) throws IOException {
        final String apkName = "/apk/apk-debug.apk";
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, ".");
        File app = new File(appDir.getCanonicalPath(), apkName);
        capabilities.setCapability("app", app.getAbsolutePath());
    }

    //endregion
}
