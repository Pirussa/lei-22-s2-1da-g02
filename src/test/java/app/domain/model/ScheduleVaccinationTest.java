package app.domain.model;

import app.controller.App;
import app.ui.console.utils.Utils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScheduleVaccinationTest {

    private static List<Vaccine> vaccines;

    private Company company = App.getInstance().getCompany();

    @Test
    /**
     * Verifies if a Vaccine with all the right attributes is created
     */
    public void scheduleVaccination() {
        ScheduledVaccine scheduledVaccine = new ScheduledVaccine(100000000, company.getVaccineTypes().get(0), LocalDateTime.now());
    }

    /*
    Vamos precisar de testes que cubram, lista de centros vazia, lista de tipos de vacinas vazia e lista de SNS Users vazia.
    Também vamos precisar de testes que verifiquem se o tempo desde a última dose está a ser respeitado e que o User
    que tenta registar a vacinação tem idade que encaixe dentro de um dos age groups.
    Testar com SNS Numbver inválido.
     */
}
