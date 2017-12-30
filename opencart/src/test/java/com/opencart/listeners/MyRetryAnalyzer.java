package com.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    private int i = 0;
    private int max = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (i < max) {
            i++;
            return true;
        } else
            return false;
    }
}
