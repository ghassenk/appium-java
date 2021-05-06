import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainScreenTests extends AbsTest {

    @Test
    public void testButtonClick() {
        MobileElement textView = AppiumHelper.findElementById("simpleTextView");
        MobileElement button = AppiumHelper.findElementById("simpleButton");

        Assert.assertNotNull(textView);
        Assert.assertNotNull(button);
        Assert.assertEquals(AppiumHelper.getElementText(textView), "NOT_CLICKED");

        AppiumHelper.clickButton(button);
        AppiumHelper.waitUntilTextPresent(textView, "CLICKED");
    }
}
