import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ButtonClickTest extends AbsTest {

    @AndroidFindBy(id = "com.gk.app.myapplication:id/simpleButton")
    private MobileElement button;

    @AndroidFindBy(id = "com.gk.app.myapplication:id/simpleTextView")
    private MobileElement textView;

    private void clickOnButton() {
        button.click();
    }

    private String getText() {
        return textView.getText();
    }

    @Test
    public void testButtonClick() {
        Assert.assertNotNull(textView);
        Assert.assertNotNull(button);

        //System.out.println(button.getId());
        Assert.assertEquals(getText(), "NOT_CLICKED");
        clickOnButton();
        Assert.assertEquals(getText(), "CLICKED");
    }
}
