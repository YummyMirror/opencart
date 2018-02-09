package com.opencart.listeners;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class RetryListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getMethod().getRetryAnalyzer().retry(result))
            result.setStatus(ITestResult.SKIP);

        Reporter.setCurrentTestResult(null);
    }
}
