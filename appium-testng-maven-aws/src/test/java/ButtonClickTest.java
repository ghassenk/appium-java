import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ButtonClickTest extends AbsTest {

    @AndroidFindBy(id = "com.gk.app.myapplication:id/simpleButton")
    private MobileElement button;

    @AndroidFindBy(id = "com.gk.app.myapplication:id/simpleTextView")
    private MobileElement textView;

    @Test
    public void testButtonClick() {
        Assert.assertNotNull(textView);
        Assert.assertNotNull(button);
        Assert.assertEquals(AppiumHelper.getElementText(textView), "NOT_CLICKED");

        AppiumHelper.clickButton(button);
        AppiumHelper.waitUntilTextPresent(textView, "CLICKED");

    }
}
