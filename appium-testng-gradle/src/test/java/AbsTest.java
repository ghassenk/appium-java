import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public abstract class AbsTest {

    @BeforeSuite
    public void setUpAppium() throws IOException, InterruptedException {
        AppiumHelper.setUpAppium();
    }

    @AfterSuite
    public void tearDownAppium() {
        AppiumHelper.tearDownAppium();
    }

}
