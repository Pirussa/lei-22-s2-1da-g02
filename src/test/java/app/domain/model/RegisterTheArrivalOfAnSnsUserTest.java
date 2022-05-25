package app.domain.model;

import app.controller.App;
import app.controller.RegisterTheArrivalOfAnSnsUserController;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterTheArrivalOfAnSnsUserTest {

    private Company c = App.getInstance().getCompany();
    private RegisterTheArrivalOfAnSnsUserController ctrl = new RegisterTheArrivalOfAnSnsUserController();

    @Test
    /**
     * Verifies if a valid arrival meets the requirements to be registered
     *
     * This test depends on the hour previously created on the creationOfTheNecessary() method
     */
    public void registerValidArrival() {

        setUp();

        ctrl.setVaccinationCenter(0);
        ctrl.checkAndSetUserAppointment(100000000);
        ctrl.setArrival(100000000);

        assertTrue(ctrl.checkAndSetUserAppointment(100000000) && ctrl.checkIfAlreadyRegistered(100000000) && ctrl.validateDateAndTime());

    }

    @Test
    /**
     * Verifies if a user with no appointment meets the requirements to be registered
     */
    public void registerArrivalWithNoAppointment() {

        setUp();

        ctrl.setVaccinationCenter(0);
        ctrl.checkAndSetUserAppointment(300000000);

        assertFalse(ctrl.checkAndSetUserAppointment(300000000) && ctrl.checkIfAlreadyRegistered(300000000) && ctrl.validateDateAndTime());
    }

    @Test
    /**
     * Verifies if an Arrival with a wrong date meets the requirements to be registered
     */
    public void registerArrivalWithWrongDate() {

        setUp();

        ctrl.setVaccinationCenter(0);
        ctrl.checkAndSetUserAppointment(200000000);
        ctrl.setArrival(200000000);

        assertFalse(ctrl.checkAndSetUserAppointment(200000000) && ctrl.checkIfAlreadyRegistered(200000000) && ctrl.validateDateAndTime());
    }

    @Test
    /**
     * Verifies if an Arrival on the wrong vaccination center meets the requirements to be registered
     */
    public void registerArrivalWithWrongVaccinationCenters() {

        setUp();

        ctrl.setVaccinationCenter(0);
        ctrl.checkAndSetUserAppointment(100000000);
        ctrl.setArrival(100000000);

        assertFalse(ctrl.checkAndSetUserAppointment(100000000) && ctrl.checkIfAlreadyRegistered(100000000) && ctrl.validateDateAndTime());
    }


    @Test
    /**
     * Verifies if an already registered arrival meets the requirements to be registered
     */
    public void registerArrivalWithAnAlreadyRegisteredArrival() {

        setUp();

        ctrl.setVaccinationCenter(0);
        ctrl.checkAndSetUserAppointment(100000000);
        ctrl.setArrival(100000000);
        ctrl.registerArrival();

        assertFalse(ctrl.checkAndSetUserAppointment(100000000) && ctrl.checkIfAlreadyRegistered(100000000) && ctrl.validateDateAndTime());
    }


    /**
     * Creations instances of what is necessary in order to test the User Story correctly
     */
    private void setUp() {
        VaccinationCenter vcR = new VaccinationCenter("1234", "CVC Matosinhos", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");
        VaccinationCenter vcU = new VaccinationCenter("1234", "Isep", "915607321", "cvcmatosinhos@gmail.com", "915607321", "www.cvcmatosinhos.com", "9",
                "16", "30", "1", "Rua do Amial", "4460-098", "Matosinhos", "CC-95634");
        c.getVaccinationCenters().add(vcR);
        c.getVaccinationCenters().add(vcU);

        VaccineType vt1 = new VaccineType("12345" ,"Covid", VaccineType.vaccineTechnologies[0]);

        ScheduledVaccine appointment1 = new ScheduledVaccine(100000000, vt1, LocalDateTime.of(2022, 5, 24, 21, 30));
        ScheduledVaccine appointment2 = new ScheduledVaccine(200000000, vt1, LocalDateTime.of(2022, 5, 24, 22, 30));

        vcR.getScheduledVaccineList().add(appointment1);
        vcR.getScheduledVaccineList().add(appointment2);
    }

}