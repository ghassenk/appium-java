import org.testng.annotations.*;

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

    @BeforeClass
    public void setUpClass() {
        // TODO open screen if any
    }

    @AfterClass
    public void tearDownClass() {

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
