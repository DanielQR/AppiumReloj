package com.daniel.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.URI;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        // Las mismas capabilities que usamos en Appium Inspector
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setAppPackage("com.google.android.deskclock")
                .setAppActivity("com.android.deskclock.DeskClock")
                .setNoReset(true);

        // Se conecta al servidor Appium que está corriendo en el puerto 4723
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
    }

    @AfterEach
    public void tearDown() {
        // Cierra la sesión al terminar el test
        if (driver != null) {
            driver.quit();
        }
    }
}