package app.domain.model;

import app.controller.App;
import app.controller.CreateVaccinationCenterController;
import app.controller.RegisterTheArrivalOfAnSnsUserController;
import app.domain.shared.Constants;
import app.ui.console.RegisterTheArrivalOfAnSnsUserUI;
import app.ui.console.ScheduleVaccineUI;
import app.ui.console.utils.Utils;
import dto.MassVaccinationCenterDto;
import dto.SNSUserDto;
import dto.VaccineAndAdminProcessDto;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterTheArrivalOfAnSnsUserTest {
    private static List<Vaccine> vaccines;

    private Company c = App.getInstance().getCompany();
    private RegisterTheArrivalOfAnSnsUserUI UI = new RegisterTheArrivalOfAnSnsUserUI();

    @Test
    /**
     * Verifies if a valid arrival meets the requirements to be registered
     */
    public void registerValidArrival() {
        VaccineType vt = new VaccineType("12345" ,"Covideiros", VaccineType.vaccineTechnologies[0]);
        Arrival arrival = new Arrival(100000000, vt);
        ScheduledVaccine appointment1 = new ScheduledVaccine(arrival.getSnsNumber(), vt, LocalDateTime.now());
        ScheduledVaccine appointment2 = new ScheduledVaccine(200000000, vt, LocalDateTime.now());
        List<ScheduledVaccine> list = new ArrayList<>();
        list.add(appointment1);
        list.add(appointment2);
        VaccinationCenter vc = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");

        assertTrue(UI.checkRequirementsForRegistration(arrival.getSnsNumber(), list, vc, vc));

    }

    @Test
    /**
     * Verifies if a user with no appointment meets the requirements to be registered
     */
    public void registerArrivalWithNoAppointment() {
        VaccineType vt = new VaccineType("12345" ,"Covideiros", VaccineType.vaccineTechnologies[0]);
        Arrival arrival = new Arrival(100000000, vt);
        ScheduledVaccine appointment1 = new ScheduledVaccine(300000000, vt, LocalDateTime.now());
        ScheduledVaccine appointment2 = new ScheduledVaccine(200000000, vt, LocalDateTime.now());
        List<ScheduledVaccine> list = new ArrayList<>();
        list.add(appointment1);
        list.add(appointment2);
        VaccinationCenter vc = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");

        assertFalse(UI.checkRequirementsForRegistration(arrival.getSnsNumber(), list, vc, vc));

    }

    @Test
    /**
     * Verifies if an Arrival with a wrong date meets the requirements to be registered
     */
    public void registerArrivalWithWrongDate() {
        VaccineType vt = new VaccineType("12345" ,"Covideiros", VaccineType.vaccineTechnologies[0]);
        Arrival arrival = new Arrival(100000000, vt);
        ScheduledVaccine appointment1 = new ScheduledVaccine(arrival.getSnsNumber(), vt, LocalDateTime.of(2022, 5, 22, 10, 0));
        ScheduledVaccine appointment2 = new ScheduledVaccine(200000000, vt, LocalDateTime.now());
        List<ScheduledVaccine> list = new ArrayList<>();
        list.add(appointment1);
        list.add(appointment2);
        VaccinationCenter vc = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");

        assertFalse(UI.checkRequirementsForRegistration(arrival.getSnsNumber(), list, vc, vc));
    }

    @Test
    /**
     * Verifies if an Arrival on the wrong vaccination center meets the requirements to be registered
     */
    public void registerArrivalWithWrongVaccinationCenters() {
        VaccineType vt = new VaccineType("12345" ,"Covideiros", VaccineType.vaccineTechnologies[0]);
        Arrival arrival = new Arrival(100000000, vt);
        ScheduledVaccine appointment1 = new ScheduledVaccine(arrival.getSnsNumber(), vt, LocalDateTime.now());
        ScheduledVaccine appointment2 = new ScheduledVaccine(200000000, vt, LocalDateTime.now());
        List<ScheduledVaccine> list = new ArrayList<>();
        list.add(appointment1);
        list.add(appointment2);
        VaccinationCenter vcR = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");
        VaccinationCenter vcU = new VaccinationCenter("1234", "Isep", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");

        assertFalse(UI.checkRequirementsForRegistration(arrival.getSnsNumber(), list, vcR, vcU));
    }


    @Test
    /**
     * Verifies if an already registered arrival meets the requirements to be registered
     */
    public void registerArrivalWithAnAlreadyRegisteredArrival() {
        VaccineType vt = new VaccineType("12345" ,"Covideiros", VaccineType.vaccineTechnologies[0]);
        Arrival arrival = new Arrival(100000000, vt);
        ScheduledVaccine appointment1 = new ScheduledVaccine(arrival.getSnsNumber(), vt, LocalDateTime.now());
        ScheduledVaccine appointment2 = new ScheduledVaccine(200000000, vt, LocalDateTime.now());
        List<ScheduledVaccine> list = new ArrayList<>();
        list.add(appointment1);
        list.add(appointment2);
        VaccinationCenter vc = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");
        vc.registerArrival(arrival, vc);

        assertFalse(UI.checkRequirementsForRegistration(arrival.getSnsNumber(), list, vc, vc));

    }

    /*
    Tests to make
    • Registration of a valid arrival
    • Registration Fails
        • Wrong date
        • Wrong vacciantion Center
        • Already Registered
        • Invalid Sns Number
     */

}