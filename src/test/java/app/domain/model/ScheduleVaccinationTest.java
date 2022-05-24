package app.domain.model;

import app.controller.App;
import app.controller.ScheduleVaccineController;
import app.ui.console.utils.Utils;
import dto.SNSUserDto;
import dto.ScheduledVaccineDto;
import mapper.ScheduledVaccineMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleVaccinationTest {

    private final ScheduleVaccineController controller = new ScheduleVaccineController();

    private final ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
    private final Company company = App.getInstance().getCompany();

    @Test
    void scheduleInvalidTimeSinceLastAppointment() {
        Utils.bootstrap();
        VaccineType vaccineType = new VaccineType("COVID", "A vaccine to prevent serious infections of the Covid-19 Virus", VaccineType.vaccineTechnologies[5]);

        VaccinationCenter vaccinationCenter = new VaccinationCenter("test", "test", "911111111", "test@gmail.com", "911111111", "www.test.com", "9", "16", "420", "1", "test", "4470-111", "test", "00006");
        company.getVaccinationCenters().add(vaccinationCenter);
        controller.setVaccinationCenter(company.getVaccinationCenters().size()-1);

        ArrayList<Integer> minAge1 = new ArrayList<>(List.of(1, 19));
        ArrayList<Integer> maxAge1 = new ArrayList<>(List.of(18, 30));

        ArrayList<Integer> timeBetween1stAnd2ndDose1 = new ArrayList<>(List.of(15, 15));
        ArrayList<Integer> timeBetween2ndAnd3rdDose1 = new ArrayList<>(List.of(0, 150));
        AdministrationProcess administrationProcess1 = new AdministrationProcess(new ArrayList<>(Arrays.asList(minAge1, maxAge1)), new ArrayList<>(List.of(2, 3)), new ArrayList<>(List.of(20.0, 30.0)), new ArrayList<>(Arrays.asList(timeBetween1stAnd2ndDose1, timeBetween2ndAnd3rdDose1)));
        Vaccine vaccine1 = new Vaccine("Test", 12, "Brand1", administrationProcess1, vaccineType);

        TakenVaccine takenVaccine1 = new TakenVaccine(vaccine1, LocalDateTime.of(2022, 5, 23, 10, 30), 1);
        company.getSNSUserList().get(4).registerVaccine(takenVaccine1);

        ScheduledVaccineDto scheduledVaccineDto = new ScheduledVaccineDto();
        scheduledVaccineDto.snsNumber = 999999999;
        scheduledVaccineDto.vaccineType = vaccineType;
        scheduledVaccineDto.date = LocalDateTime.of(2022, 5, 28, 10, 0);

        assertFalse(controller.validateAppointment(scheduledVaccineDto));
    }
    /*
    Vamos precisar de testes que cubram, lista de centros vazia, lista de tipos de vacinas vazia e lista de SNS Users vazia.
    Também vamos precisar de testes que verifiquem se o tempo desde a última dose está a ser respeitado e que o User
    que tenta registar a vacinação tem idade que encaixe dentro de um dos age groups.
    Testar com SNS Numbver inválido.
     */
}
