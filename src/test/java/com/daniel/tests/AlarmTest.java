package com.daniel.tests;

import com.daniel.base.BaseTest;
import com.daniel.pages.AlarmPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlarmTest extends BaseTest {

    @Test
    @DisplayName("Crear una nueva alarma en la app Reloj")
    public void crearNuevaAlarma() {
        AlarmPage alarmPage = new AlarmPage(driver);

        // 1. Ir a la pestaña Alarms
        alarmPage.openAlarmsTab();

        // 2. Contar cuántas alarmas hay antes
        int alarmasAntes = alarmPage.countAlarms();

        // 3. Crear la alarma: botón + y luego OK
        alarmPage.tapAddAlarm();
        alarmPage.confirmTime();

        // 4. Verificar que ahora hay una alarma más
        int alarmasDespues = alarmPage.waitForAlarmCount(alarmasAntes + 1);
        assertEquals(alarmasAntes + 1, alarmasDespues,
                "La nueva alarma no apareció en la lista");
    }
}