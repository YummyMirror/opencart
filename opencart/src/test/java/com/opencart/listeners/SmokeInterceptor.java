package com.opencart.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import java.util.*;

public class SmokeInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> list = new ArrayList<IMethodInstance>(0);
        for (IMethodInstance method : methods) {
            Test test = method.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
            String[] groupsArray = test.groups();
            Set<String> groupsSet = new HashSet<String>(0);
            groupsSet.addAll(Arrays.asList(groupsArray));
            if (test.priority() == 0 && groupsSet.contains("smoke"))
                list.add(method);
        }
        return list;
    }
}
