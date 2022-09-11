package io.beaniejoy.junittest;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static final String START_TIME = "START_TIME";
    private long THRESHOLD;

    public FindSlowExtension(long THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method requiredTestMethod = context.getRequiredTestMethod();
        SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);

        String testMethodName = requiredTestMethod.getName();
        ExtensionContext.Store store = getStore(context);

        long startTime = store.remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;

        // @SlowTest 어노테이션이 없으면서 duration이 기준시간을 넘어간 경우
        if (duration > THRESHOLD && annotation == null) {
            System.out.printf("Please consider mark method [%s] with @SlowTest \n", testMethodName);
        }
    }

    private static ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        return store;
    }
}
