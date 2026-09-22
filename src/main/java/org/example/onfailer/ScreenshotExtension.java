package org.example.onfailer;

import org.example.BaseClass;
import org.example.utils.ActionHelper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class ScreenshotExtension
        implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(
            ExtensionContext context,
            Throwable throwable
    ) throws Throwable {

        System.out.println(
                "===================================="
        );

        System.out.println(
                "TEST FAILED: "
                        + context.getDisplayName()
        );

        System.out.println(
                "ERROR: "
                        + throwable.getMessage()
        );

        System.out.println(
                "===================================="
        );

        try {

            if (BaseClass.driver == null) {

                System.out.println(
                        "Driver is NULL"
                );

            } else {

                ActionHelper action =
                        new ActionHelper(
                                BaseClass.driver
                        );

                String testName =
                        context
                                .getTestMethod()
                                .map(method ->
                                        method.getName()
                                )
                                .orElse(
                                        "unknown_test"
                                );

                String fileName =
                        testName
                                + "_FAILED_"
                                + System.currentTimeMillis();

                action.takeScreenshot(
                        fileName
                );

                System.out.println(
                        "Screenshot saved: "
                                + fileName
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Screenshot failed: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        throw throwable;
    }
}