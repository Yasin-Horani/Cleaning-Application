package com.example.cleaningbuddy;

import android.os.Build;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class AddRoomActivityTest {

    private NewRoomActivity newRoomActivity = new NewRoomActivity();
    @Test
    public void stringNotEmptyNoNumbers_should_return_false_when_string_contains_number() {
        // Arrange
        String testString = "l33t";

        // Act
        boolean result = newRoomActivity.stringNotEmptyNoNumbers(testString);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void stringNotEmptyNoNumbers_should_return_false_when_string_is_Empty() {
        // Arrange
        String testString = "";

        // Act
        boolean result = newRoomActivity.stringNotEmptyNoNumbers(testString);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void stringNotEmptyNoNumbers_should_return_false_when_string_is_null() {
        // Arrange
        String testString = null;

        // Act
        boolean result = newRoomActivity.stringNotEmptyNoNumbers(testString);

        // Assert
        Assert.assertFalse(result);
    }
}


