package com.example.cleaningbuddy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class registerActivityTest {
    RegisterActivity registerActivity = new RegisterActivity();
    @Test
    public void passwordValidationController_validPassword_should_return_true() {
        // Arrange
        String testPassword = "Password123";

        // Act
        boolean result = registerActivity.password_validation_controller(testPassword);

        // Assert
        Assert.assertTrue(result);
    }
    @Test
    public void passwordValidationController_emptyPassword_should_return_false() {
        // Arrange
        String testPassword = "";

        // Act
        boolean result = registerActivity.password_validation_controller(testPassword);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void passwordValidationController_nullPassword_should_return_false() {
        // Arrange
        String testPassword = null;

        // Act
        boolean result = registerActivity.password_validation_controller(testPassword);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void passwordValidationController_shortPassword_should_return_false() {
        // Arrange
        String testPassword = "abc123";

        // Act
        boolean result = registerActivity.password_validation_controller(testPassword);

        // Assert
        Assert.assertFalse(result);
    }
}
