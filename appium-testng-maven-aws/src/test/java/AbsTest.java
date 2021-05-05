import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public abstract class AbsTest {

    @BeforeSuite
    public void setUpAppium() throws Throwable {
        AppiumHelper.setUpAppium();
    }

    @AfterSuite
    public void tearDownAppium() {
        AppiumHelper.tearDownAppium();
    }

    @BeforeTest
    public void setUpTest() {
        AppiumHelper.setUpTest(this);
    }

    @AfterTest
    public void tearDownTest() {
        AppiumHelper.tearDownTest();
    }
}
