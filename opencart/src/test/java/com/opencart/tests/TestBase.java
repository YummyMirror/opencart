package com.opencart.tests;

import com.opencart.app.Application;
import com.opencart.listeners.MyRetryListener;
import org.apache.commons.mail.EmailException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import java.io.IOException;

@Listeners(MyRetryListener.class)
public class TestBase {
    protected static Application app = new Application("CHROME");

    @BeforeSuite
    public void start() throws IOException {
        app.init();
    }

    @AfterSuite
    public void stop() throws IOException, EmailException {
        app.tearDown();
    }
}
