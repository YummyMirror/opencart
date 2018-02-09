package com.opencart.tests;

import com.opencart.app.Application;
import com.opencart.listeners.RetryListener;
import org.apache.commons.mail.EmailException;
import org.testng.annotations.*;
import java.io.IOException;

@Listeners(RetryListener.class)
public class TestBase {
    protected Application app = new Application();

    @Parameters("browser")
    @BeforeClass
    public void start(@Optional("CHROME") String browser) throws IOException {
        app.init(browser);
    }

    @AfterClass
    public void stop() throws IOException, EmailException {
        app.tearDown();
    }
}
