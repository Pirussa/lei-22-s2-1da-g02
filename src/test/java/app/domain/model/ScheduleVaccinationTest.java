package app.domain.model;

import app.controller.App;
import app.controller.ScheduleVaccineController;
import dto.SNSUserDto;
import dto.ScheduledVaccineDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleVaccinationTest {

    private final ScheduleVaccineController controller = new ScheduleVaccineController();

    private final Company company = App.getInstance().getCompany();

    @Test
    /**
     * Verifies if a Vaccine with all the right attributes is created
     */
    public void scheduleValidVaccination() {
        SNSUserDto snsUserDto = new SNSUserDto("User Default", 100000000, "u0@gmail.com", "01/01/1998", "915604428", "Male", "Default # 4000-000 # Default", "14698413 7 ZY7", "AAA00aa");
        company.saveSNSUser(snsUserDto);

        VaccineType vaccineType = new VaccineType("COVID", "A vaccine to prevent serious infections of the Covid-19 Virus", VaccineType.vaccineTechnologies[5]);

        VaccinationCenter vaccinationCenter = new VaccinationCenter("1236", "Centro de Saude da Maia", "915372312", "csmaia@gmail.com", "915372312", "www.csmaia.com", "9", "17", "15", "3", "Rua da Escola", "4470-073", "Maia", "CC-92634");

        ScheduledVaccineDto scheduledVaccineDto = new ScheduledVaccineDto();
        scheduledVaccineDto.snsNumber = snsUserDto.snsUserNumber;
        scheduledVaccineDto.vaccineType = vaccineType;
        scheduledVaccineDto.date = LocalDateTime.now();

        //assertTrue(controller.validateAppointment(scheduledVaccineDto, vaccinationCenter));
    }

    @Test

    public void scheduleInvalidTimeSinceLastAppointment() {
        SNSUserDto snsUserDto = new SNSUserDto("User Default", 100000000, "u0@gmail.com", "01/01/2020", "915604428", "Male", "Default # 4000-000 # Default", "14698413 7 ZY7", "AAA00aa");
        company.saveSNSUser(snsUserDto);

        VaccineType vaccineType = new VaccineType("COVID", "A vaccine to prevent serious infections of the Covid-19 Virus", VaccineType.vaccineTechnologies[5]);

        VaccinationCenter vaccinationCenter = new VaccinationCenter("1236", "Centro de Saude da Maia", "915372312", "csmaia@gmail.com", "915372312", "www.csmaia.com", "9", "17", "15", "3", "Rua da Escola", "4470-073", "Maia", "CC-92634");

        ScheduledVaccineDto scheduledVaccineDto = new ScheduledVaccineDto();
        scheduledVaccineDto.snsNumber = snsUserDto.snsUserNumber;
        scheduledVaccineDto.vaccineType = vaccineType;
        scheduledVaccineDto.date = LocalDateTime.now();

        ArrayList<Integer> minAge1 = new ArrayList<>(List.of(1, 19));
        ArrayList<Integer> maxAge1 = new ArrayList<>(List.of(18, 30));

        ArrayList<Integer> timeBetween1stAnd2ndDose1 = new ArrayList<>(List.of(15, 15));
        ArrayList<Integer> timeBetween2ndAnd3rdDose1 = new ArrayList<>(List.of(0, 150));
        AdministrationProcess administrationProcess1 = new AdministrationProcess(new ArrayList<>(Arrays.asList(minAge1, maxAge1)), new ArrayList<>(List.of(2, 3)), new ArrayList<>(List.of(20.0, 30.0)), new ArrayList<>(Arrays.asList(timeBetween1stAnd2ndDose1, timeBetween2ndAnd3rdDose1)));
        Vaccine vaccine1 = new Vaccine("Test", 12, "Brand1", administrationProcess1, vaccineType);
        TakenVaccine takenVaccine1 = new TakenVaccine(vaccine1, LocalDateTime.of(2022, 5, 15, 10, 30), 1);
        company.getSNSUserList().get(0).registerVaccine(takenVaccine1);

        //assertFalse(controller.validateAppointment(scheduledVaccineDto, vaccinationCenter));
    }
    /*
    Vamos precisar de testes que cubram, lista de centros vazia, lista de tipos de vacinas vazia e lista de SNS Users vazia.
    Também vamos precisar de testes que verifiquem se o tempo desde a última dose está a ser respeitado e que o User
    que tenta registar a vacinação tem idade que encaixe dentro de um dos age groups.
    Testar com SNS Numbver inválido.
     */
}
