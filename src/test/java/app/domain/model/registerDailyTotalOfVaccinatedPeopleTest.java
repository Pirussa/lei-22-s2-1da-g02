package app.domain.model;

import app.controller.App;
import app.controller.ConsultUsersInTheWaitingRoomController;
import app.controller.RegisterTheArrivalOfAnSnsUserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class registerDailyTotalOfVaccinatedPeopleTest {
    private Company company = App.getInstance().getCompany();
    private ConsultUsersInTheWaitingRoomController controller = new ConsultUsersInTheWaitingRoomController();
    private RegisterTheArrivalOfAnSnsUserController controller2 = new RegisterTheArrivalOfAnSnsUserController();


    @BeforeEach
    private void setup() {
        VaccinationCenter vc = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "1", "9", "30", "16", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");
        company.getVaccinationCenters().add(vc);
        controller.setVaccinationCenter(0);

        VaccineType vt1 = new VaccineType("COVID", "A vaccine to prevent serious infections of the Covid-19 Virus", VaccineType.vaccineTechnologies[5]);

        ScheduledVaccine appointment1 = new ScheduledVaccine(100000000, vt1, LocalDateTime.of(2022, 5, 25, 10, 0));
        ScheduledVaccine appointment2 = new ScheduledVaccine(200000000, vt1, LocalDateTime.of(2022, 5, 25, 10, 10));

        vc.getScheduledVaccineList().add(appointment1);
        vc.getScheduledVaccineList().add(appointment2);

        controller2.setVaccinationCenter(0);

        controller2.checkAndSetUserAppointment(100000000);
        controller2.setArrival(100000000);
        controller2.checkAndSetUserAppointment(200000000);
        controller2.setArrival(200000000);

        Arrival arrival1 = new Arrival(100000000, vt1);
        Arrival arrival2 = new Arrival(200000000, vt1);

        vc.getArrivalsList().add(arrival1);
        vc.getArrivalsList().add(arrival2);


        SnsUser snsuser1 = new SnsUser("User Default", "Male", "01/01/1998", "Default # 4000-000 # Default", "915604428", "u@gmail.com", 100000000, "14698413 7 ZY7", "AAA00aa");
        SnsUser snsuser2 = new SnsUser("User Default1", "Male", "01/01/2003", "Default # 4000-001 # Default", "915604429", "u1@gmail.com", 200000000, "16068893 0 ZX7", "AAA11aa");

        company.getSnsUserList().add(snsuser1);
        company.getSnsUserList().add(snsuser2);

        ArrayList<Integer> minAge1 = new ArrayList<>(List.of(1, 19));
        ArrayList<Integer> maxAge1 = new ArrayList<>(List.of(18, 30));

        ArrayList<Integer> timeBetween1stAnd2ndDose1 = new ArrayList<>(List.of(15, 15));
        ArrayList<Integer> timeBetween2ndAnd3rdDose1 = new ArrayList<>(List.of(0, 150));
        AdministrationProcess administrationProcess1 = new AdministrationProcess(new ArrayList<>(Arrays.asList(minAge1, maxAge1)), new ArrayList<>(List.of(2, 3)), new ArrayList<>(List.of(20.0, 30.0)), new ArrayList<>(Arrays.asList(timeBetween1stAnd2ndDose1, timeBetween2ndAnd3rdDose1)));
        Vaccine vaccine1 = new Vaccine("Test", 12, "Brand1", administrationProcess1, company.getVaccineTypes().get(0));
        VaccineBulletin vaccineBulletin1 = new VaccineBulletin(vaccine1, LocalDateTime.of(2022, 12, 30, 10, 30), 1, "54321-21");
        company.getSnsUserList().get(0).registerVaccine(vaccineBulletin1);
        company.getVaccinesList().add(vaccine1);
        //---------------------------------------------------------------------------------------------------------------------------------------------------

        ArrayList<Integer> minAge2 = new ArrayList<>(List.of(8, 22));
        ArrayList<Integer> maxAge2 = new ArrayList<>(List.of(20, 30));

        ArrayList<Integer> timeBetween1stAnd2ndDose2 = new ArrayList<>(List.of(150, 15));
        ArrayList<Integer> timeBetween2ndAnd3rdDose2 = new ArrayList<>(List.of(2, 150));
        AdministrationProcess administrationProcess2 = new AdministrationProcess(new ArrayList<>(Arrays.asList(minAge2, maxAge2)), new ArrayList<>(List.of(1, 3)), new ArrayList<>(List.of(25.0, 35.0)), new ArrayList<>(Arrays.asList(timeBetween1stAnd2ndDose2, timeBetween2ndAnd3rdDose2)));
        Vaccine vaccine2 = new Vaccine("Test", 15, "Brand1", administrationProcess2, company.getVaccineTypes().get(0));
        VaccineBulletin vaccineBulletin2 = new VaccineBulletin(vaccine2, LocalDateTime.of(2022, 5, 3, 15, 30), 1, "12345-12");
        company.getSnsUserList().get(1).registerVaccine(vaccineBulletin2);
        company.getVaccinesList().add(vaccine2);


    }

    @Test
    void registerDailyTotalOfVaccinatedPeople() {
        setup();
        List<String> expectedFileList = new ArrayList<>();
        List<String> actualFileList = new ArrayList<>();
       File expectedFile = new File("DailyTotalOfVaccinatedPeopleTest.csv");
        try {
            company.registerDailyTotalOfVaccinatedPeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Scanner readExpected = new Scanner(expectedFile);
            while(readExpected.hasNextLine()) {
            String[] expectedFileArray = readExpected.nextLine().split(";");
            expectedFileList.addAll(List.of(expectedFileArray));
            }
            Scanner readActual = new Scanner("DailyTotalOfVaccinatedPeople.csv");
            while(readExpected.hasNextLine()) {
                String[] actualFileArray = readActual.nextLine().split(";");
                actualFileList.addAll(List.of(actualFileArray));
            }
            } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        assertTrue(actualFileList.equals(expectedFileList));
    }
}