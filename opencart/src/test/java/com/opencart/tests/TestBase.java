package com.opencart.tests;

import com.opencart.app.Application;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected static Application app = new Application("CHROME");

    @BeforeSuite
    public void start() {
        app.init();
    }

    @AfterSuite
    public void stop() {
        app.tearDown();
    }
}
