package com.opencart.tests;

import com.opencart.app.Application;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;

public class TestBase {
    protected static Application app = new Application("CHROME");

    @BeforeSuite
    public void start() throws IOException {
        app.init();
    }

    @AfterSuite
    public void stop() {
        app.tearDown();
    }
}
