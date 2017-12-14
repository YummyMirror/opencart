package com.opencart;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected Application application = new Application();

    @BeforeMethod
    public void start() {
        application.init();
    }

    @AfterMethod
    public void stop() {
        application.tearDown();
    }
}
