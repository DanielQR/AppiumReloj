package com.daniel.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlarmPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // ===== Localizadores (obtenidos con Appium Inspector) =====
    private final By tabAlarms   = AppiumBy.accessibilityId("Alarms");
    private final By btnAddAlarm = AppiumBy.accessibilityId("Add alarm");
    private final By btnOk       = AppiumBy.id("com.google.android.deskclock:id/material_timepicker_ok_button");
    private final By alarmCards  = AppiumBy.xpath("//androidx.cardview.widget.CardView[contains(@content-desc,'Alarm')]");

    public AlarmPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===== Acciones =====

    public void openAlarmsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabAlarms)).click();
    }

    public void tapAddAlarm() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAddAlarm)).click();
    }

    public void confirmTime() {
        wait.until(ExpectedConditions.elementToBeClickable(btnOk)).click();
    }

    public int countAlarms() {
        // Espera a que la pantalla de alarmas esté cargada antes de contar
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnAddAlarm));
        return driver.findElements(alarmCards).size();
    }

    public int waitForAlarmCount(int expected) {
        // Espera a que aparezca la nueva tarjeta; si no aparece, devuelve lo que haya
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(alarmCards, expected));
        } catch (TimeoutException e) {
            // La aserción en el test se encarga de reportar el fallo
        }
        return driver.findElements(alarmCards).size();
    }
}